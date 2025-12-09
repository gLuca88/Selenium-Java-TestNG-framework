package gianluca.com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import gianluca.com.configurazionereport.ExtentLogger;
import gianluca.com.configurazionereport.LoggerUtils;
import gianluca.com.configurazionereport.ReportFactory;
import gianluca.com.configurazionereport.IReportManager;
import gianluca.com.configurazionereport.ScreenshotUtils;
import gianluca.com.utils.ConfigReader;

public class TestListener implements ITestListener {

	private IReportManager reportManager;

	@Override
	public void onStart(ITestContext context) {

		// Carichiamo il tipo di report dal properties
		String reportType = ConfigReader.getProperty("report");
		// Prendiamo l'implementazione dalla factory (ExtentReportManager per ora)
		reportManager = ReportFactory.get(reportType);
		// Impostiamo il logger ExtentLogger per i log step-by-step
		ExtentLogger.setManager(reportManager);
		// Logging con Log4j2
		LoggerUtils.info("SUITE STARTED: " + context.getName());
		// Notifica al sistema di report
		reportManager.onStartSuite(context.getName());
		// SYSTEM INFO NEL REPORT
		String browser = ConfigReader.getProperty("browser");
		String environment = System.getProperty("env"); // <-- QUI LA FIX
		String headless = ConfigReader.getProperty("headless");

		reportManager.setSystemInfo("Browser", browser);
		reportManager.setSystemInfo("Environment", environment);
		reportManager.setSystemInfo("Headless", headless);
	}

	@Override
	public void onFinish(ITestContext context) {

		LoggerUtils.info("SUITE FINISHED: " + context.getName());
		reportManager.onFinishSuite(context.getName());
	}

	@Override
	public void onTestStart(ITestResult result) {

		LoggerUtils.info("TEST START: " + result.getName());
		reportManager.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		LoggerUtils.info("TEST PASSED: " + result.getName());
		reportManager.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {

		LoggerUtils.error("TEST FAILED: " + result.getName());

		// Screenshot automatico
		String screenshotPath = ScreenshotUtils.takeScreenshot(result.getName());

		// Passiamo screenshot al report
		reportManager.onTestFailure(result, screenshotPath);
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		LoggerUtils.warn("TEST SKIPPED: " + result.getName());
		reportManager.onTestSkipped(result);
	}
}
