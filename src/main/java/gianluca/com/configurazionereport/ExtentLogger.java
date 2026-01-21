package gianluca.com.configurazionereport;

import com.aventstack.extentreports.Status;

public class ExtentLogger {

    private static ExtentReportManager manager;

    private ExtentLogger() {}

    public static void init(ExtentReportManager reportManager) {
        manager = reportManager;
    }

    public static void info(String message) {
        manager.log(Status.INFO, message);
    }

    public static void pass(String message) {
        manager.log(Status.PASS, message);
    }

    public static void fail(String message) {
        manager.log(Status.FAIL, message);
    }
}
