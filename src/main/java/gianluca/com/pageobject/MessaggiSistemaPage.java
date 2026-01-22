package gianluca.com.pageobject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import gianluca.com.configurazionereport.LoggerUtils;
import gianluca.com.selenium.BasePage;

public class MessaggiSistemaPage extends BasePage {

	private By buttonContinue = By.cssSelector("a[data-qa='continue-button']");

	public MessaggiSistemaPage(WebDriver driver) {
		super(driver);
	}

	public void clickContinue() {

		LoggerUtils.info("Tentativo click standard su Continue.");

		try {
			actions.click(buttonContinue);
		} catch (ElementClickInterceptedException e) {
			LoggerUtils.warn("Click standard fallito, overlay ads presente.");
		}

		if (!redirectAvvenuto()) {
			LoggerUtils.warn("Redirect non avvenuto. Uso JS click di fallback.");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", driver.findElement(buttonContinue));
		}

		if (!redirectAvvenuto()) {
			throw new AssertionError("Il bottone Continue non naviga a causa di overlay ADS.");
		}

		LoggerUtils.info("Redirect post-Continue completato.");
	}

	private boolean redirectAvvenuto() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			return wait.until(d -> !d.getCurrentUrl().contains("account_created"));
		} catch (TimeoutException e) {
			return false;
		}
	}
}
