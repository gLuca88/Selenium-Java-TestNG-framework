package gianluca.com.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import gianluca.com.interfacefactory.BrowserDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager implements BrowserDriver {

	@Override
	public WebDriver createDriver() {
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	}
}