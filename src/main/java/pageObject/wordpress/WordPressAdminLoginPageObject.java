package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import commons.basePage;
import pageUIs.wordpress.WordPressAdminLoginPageUI;

public class WordPressAdminLoginPageObject extends basePage {
	WebDriver driver;
	
	public WordPressAdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToUsernameTextbox(String username) {
		waitForElementVisible(driver, WordPressAdminLoginPageUI.LOGIN_TEXTBOX);
		sendkeyToElement(driver, WordPressAdminLoginPageUI.LOGIN_TEXTBOX, username);	
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, WordPressAdminLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, WordPressAdminLoginPageUI.PASSWORD_TEXTBOX, password);	
	}
	
	public WordPressAdminDashboardPageObject clickOnLoginButton() {
		waitForElementClickable(driver, WordPressAdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, WordPressAdminLoginPageUI.LOGIN_BUTTON);
		return WordPressPageGeneratorManager.getAdminDashboardPage(driver);
	}

}
