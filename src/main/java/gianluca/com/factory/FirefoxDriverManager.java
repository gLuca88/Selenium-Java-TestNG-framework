package gianluca.com.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import gianluca.com.interfacefactory.BrowserDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager implements BrowserDriver {

	@Override
	public WebDriver createDriver(boolean headless) {
		WebDriverManager.firefoxdriver().setup();

		FirefoxOptions options = new FirefoxOptions();
		if (headless) {
			options.addArguments("--headless");
			options.addArguments("--width=1920");
			options.addArguments("--height=1080");
		}

		return new FirefoxDriver(options);
	}
}