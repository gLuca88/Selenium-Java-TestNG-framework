package gianluca.com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import gianluca.com.configurazionereport.ExtentLogger;
import gianluca.com.configurazionereport.ExtentReportManager;
import gianluca.com.configurazionereport.LoggerUtils;
import gianluca.com.configurazionereport.ReportFactory;
import gianluca.com.configurazionereport.ScreenshotUtils;
import gianluca.com.utils.ConfigReader;

public class TestListener implements ITestListener {

	private ExtentReportManager reportManager;

	@Override
	public void onStart(ITestContext context) {

		reportManager = (ExtentReportManager) ReportFactory.get(ConfigReader.getProperty("report"));

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
		reportManager.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		reportManager.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String screenshotPath = ScreenshotUtils.takeScreenshot(result.getName());

		reportManager.onTestFailure(result, screenshotPath);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		reportManager.onTestSkipped(result);
	}
}