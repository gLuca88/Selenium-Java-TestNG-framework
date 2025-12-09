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

			// Report
			ExtentLogger.pass("Click eseguito sull'elemento:" + locator);
			// Log file
			LoggerUtils.info("Click eseguito su locator: " + locator);

		} catch (Exception e) {
			// Report
			ExtentLogger.fail("Impossibile eseguire il click sull'elemento.");
			// Log file
			LoggerUtils.error("Errore click su locator: " + locator + " - " + e.getMessage());
			throw e;
		}
	}

	@Override
	public void type(By locator, String text) {
		try {
			waitForVisible(locator);
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(text);
			// Report
			ExtentLogger.pass("Testo inserito nel campo richiesto.");
			// Log file
			LoggerUtils.info("Digitato '" + text + "' su locator: " + locator);

		} catch (Exception e) {
			// Report
			ExtentLogger.fail("Errore durante l'inserimento del testo.");
			// Log file
			LoggerUtils.error("Errore digitazione su locator: " + locator + " - " + e.getMessage());
			throw e;
		}
	}

	@Override
	public String getText(By locator) {
		try {
			waitForVisible(locator);
			String value = driver.findElement(locator).getText();
			// Report
			ExtentLogger.info("Testo letto dall'elemento:" + value);
			// Log file
			LoggerUtils.info("getText su locator: " + locator + " - valore: " + value);

			return value;

		} catch (Exception e) {
			// Report
			ExtentLogger.fail("Errore durante la lettura del testo.");
			// Log file
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	@Override
	public void waitForClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	@Override
	public void selectByValue(By locator, String value) {
		try {
			waitForVisible(locator);

			Select select = new Select(driver.findElement(locator));
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

			return actual.equalsIgnoreCase(expected);

		} catch (Exception e) {
			LoggerUtils.error("Errore confronto testo su locator " + locator + ": " + e.getMessage());
			ExtentLogger.fail("Errore durante il confronto del testo UI");
			return false;
		}
	}

	@Override
	public WebElement createWebElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	@Override
	public void scrollToElement(By locator) {
		WebElement element = createWebElement(locator);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
	}

}
