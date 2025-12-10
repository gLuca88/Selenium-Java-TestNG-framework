package gianluca.com.configurazionereport;

import com.aventstack.extentreports.Status;

public class ExtentLogger {

	private static ThreadLocal<IReportManager> report = new ThreadLocal<>();

	public static void setManager(IReportManager manager) {
		report.set(manager);
	}

	public static void clear() {
		report.remove();
	}

	private static void log(Status status, String message) {
		IReportManager r = report.get();
		if (r != null)
			r.log(status, message);
	}

	public static void info(String message) {
		log(Status.INFO, message);
	}

	public static void pass(String message) {
		log(Status.PASS, message);
	}

	public static void fail(String message) {
		log(Status.FAIL, message);
	}
}
