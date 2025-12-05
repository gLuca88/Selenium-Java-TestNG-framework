package gianluca.com.configurazionereport;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements IReportManager {

	private ExtentReports extent;
	private ExtentTest test;

	public ExtentReportManager() {

		String reportPath = ScreenshotUtils.getRunFolder() + "ExtentReport_" + TimeUtils.getTimestamp() + ".html";

		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

		extent = new ExtentReports();
		extent.attachReporter(spark);
	}

	@Override
	public void onStartSuite(String suiteName) {
		extent.setSystemInfo("Suite", suiteName);
	}

	@Override
	public void onFinishSuite(String suiteName) {
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.INFO, "Test started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result, String screenshotPath) {
		test.log(Status.FAIL, "Test failed: " + result.getThrowable());
		try {
			test.addScreenCaptureFromPath(screenshotPath);
		} catch (Exception ignored) {
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test skipped");
	}

	public ExtentTest getTest() {
		return test;
	}
}
