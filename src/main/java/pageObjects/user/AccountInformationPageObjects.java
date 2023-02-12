package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.Navigation.PageGeneratorManager;
import pageObjects.Navigation.SideBarMyAccountPageObjects;
import pageUIs.user.AccountInformationPageUI;

public class AccountInformationPageObjects extends SideBarMyAccountPageObjects {
	WebDriver driver;
	
	public AccountInformationPageObjects(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void updateFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, AccountInformationPageUI.FIRSTNAME_TEXTBOX);
		sendkeyToElement(driver, AccountInformationPageUI.FIRSTNAME_TEXTBOX, firstName);
	}

	public void updateLasttNameTextbox(String lastName) {
		waitForElementVisible(driver, AccountInformationPageUI.LASTNAME_TEXTBOX);
		sendkeyToElement(driver, AccountInformationPageUI.LASTNAME_TEXTBOX, lastName);
	}

	public void updateEmailAddressTextbox(String emailAddress) {
		waitForElementVisible(driver, AccountInformationPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, AccountInformationPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void reInputPassTextbox(String password) {
		waitForElementVisible(driver, AccountInformationPageUI.CURRENT_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, AccountInformationPageUI.CURRENT_PASSWORD_TEXTBOX, password);
	}

	public MyDashboardPageObjects clickToSaveButton() {
		waitForElementVisible(driver, AccountInformationPageUI.SAVE_BUTTON);
		clickToElement(driver, AccountInformationPageUI.SAVE_BUTTON);
		return PageGeneratorManager.getMyDashboardPage(driver);
	}

}
