package gianluca.com.configurazionereport;

import org.testng.ITestResult;

public interface IReportManager {
	void onStartSuite(String suiteName);

	void onFinishSuite(String suiteName);

	void onTestStart(ITestResult result);

	void onTestSuccess(ITestResult result);

	void onTestFailure(ITestResult result, String screenshotPath);

	void onTestSkipped(ITestResult result);

	void setSystemInfo(String key, String value);
}
