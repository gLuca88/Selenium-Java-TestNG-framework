package gianluca.com.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gianluca.com.selenium.BasePage;

public class LoginRegistrazionePage extends BasePage {

	private final String titoloAttesoFormRegoistrazione = "New User Signup!";

	public LoginRegistrazionePage(WebDriver driver) {
		super(driver);

	}

	private By containerTitoloFormRegistrazione = By.xpath("//div[contains(@class,'signup-form')]/h2");
	private By input_Name = By.cssSelector("input[data-qa='signup-name']");
	private By input_Email = By.cssSelector("input[data-qa='signup-email']");
	private By button_SignUpRegistrazione = By.cssSelector("button[data-qa='signup-button']");

	public String getTextTitoloFormRegistrazione() {
		return actions.getText(containerTitoloFormRegistrazione);
	}

	public String getTitoloAttesoFormRegostrazione() {
		return titoloAttesoFormRegoistrazione;
	}

	public void inserisciNomeEmailRegistrazione(String nome, String email) {
		actions.type(input_Name, nome);
		actions.type(input_Email, email);
	}

	public void clickSignuUpRegistrazione() {
		actions.click(button_SignUpRegistrazione);
	}
}
