package gianluca.com.basefactory;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import gianluca.com.configurazionereport.ExtentLogger;
import gianluca.com.configurazionereport.LoggerUtils;
import gianluca.com.factory.DriverFactory;
import gianluca.com.pageobject.HomePage;
import gianluca.com.utils.ConfigReader;

public class BaseTest {

	@BeforeMethod
	public void setUp() {
		LoggerUtils.info("Inizializzazione del WebDriver tramite DriverFactory.");

		DriverFactory.initDriver();

		WebDriver driver = DriverFactory.getDriver();
		driver.manage().window().maximize();

		String url = ConfigReader.getProperty("baseUrl");
		LoggerUtils.info("Navigazione verso URL: " + url);
		driver.get(url);
	}

	@AfterMethod
	public void tearDown() {
		ExtentLogger.info("Chiusura del WebDriver.");
		LoggerUtils.info("Esecuzione DriverFactory.quitDriver().");

		DriverFactory.quitDriver();
	}

	protected WebDriver getDriver() {
		return DriverFactory.getDriver();
	}
	
	protected void openLoginPage() {
	    HomePage home = new HomePage(getDriver());

	    ExtentLogger.info("Gestione cookie (se presenti).");
	    LoggerUtils.info("Gestione cookie.");
	    home.gestisciCookie();

	    ExtentLogger.info("Verifica elementi base homepage.");
	    assertTrue(home.isNavbarVisible(), "Navbar non visibile.");
	    assertTrue(home.isUrlCorrect(), "URL non corretto.");
	    assertTrue(home.isTitleCorrect(), "Titolo non corretto.");

	    ExtentLogger.info("Navigazione alla pagina Login / Signup.");
	    home.clickSignUpLogin();
	}
}
