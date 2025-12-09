package gianluca.com.interfacefactory;

import org.openqa.selenium.WebDriver;

public interface BrowserDriver {
	
	WebDriver createDriver(boolean headless);
}
