package pageObjects.Navigation;

import org.openqa.selenium.WebDriver;

import commons.basePage;
import pageObjects.user.AboutUsPageObjects;
import pageObjects.user.MyAccountPageObjects;
import pageObjects.user.SiteMapPageObjects;
import pageObjects.user.UserLoginPageObjects;
import pageUIs.navigation.FooterContainerPageUI;

public class FooterContainerPageObjects extends basePage {
	WebDriver driver;
	
	public FooterContainerPageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	public AboutUsPageObjects openAboutUSPage() {
		waitForElementClickable(driver, FooterContainerPageUI.ABOUT_US_LINK);
		clickToElement(driver,  FooterContainerPageUI.ABOUT_US_LINK);
		return PageGeneratorManager.getAboutUSPage(driver);
	}
	
	public SiteMapPageObjects openSiteMapPage() {
		waitForElementClickable(driver, FooterContainerPageUI.SITE_MAP_LINK);
		clickToElement(driver,  FooterContainerPageUI.SITE_MAP_LINK);
		return PageGeneratorManager.getSiteMapPage(driver);
	}
	
	public MyAccountPageObjects openMyAccountPageLogged() {
		waitForElementClickable(driver, FooterContainerPageUI.MY_ACCOUNT_LINK);
		clickToElement(driver,  FooterContainerPageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getMyAccountPage(driver);
	}
	
	public UserLoginPageObjects openMyAccountPageNotLogged() {
		waitForElementClickable(driver, FooterContainerPageUI.MY_ACCOUNT_LINK);
		clickToElement(driver,  FooterContainerPageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}
}
