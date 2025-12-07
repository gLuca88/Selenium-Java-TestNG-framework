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

	public boolean verificaMessaggio(String nomeAtteso) {
		String nomeOttenuto = actions.getText(container_NomeUtenteLoggato).trim();
		return nomeOttenuto.equalsIgnoreCase(nomeAtteso);
	}

	public void clickCancellaAccount() {
		actions.click(button_CancellaAccount);
	}

	public boolean verificaMessaggioAccountCancellato(String messaggioAtteso) {
		String messaggioOttenuto = actions.getText(container_MessaggioAccountCancellato);
		return messaggioOttenuto.equalsIgnoreCase(messaggioAtteso);
	}

	public boolean verificaPresenzaContainerUtenteLoggato() {

		return actions.isDisplayed(container_NomeUtenteLoggato);
	}

}
