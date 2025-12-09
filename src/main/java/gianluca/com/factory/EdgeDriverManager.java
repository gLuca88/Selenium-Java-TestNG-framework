package gianluca.com.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import gianluca.com.interfacefactory.BrowserDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeDriverManager implements BrowserDriver {

	@Override
	public WebDriver createDriver(boolean headless) {
		WebDriverManager.edgedriver().setup();

		EdgeOptions options = new EdgeOptions();
		if (headless) {
			options.addArguments("--headless=new");
			options.addArguments("--disable-gpu");
			options.addArguments("--window-size=1920,1080");
		}

		return new EdgeDriver(options);
	}
}
