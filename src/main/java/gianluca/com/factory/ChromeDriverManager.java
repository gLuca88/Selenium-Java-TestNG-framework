package gianluca.com.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import gianluca.com.interfacefactory.BrowserDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager implements BrowserDriver {

	@Override
	public WebDriver createDriver() {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	}
}