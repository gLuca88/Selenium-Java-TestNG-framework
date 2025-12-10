package gianluca.com.configurazionereport;

import java.io.File;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements IReportManager {

	private ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

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
		test.remove(); // sicurezza aggiuntiva
	}

	@Override
	public void onTestStart(ITestResult result) {

		String testName = result.getName();

		LoggerUtils.startTestLogging(testName);

		ExtentTest t = extent.createTest(testName).assignAuthor("Gianluca").info("Test started");

		test.set(t);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		String testName = result.getName();
		String logLink = buildLogLink(testName);

		try {
			ExtentTest t = test.get();
			t.pass("Test passed");
			t.info("📄 Log file: <a href='" + logLink + "' target='_blank'>Apri log</a>");
		} finally {
			LoggerUtils.stopTestLogging();
			test.remove();
		}
	}

	@Override
	public void onTestFailure(ITestResult result, String screenshotPath) {

		String testName = result.getName();
		String logLink = buildLogLink(testName);

		try {
			ExtentTest t = test.get();
			t.fail("Errore: " + result.getThrowable());
			t.fail("Screenshot:").addScreenCaptureFromPath(screenshotPath);
			t.info("📄 Log file: <a href='" + logLink + "' target='_blank'>Apri log</a>");
		} catch (Exception e) {
			LoggerUtils.error("Errore allegando log o screenshot: " + e.getMessage());
		} finally {
			LoggerUtils.stopTestLogging();
			test.remove();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			test.get().skip("Test skipped");
		} finally {
			test.remove();
		}
	}

	@Override
	public void setSystemInfo(String key, String value) {
		extent.setSystemInfo(key, value);
	}

	@Override
	public void log(Status status, String message) {
		ExtentTest t = test.get();
		if (t != null) {
			t.log(status, message);
		}
	}

	// ===========================
	// METODO PRIVATO OTTIMIZZATO
	// ===========================
	private String buildLogLink(String testName) {
		String path = new File(LoggerUtils.getTestLogPath(testName)).getAbsolutePath().replace("\\", "/");
		return "file:///" + path;
	}
}
