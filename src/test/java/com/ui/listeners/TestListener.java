package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;
import com.utility.TestBase;

public class TestListener implements ITestListener{
	Logger logger=LoggerUtility.getLogger(this.getClass());
	
	
	

	 public void onTestStart(ITestResult result) {
		    // not implemented
		 logger.info(result.getMethod().getMethodName());
		 logger.info(result.getMethod().getDescription());
		 logger.info(Arrays.toString(result.getMethod().getGroups()));
		// extentTest=extentReports.createTest(result.getMethod().getMethodName());
		 ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
		  }
	 
	 public void onTestSuccess(ITestResult result) {
		    // not implemented
		 logger.info(result.getMethod().getMethodName()+" "+"PASSED");
		 //extentTest.log(Status.PASS, result.getMethod().getMethodName()+" "+"PASSED");
		 ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName()+" "+"PASSED");
		  }
	 
	 public void onTestFailure(ITestResult result) {
		    // not implemented
		 logger.info(result.getMethod().getMethodName()+" "+"FAILED");
		 //extentTest.log(Status.FAIL, result.getMethod().getMethodName()+" "+"FAILED");
		 ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName()+" "+"FAILED");
		 
		 Object testClass=result.getInstance();
		 
		 BrowserUtility bu=((TestBase)testClass).getInstance();
		 logger.info("Capturing screenshot for failed test cases");
		String screenshotPath= bu.takeScreenshot(result.getMethod().getMethodName());
		ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
		  }
	 
	 public void onTestSkipped(ITestResult result) {
		    // not implemented
		 ExtentTest test = ExtentReporterUtility.getTest();
		 if(test==null) {
			 
			 ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
			 test= ExtentReporterUtility.getTest();
		 }
		 logger.info(result.getMethod().getMethodName()+" "+"SKIPPED");
		 //extentTest.log(Status.SKIP, result.getMethod().getMethodName()+" "+"SKIPPED");
		 test.log(Status.SKIP, result.getMethod().getMethodName()+" "+"SKIPPED");
		  }
	 
	 public void onStart(ITestContext context) {
		    // not implemented
		 logger.info("Test suite Started successfully");
		 ExtentReporterUtility.setUpSparkReporter("report.html");
		  }
	 
	 public void onFinish(ITestContext context) {
		    // not implemented
		 logger.info("Test suite completed successfully");
		 ExtentReporterUtility.flushReport();
		  }
}
