package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

import commons.basePage;
import pageUIs.facebook.LoginPageUIs;

public class LoginPageObjects extends basePage {
	WebDriver driver;
	
	public LoginPageObjects (WebDriver driver){
		this.driver = driver;
	}
	
	public void clickToRegisterButton() {
		waitForElementClickable(driver, LoginPageUIs.CREATE_NEW_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUIs.CREATE_NEW_ACCOUNT_BUTTON);
	}
	
	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, LoginPageUIs.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUIs.EMAIL_TEXTBOX, emailAddress);
	}
	
	public void closeSignUpForm() {
		waitForElementClickable(driver, LoginPageUIs.CLOSE_SIGN_UP_POPUP);
		clickToElement(driver, LoginPageUIs.CLOSE_SIGN_UP_POPUP);
	}
	
	public void waitForReEnterVisible() {
		waitForElementVisible(driver, LoginPageUIs.RE_ENTER_EMAIL_TEXTBOX);
	}
	
	public void waitForReEnterInvisibleInDOM() {
		waitForElementInvisibleInDOM(driver, LoginPageUIs.RE_ENTER_EMAIL_TEXTBOX);
	}
	
	public void waitForReEnterInvisibleNotInDOM() {
		waitForElementInvisibleNotInDOM(driver, LoginPageUIs.RE_ENTER_EMAIL_TEXTBOX);
	}
	
	public boolean isReEnterDisplayed() {
		return isElementDisplayedInDOM(driver, LoginPageUIs.RE_ENTER_EMAIL_TEXTBOX);
	}
	
	public boolean isReEnterUndisplayed() {
		return isElementUndisplayed(driver, LoginPageUIs.RE_ENTER_EMAIL_TEXTBOX);
	}
}
