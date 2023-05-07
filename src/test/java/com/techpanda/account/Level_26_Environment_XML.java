package com.techpanda.account;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.baseTest;
import data.techpanda.AccountDataJson;
import pageObjects.Navigation.PageGeneratorManager;
import pageObjects.user.AccountInformationPageObjects;
import pageObjects.user.MyDashboardPageObjects;
import pageObjects.user.UserHomePageObjects;
import pageObjects.user.UserLoginPageObjects;
import pageObjects.user.UserRegisterPageObject;

public class Level_26_Environment_XML extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	AccountDataJson accountDataJson;
	UserHomePageObjects homePage;
	UserLoginPageObjects loginPage;
	MyDashboardPageObjects myDashboardPage;
	AccountInformationPageObjects accountInformationPage;
	UserRegisterPageObject registerPage;
	String emailAddress;
	
	// Khai báo + Khởi tạo = Declare + Initial
	String osName = System.getProperty("os.name");
//	String url = PropertiesConfig.getProperties().getValue("UserURL");
	@Parameters({"browser","server", "role"})
	@BeforeClass
	public void beforeClass(String browserName, String server, String role) {
		driver = getBrowserDriver(browserName, server, role);
		accountDataJson = AccountDataJson.getAccountData();
		
		// Khởi tạo HomePageObject để dùng
		homePage = PageGeneratorManager.getUserHomePage(driver);
		emailAddress = accountDataJson.getEmailAddress()  + getRandomNumber() + accountDataJson.getWebMail();
		
	  }

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		// Home Page > click My account > Login Page
		log.info("Login_01: Navigate to login page");
		loginPage = homePage.getFooterContainerPage(driver).openMyAccountPageNotLogged();
		 
		log.info("Login_01: Input 'Email' field with blank text");
		loginPage.inputToTextboxByID(driver, "email", "");
		
		log.info("Login_01: Input 'Password' field with blank text");
		loginPage.inputToTextboxByID(driver, "pass", "");
		
		log.info("Login_01: Click login button");
		loginPage.clickButtonByTitle(driver, "Login");
		
		log.info("Login_01: Verify error message with blank email");
		Assert.assertEquals(loginPage.getFieldErrorMessageByID(driver, "advice-required-entry-email"), "This is a required field.");
		
		log.info("Login_01: Verify error message with blank password");
		Assert.assertEquals(loginPage.getFieldErrorMessageByID(driver, "advice-required-entry-pass"), "This is a required field.");
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		// Home Page > click My account > Login Page
		log.info("Login_02: Navigate to login page");
		loginPage = homePage.getFooterContainerPage(driver).openMyAccountPageNotLogged();
		
		log.info("Login_02: Input 'Email' field with invalid email");
		loginPage.inputToTextboxByID(driver, "email", "123@456.789");
		
		log.info("Login_02: Input 'Password' field with valid password");
		loginPage.inputToTextboxByID(driver, "pass", accountDataJson.getPassWord());
		
		log.info("Login_02: Click login button");
		loginPage.clickButtonByTitle(driver, "Login");
		
		log.info("Login_02: Verify Email invalid error message");
		Assert.assertEquals(loginPage.getFieldErrorMessageByID(driver, "advice-validate-email-email"), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_LoginWithIncorrectEmail() {
		log.info("Login_03: Navigate to login page");
		loginPage = homePage.getFooterContainerPage(driver).openMyAccountPageNotLogged();
		
		loginPage.inputToTextboxByID(driver, "email", emailAddress);
		loginPage.inputToTextboxByID(driver, "pass", accountDataJson.getPassWord());
		loginPage.clickButtonByTitle(driver, "Login");
		
		Assert.assertEquals(loginPage.getPageErrorMessage(driver), "Invalid login or password.");
	}

	@Test
	public void TC_04_LoginWithInvalidPassword() {
		log.info("Login_04: Navigate to login page");
		loginPage = homePage.getFooterContainerPage(driver).openMyAccountPageNotLogged();
		
		loginPage.inputToTextboxByID(driver, "email", emailAddress);
		loginPage.inputToTextboxByID(driver, "pass", "123");
		loginPage.clickButtonByTitle(driver, "Login");
		
		Assert.assertEquals(loginPage.getFieldErrorMessageByID(driver, "advice-validate-password-pass"), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_05_Register_Success() {
		log.info("Login_05: Navigate to login page");
		loginPage = homePage.getFooterContainerPage(driver).openMyAccountPageNotLogged();
		loginPage.clickButtonByTitle(driver, "Create an Account");
		registerPage = PageGeneratorManager.getUserRegisterPage(driver);
		
		registerPage.inputToTextboxByID(driver, "firstname", accountDataJson.getFirstName());
		registerPage.inputToTextboxByID(driver, "lastname",accountDataJson.getLastName());
		registerPage.inputToTextboxByID(driver, "email_address", emailAddress);
		registerPage.inputToTextboxByID(driver, "password", accountDataJson.getPassWord());
		System.out.println("Password = " + accountDataJson.getPassWord());
		registerPage.inputToTextboxByID(driver, "confirmation",accountDataJson.getPassWord());
		System.out.println("Confirm Password = " + accountDataJson.getPassWord());
		registerPage.clickOnRegisterButton();
		
		homePage = registerPage.clickOnUserLogOutLink(driver);
		
	}
	
	@Test
	public void TC_06_LoginWithIncorrectPassword() {
		log.info("Login_06: Navigate to login page");
		loginPage = homePage.getFooterContainerPage(driver).openMyAccountPageNotLogged();
		
		loginPage.inputToTextboxByID(driver, "email", emailAddress);
		loginPage.inputToTextboxByID(driver, "pass", getRandomNumber() + "");
		loginPage.clickButtonByTitle(driver, "Login");
		
		Assert.assertEquals(loginPage.getPageErrorMessage(driver), "Invalid login or password.");
	}

	@Test
	public void TC_07_LoginWithValidEmailAndPassword() {
		log.info("Login_07: Navigate to login page");
		loginPage = homePage.getFooterContainerPage(driver).openMyAccountPageNotLogged();
		
		log.info("Login_07: Input 'Email' field with value " + emailAddress);
		loginPage.inputToTextboxByID(driver, "email", emailAddress);
		
		log.info("Login_07: Input 'Password' field with value " + accountDataJson.getPassWord());
		loginPage.inputToTextboxByID(driver, "pass", accountDataJson.getPassWord());
		loginPage.clickButtonByTitle(driver, "Login");
		myDashboardPage = PageGeneratorManager.getMyDashboardPage(driver);	
		
		log.info("Login_07: Verify contact infor is displayed");
		Assert.assertTrue(myDashboardPage.getInfoContactText(accountDataJson.getFullName()));
		Assert.assertTrue(myDashboardPage.getInfoContactText(emailAddress));
		
	}
	

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

}

