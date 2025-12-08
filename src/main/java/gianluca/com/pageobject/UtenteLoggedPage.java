package gianluca.com.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gianluca.com.selenium.BasePage;

public class UtenteLoggedPage extends BasePage {

	public UtenteLoggedPage(WebDriver driver) {
		super(driver);
	}

	private By container_NomeUtenteLoggato = By.xpath("//i[contains(@class,'fa fa-user')]/following-sibling::b");
	private By button_CancellaAccount = By.xpath("//a[contains(@href,'delete')]");
	private By container_MessaggioAccountCancellato = By.cssSelector("h2[data-qa='account-deleted']");

	public boolean verificaMessaggioLoggedUtente(String nomeAtteso) {
		return actions.isTextEqual(container_NomeUtenteLoggato, nomeAtteso);
	}

	public void clickCancellaAccount() {
		actions.click(button_CancellaAccount);
	}

	public boolean verificaMessaggioAccountCancellato(String messaggioAtteso) {
		return actions.isTextEqual(container_MessaggioAccountCancellato, messaggioAtteso);
	}

	public boolean verificaPresenzaContainerUtenteLoggato() {

		return actions.isDisplayed(container_NomeUtenteLoggato);
	}

}
