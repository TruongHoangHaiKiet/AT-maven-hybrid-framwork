package com.techpanda.cookie;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.baseTest;
import pageObjects.Navigation.PageGeneratorManager;
import pageObjects.user.AccountInformationPageObjects;
import pageObjects.user.MyDashboardPageObjects;
import pageObjects.user.UserHomePageObjects;
import pageObjects.user.UserLoginPageObjects;

public class Payment_01_Order_Product extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
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
		
		loginPage = homePage.clickToMyAccountLink();
		loginPage.setCookies(driver, Cookie_Login.loginCookies);
		
		myDashboardPage = PageGeneratorManager.getMyDashboardPage(driver);
		Assert.assertTrue(myDashboardPage.getInfoContactText("Automation FC"));
		Assert.assertTrue(myDashboardPage.getInfoContactText("automationfc.vn@gmail.com"));
	  }

	@Test
	public void Order_01() {
		
	}

	@Test
	public void Order_02() {
		
	}

	@Test
	public void Order_03() {
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

