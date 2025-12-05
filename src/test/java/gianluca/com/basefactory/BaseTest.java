package gianluca.com.basefactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import gianluca.com.factory.DriverFactory;
import gianluca.com.utils.ConfigReader;

public class BaseTest {

	protected WebDriver driver;

	@BeforeMethod
	public void setUp() {
		DriverFactory.initDriver();
		driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
		driver.get(ConfigReader.getProperty("baseUrl"));
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}

}
