package gianluca.com.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gianluca.com.basefactory.BaseTest;
import gianluca.com.configurazionereport.ExtentLogger;
import gianluca.com.configurazionereport.LoggerUtils;
import gianluca.com.datatestmodel.LoginInvalidData;
import gianluca.com.datatestmodel.UtenteRegistrato;

import gianluca.com.pageobject.LoginRegistrazionePage;
import gianluca.com.pageobject.UtenteLoggedPage;
import gianluca.com.utilstest.JsonUtils;

public class LoginTest extends BaseTest {

	@Test
	public void testLoginValid() {
		UtenteRegistrato utenteRegistrato = JsonUtils.readJsonFromResources("dataJson/utenteRegistrato.json",
				UtenteRegistrato.class);

		// apertura login page e verifica url,navbar,titolo egestione cokkie se
		// presenti(metodo in base test)
		openLoginPage();
		// ===== VERIFICA PAGINA DI LOGIN =====
		LoginRegistrazionePage login = new LoginRegistrazionePage(getDriver());
		ExtentLogger.info("Verifica apertura form login.");
		String titoloAttesoPaginaLogin = utenteRegistrato.getTitoloAtteso();
		String messaggioEstratto = login.getTextTitoloLoginForm();
		LoggerUtils.info("Titolo form → atteso: " + titoloAttesoPaginaLogin + " | ottenuto: " + messaggioEstratto);
		assertEquals(titoloAttesoPaginaLogin, messaggioEstratto,
				"Il titolo del form di registrazione non corrisponde.");
		// ===== INSERISCI NOME + EMAIL =====
		ExtentLogger.info("Inserimento nome ed email.--->PAGINA LOGIN");
		LoggerUtils.info("Compilazione nome + email dal JSON.");
		login.inserisciNomeEmailLogin(utenteRegistrato.getEmail(), utenteRegistrato.getPassword());
		ExtentLogger.info("Click sul pulsante login--->PAGINA LOGIN");
		LoggerUtils.info("Invio del form login");
		login.clickButtonLogin();
		// === VERIFICA UTENTE LOGGATO ===
		String nomeUtente = utenteRegistrato.getNome();
		ExtentLogger.info("Verifica utente loggato.");
		LoggerUtils.info("Controllo nome utente loggato → atteso: " + nomeUtente);
		UtenteLoggedPage utenteLoggato = new UtenteLoggedPage(getDriver());
		assertTrue(utenteLoggato.verificaMessaggioLoggedUtente(nomeUtente),
				"L'utente NON risulta loggato correttamente.");
		ExtentLogger.pass("Utente loggato correttamente.");
		LoggerUtils.info("Login verificato.");

	}

	@DataProvider(name = "invalidLoginData")
	public Object[][] invalidLoginData() {

		List<LoginInvalidData> dataList = JsonUtils.readJsonArray("dataJson/loginInvalid.json", LoginInvalidData.class);

		Object[][] data = new Object[dataList.size()][1];

		for (int i = 0; i < dataList.size(); i++) {
			data[i][0] = dataList.get(i);
		}

		return data;
	}

	@Test(dataProvider = "invalidLoginData")
	public void loginInvalid(LoginInvalidData data) {
		// apertura login page e verifica url,navbar,titolo egestione cokkie se
		// presenti(metodo in base test)click button login
		openLoginPage();
		// ===== INSERISCI NOME + EMAIL =====
		LoginRegistrazionePage login = new LoginRegistrazionePage(getDriver());
		ExtentLogger.info("Inserimento nome ed email.--->PAGINA LOGIN");
		LoggerUtils.info("Compilazione nome + email dal JSON.");
		login.inserisciNomeEmailLogin(data.getEmail(), data.getPassword());
		// ===== CLICK LOGIN =====
		ExtentLogger.info("Click sul pulsante login--->PAGINA LOGIN");
		login.clickButtonLogin();
		// ===== LETTURA MESSAGGIO DI VALIDAZIONE =====
		String actualMessage = login.getLoginValidationMessage();
		ExtentLogger.info("Messaggio ottenuto: " + actualMessage);
		ExtentLogger.info("Messaggio atteso: " + data.getExpectedMessage());
		LoggerUtils.info("Messaggio ottenuto: " + actualMessage + " | Atteso: " + data.getExpectedMessage());
		// ===== ASSERT =====
		assertEquals(actualMessage, data.getExpectedMessage());
		LoggerUtils.info("Confronto messaggi → PASS");
		ExtentLogger.pass("Messaggio corretto");
	}

}
