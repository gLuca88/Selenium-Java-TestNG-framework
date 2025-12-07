package gianluca.com.basefactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import gianluca.com.configurazionereport.ExtentLogger;
import gianluca.com.configurazionereport.LoggerUtils;
import gianluca.com.factory.DriverFactory;
import gianluca.com.utils.ConfigReader;

public class BaseTest {

	protected WebDriver driver;

	@BeforeMethod
	public void setUp() {
		// Log file
		LoggerUtils.info("Inizializzazione del WebDriver tramite DriverFactory.");
		DriverFactory.initDriver();
		driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
		String url = ConfigReader.getProperty("baseUrl");
		// Log file
		LoggerUtils.info("Navigazione verso URL: " + url);
		driver.get(url);
	}

	@AfterMethod
	public void tearDown() {
		// Report
		ExtentLogger.info("Chiusura del WebDriver.");
		// Log file
		LoggerUtils.info("Esecuzione DriverFactory.quitDriver().");
		DriverFactory.quitDriver();
	}

}
