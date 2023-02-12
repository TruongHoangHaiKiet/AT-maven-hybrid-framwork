package pageObjects.Jquery;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	public static HomePageObjects getHomePageObjects(WebDriver driver) {
		return new HomePageObjects(driver);
	}
}
