package gianluca.com.configurazionereport;

import java.util.Map;



public class ReportFactory {

	private static final Map<String, IReportManager> REPORTS = Map.of("extent", new ExtentReportManager()
	// futuro: "allure", new AllureReportManager()
	// futuro: "html", new HtmlReportManager()
	);

	public static IReportManager get(String reportType) {
		IReportManager rm = REPORTS.get(reportType.toLowerCase());
		if (rm == null) {
			throw new RuntimeException("Report non supportato: " + reportType);
		}
		return rm;
	}

}
