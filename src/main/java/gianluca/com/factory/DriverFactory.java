package gianluca.com.factory;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import gianluca.com.interfacefactory.BrowserDriver;
import gianluca.com.utils.ConfigReader;

public class DriverFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private static final Map<String, BrowserDriver> DRIVERS = Map.of("chrome", new ChromeDriverManager(), "firefox",
			new FirefoxDriverManager(), "edge", new EdgeDriverManager());

	public static void initDriver() {
		String browser = ConfigReader.getProperty("browser").toLowerCase();

		BrowserDriver browserDriver = DRIVERS.get(browser);
		if (browserDriver == null) {
			throw new RuntimeException("Browser non supportato: " + browser);
		}

		driver.set(browserDriver.createDriver());
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
