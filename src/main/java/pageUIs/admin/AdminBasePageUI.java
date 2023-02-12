package pageUIs.admin;

public class AdminBasePageUI {
	public static final String LOGOUT_lINK = "xpath=//a[@class='link-logout']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String DYNAMIC_BUTTON_BY_TITLE = "CSS=*[title='%s']";
	public static final String DYNAMIC_FIELD_ERROR_MESSAGE_BY_ID = "xpath=//div[@id='%s']";
	public static final String PAGE_ERROR_MESSAGE = "xpath=//li[@class='error-msg']//span";
	public static final String PAGE_SUCCESS_MESSAGE = "xpath=//li[@class='success-msg']//span";
	public static final String DYNAMIC_DROPDOWN_BY_ID = "xpath=//select[@id='%s']";
}
