package gianluca.com.configurazionereport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import gianluca.com.factory.DriverFactory;

public class ScreenshotUtils {

	private static final String RUN_FOLDER = "reports/" + TimeUtils.getTimestamp() + "/";

	public static String takeScreenshot(String testName) {
		WebDriver driver = DriverFactory.getDriver();

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String folderPath = RUN_FOLDER + "screenshots/";
		new File(folderPath).mkdirs();

		String screenshotPath = folderPath + testName + "_" + System.currentTimeMillis() + ".png";

		try {
			Files.copy(src.toPath(), new File(screenshotPath).toPath());
		} catch (IOException e) {
			throw new RuntimeException("Errore salvataggio screenshot", e);
		}

		return new File(screenshotPath).getAbsolutePath();
	}

	public static String getRunFolder() {
		return RUN_FOLDER;
	}
}
