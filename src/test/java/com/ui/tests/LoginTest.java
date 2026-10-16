package com.ui.tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.ui.pages.LogInPage;
import com.ui.pojo.User;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import com.utility.TestBase;

@Listeners(com.ui.listeners.TestListener.class)
public class LoginTest extends TestBase{
	
	Logger logger=LoggerUtility.getLogger(this.getClass());

	

	@Test(description = "This test is to verify the valid user is able to login", groups = { "e2e",
			"regression" }, dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")

	public void loginTest(User user) {
		// TODO Auto-generated method stub
		// System.setProperty("webdriver.chrome.driver",
		// "D:\\chrome-win64\\chromedriver.exe");

		// String userName=homePage.goTOLogInPage().doLoginWith("wogor88123@duidir.com",
		// "password").getUserName();

		Assert.assertEquals(
				homePage.goTOLogInPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"James Bond");

	}

	/*
	 * @Test(description = "This test is to verify the valid user is able to login",
	 * groups = { "e2e", "regression" }, dataProviderClass =
	 * com.ui.dataProviders.LoginDataProvider.class, dataProvider =
	 * "LoginTestCSVDataProvider")
	 * 
	 * public void loginCSVTest(User user) { // TODO Auto-generated method stub //
	 * System.setProperty("webdriver.chrome.driver", //
	 * "D:\\chrome-win64\\chromedriver.exe");
	 * 
	 * // String
	 * userName=homePage.goTOLogInPage().doLoginWith("wogor88123@duidir.com", //
	 * "password").getUserName();
	 * 
	 * Assert.assertEquals(
	 * homePage.goTOLogInPage().doLoginWith(user.getEmailAddress(),
	 * user.getPassword()).getUserName(), "James Bond");
	 * 
	 * }
	 * 
	 * @Test(description = "This test is to verify the valid user is able to login",
	 * groups = { "e2e", "regression" }, dataProviderClass =
	 * com.ui.dataProviders.LoginDataProvider.class, dataProvider =
	 * "LoginTestExcelDataProvider", retryAnalyzer =
	 * com.ui.listeners.MyRetryAnalyzers.class)
	 * 
	 * public void loginExcelTest(User user) { // TODO Auto-generated method stub
	 * //System.setProperty("webdriver.chrome.driver",
	 * "D:\\chrome-win64\\chromedriver.exe");
	 * 
	 * //String
	 * userName=homePage.goTOLogInPage().doLoginWith("wogor88123@duidir.com",
	 * "password").getUserName(); logger.info("Log in test has started");
	 * Assert.assertEquals(
	 * homePage.goTOLogInPage().doLoginWith(user.getEmailAddress(),
	 * user.getPassword()).getUserName(), "James Bond");
	 * logger.info("Log in test is completed"); }
	 */

}
