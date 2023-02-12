package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

public class WordPressPageGeneratorManager {
	
	public static WordPressAdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new WordPressAdminDashboardPageObject(driver);
	}
	public static WordPressAdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new WordPressAdminLoginPageObject(driver);
	}
	public static WordPressAdminPostPageObject getAdminPostPage(WebDriver driver) {
		return new WordPressAdminPostPageObject(driver);
	}
	public static WordPressUserHomePageObject getUserHomePage(WebDriver driver) {
		return new WordPressUserHomePageObject(driver);
	}
	public static WordPressUserPostPageObject getUserPostPage(WebDriver driver) {
		return new WordPressUserPostPageObject(driver);
	}
	public static WordPressUserSearchPageObject getUserSearchPage(WebDriver driver) {
		return new WordPressUserSearchPageObject(driver);
	}

}
