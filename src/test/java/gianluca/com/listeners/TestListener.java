package gianluca.com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import gianluca.com.configurazionereport.ExtentLogger;
import gianluca.com.configurazionereport.ExtentReportManager;
import gianluca.com.configurazionereport.LoggerUtils;
import gianluca.com.configurazionereport.ReportFactory;
import gianluca.com.configurazionereport.ScreenshotUtils;
import gianluca.com.factory.DriverFactory;
import gianluca.com.utils.ConfigReader;

public class TestListener implements ITestListener {

	private ExtentReportManager reportManager;

	@Override
	public void onStart(ITestContext context) {

		reportManager = (ExtentReportManager) ReportFactory.get(ConfigReader.getProperty("report"));

		// inizializza UNA SOLA VOLTA
		ExtentLogger.init(reportManager);

		LoggerUtils.info("SUITE STARTED: " + context.getName());

		reportManager.onStartSuite(context.getName());
		reportManager.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
		reportManager.setSystemInfo("Headless", ConfigReader.getProperty("headless"));
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
		try {
			LoggerUtils.info("TEST PASSED: " + result.getName());
			reportManager.onTestSuccess(result);
		} finally {
			DriverFactory.quitDriver();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			LoggerUtils.error("TEST FAILED: " + result.getName());
			String screenshotPath = ScreenshotUtils.takeScreenshot(result.getName());
			reportManager.onTestFailure(result, screenshotPath);
		} finally {
			DriverFactory.quitDriver();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			LoggerUtils.warn("TEST SKIPPED: " + result.getName());
			reportManager.onTestSkipped(result);
		} finally {
			DriverFactory.quitDriver();
		}
	}
}
