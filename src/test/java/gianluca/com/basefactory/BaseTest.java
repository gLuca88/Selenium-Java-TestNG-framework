package gianluca.com.basefactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import gianluca.com.configurazionereport.ExtentLogger;
import gianluca.com.configurazionereport.LoggerUtils;
import gianluca.com.datatestmodel.UtenteRegistrato;
import gianluca.com.factory.DriverFactory;
import gianluca.com.listeners.TestListener;
import gianluca.com.pageobject.HomePage;
import gianluca.com.pageobject.LoginRegistrazionePage;
import gianluca.com.pageobject.UtenteLoggedPage;
import gianluca.com.utils.ConfigReader;
import gianluca.com.utilstest.JsonUtils;

@Listeners(TestListener.class)
public class BaseTest {

	@BeforeMethod
	public void setUp() {
		LoggerUtils.info("Inizializzazione del WebDriver.");
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

	protected UtenteLoggedPage loginValidUser() {

		UtenteRegistrato utente = JsonUtils.readJsonFromResources("dataJson/utenteRegistrato.json",
				UtenteRegistrato.class);

		HomePage home = new HomePage(getDriver());
		home.gestisciCookie();
		home.clickSignUpLogin();

		LoginRegistrazionePage login = new LoginRegistrazionePage(getDriver());
		login.inserisciNomeEmailLogin(utente.getEmail(), utente.getPassword());
		login.clickButtonLogin();

		return new UtenteLoggedPage(getDriver());
	}

}
