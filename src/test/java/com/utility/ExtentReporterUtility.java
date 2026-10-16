package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {

	private static ExtentReports extentReport;
	private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	
	public static void setUpSparkReporter(String reportName) {
		ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"//"+reportName);
		extentReport=new ExtentReports();
		extentReport.attachReporter(extentSparkReporter);
	}
	
	public static void createExtentTest(String testName) {
		ExtentTest test=extentReport.createTest(testName);
		extentTest.set(test);
	}
	
	public static ExtentTest getTest() {
		return extentTest.get();
	}
	
	public static void flushReport() {
		extentReport.flush();
	}
}
