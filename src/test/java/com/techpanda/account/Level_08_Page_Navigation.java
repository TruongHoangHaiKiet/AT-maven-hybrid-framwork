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

public class Level_08_Page_Navigation extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
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
		myOrderPage = myDashboardPage.openMyOrderPage();
		
		// My Order > My Application
		myApplicationPage = myOrderPage.openMyApplicationPage();
		
		// My Application > My Product Review
		myProductReview = myApplicationPage.openMyProductReviewPage();
		
		// My Product Review > My Order
		myOrderPage = myProductReview.openMyOrderPage();
		
		// My Order > My Product Review
		myProductReview = myOrderPage.openMyProductReviewPage();
		
		// My Product Review > My Application
		myApplicationPage = myProductReview.openMyApplicationPage();
	}
	
	@Test
	public void TC_03_Switch_Page_Footer() {
		// Tất cả các Class kế thừa basePage thì đều gọi ra các hàm mà basePage chia sẻ ra dùng được
		aboutUsPage = myApplicationPage.getFooterContainerPage(driver).openAboutUSPage();
		siteMapPage = aboutUsPage.openSiteMapPage();
		myAccountPage = siteMapPage.openMyAccountPageLogged();
		aboutUsPage = myAccountPage.openAboutUSPage();
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

