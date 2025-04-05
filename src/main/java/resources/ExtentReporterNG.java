package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	public static ExtentReports getReportObject()
	{
		
		String path=System.getProperty("user.dir")+"\\reports\\HTMLReport.html";
	    ExtentSparkReporter extentReporter = new ExtentSparkReporter(path);
	    extentReporter.config().setDocumentTitle("Test Results");
	    extentReporter.config().setReportName("Web Automation Results");
	    ExtentReports extent =new ExtentReports();
	    extent.attachReporter(extentReporter);
	    extent.setSystemInfo("Tester", "Rasheeda");
	    return extent;
		

	}
}
