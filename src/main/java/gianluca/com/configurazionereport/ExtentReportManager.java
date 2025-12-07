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
		test.assignAuthor("Gianluca");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result, String screenshotPath) {

		test.fail("Test failed: " + result.getThrowable());

		try {
			test.fail("Screenshot:").addScreenCaptureFromPath(screenshotPath);
		} catch (Exception e) {
			LoggerUtils.error("Errore allegando screenshot: " + e.getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test skipped");
	}

	public void setSystemInfo(String key, String value) {
		extent.setSystemInfo(key, value);
	}

	public ExtentTest getTest() {
		return test;
	}
}
