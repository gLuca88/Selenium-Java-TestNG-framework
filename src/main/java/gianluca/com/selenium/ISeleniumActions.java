package gianluca.com.selenium;

import org.openqa.selenium.By;

public interface ISeleniumActions {

	void click(By locator);

	void type(By locator, String text);

	String getText(By locator);

	boolean isDisplayed(By locator);

	void waitForVisible(By locator);

	void waitForClickable(By locator);

}
