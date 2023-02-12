package pageObjects.Navigation;

import org.openqa.selenium.WebDriver;

import pageObjects.admin.AdminLoginPageObjects;
import pageObjects.admin.AdminManageCustomerPageObjects;
import pageObjects.user.AboutUsPageObjects;
import pageObjects.user.AccountInformationPageObjects;
import pageObjects.user.MyAccountPageObjects;
import pageObjects.user.MyApplicationPageObjects;
import pageObjects.user.MyDashboardPageObjects;
import pageObjects.user.MyOrderPageObjects;
import pageObjects.user.MyProductReviewPageObjects;
import pageObjects.user.SiteMapPageObjects;
import pageObjects.user.UserHomePageObjects;
import pageObjects.user.UserLoginPageObjects;
import pageObjects.user.UserRegisterPageObject;

public class PageGeneratorManager {
	
	public static UserHomePageObjects getUserHomePage (WebDriver driver) {
		return new UserHomePageObjects(driver);
	}
	
	public static UserLoginPageObjects getUserLoginPage (WebDriver driver) {
		return new UserLoginPageObjects(driver);
	}
	
	public static UserRegisterPageObject getUserRegisterPage (WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	
	public static MyDashboardPageObjects getMyDashboardPage (WebDriver driver) {
		return new MyDashboardPageObjects(driver);
	}
	
	public static AccountInformationPageObjects getAccountInformationPage (WebDriver driver) {
		return new AccountInformationPageObjects(driver);
	}
	
	public static MyApplicationPageObjects getMyApplicationPage (WebDriver driver) {
		return new MyApplicationPageObjects(driver);
	}
	
	public static MyOrderPageObjects getMyOrderPage (WebDriver driver) {
		return new MyOrderPageObjects(driver);
	}
	
	public static MyProductReviewPageObjects getMyProductReviewPage (WebDriver driver) {
		return new MyProductReviewPageObjects(driver);
	}
	
	public static AboutUsPageObjects getAboutUSPage (WebDriver driver) {
		return new AboutUsPageObjects(driver);
	}
	
	public static SiteMapPageObjects getSiteMapPage (WebDriver driver) {
		return new SiteMapPageObjects(driver);
	}
	
	public static MyAccountPageObjects getMyAccountPage (WebDriver driver) {
		return new MyAccountPageObjects(driver);
	}
	
	public static AdminLoginPageObjects getAdminLoginPage (WebDriver driver) {
		return new AdminLoginPageObjects(driver);
	}
	
	public static AdminManageCustomerPageObjects getAdminManageCustomerPage (WebDriver driver) {
		return new AdminManageCustomerPageObjects(driver);
	}
}



