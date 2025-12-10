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

		reportManager = ReportFactory.get(ConfigReader.getProperty("report"));
		ExtentLogger.setManager(reportManager);

		LoggerUtils.info("SUITE STARTED: " + context.getName());
		reportManager.onStartSuite(context.getName());

		reportManager.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
		reportManager.setSystemInfo("Environment", System.getProperty("env"));
		reportManager.setSystemInfo("Headless", ConfigReader.getProperty("headless"));
	}

	@Override
	public void onFinish(ITestContext context) {
		LoggerUtils.info("SUITE FINISHED: " + context.getName());
		reportManager.onFinishSuite(context.getName());
		ExtentLogger.clear();
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

		String screenshotPath = ScreenshotUtils.takeScreenshot(result.getName());
		reportManager.onTestFailure(result, screenshotPath);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		LoggerUtils.warn("TEST SKIPPED: " + result.getName());
		reportManager.onTestSkipped(result);
	}
}
