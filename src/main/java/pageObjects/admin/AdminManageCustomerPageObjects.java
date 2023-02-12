package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.basePage;
import pageUIs.admin.AdminManageCustomerPageUI;

public class AdminManageCustomerPageObjects extends basePage {
	WebDriver driver;
	
	public AdminManageCustomerPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void closeIncomingMessagePopup() {
		waitForElementVisible(driver, AdminManageCustomerPageUI.INCOMING_MESSAGE_POPUP);
		waitForElementClickable(driver, AdminManageCustomerPageUI.INCOMING_MESSAGE_POPUP_CLOSE_ICON);
		clickToElement(driver, AdminManageCustomerPageUI.INCOMING_MESSAGE_POPUP_CLOSE_ICON);
	}

}
