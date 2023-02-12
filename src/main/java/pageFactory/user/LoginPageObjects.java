package pageFactory.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPageObjects extends basePageFactory {
	WebDriver driver;
	
	public LoginPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "email") WebElement emailAddressTextbox;
	@FindBy(id = "pass") WebElement passTextbox;
	@FindBy(id = "send2") WebElement loginButton;
	@FindBy(xpath = "//div[@id='advice-required-entry-email']") WebElement emailEmptyErrorMessage;
	@FindBy(xpath = "//div[@id='advice-validate-email-email']") WebElement emailInvalidErrorMessage;
	@FindBy(xpath = "//li[@class='error-msg']//span") WebElement emailIncorrectErrorMessage;
	@FindBy(xpath = "//div[@id='advice-required-entry-pass']") WebElement passEmptyErrorMessage;
	@FindBy(xpath = "//div[@id='advice-validate-password-pass']") WebElement passInvalidErrorMessage;
	@FindBy(xpath = "//li[@class='error-msg']//span") WebElement passIncorrectErrorMessage;
	
	// Action objects
	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, emailAddressTextbox);
		sendkeyToElement(driver, emailAddressTextbox, emailAddress);	
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passTextbox);
		sendkeyToElement(driver, passTextbox, password);	
	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
		
	}

	public String getEmailEmptyErrorMessage() {
		waitForElementVisible(driver, emailEmptyErrorMessage);
		return getElementText(driver, emailEmptyErrorMessage);
	}

	public String getPasswordEmptyErrorMessage() {
		waitForElementVisible(driver, passEmptyErrorMessage);
		return getElementText(driver, passEmptyErrorMessage);
	}

	public String getEmailInvalidErrorMessage() {
		waitForElementVisible(driver, emailInvalidErrorMessage);
		return getElementText(driver, emailInvalidErrorMessage);
	}

	public String getEmailIncorrectErrorMessage() {
		waitForElementVisible(driver, emailIncorrectErrorMessage);
		return getElementText(driver, emailIncorrectErrorMessage);
	}

	public String getPasswordInvalidErrorMessage() {
		waitForElementVisible(driver, passInvalidErrorMessage);
		return getElementText(driver, passInvalidErrorMessage);
	}

	public String getPasswordIncorrectErrorMessage() {
		waitForElementVisible(driver, passIncorrectErrorMessage);
		return getElementText(driver, passIncorrectErrorMessage);
	}
}
