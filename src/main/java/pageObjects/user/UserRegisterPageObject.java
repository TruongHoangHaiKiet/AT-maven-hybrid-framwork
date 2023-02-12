package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.basePage;
import pageUIs.user.RegisterPageUI;

public class UserRegisterPageObject  extends basePage {
	WebDriver driver;
	
	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BTN);
		clickToElement(driver, RegisterPageUI.REGISTER_BTN);
	}
}
