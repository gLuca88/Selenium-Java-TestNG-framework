package gianluca.com.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import gianluca.com.interfacefactory.BrowserDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeDriverManager implements BrowserDriver {

	@Override
	public WebDriver createDriver() {
		WebDriverManager.edgedriver().setup();
		return new EdgeDriver();
	}
}
