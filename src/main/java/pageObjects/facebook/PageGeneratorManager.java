package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	public static LoginPageObjects getLoginPageObjects(WebDriver driver) {
		return new LoginPageObjects(driver);
	}
}
