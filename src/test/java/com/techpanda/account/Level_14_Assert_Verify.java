package com.techpanda.account;

import org.testng.annotations.Test;

import commons.baseTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

import pageObjects.Navigation.PageGeneratorManager;
import pageObjects.user.AccountInformationPageObjects;
import pageObjects.user.UserHomePageObjects;
import pageObjects.user.UserLoginPageObjects;
import pageObjects.user.MyDashboardPageObjects;

public class Level_14_Assert_Verify extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	UserHomePageObjects homePage;
	UserLoginPageObjects loginPage;
	MyDashboardPageObjects myDashboardPage;
	AccountInformationPageObjects accountInformationPage;
	
	// Khai báo + Khởi tạo = Declare + Initial
	String osName = System.getProperty("os.name");
	String emailAddress;
  
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		emailAddress = "afc" + getRandomNumber() + "@mail.vn";
		
		// Khởi tạo HomePageObject để dùng
		homePage = PageGeneratorManager.getUserHomePage(driver);
	  }

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		// Home Page > click My account > Login Page
		loginPage = homePage.clickToMyAccountLink();
		 
		loginPage.inputToEmailTextbox("");
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();
	
		verifyEquals(loginPage.getEmailEmptyErrorMessage(), "This is a required field....");
		
		verifyEquals(loginPage.getPasswordEmptyErrorMessage(), "This is a required field.");
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		// Home Page > click My account > Login Page
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("123@456.789");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();
		
		verifyEquals(loginPage.getEmailInvalidErrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_LoginWithIncorrectEmail() {
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();

		verifyEquals(loginPage.getEmailIncorrectErrorMessage(), "Invalid login or password.");
	}

	@Test
	public void TC_04_LoginWithInvalidPassword() {
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToLoginButton();

		verifyEquals(loginPage.getPasswordInvalidErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox(getRandomNumber() + "");
		loginPage.clickToLoginButton();
		
		verifyEquals(loginPage.getPasswordIncorrectErrorMessage(), "Invalid login or password.");
	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("automationfc.vn@gmail.com");
		loginPage.inputToPasswordTextbox("123123");
		myDashboardPage = loginPage.clickToLoginButton();	
		
		verifyTrue(myDashboardPage.getInfoContactText("Automation FC..."));
		verifyTrue(myDashboardPage.getInfoContactText("automationfc.vn@gmail.com..."));
		verifyTrue(myDashboardPage.getInfoContactText("automationfc.vn@gmail.com"));
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

