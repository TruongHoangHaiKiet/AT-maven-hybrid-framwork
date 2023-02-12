package pageObject.SauceLab;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static InventoryPageObject getInventoryPage(WebDriver driver) {
		return new InventoryPageObject(driver);
	}
}
