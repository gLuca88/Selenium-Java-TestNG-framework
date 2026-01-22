package gianluca.com.selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import gianluca.com.configurazionereport.ExtentLogger;
import gianluca.com.configurazionereport.LoggerUtils;
import gianluca.com.utils.AdsHandler;

public class SeleniumActions implements ISeleniumActions {

	private final WebDriver driver;
	private final WebDriverWait wait;
	private final AdsHandler adsHandler;

	public SeleniumActions(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.adsHandler = new AdsHandler(driver);
	}

	/*
	 * ========================= METODI DI SUPPORTO INTERNI
	 * =========================
	 */

	private WebElement waitVisible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	private WebElement waitClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/*
	 * ========================= AZIONI =========================
	 */

	@Override
	public void click(By locator) {
		try {
			adsHandler.hideAdsIframeIfPresent(); // 👈 QUI
			WebElement element = waitClickable(locator);
			element.click();

			ExtentLogger.pass("Click eseguito sull'elemento: " + locator);
			LoggerUtils.info("Click eseguito su locator: " + locator);

		} catch (Exception e) {
			ExtentLogger.fail("Impossibile eseguire il click sull'elemento.");
			LoggerUtils.error("Errore click su locator: " + locator + " - " + e.getMessage());
			throw e;
		}
	}

	@Override
	public void type(By locator, String text) {
		try {
			adsHandler.hideAdsIframeIfPresent();
			WebElement element = waitVisible(locator);
			element.clear();
			element.sendKeys(text);

			ExtentLogger.pass("Testo inserito nel campo richiesto.");
			LoggerUtils.info("Digitato '" + text + "' su locator: " + locator);

		} catch (Exception e) {
			ExtentLogger.fail("Errore durante l'inserimento del testo.");
			LoggerUtils.error("Errore digitazione su locator: " + locator + " - " + e.getMessage());
			throw e;
		}
	}

	@Override
	public String getText(By locator) {
		try {
			adsHandler.hideAdsIframeIfPresent();
			WebElement element = waitVisible(locator);
			String value = element.getText();

			ExtentLogger.info("Testo letto dall'elemento: " + value);
			LoggerUtils.info("getText su locator: " + locator + " - valore: " + value);

			return value;

		} catch (Exception e) {
			ExtentLogger.fail("Errore durante la lettura del testo.");
			LoggerUtils.error("Errore getText su locator: " + locator + " - " + e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean isDisplayed(By locator) {
		try {
			List<WebElement> elements = driver.findElements(locator);
			if (elements.isEmpty()) {
				LoggerUtils.info("Elemento NON presente nel DOM → " + locator);
				return false;
			}

			boolean visible = elements.get(0).isDisplayed();
			LoggerUtils.info("isDisplayed(" + locator + ") = " + visible);
			return visible;

		} catch (Exception e) {
			LoggerUtils.warn("Errore in isDisplayed, ritorno false. Locator: " + locator);
			return false;
		}
	}

	@Override
	public void waitForVisible(By locator) {
		waitVisible(locator);
	}

	@Override
	public void waitForClickable(By locator) {
		waitClickable(locator);
	}

	@Override
	public void selectByValue(By locator, String value) {
		try {
			adsHandler.hideAdsIframeIfPresent();
			WebElement element = waitVisible(locator);
			Select select = new Select(element);
			select.selectByValue(value);

			LoggerUtils.info("Select by value: '" + value + "' su locator: " + locator);
			ExtentLogger.pass("Selezionato valore '" + value + "' dal menu a tendina.");

		} catch (Exception e) {
			LoggerUtils.error("Errore selectByValue su " + locator + ": " + e.getMessage());
			ExtentLogger.fail("Errore nella selezione del valore.");
			throw e;
		}
	}

	@Override
	public boolean isTextEqual(By locator, String expected) {
		try {
			String actual = getText(locator).trim();

			LoggerUtils.info("Confronto testo → Atteso: '" + expected + "' | Ottenuto: '" + actual + "'");
			ExtentLogger.info("Verifica testo UI");

			return actual.equalsIgnoreCase(expected); // scelta tua, lasciata apposta

		} catch (Exception e) {
			LoggerUtils.error("Errore confronto testo su locator " + locator + ": " + e.getMessage());
			ExtentLogger.fail("Errore durante il confronto del testo UI");
			return false;
		}
	}

	@Override
	public WebElement createWebElement(By locator) {
		return waitVisible(locator);
	}

	@Override
	public void scrollToElement(By locator) {
		WebElement element = waitVisible(locator);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
	}

	@Override
	public String getValidationMessage(By locator) {
		try {
			adsHandler.hideAdsIframeIfPresent();
			WebElement element = driver.findElement(locator);
			String message = element.getAttribute("validationMessage");

			LoggerUtils.info("validationMessage su " + locator + " = " + message);
			ExtentLogger.info("Browser validation message: " + message);

			return message != null ? message : "";

		} catch (Exception e) {
			LoggerUtils.warn("Impossibile ottenere il validationMessage per: " + locator);
			return "";
		}
	}
}
