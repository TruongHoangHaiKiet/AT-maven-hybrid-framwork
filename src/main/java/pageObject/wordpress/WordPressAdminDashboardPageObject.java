package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

public class WordPressAdminDashboardPageObject extends WordPressAdminMenuItemPageObject {
	WebDriver driver;
	
	public WordPressAdminDashboardPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	
}
