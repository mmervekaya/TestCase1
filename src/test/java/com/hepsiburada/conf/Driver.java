package com.hepsiburada.conf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Driver{

	public static WebDriver driver;
	public static WebDriverWait wait;
	
	@BeforeClass
	public static void chromeSetup(){
		 
		String path=System.getProperty("user.dir");
		String chromePath= path + "//src//test//resources//drivers//chromeDriver//chromedriver";
		System.setProperty("webdriver.chrome.driver", chromePath);
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver,30);
		
		driver.get("https://www.hepsiburada.com/");
}
	
	/*@AfterClass
	public static void closeBrowser() {
		driver.close();
	}*/
	
	
}
