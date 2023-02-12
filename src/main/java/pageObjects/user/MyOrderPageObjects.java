package pageObjects.user;

import org.openqa.selenium.WebDriver;
import pageObjects.Navigation.SideBarMyAccountPageObjects;
import pageUIs.user.MyOrderPageUI;

public class MyOrderPageObjects extends SideBarMyAccountPageObjects {
	WebDriver driver;
	
	public MyOrderPageObjects(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickToUploadButton() {
		String locator = null;
		if (driver.toString().contains("edge")) {
			locator = MyOrderPageUI.EDGE_LOGIN_BUTTON;
		}else if(driver.toString().contains("firefox")){
			locator = MyOrderPageUI.FIREFOX_LOGIN_BUTTON;
		}else { // Chromium: Chrome/ Coc Coc/ Edge/ Opera
			locator = MyOrderPageUI.CHROME_LOGIN_BUTTON;
		}
		waitForElementClickable(driver, locator);
		clickToElement(driver, locator);
	}
}
