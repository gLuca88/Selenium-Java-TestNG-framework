package gianluca.com.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gianluca.com.selenium.BasePage;

public class LoginRegistrazionePage extends BasePage {

	private final String titoloAttesoFormRegoistrazione = "New User Signup!";

	public LoginRegistrazionePage(WebDriver driver) {
		super(driver);

	}

	private By container_TitoloFormRegistrazione = By.xpath("//div[contains(@class,'signup-form')]/h2");
	private By input_NameRegistrazione = By.cssSelector("input[data-qa='signup-name']");
	private By input_EmailRegistrazione = By.cssSelector("input[data-qa='signup-email']");
	private By button_SignUpRegistrazione = By.cssSelector("button[data-qa='signup-button']");

	private By container_TitoloFormLogin = By.xpath("//div[contains(@class,'login-form')]/h2");
	private By input_EmailLogin = By.cssSelector("input[data-qa='login-email']");
	private By input_PasswordLogin = By.cssSelector("input[data-qa='login-password']");
	private By button_Login = By.cssSelector("button[data-qa='login-button']");
	private By messaggioErroreLogin = By.xpath("//div[contains(@class,'login-form')]//p");

	public String getTextTitoloFormRegistrazione() {
		return actions.getText(container_TitoloFormRegistrazione);
	}

	public String getTextTitoloLoginForm() {
		return actions.getText(container_TitoloFormLogin);
	}

	// metodo per titoloatteso da modficare e togliere
	public String getTitoloAttesoFormRegostrazione() {
		return titoloAttesoFormRegoistrazione;
	}

	public void inserisciNomeEmailRegistrazione(String nome, String email) {
		actions.type(input_NameRegistrazione, nome);
		actions.type(input_EmailRegistrazione, email);
	}

	public void inserisciNomeEmailLogin(String email, String password) {

		actions.type(input_EmailLogin, email);
		actions.type(input_PasswordLogin, password);
	}

	public void clickButtonLogin() {
		actions.click(button_Login);
	}

	public void clickSignuUpRegistrazione() {
		actions.click(button_SignUpRegistrazione);
	}

	public String getLoginValidationMessage() {

		//Messaggio applicativo (backend)
		if (actions.isDisplayed(messaggioErroreLogin)) {
			return actions.getText(messaggioErroreLogin).trim();
		}

		//Validazione HTML5 browser
		String emailValidation = actions.getValidationMessage(input_EmailLogin);
		if (!emailValidation.isBlank()) {
			return emailValidation;
		}

		String passwordValidation = actions.getValidationMessage(input_PasswordLogin);
		if (!passwordValidation.isBlank()) {
			return passwordValidation;
		}

		return "";
	}
}
