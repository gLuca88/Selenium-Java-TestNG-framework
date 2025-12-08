package gianluca.com.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gianluca.com.selenium.BasePage;

public class MessaggiSistemaPage extends BasePage {

	public MessaggiSistemaPage(WebDriver driver) {
		super(driver);

	}

	private By button_continueDopoCreazioneAccount = By.cssSelector("a[data-qa='continue-button']");

	public void clickContinue() {
		actions.click(button_continueDopoCreazioneAccount);
	}
}
