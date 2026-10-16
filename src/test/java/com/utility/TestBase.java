package com.utility;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;

public class TestBase {
	
	protected HomePage homePage;
	Logger logger=LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest;
	//private boolean isHeadless=true;
	
	
	@Parameters({"browser", "isLambdaTest", "isHeadless"})
	@BeforeMethod(description = "This method is to load the homepage of the website")
	public void setUp(ITestResult result,@Optional("chrome") String browser,@Optional("false") boolean isLambdaTest,@Optional("true") boolean isHeadless) {
		WebDriver lambdaDriver;
		this.isLambdaTest=isLambdaTest;
		if(isLambdaTest){
			lambdaDriver=LambdaUtility.initializeLambdaTestSession("chrome", result.getMethod().getMethodName());
			homePage=new HomePage(lambdaDriver);
		}
		else {
			logger.info("Load the homepage of the website");
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()),isHeadless);
		}
		
	}
	
	public BrowserUtility getInstance() {
		return homePage;
	}
	
	public void tearDown() {
		if(isLambdaTest) {
			LambdaUtility.quitSession();
		}
		else {
			homePage.quit();
		}
	}

}
