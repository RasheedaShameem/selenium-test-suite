package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * Utility class for generating Extent Reports in the form of an HTML report.
 * Configures and initializes the ExtentReports instance and its settings.
 */
public class ExtentReporterNG {

    /**
     * Configures and returns an ExtentReports object for generating HTML reports.
     * @return ExtentReports object with the configured settings
     */
    public static ExtentReports getReportObject() {

        // Define the path where the HTML report will be saved
        String path = System.getProperty("user.dir") + "\\reports\\HTMLReport.html";
        
        // Create an instance of ExtentSparkReporter and set the report file location
        ExtentSparkReporter extentReporter = new ExtentSparkReporter(path);
        
        // Configure the document title and report name for the generated report
        extentReporter.config().setDocumentTitle("Test Results");
        extentReporter.config().setReportName("Web Automation Results");
        
        // Create an ExtentReports instance and attach the ExtentSparkReporter to it
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(extentReporter);
        
        // Add system information to the report (e.g., the name of the tester)
        extent.setSystemInfo("Tester", "Rasheeda");
        
        // Return the configured ExtentReports object
        return extent;
    }
}
