package gianluca.com.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gianluca.com.datatestmodel.UtenteRegistrazione;
import gianluca.com.selenium.BasePage;

public class FormRegistrazionePage extends BasePage {

	public FormRegistrazionePage(WebDriver driver) {
		super(driver);
	}

	private By radioButton_Maschio = By.id("id_gender1");
	private By radioButton_Femmina = By.id("id_gender2");
	private By input_Password = By.cssSelector("input[data-qa='password']");
	private By dataNascita_Giorno = By.id("days");
	private By dataNascita_Mese = By.id("months");
	private By dataNascita_Anno = By.id("years");
	private By radioButton_SignNewSletter = By.id("newsletter");
	private By radioButton_RiceviOfferte = By.id("optin");

	private By input_FirstNome = By.cssSelector("input[data-qa='first_name']");
	private By input_LastCognome = By.cssSelector("input[data-qa='last_name']");
	private By input_Company = By.cssSelector("input[data-qa='company']");
	private By input_AddressUno = By.cssSelector("input[data-qa='address']");
	private By input_AddressDue = By.cssSelector("input[data-qa='address2']");
	private By menuSelezionaPaese = By.id("country");
	private By input_Stato = By.cssSelector("input[data-qa='state']");
	private By input_Citta = By.cssSelector("input[data-qa='city']");
	private By input_ZipCode = By.cssSelector("input[data-qa='zipcode']");
	private By input_Telefono = By.cssSelector("input[data-qa='mobile_number']");

	private By button_CreaAccount = By.cssSelector("button[data-qa='create-account']");

	private By container_MexAccountCreato = By.cssSelector("h2[data-qa='account-created']");

	private By button_continueDopoCreazioneAccount = By.cssSelector("a[data-qa='continue-button']");

	/*
	 * ============================== SECTION: ENTER ACCOUNT INFO
	 * ==============================
	 */

	private void selezionaTitolo(String sesso) {
		if (sesso.equalsIgnoreCase("maschio")) {
			actions.click(radioButton_Maschio);
		} else {
			actions.click(radioButton_Femmina);
		}
	}

	private void inserisciPassword(String password) {
		actions.type(input_Password, password);
	}

	private void selezionaData(String giorno, String mese, String anno) {
		actions.selectByValue(dataNascita_Giorno, giorno);
		actions.selectByValue(dataNascita_Mese, mese);
		actions.selectByValue(dataNascita_Anno, anno);
	}

	private void gestisciCheckbox(boolean newsletter, boolean offerte) {
		if (newsletter)
			actions.click(radioButton_SignNewSletter);
		if (offerte)
			actions.click(radioButton_RiceviOfferte);
	}

	// Metodo aggregato per tutta la prima sezione
	public void compilaInformazioniAccount(UtenteRegistrazione user) {
		selezionaTitolo(user.getSesso());
		inserisciPassword(user.getPassword());
		selezionaData(user.getGiornoNascita(), user.getMeseNascita(), user.getAnnoNascita());
		gestisciCheckbox(user.isNewsletter(), user.isOfferteSpeciali());
	}

	/*
	 * ============================== SECTION: ADDRESS INFORMATION
	 * ==============================
	 */

	private void inserisciDatiAnagrafici(String nome, String cognome, String company) {
		actions.type(input_FirstNome, nome);
		actions.type(input_LastCognome, cognome);
		actions.type(input_Company, company);
	}

	private void inserisciIndirizzi(String address1, String address2) {
		actions.type(input_AddressUno, address1);
		actions.type(input_AddressDue, address2);
	}

	private void selezionaPaese(String paese) {
		actions.selectByValue(menuSelezionaPaese, paese);
	}

	private void inserisciLocalita(String stato, String citta, String cap, String telefono) {
		actions.type(input_Stato, stato);
		actions.type(input_Citta, citta);
		actions.type(input_ZipCode, cap);
		actions.type(input_Telefono, telefono);
	}

	// Metodo aggregato per tutta la sezione indirizzo
	public void compilaIndirizzo(UtenteRegistrazione user) {
		inserisciDatiAnagrafici(user.getNome(), user.getCognome(), user.getAzienda());
		inserisciIndirizzi(user.getIndirizzo1(), user.getIndirizzo2());
		selezionaPaese(user.getPaese());
		inserisciLocalita(user.getStato(), user.getCitta(), user.getCap(), user.getTelefono());
	}

	// Invio finale del form
	public void cliccaCreaAccount() {
		actions.click(button_CreaAccount);
	}

	public boolean verificaMessaggioAccountCreato(String messaggioAtteso) {
		return actions.getText(container_MexAccountCreato).equalsIgnoreCase(messaggioAtteso);
	}

	public void clickContinueDopoCompilazioneForm() {
		actions.click(button_continueDopoCreazioneAccount);
	}

}
