package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;
import com.utility.PropertiesUtil;

public final class HomePage extends BrowserUtility{
	Logger logger=LoggerUtility.getLogger(this.getClass());
	private static final By SIGN_IN_LINK_LOCATOR=By.xpath("//a[contains(text(),'Sign in')]");
	
	public HomePage(Browser browserName, boolean isHeadless) {
		super(browserName,isHeadless);
		//goToWebsite(PropertiesUtil.readProperty(QA, "URL"));
	    goToWebsite(JSONUtility.readJSON(QA).getUrl());
		maximizeWindow();
	}
	
	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		goToWebsite(JSONUtility.readJSON(QA).getUrl());
	}

	public LogInPage goTOLogInPage() {
		logger.info("Trying to perform clik to go to sign in link");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LogInPage loginPage=new LogInPage(getDriver());
		return loginPage;
	}
}
