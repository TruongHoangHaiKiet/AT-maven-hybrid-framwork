package com.techpanda.account;

import org.testng.annotations.Test;

import commons.GlobalConstants;
import commons.baseTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

import pageObjects.Navigation.PageGeneratorManager;
import pageObjects.admin.AdminLoginPageObjects;
import pageObjects.admin.AdminManageCustomerPageObjects;
import pageObjects.user.AccountInformationPageObjects;
import pageObjects.user.UserHomePageObjects;
import pageObjects.user.UserLoginPageObjects;
import pageObjects.user.MyApplicationPageObjects;
import pageObjects.user.MyDashboardPageObjects;
import pageObjects.user.MyOrderPageObjects;
import pageObjects.user.MyProductReviewPageObjects;

public class Level_11_Global_Constants extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	UserHomePageObjects userHomePage;
	UserLoginPageObjects userLoginPage;
	AdminLoginPageObjects adminLoginPage;
	AdminManageCustomerPageObjects adminManageCustomerPage;
	MyDashboardPageObjects myDashboardPage;
	AccountInformationPageObjects accountInformationPage;
	MyOrderPageObjects myOrderPage;
	MyApplicationPageObjects myApplicationPage;
	MyProductReviewPageObjects myProductReview;
	String userUrl, adminUrl;
	
	// Khai báo + Khởi tạo = Declare + Initial
	String osName = System.getProperty("os.name");
	String emailAddress;
  
	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		this.userUrl = GlobalConstants.LIVE_USER_URL;
		this.adminUrl = GlobalConstants.LIVE_ADMIN_URL;
		
		driver = getBrowserDriver(browserName,userUrl) ;
		emailAddress = "afc" + getRandomNumber() + "@mail.vn";
		
		// Khởi tạo HomePageObject để dùng
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
	  }
	
	@Test
	public void TC_01_Switch_Roles() {
		userLoginPage = userHomePage.clickToMyAccountLink();
		
		userLoginPage.inputToEmailTextbox("kietautomation@gmail.com");
		userLoginPage.inputToPasswordTextbox("123123");
		myDashboardPage = userLoginPage.clickToLoginButton();	
		
		assertTrue(myDashboardPage.getInfoContactText("kiet automation"));
		assertTrue(myDashboardPage.getInfoContactText("kietautomation@gmail.com"));
		
		// Logout to Homepage
		userHomePage = myDashboardPage.clickOnUserLogOutLink(driver);
		
		//	User > Admin > Login(Admin)
		adminLoginPage = userHomePage.openAdminLoginPage(driver, adminUrl);
		
		adminLoginPage.enterToUserNameTextbox("user01");
		adminLoginPage.enterToPasswordTextbox("guru99com");
		adminManageCustomerPage = adminLoginPage.clickOnLoginButton();
		
		adminManageCustomerPage.closeIncomingMessagePopup();
		adminLoginPage = adminManageCustomerPage.clickOnAdminLogOutLink(driver);
		
		// Admin > User
		userHomePage = adminLoginPage.openUserHomePage(driver, userUrl);
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

