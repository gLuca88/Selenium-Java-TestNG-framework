package gianluca.com.configurazionereport;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements IReportManager {

	private ExtentReports extent;
	private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public ExtentReportManager() {

		String reportPath = ScreenshotUtils.getRunFolder() + "ExtentReport.html";

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

		String testName = result.getName();
		LoggerUtils.startTestLogging(testName);

		ExtentTest extentTest = extent.createTest(testName).assignAuthor("Gianluca");

		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		String testName = result.getName();

		test.get().pass("✅ Test superato");
		test.get().info("<a href='logs/" + testName + ".log' target='_blank'>📄 Apri log</a>");

		LoggerUtils.stopTestLogging();
		test.remove();
	}

	@Override
	public void onTestFailure(ITestResult result, String screenshotPath) {

		String testName = result.getName();

		test.get().fail("❌ Test fallito");
		test.get().fail(result.getThrowable());

		if (screenshotPath != null) {
			test.get().fail("<a href='" + screenshotPath + "' target='_blank'>📸 Apri screenshot</a>");
		}

		test.get().info("<a href='logs/" + testName + ".log' target='_blank'>📄 Apri log</a>");

		LoggerUtils.stopTestLogging();
		test.remove();
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		test.get().skip("⚠️ Test saltato");
		LoggerUtils.stopTestLogging();
		test.remove();
	}

	@Override
	public void setSystemInfo(String key, String value) {
		extent.setSystemInfo(key, value);
	}

	@Override
	public void log(Status status, String message) {
		if (test.get() != null) {
			test.get().log(status, message);
		}
	}
}
