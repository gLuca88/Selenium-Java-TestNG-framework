package gianluca.com.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gianluca.com.configurazionereport.ExtentLogger;
import gianluca.com.configurazionereport.LoggerUtils;
import gianluca.com.interfacefactory.ICookieBanner;
import gianluca.com.selenium.ISeleniumActions;
import gianluca.com.selenium.SeleniumActions;

public class CookieBanner implements ICookieBanner {

	protected WebDriver driver;
	private ISeleniumActions actions;

	public CookieBanner(WebDriver driver) {
		this.driver = driver;
		this.actions = new SeleniumActions(driver);
	}

	private By banner = By.xpath("//div[contains(@class,'fc-dialog fc-choice-dialog')]");
	private By button_AccettaCookie = By.xpath("//button[contains(@class,'fc-cta-consent')]");

	@Override
	public void gestisciCookie() {

		LoggerUtils.info("Verifica presenza Cookie Banner...");
		ExtentLogger.info("Verifica presenza Cookie Banner...");

		try {
			//Attendo che il banner sia visibile (max 5 sec)
			actions.waitForVisible(banner);

			if (actions.isDisplayed(banner)) {
				LoggerUtils.info("Cookie banner presente, procedo con accettazione cookie.");
				ExtentLogger.info("Cookie banner presente, procedo con accettazione cookie.");

				//Attendo che il bottone sia cliccabile
				actions.waitForClickable(button_AccettaCookie);

				//Clic sul pulsante Accetta
				actions.click(button_AccettaCookie);

				LoggerUtils.info("Cookie accettati correttamente.");
				ExtentLogger.pass("Cookie accettati correttamente.");
			}

		} catch (Exception e) {

			LoggerUtils.warn("Cookie banner non presente o già gestito.");
			ExtentLogger.info("Cookie banner non presente o già gestito.");
		}
	}

}
