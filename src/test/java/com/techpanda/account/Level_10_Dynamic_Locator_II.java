package com.techpanda.account;

import org.testng.annotations.Test;

import commons.baseTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

import pageObjects.Navigation.PageGeneratorManager;
import pageObjects.user.AboutUsPageObjects;
import pageObjects.user.AccountInformationPageObjects;
import pageObjects.user.UserHomePageObjects;
import pageObjects.user.UserLoginPageObjects;
import pageObjects.user.MyAccountPageObjects;
import pageObjects.user.MyApplicationPageObjects;
import pageObjects.user.MyDashboardPageObjects;
import pageObjects.user.MyOrderPageObjects;
import pageObjects.user.MyProductReviewPageObjects;
import pageObjects.user.SiteMapPageObjects;

public class Level_10_Dynamic_Locator_II extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	UserHomePageObjects homePage;
	UserLoginPageObjects loginPage;
	MyDashboardPageObjects myDashboardPage;
	AccountInformationPageObjects accountInformationPage;
	MyOrderPageObjects myOrderPage;
	MyApplicationPageObjects myApplicationPage;
	MyProductReviewPageObjects myProductReview;
	AboutUsPageObjects aboutUsPage;
	SiteMapPageObjects siteMapPage;
	MyAccountPageObjects myAccountPage;
	
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
	public void TC_01_LoginWithValidEmailAndPassword() {
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("automationfc.vn@gmail.com");
		loginPage.inputToPasswordTextbox("123123");
		myDashboardPage = loginPage.clickToLoginButton();	
		
		assertTrue(myDashboardPage.getInfoContactText("Automation FC"));
		assertTrue(myDashboardPage.getInfoContactText("automationfc.vn@gmail.com"));
	}
	
	@Test
	public void TC_02_Switch_Page_Sidebar() {
		// My Dashboard > My Order
		myOrderPage  = (MyOrderPageObjects) myDashboardPage.openSideBarLinkByPageNames("My Orders");
		
		// My Order > My Application
		myApplicationPage = (MyApplicationPageObjects) myOrderPage.openSideBarLinkByPageNames("My Applications");
		
		// My Application > My Product Review
		myProductReview = (MyProductReviewPageObjects) myApplicationPage.openSideBarLinkByPageNames("My Product Reviews");
		
		// My Product Review > My Order
		myOrderPage = (MyOrderPageObjects) myProductReview.openSideBarLinkByPageNames("My Orders");
		
		// My Order > My Product Review
		myProductReview = (MyProductReviewPageObjects) myOrderPage.openSideBarLinkByPageNames("My Product Reviews");
		
		// My Product Review > My Application
		myApplicationPage =(MyApplicationPageObjects) myProductReview.openSideBarLinkByPageNames("My Applications");
		
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

