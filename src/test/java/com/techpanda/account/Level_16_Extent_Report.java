package com.techpanda.account;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.baseTest;
import pageObjects.Navigation.PageGeneratorManager;
import pageObjects.user.AccountInformationPageObjects;
import pageObjects.user.MyDashboardPageObjects;
import pageObjects.user.UserHomePageObjects;
import pageObjects.user.UserLoginPageObjects;
import reportConfig.ExtentManager;

public class Level_16_Extent_Report extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	UserHomePageObjects homePage;
	UserLoginPageObjects loginPage;
	MyDashboardPageObjects myDashboardPage;
	AccountInformationPageObjects accountInformationPage;
	String emailAddress, password, browserName;
	
	// Khai báo + Khởi tạo = Declare + Initial
	String osName = System.getProperty("os.name");
  
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		this.browserName = browserName.toUpperCase();
		driver = getBrowserDriver(browserName);
		emailAddress = "afc" + getRandomNumber() + "@mail.vn";
		
		// Khởi tạo HomePageObject để dùng
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		emailAddress = "automationfc.vn@gmail.com";
		password = "123123";
	  }

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword(Method method) {
		ExtentManager.startTest(method.getName() + "_" + browserName, "TC_01_LoginWithEmptyEmailAndPassword");
		// Home Page > click My account > Login Page
		ExtentManager.getTest().log(Status.INFO, "Login_01 - Step 01: Click to My Account");
		loginPage = homePage.clickToMyAccountLink();
		
		ExtentManager.getTest().log(Status.INFO, "Login_01 - Step 02: Fill with empty Email");
		loginPage.inputToEmailTextbox("");
		
		ExtentManager.getTest().log(Status.INFO, "Login_01 - Step 03: Fill with empty Password");
		loginPage.inputToPasswordTextbox("");
		
		ExtentManager.getTest().log(Status.INFO, "Login_01 - Step 04: Click on Login button");
		loginPage.clickToLoginButton();
		
		ExtentManager.getTest().log(Status.INFO, "Login_01 - Step 05: Verify Error messagge with empty values ");
		Assert.assertEquals(loginPage.getEmailEmptyErrorMessage(), "This is a required field....");
		Assert.assertEquals(loginPage.getPasswordEmptyErrorMessage(), "This is a required field.");
	}

	@Test
	public void TC_02_LoginWithInvalidEmail(Method method) {
		// Home Page > click My account > Login Page
		ExtentManager.startTest(method.getName() + "_" + browserName, "TC_02_LoginWithInvalidEmail");
		ExtentManager.getTest().log(Status.INFO, "Login_02 - Step 01: Click to My Account");
		loginPage = homePage.clickToMyAccountLink();
		
		ExtentManager.getTest().log(Status.INFO, "Login_02 - Step 02: Fill with invalid Email");
		loginPage.inputToEmailTextbox("123@456.789");
		
		ExtentManager.getTest().log(Status.INFO, "Login_02 - Step 03: Fill with valid Password");
		loginPage.inputToPasswordTextbox("123456");
		
		ExtentManager.getTest().log(Status.INFO, "Login_02 - Step 04: Click on Login button");
		loginPage.clickToLoginButton();
		
		ExtentManager.getTest().log(Status.INFO, "Login_02 - Step 05: Verify Error messagge with invalid Email ");
		Assert.assertEquals(loginPage.getEmailInvalidErrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_LoginWithIncorrectEmail(Method method) {
		ExtentManager.startTest(method.getName() + "_" + browserName, "TC_03_LoginWithIncorrectEmail");
		ExtentManager.getTest().log(Status.INFO, "Login_03 - Step 01: Click to My Account");
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailIncorrectErrorMessage(), "Invalid login or password.");
	}

	@Test
	public void TC_04_LoginWithInvalidPassword(Method method) {
		ExtentManager.startTest(method.getName() + "_" + browserName, "TC_04_LoginWithInvalidPassword");
		ExtentManager.getTest().log(Status.INFO, "Login_04 - Step 01: Click to My Account");
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getPasswordInvalidErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_05_LoginWithIncorrectPassword(Method method) {
		ExtentManager.startTest(method.getName() + "_" + browserName, "TC_05_LoginWithIncorrectPassword");
		ExtentManager.getTest().log(Status.INFO, "Login_05 - Step 01: Click to My Account");
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox(getRandomNumber() + "");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getPasswordIncorrectErrorMessage(), "Invalid login or password.");
	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword(Method method) {
		ExtentManager.startTest(method.getName() + "_" + browserName, "TC_06_LoginWithValidEmailAndPassword");
		ExtentManager.getTest().log(Status.INFO, "Login_06 - Step 01: Click to My Account");
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("automationfc.vn@gmail.com");
		loginPage.inputToPasswordTextbox("123123");
		myDashboardPage = loginPage.clickToLoginButton();	
		
		ExtentManager.getTest().log(Status.INFO, "Login_06 - Step 05: Verify get Display Name contact text");
		Assert.assertFalse(myDashboardPage.getInfoContactText("Automation FC"));
		
		ExtentManager.getTest().log(Status.INFO, "Login_06 - Step 06: Verify get Email contact text");
		Assert.assertTrue(myDashboardPage.getInfoContactText("automationfc.vn@gmail.com"));
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

