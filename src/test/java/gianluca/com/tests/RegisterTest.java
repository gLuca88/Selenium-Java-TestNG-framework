package gianluca.com.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import gianluca.com.basefactory.BaseTest;
import gianluca.com.configurazionereport.ExtentLogger;
import gianluca.com.configurazionereport.LoggerUtils;
import gianluca.com.datatestmodel.UtenteRegistrazione;
import gianluca.com.pageobject.FormRegistrazionePage;
import gianluca.com.pageobject.HomePage;
import gianluca.com.pageobject.LoginRegistrazionePage;
import gianluca.com.pageobject.MessaggiSistemaPage;
import gianluca.com.pageobject.UtenteLoggedPage;
import gianluca.com.utilstest.JsonUtils;

public class RegisterTest extends BaseTest {

	UtenteRegistrazione user = JsonUtils.readJsonFromResources("dataJson/utenteRegistrazione.json",
			UtenteRegistrazione.class);

	@Test
	public void testRegistrazioneEliminazione() {

		 // ===== HOME PAGE =====
        HomePage home = new HomePage(getDriver());

        ExtentLogger.info("Gestione cookie (se presenti).");
        home.gestisciCookie();

        ExtentLogger.info("Verifica homepage (URL, titolo, navbar).");
        assertTrue(home.isHomePageVisible(),
                "Homepage non visibile correttamente");

        ExtentLogger.info("Navigazione alla pagina Login / Signup.");
        home.clickSignUpLogin();
		// ===== VERIFICA PAGINA DI REGISTRAZIONE =====
		LoginRegistrazionePage registra = new LoginRegistrazionePage(getDriver());

		ExtentLogger.info("Verifica apertura form registrazione.");
		String titoloAttesoPaginaRegistrazione = registra.getTitoloAttesoFormRegostrazione();
		String messaggioEstratto = registra.getTextTitoloFormRegistrazione();

		LoggerUtils
				.info("Titolo form → atteso: " + titoloAttesoPaginaRegistrazione + " | ottenuto: " + messaggioEstratto);

		assertEquals(titoloAttesoPaginaRegistrazione, messaggioEstratto,
				"Il titolo del form di registrazione non corrisponde.");

		// ===== INSERISCI NOME + EMAIL =====
		ExtentLogger.info("Inserimento nome ed email.--->PAGINA REGISTRAZIONE");
		LoggerUtils.info("Compilazione nome + email dal JSON.");
		registra.inserisciNomeEmailRegistrazione(user.getNome(), user.getEmail());

		ExtentLogger.info("Click sul pulsante Signup.--->PAGINA REGISTRAZIONE");
		LoggerUtils.info("Invio del form iniziale di registrazione.");
		registra.clickSignuUpRegistrazione();

		// ===== COMPILAZIONE FORM REGISTRAZIONE =====
		FormRegistrazionePage formRegistrazione = new FormRegistrazionePage(getDriver());

		ExtentLogger.info(
				"Compilazione dati account.(sesso,password,data,newsletter,ricevi regalo)---->FORM REGISTRAZIONE");
		LoggerUtils.info("Compilazione sezione 'Enter Account Information'.");
		formRegistrazione.compilaInformazioniAccount(user);
		ExtentLogger.info("Compilazione dati account.(sesso,password,data,newsletter,ricevi regalo-->COMPILATI");

		ExtentLogger.info("Compilazione indirizzo completo.");
		LoggerUtils.info("Compilazione sezione 'Address Information'.");
		formRegistrazione.compilaIndirizzo(user);
		ExtentLogger.info("nome,cognome,company,address1,addres2,country,state,city,zipcode,mobile number-->COMPILATI");

		ExtentLogger.info("CLICK BUTTON CREATE ACCOUNT");
		LoggerUtils.info("Click su 'Create Account'.");
		formRegistrazione.cliccaCreaAccount();

		// ===== VERIFICA MESSAGGIO ACCOUNT CREATO =====
		ExtentLogger.info("Verifica messaggio di conferma creazione account.");
		LoggerUtils.info("Messaggio atteso: " + user.getMessaggioConfermaCreazioneAccount());

		assertTrue(formRegistrazione.verificaMessaggioAccountCreato(user.getMessaggioConfermaCreazioneAccount()),
				"Il messaggio di conferma account NON è corretto.");

		ExtentLogger.pass("Messaggio di account creato verificato correttamente.");
		LoggerUtils.info("Messaggio conferma account OK.");

		// === CONTINUE dopo registrazione ===
		MessaggiSistemaPage messaggiSistema = new MessaggiSistemaPage(getDriver());
		ExtentLogger.info("Click sul pulsante 'Continue'.--->DOPO REGISTRAZIONE");
		LoggerUtils.info("Click su Continue dopo creazione account.");
		messaggiSistema.clickContinue();

		// === VERIFICA UTENTE LOGGATO ===
		ExtentLogger.info("Verifica utente loggato.");
		LoggerUtils.info("Controllo nome utente loggato → atteso: " + user.getNome());

		UtenteLoggedPage utenteLoggato = new UtenteLoggedPage(getDriver());

		assertTrue(utenteLoggato.verificaMessaggioLoggedUtente(user.getNome()),
				"L'utente NON risulta loggato correttamente.");

		ExtentLogger.pass("Utente loggato correttamente.");
		LoggerUtils.info("Login verificato.");

		// === CANCELLA ACCOUNT ===
		ExtentLogger.info("click sul button delete account");
		LoggerUtils.info("Click su 'Delete Account'.");
		utenteLoggato.clickCancellaAccount();

		ExtentLogger.info("Verifica conferma eliminazione account.");
		LoggerUtils.info("Messaggio atteso: " + user.getMessaggioConfermaCancellazioneAccount());

		assertTrue(utenteLoggato.verificaMessaggioAccountCancellato(user.getMessaggioConfermaCancellazioneAccount()),
				"Il messaggio di eliminazione account NON è corretto.");

		// === CONTINUE ELIMINAZIONE ACCOUNT ===
		ExtentLogger.info("Click sul pulsante 'Continue'.--->DOPO ELIMINAZIONE ACCOUNT");
		messaggiSistema.clickContinue();
		ExtentLogger.pass("Account eliminato correttamente.");

		// ==VERIFICO CHE NON ESESISTE L'ACCOUNT===
		ExtentLogger.info("Click sul pulsante 'Continue'.--->DOPO ELIMINAZIONE ACCOUNT");
		ExtentLogger.info("verifica container utente loggato dopo il click");
		assertFalse(utenteLoggato.verificaPresenzaContainerUtenteLoggato(),
				"il nome utente ancora compare nella pagina dopo l'eliminazione");
		ExtentLogger.info("ACCOUNT NON COMPARE DOPO IL CLICK CONTINUE-->ACCOUNT ELIMINATO");

	}
}
