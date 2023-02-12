package com.techpanda.account;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.baseTest;
import io.qameta.allure.Description;
import pageObjects.Navigation.PageGeneratorManager;
import pageObjects.user.AccountInformationPageObjects;
import pageObjects.user.MyDashboardPageObjects;
import pageObjects.user.UserHomePageObjects;
import pageObjects.user.UserLoginPageObjects;

public class Level_17_Allure_Report extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call
														// data nữa
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
	
	@Description("Login With Empty Email And Password")
	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword(Method method) {
		// Home Page > click My account > Login Page
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("");

		loginPage.inputToPasswordTextbox("");

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailEmptyErrorMessage(), "This is a required field....");
		Assert.assertEquals(loginPage.getPasswordEmptyErrorMessage(), "This is a required field.");
	}
	
	@Description("Login With Invalid Email")
	@Test
	public void TC_02_LoginWithInvalidEmail(Method method) {
		// Home Page > click My account > Login Page
		loginPage = homePage.clickToMyAccountLink();

		loginPage.inputToEmailTextbox("123@456.789");

		loginPage.inputToPasswordTextbox("123456");

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailInvalidErrorMessage(),"Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Description("Login With Incorrect Email")
	@Test
	public void TC_03_LoginWithIncorrectEmail(Method method) {
		loginPage = homePage.clickToMyAccountLink();

		loginPage.inputToEmailTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailIncorrectErrorMessage(), "Invalid login or password.");
	}
	
	@Description("Login With Invalid Password")
	@Test
	public void TC_04_LoginWithInvalidPassword(Method method) {
		loginPage = homePage.clickToMyAccountLink();

		loginPage.inputToEmailTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getPasswordInvalidErrorMessage(),"Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Description("Login With Incorrect Password")
	@Test
	public void TC_05_LoginWithIncorrectPassword(Method method) {
		loginPage = homePage.clickToMyAccountLink();

		loginPage.inputToEmailTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox(getRandomNumber() + "");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getPasswordIncorrectErrorMessage(), "Invalid login or password.");
	}
	
	@Description("Login With Valid Email And Password")
	@Test
	public void TC_06_LoginWithValidEmailAndPassword(Method method) {
		loginPage = homePage.clickToMyAccountLink();

		loginPage.inputToEmailTextbox("automationfc.vn@gmail.com");
		loginPage.inputToPasswordTextbox("123123");
		myDashboardPage = loginPage.clickToLoginButton();

		Assert.assertFalse(myDashboardPage.getInfoContactText("Automation FC"));
		Assert.assertTrue(myDashboardPage.getInfoContactText("automationfc.vn@gmail.com"));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
