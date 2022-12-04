package automation.Resources;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReportManager {

	private static ExtentReports reports;
	public static String filePath;

	public static ExtentReports getExtentInstance() {
		String fileName = getExtentReportName();
		String directory = System.getProperty("user.dir") + "\\reports\\";

		// storing file path in paths
		Path path = Paths.get(directory);
		// Creating new File Directory, fails cause false
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		// setting path of extent report
		filePath = directory + fileName;

		// Extent Report
		ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
		// setting reporter
		reporter.config().setEncoding("utf-8");
		reporter.config().setDocumentTitle("Title : Auomation Testing"); // Title of Report
		reporter.config().setReportName("Report: Automation Test Results"); // Name of the report
		reporter.config().setTheme(Theme.STANDARD);

		// Storing in extent report
		reports.attachReporter(reporter);
		reports.setSystemInfo("tested by", "raBi");
		return reports;
	}

	// re-initiate extent report
	public static ExtentReports getExtentReportreInitiate() {
		if (reports == null) {
			getExtentInstance();
		}
		return reports;
	}

	public static String getExtentReportName() {
		Date dates = new Date();
		String fileName = "AutomatedReport" + dates.toString().replace(":", "_").replace(" ", "_") + ".html";
		return fileName;
	}
}
