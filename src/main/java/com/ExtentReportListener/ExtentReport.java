package com.ExtentReportListener;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.util.TestUtil;

public class ExtentReport {

    // Create global variable which will be used in all method
ExtentReports extent;
ExtentTest logger;
WebDriver driver;

   // This code will run before executing any testcase
@BeforeMethod
public   void setup()
{
   ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Reports/learn_automation2.html");
	
   extent = new ExtentReports();
   
   extent.attachReporter(reporter);
   
 //  logger=extent.createTest("LoginTest");
}


// This will run after testcase and it will capture screenshot and add in report
/*
 * @AfterMethod public void tearDown(ITestResult result) throws IOException {
 * 
 * if(result.getStatus()==ITestResult.FAILURE) { String
 * temp=TestUtil.getScreenshot(driver);
 * 
 * logger.fail(result.getThrowable().getMessage(),
 * MediaEntityBuilder.createScreenCaptureFromPath(temp).build()); }
 * 
 * extent.flush(); }
 */

}
