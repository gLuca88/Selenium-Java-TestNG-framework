package gianluca.com.configurazionereport;


import com.aventstack.extentreports.Status;

public class ExtentLogger {//per report

	private static IReportManager reportManager;

	public static void setManager(IReportManager manager) {
		reportManager = manager;
	}

	public static void info(String message) {
		((ExtentReportManager) reportManager).getTest().log(Status.INFO, message);
	}

	public static void pass(String message) {
		((ExtentReportManager) reportManager).getTest().log(Status.PASS, message);
	}

	public static void fail(String message) {
		((ExtentReportManager) reportManager).getTest().log(Status.FAIL, message);
	}
}
