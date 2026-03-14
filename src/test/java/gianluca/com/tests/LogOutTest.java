package gianluca.com.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import gianluca.com.basefactory.BaseTest;
import gianluca.com.configurazionereport.ExtentLogger;
import gianluca.com.configurazionereport.LoggerUtils;
import gianluca.com.pageobject.HomePage;
import gianluca.com.pageobject.UtenteLoggedPage;

public class LogOutTest extends BaseTest {

	@Test
	public void testLogOut() {

		ExtentLogger.info("Login utente valido (precondizione).");
		LoggerUtils.info("Esecuzione login utente valido tramite helper.");

		UtenteLoggedPage utente = loginValidUser();

		ExtentLogger.info("Click sul pulsante Logout.");
		LoggerUtils.info("Click su bottone Logout.");

		utente.clickButtonLogOut();

		ExtentLogger.info("Verifica ritorno alla homepage dopo logout.");
		LoggerUtils.info("Verifica che la homepage sia visibile dopo il logout.");

		HomePage home = new HomePage(getDriver());

		assertTrue(home.isHomePageVisibleAfterLogOut(), "Logout non riuscito");

		ExtentLogger.pass("Logout eseguito correttamente.");
		LoggerUtils.info("Logout verificato correttamente.");
	}
}
