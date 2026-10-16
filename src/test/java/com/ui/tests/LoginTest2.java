package com.ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ui.pages.HomePage;
import com.ui.pages.LogInPage;
import com.utility.BrowserUtility;

public class LoginTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.chrome.driver", "D:\\chrome-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		HomePage homePage=new HomePage(driver);
		LogInPage loginPage=homePage.goTOLogInPage();
		loginPage.doLoginWith("wogor88123@duidir.com", "password");
		
	
		
		 
		 
	}

}
