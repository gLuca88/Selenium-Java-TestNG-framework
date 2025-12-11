package gianluca.com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface ISeleniumActions {

	void click(By locator);

	void type(By locator, String text);

	String getText(By locator);

	boolean isDisplayed(By locator);

	void waitForVisible(By locator);

	void waitForClickable(By locator);

	void selectByValue(By locator, String value);

	boolean isTextEqual(By locator, String expected);

	WebElement createWebElement(By locator);

	void scrollToElement(By locator);

	// nuovo metodo per leggere i messaggi HTML5 del browser
	String getValidationMessage(By locator);

}
