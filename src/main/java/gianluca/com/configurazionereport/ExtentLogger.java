package gianluca.com.configurazionereport;

import com.aventstack.extentreports.Status;

public class ExtentLogger {

	private static ExtentReportManager extent;

	public static void setManager(IReportManager manager) {
		extent = (ExtentReportManager) manager;
	}

	public static void info(String message) {
		if (extent == null || extent.getTest() == null)
			return;
		extent.getTest().log(Status.INFO, message);
	}

	public static void pass(String message) {
		if (extent == null || extent.getTest() == null)
			return;
		extent.getTest().log(Status.PASS, message);
	}

	public static void fail(String message) {
		if (extent == null || extent.getTest() == null)
			return;
		extent.getTest().log(Status.FAIL, message);
	}
}
