package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.constants.Browser;


public abstract class BrowserUtility {
		private static ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
		Logger logger=LoggerUtility.getLogger(this.getClass());
		
		public WebDriver getDriver() {
			return driver.get();
		}

		public BrowserUtility(WebDriver driver) {
			super();
			this.driver.set(driver);;
		}
		
		public BrowserUtility(String browser) {
			if(browser.equalsIgnoreCase("chrome")) {
				driver.set(new ChromeDriver());
			}
			else if(browser.equalsIgnoreCase("edge")) {
				driver.set(new EdgeDriver());
			}
			else {
				System.out.println("Please provide chrome or edge browser only");
			}
		}
		
		
		public BrowserUtility(Browser browser) {
			logger.info("Launching browser "+browser);
			if(browser==Browser.CHROME) {
				driver.set(new ChromeDriver());
			}
			else if(browser==Browser.EDGE) {
				driver.set(new EdgeDriver());
			}
			else {
				logger.error("Please provide chrome or edge browser only");
				System.out.println("Please provide chrome or edge browser only");
			}
		}
		
		public BrowserUtility(Browser browser,boolean isHeadless) {
			logger.info("Launching browser "+browser);
			
			if(browser==Browser.CHROME) {
				if(isHeadless) {
					ChromeOptions options=new ChromeOptions();
					options.addArguments("--headless=old");
					options.addArguments("--window-size=1920,1080");
					driver.set(new ChromeDriver(options));
				}
				else {
				driver.set(new ChromeDriver());
				}
			}
			else if(browser==Browser.EDGE) {
				if(isHeadless) {
					EdgeOptions options=new EdgeOptions();
					options.addArguments("--headless=old");
					options.addArguments("disable-gpu");
					driver.set(new EdgeDriver(options));
				}
				else {
				driver.set(new EdgeDriver());
				}
			}
			else {
				logger.error("Please provide chrome or edge browser only");
				System.out.println("Please provide chrome or edge browser only");
			}
		}
		
		public void goToWebsite(String url) {
			logger.info("Visiting the website "+url);
			driver.get().get(url);
		}
		
		public void maximizeWindow() {
			logger.info("Maximizing the browser window");
			driver.get().manage().window().maximize();
		}
		
		public void clickOn(By locator) {
			logger.info("Finding element with the locator "+locator);
			WebElement element=driver.get().findElement(locator);
			logger.info("Element found and now performing click");
			element.click();
			
		}
		
		public void enterText(By locator, String text) {
			logger.info("Finding element with the locator "+locator);
			WebElement element=driver.get().findElement(locator);
			logger.info("Element found and now enter the text "+text);
			element.sendKeys(text);
		}
		
		public String getVisibleText(By locator) {
			logger.info("Finding element with the locator "+locator);
			WebElement element=driver.get().findElement(locator);
			logger.info("Element found and now returning the texgt :"+element.getText());
			return element.getText();
		}
		
		public static String takeScreenshot(String name) {
			TakesScreenshot screenshot=(TakesScreenshot) driver.get();
			File screenshotData=screenshot.getScreenshotAs(OutputType.FILE);
			Date date=new Date();
			SimpleDateFormat format=new SimpleDateFormat("HH-mm--ss");
			String timeStamp=format.format(date);
			String path="/.screenshot/"+name+" - "+timeStamp+".png";
			File screenshotFile=new File(path);
			
			try {
				FileUtils.copyFile(screenshotData, screenshotFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return path;
			
		}
		
		public static void quit() {
			driver.get().close();
		}
}
