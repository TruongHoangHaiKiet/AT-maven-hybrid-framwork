package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import commons.basePage;

public class WordPressUserSearchPageObject extends basePage {
	WebDriver driver;
	
	public WordPressUserSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
