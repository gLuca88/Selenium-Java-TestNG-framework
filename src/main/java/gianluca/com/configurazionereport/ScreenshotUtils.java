package gianluca.com.configurazionereport;

import java.io.File;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import gianluca.com.factory.DriverFactory;

public class ScreenshotUtils {

	private static final String RUN_FOLDER = "reports/" + TimeUtils.getTimestamp() + "/";

	public static String takeScreenshot(String testName) {

		WebDriver driver = DriverFactory.getDriver();
		if (driver == null) {
			return null;
		}

		String screenshotDir = RUN_FOLDER + "screenshots/" + testName + "/";
		new File(screenshotDir).mkdirs();

		String fileName = System.currentTimeMillis() + ".png";
		String fullPath = screenshotDir + fileName;

		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(src.toPath(), new File(fullPath).toPath());
		} catch (Exception e) {
			LoggerUtils.error("Errore screenshot", e);
			return null;
		}

		// 🔥 PATH RELATIVO AL REPORT
		return "screenshots/" + testName + "/" + fileName;
	}

	public static String getRunFolder() {
		return RUN_FOLDER;
	}
}
