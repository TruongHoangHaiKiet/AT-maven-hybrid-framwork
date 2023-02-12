package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.basePage;
import io.qameta.allure.Step;
import pageObjects.Navigation.PageGeneratorManager;
import pageUIs.user.LoginPageUI;

public class UserLoginPageObjects extends basePage {
	WebDriver driver;
	
	public UserLoginPageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	// Action objects
	@Step("Input to Email textbox with value {0}")
	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);	
	}
	
	@Step("Input to Password textbox with value {0}")
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);	
	}
	
	@Step("Click to Login button")
	public MyDashboardPageObjects clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BTN);
		clickToElement(driver, LoginPageUI.LOGIN_BTN);
		return PageGeneratorManager.getMyDashboardPage(driver);
	}
	
	@Step("Get Email Empty Error Message")
	public String getEmailEmptyErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_EMPTY_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_EMPTY_ERROR_MESSAGE);
	}

	@Step("Get Password Empty Error Message")
	public String getPasswordEmptyErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_EMPTY_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.PASSWORD_EMPTY_ERROR_MESSAGE);
	}
	
	@Step("Get Email Invalid Error Message")
	public String getEmailInvalidErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_INVALID_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_INVALID_ERROR_MESSAGE);
	}

	@Step("Get Email Incorrect Error Message ")
	public String getEmailIncorrectErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_INCORRECT_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_INCORRECT_ERROR_MESSAGE);
	}

	@Step("Get Password Invalid Error Message")
	public String getPasswordInvalidErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_INVALID_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.PASSWORD_INVALID_ERROR_MESSAGE);
	}

	@Step("Get Password Incorrect Error Message")
	public String getPasswordIncorrectErrorMessage() {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_INCORRECT_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.PASSWORD_INCORRECT_ERROR_MESSAGE);
	}

}
