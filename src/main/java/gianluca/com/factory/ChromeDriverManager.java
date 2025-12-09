package gianluca.com.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import gianluca.com.interfacefactory.BrowserDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager implements BrowserDriver {

	@Override
	public WebDriver createDriver(boolean headless) {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		if (headless) {
			options.addArguments("--headless=new");
			options.addArguments("--disable-gpu");
			options.addArguments("--window-size=1920,1080");
		}

		return new ChromeDriver(options);
	}
}