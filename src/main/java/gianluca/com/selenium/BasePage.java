package gianluca.com.selenium;

import org.openqa.selenium.WebDriver;

public class BasePage {

	protected WebDriver driver;
	protected ISeleniumActions actions;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.actions = new SeleniumActions(driver);
	}
}
