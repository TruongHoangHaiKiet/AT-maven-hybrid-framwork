package pageObject.SauceLab;

import org.openqa.selenium.WebDriver;

import commons.basePage;
import pageUIs.SauceLab.LoginPageUI;

public class LoginPageObject extends basePage {
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver){
		this.driver = driver;
	}

	public void enterToUsernameTextbox(String username) {
		waitForElementVisible(driver, LoginPageUI.USER_NAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USER_NAME_TEXTBOX, username);	
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASS_WORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASS_WORD_TEXTBOX, password);	
	}

	public InventoryPageObject clickOnLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BTN);
		clickToElement(driver, LoginPageUI.LOGIN_BTN);
		return PageGeneratorManager.getInventoryPage(driver);
	}
	
	
}
