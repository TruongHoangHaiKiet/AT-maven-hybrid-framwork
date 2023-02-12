package pageUIs.user;

public class LoginPageUI {
	// UI objects
	public static final String EMAIL_ADDRESS_TEXTBOX = "xpath=//input[@id='email']";
	public static final String PASSWORD_TEXTBOX = "Xpath=//input[@id='pass']";
	public static final String LOGIN_BTN = "Css=button[id='send2']";
	public static final String EMAIL_EMPTY_ERROR_MESSAGE = "XPATH=//div[@id='advice-required-entry-email']";
	public static final String EMAIL_INVALID_ERROR_MESSAGE = "xpath=//div[@id='advice-validate-email-email']";
	public static final String EMAIL_INCORRECT_ERROR_MESSAGE = "xpath=//li[@class='error-msg']//span";
	public static final String PASSWORD_EMPTY_ERROR_MESSAGE = "xpath=//div[@id='advice-required-entry-pass']";
	public static final String PASSWORD_INVALID_ERROR_MESSAGE = "xpath=//div[@id='advice-validate-password-pass']";
	public static final String PASSWORD_INCORRECT_ERROR_MESSAGE = "xpath=//li[@class='error-msg']//span";
	
}
