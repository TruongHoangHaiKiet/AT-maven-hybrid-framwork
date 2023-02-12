package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.basePage;
import pageObjects.Navigation.PageGeneratorManager;
import pageUIs.admin.AdminLoginPageUI;

public class AdminLoginPageObjects extends basePage {
	WebDriver driver;
	
	public AdminLoginPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUserNameTextbox(String username) {
		waitForElementVisible(driver, AdminLoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.USERNAME_TEXTBOX, username);
		
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public AdminManageCustomerPageObjects clickOnLoginButton() {
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BTN);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BTN);
		return PageGeneratorManager.getAdminManageCustomerPage(driver);
	}
	
}
