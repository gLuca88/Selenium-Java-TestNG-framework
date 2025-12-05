package gianluca.com.selenium;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gianluca.com.configurazionereport.ExtentLogger;
import gianluca.com.configurazionereport.LoggerUtils;

public class SeleniumActions implements ISeleniumActions {

	private WebDriver driver;
	private WebDriverWait wait;

	public SeleniumActions(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Override
	public void click(By locator) {
		try {
			waitForClickable(locator);
			driver.findElement(locator).click();
			ExtentLogger.pass("Click su elemento: " + locator);
			LoggerUtils.info("Click eseguito su: " + locator);

		} catch (Exception e) {

			ExtentLogger.fail("Errore durante il click su: " + locator);
			LoggerUtils.error("Errore click su " + locator + ": " + e.getMessage());

			throw e;
		}
	}

	@Override
	public void type(By locator, String text) {
		try {
			waitForVisible(locator);
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(text);

			ExtentLogger.pass("Digitato testo '" + text + "' in: " + locator);
			LoggerUtils.info("Digitato '" + text + "' in " + locator);

		} catch (Exception e) {

			ExtentLogger.fail("Errore digitazione su: " + locator);
			LoggerUtils.error("Errore digitazione su " + locator + ": " + e.getMessage());

			throw e;
		}
	}

	@Override
	public String getText(By locator) {
		try {
			waitForVisible(locator);
			String value = driver.findElement(locator).getText();

			ExtentLogger.info("Testo estratto da " + locator + ": " + value);
			LoggerUtils.info("getText da " + locator + ": " + value);

			return value;

		} catch (Exception e) {

			ExtentLogger.fail("Errore getText su: " + locator);
			LoggerUtils.error("Errore getText su " + locator + ": " + e.getMessage());

			throw e;
		}
	}

	@Override
	public boolean isDisplayed(By locator) {
		try {
			boolean result = driver.findElement(locator).isDisplayed();
			LoggerUtils.info("isDisplayed(" + locator + ") = " + result);
			return result;

		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@Override
	public void waitForVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	@Override
	public void waitForClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
}
