package com.techpanda.cookie;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.baseTest;
import pageObjects.Navigation.PageGeneratorManager;
import pageObjects.user.AccountInformationPageObjects;
import pageObjects.user.MyDashboardPageObjects;
import pageObjects.user.UserHomePageObjects;
import pageObjects.user.UserLoginPageObjects;

public class Cookie_Login extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	UserHomePageObjects homePage;
	UserLoginPageObjects loginPage;
	MyDashboardPageObjects myDashboardPage;
	AccountInformationPageObjects accountInformationPage;
	String emailAddress, password;
	static Set<Cookie> loginCookies;
	
	// Khai báo + Khởi tạo = Declare + Initial
	String osName = System.getProperty("os.name");
  
	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = getBrowserDriver(browserName);
		emailAddress = "afc" + getRandomNumber() + "@mail.vn";
		
		// Khởi tạo HomePageObject để dùng
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		emailAddress = "automationfc.vn@gmail.com";
		password = "123123";
		
		log.info("Login_06: Navigate to login page");
		loginPage = homePage.clickToMyAccountLink();
		
		log.info("Login_06: Input 'Email' field with value " + emailAddress);
		loginPage.inputToEmailTextbox("automationfc.vn@gmail.com");
		
		log.info("Login_06: Input 'Password' field with value " + password);
		loginPage.inputToPasswordTextbox("123123");
		myDashboardPage = loginPage.clickToLoginButton();	
		
		log.info("Login_06: Verify contact infor is displayed");
		Assert.assertTrue(myDashboardPage.getInfoContactText("Automation FC"));
		Assert.assertTrue(myDashboardPage.getInfoContactText("automationfc.vn@gmail.com"));
		
		loginCookies = myDashboardPage.getCookies(driver);
		driver.quit();
	  }

}

