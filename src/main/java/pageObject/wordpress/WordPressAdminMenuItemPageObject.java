package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import commons.basePage;
import pageUIs.wordpress.WordPressAdminMenuItemPageUI;

public class WordPressAdminMenuItemPageObject extends basePage {
	WebDriver driver;
	
	public WordPressAdminMenuItemPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public WordPressAdminPostPageObject clickOnPostLink() {
		waitForElementClickable(driver, WordPressAdminMenuItemPageUI.POST_MENU_LINK);
		clickToElement(driver, WordPressAdminMenuItemPageUI.POST_MENU_LINK);
		return WordPressPageGeneratorManager.getAdminPostPage(driver);
	}
	
	public WordPressUserHomePageObject openWordPressUserHomePage(String userUrl) {
		openPageUrl(driver, userUrl);
		return WordPressPageGeneratorManager.getUserHomePage(driver);
	}
	
	public WordPressAdminDashboardPageObject openWordPressAdminDashboardPage(String adminUrl) {
		openPageUrl(driver, adminUrl);
		return WordPressPageGeneratorManager.getAdminDashboardPage(driver);
	}
}
