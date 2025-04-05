package abstractTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import resources.ExtentReporterNG;

public class Listeners extends ParentClass implements ITestListener {
	
	ExtentReports extent= ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest  = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // unique thread id
	    
	  }

	  
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().log(Status.PASS, "Test passed");
		//test.log(Status.PASS, "Test passed");
	   
	  }


	  public void onTestFailure(ITestResult result) {
		  extentTest.get().fail(result.getThrowable());
		  //attach screenshot to report
	    try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		String filepath=null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
		
	    
	  }

	 
	 public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }

	  
	  public void onStart(ITestContext context) {
	    // not implemented
	  }

	  
	 
	  public void onFinish(ITestContext context) {
		  extent.flush();
	  }
	}

	
	

