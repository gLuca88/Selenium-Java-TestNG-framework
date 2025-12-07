package gianluca.com.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gianluca.com.interfacefactory.ICookieBanner;
import gianluca.com.selenium.BasePage;
import gianluca.com.utils.CookieBanner;

public class HomePage extends BasePage {

	private ICookieBanner cookieBanner;

	private final String urlHomePage = "https://automationexercise.com/";
	private final String titoloPagina = "Automation Exercise";

	public HomePage(WebDriver driver) {
		super(driver);
		this.cookieBanner = new CookieBanner(driver);
	}

	private By navBar = By.xpath("//ul[contains(@class,'navbar-nav')]");

	private By button_SignUpLogin = By.xpath("//a[contains(text(),'Signup') or contains(text(),'Login')]");

	public boolean isNavbarVisible() {
		try {
			actions.waitForVisible(navBar);
			return actions.isDisplayed(navBar);
		} catch (Exception e) {
			return false;
		}
	}

	public String getUrlAtteso() {
		return urlHomePage;
	}

	public String getTitoloAtteso() {
		return titoloPagina;
	}

	public boolean isUrlCorrect() {
		return getCurrentUrl().equalsIgnoreCase(urlHomePage);
	}

	public boolean isTitleCorrect() {
		return getTitoloPagina().equalsIgnoreCase(titoloPagina);
	}

	public void gestisciCookie() {
		cookieBanner.gestisciCookie();
	}

	public String getUrldriver() {
		return getCurrentUrl();
	}

	public String getTitoloPaginaDriver() {
		return getTitoloPagina();
	}

	public void clickSignUpLogin() {
		actions.click(button_SignUpLogin);
	}
}
