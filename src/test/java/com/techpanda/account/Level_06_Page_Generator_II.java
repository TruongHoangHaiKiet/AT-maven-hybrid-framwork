package com.techpanda.account;

import org.testng.annotations.Test;

import commons.baseTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import pageObjects.user.UserHomePageObjects;
import pageObjects.user.UserLoginPageObjects;
import pageObjects.user.MyDashboardPageObjects;

public class Level_06_Page_Generator_II extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	UserHomePageObjects homePage;
	UserLoginPageObjects loginPage;
	MyDashboardPageObjects myDashboardPage;
	
	// Khai báo + Khởi tạo = Declare + Initial
	String osName = System.getProperty("os.name");
	String emailAddress;
  
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		emailAddress = "afc" + randomNumber() + "@mail.vn";
		
		// Khởi tạo HomePageObject để dùng
		homePage = new UserHomePageObjects(driver);
	  }

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		// Home Page > click My account > Login Page
		loginPage = homePage.clickToMyAccountLink();
		 
		loginPage.inputToEmailTextbox("");
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();
	
		assertEquals(loginPage.getEmailEmptyErrorMessage(), "This is a required field.");
		assertEquals(loginPage.getPasswordEmptyErrorMessage(), "This is a required field.");
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		// Home Page > click My account > Login Page
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("123@456.789");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();
		
		assertEquals(loginPage.getEmailInvalidErrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_LoginWithIncorrectEmail() {
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();

		assertEquals(loginPage.getEmailIncorrectErrorMessage(), "Invalid login or password.");
	}

	@Test
	public void TC_04_LoginWithInvalidPassword() {
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToLoginButton();

		assertEquals(loginPage.getPasswordInvalidErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox(randomNumber() + "");
		loginPage.clickToLoginButton();
		
		assertEquals(loginPage.getPasswordIncorrectErrorMessage(), "Invalid login or password.");
	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("automationfc.vn@gmail.com");
		loginPage.inputToPasswordTextbox("123123");
		myDashboardPage = loginPage.clickToLoginButton();	
		
		assertTrue(myDashboardPage.getInfoContactText("Automation FC"));
		assertTrue(myDashboardPage.getInfoContactText("automationfc.vn@gmail.com"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
}

