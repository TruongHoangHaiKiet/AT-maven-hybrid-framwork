package com.techpanda.account;

import org.testng.annotations.Test;

import commons.baseTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import pageObjects.Navigation.PageGeneratorManager;
import pageObjects.user.AccountInformationPageObjects;
import pageObjects.user.UserHomePageObjects;
import pageObjects.user.UserLoginPageObjects;
import pageObjects.user.MyDashboardPageObjects;

public class Level_15_Logs_ReportNG extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	UserHomePageObjects homePage;
	UserLoginPageObjects loginPage;
	MyDashboardPageObjects myDashboardPage;
	AccountInformationPageObjects accountInformationPage;
	String emailAddress, password;
	
	// Khai báo + Khởi tạo = Declare + Initial
	String osName = System.getProperty("os.name");
  
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		emailAddress = "afc" + getRandomNumber() + "@mail.vn";
		
		// Khởi tạo HomePageObject để dùng
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		emailAddress = "automationfc.vn@gmail.com";
		password = "123123";
	  }

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		// Home Page > click My account > Login Page
		log.info("Login_01: Navigate to login page");
		loginPage = homePage.clickToMyAccountLink();
		 
		log.info("Login_01: Input 'Email' field with blank text");
		loginPage.inputToEmailTextbox("");
		
		log.info("Login_01: Input 'Password' field with blank text");
		loginPage.inputToPasswordTextbox("");
		
		log.info("Login_01: Click login button");
		loginPage.clickToLoginButton();
		
		log.info("Login_01: Verify error message with blank email");
		Assert.assertEquals(loginPage.getEmailEmptyErrorMessage(), "This is a required field....");
		
		log.info("Login_01: Verify error message with blank password");
		Assert.assertEquals(loginPage.getPasswordEmptyErrorMessage(), "This is a required field.");
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		// Home Page > click My account > Login Page
		log.info("Login_02: Navigate to login page");
		loginPage = homePage.clickToMyAccountLink();
		
		log.info("Login_02: Input 'Email' field with invalid email");
		loginPage.inputToEmailTextbox("123@456.789");
		
		log.info("Login_02: Input 'Password' field with valid password");
		loginPage.inputToPasswordTextbox("123456");
		
		log.info("Login_02: Click login button");
		loginPage.clickToLoginButton();
		
		log.info("Login_02: Verify Email invalid error message");
		Assert.assertEquals(loginPage.getEmailInvalidErrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_LoginWithIncorrectEmail() {
		log.info("Login_03: Navigate to login page");
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailIncorrectErrorMessage(), "Invalid login or password.");
	}

	@Test
	public void TC_04_LoginWithInvalidPassword() {
		log.info("Login_04: Navigate to login page");
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getPasswordInvalidErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		log.info("Login_05: Navigate to login page");
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox(getRandomNumber() + "");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getPasswordIncorrectErrorMessage(), "Invalid login or password.");
	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		log.info("Login_06: Navigate to login page");
		loginPage = homePage.clickToMyAccountLink();
		
		log.info("Login_06: Input 'Email' field with value " + emailAddress);
		loginPage.inputToEmailTextbox("automationfc.vn@gmail.com");
		
		log.info("Login_06: Input 'Password' field with value " + password);
		loginPage.inputToPasswordTextbox("123123");
		myDashboardPage = loginPage.clickToLoginButton();	
		
		log.info("Login_06: Verify contact infor is displayed");
		Assert.assertFalse(myDashboardPage.getInfoContactText("Automation FC"));
		Assert.assertTrue(myDashboardPage.getInfoContactText("automationfc.vn@gmail.com"));
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

