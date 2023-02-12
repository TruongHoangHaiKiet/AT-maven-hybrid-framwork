package pageObjects.Navigation;

import org.openqa.selenium.WebDriver;

import commons.basePage;
import pageObjects.user.AccountInformationPageObjects;
import pageObjects.user.MyApplicationPageObjects;
import pageObjects.user.MyDashboardPageObjects;
import pageObjects.user.MyOrderPageObjects;
import pageObjects.user.MyProductReviewPageObjects;
import pageUIs.navigation.SideBarMyAccountPageUI;

public class SideBarMyAccountPageObjects extends basePage {
	WebDriver driver;
	
	public SideBarMyAccountPageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	// Mỗi page chỉ có 1 hàm mở page đại diện
		public MyOrderPageObjects openMyOrderPage() {
			waitForElementClickable(driver, SideBarMyAccountPageUI.MY_ORDER_LINK);
			clickToElement(driver,  SideBarMyAccountPageUI.MY_ORDER_LINK);
			return PageGeneratorManager.getMyOrderPage(driver);
		}

		public MyApplicationPageObjects openMyApplicationPage() {
			waitForElementClickable(driver, SideBarMyAccountPageUI.MY_APPLICATIONS_LINK);
			clickToElement(driver, SideBarMyAccountPageUI.MY_APPLICATIONS_LINK);
			return PageGeneratorManager.getMyApplicationPage(driver);
		}

		public MyProductReviewPageObjects openMyProductReviewPage() {
			waitForElementClickable(driver, SideBarMyAccountPageUI.MY_PRODUCT_REVIEW_LINK);
			clickToElement(driver, SideBarMyAccountPageUI.MY_PRODUCT_REVIEW_LINK);
			return PageGeneratorManager.getMyProductReviewPage(driver);
		}

		public AccountInformationPageObjects openAccountInformationPage() {
			waitForElementClickable(driver, SideBarMyAccountPageUI.ACCOUNT_INFORMATION_LINK);
			clickToElement(driver, SideBarMyAccountPageUI.ACCOUNT_INFORMATION_LINK);
			return PageGeneratorManager.getAccountInformationPage(driver);
		}
		
		public MyDashboardPageObjects openOpenMyDashboardPage() {
			waitForElementClickable(driver, SideBarMyAccountPageUI.MY_DASHBOARD_LINK);
			clickToElement(driver, SideBarMyAccountPageUI.MY_DASHBOARD_LINK);
			return PageGeneratorManager.getMyDashboardPage(driver);
		}
		
		// Có return: Dùng cho số lượng ít page
		public SideBarMyAccountPageObjects openSideBarLinkByPageNames(String pageName) {
			waitForElementClickable(driver,SideBarMyAccountPageUI.DYNAMIC_SIDEBAR_LINK, pageName);
			clickToElement(driver,SideBarMyAccountPageUI.DYNAMIC_SIDEBAR_LINK , pageName);
			if(pageName.equals("My Orders")) {
				return PageGeneratorManager.getMyOrderPage(driver);
			} else if (pageName.equals("My Applications")) {
				return PageGeneratorManager.getMyApplicationPage(driver);
			} else if (pageName.equals("My Product Reviews")) {
				return PageGeneratorManager.getMyProductReviewPage(driver);
			} else {
				return null;
			}
		}
		
		// Không có return về bất kỳ 1 page nào hết
		public void openSideBarLinkByPageName(String pageName) {
			waitForElementClickable(driver,SideBarMyAccountPageUI.DYNAMIC_SIDEBAR_LINK, pageName);
			clickToElement(driver,SideBarMyAccountPageUI.DYNAMIC_SIDEBAR_LINK , pageName);
		}
}
