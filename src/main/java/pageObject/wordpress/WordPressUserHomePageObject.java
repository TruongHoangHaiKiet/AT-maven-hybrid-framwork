package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import commons.basePage;
import pageUIs.wordpress.WordPressUserHomePageUI;

public class WordPressUserHomePageObject extends basePage {
	WebDriver driver;
	
	public WordPressUserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPostTitleDisplayed(String postTitle) {
		waitForElementVisible(driver,WordPressUserHomePageUI.POST_TITLE_TEXT, postTitle);
		return isElementDisplayedInDOM(driver,WordPressUserHomePageUI.POST_TITLE_TEXT, postTitle);
	}

	public boolean isPostContentDisplayed(String postBody) {
		waitForElementVisible(driver,WordPressUserHomePageUI.POST_BODY_TEXT, postBody);
		return isElementDisplayedInDOM(driver,WordPressUserHomePageUI.POST_BODY_TEXT, postBody);
	}

	public WordPressUserPostPageObject openPostDetail(String postTitle) {
		waitForElementClickable(driver,WordPressUserHomePageUI.POST_TITLE_TEXT, postTitle);
		clickToElement(driver,WordPressUserHomePageUI.POST_TITLE_TEXT, postTitle);
		return WordPressPageGeneratorManager.getUserPostPage(driver);
	}
	
	public boolean isPostTitleUnDisplayed(String postTitle) {
		waitForElementInvisibleInDOM(driver,WordPressUserHomePageUI.POST_TITLE_TEXT, postTitle);
		return isElementUndisplayed(driver,WordPressUserHomePageUI.POST_TITLE_TEXT, postTitle);
	}
	
	public boolean isPostContentUnDisplayed(String postBody) {
		waitForElementInvisibleInDOM(driver,WordPressUserHomePageUI.POST_BODY_TEXT, postBody);
		return isElementUndisplayed(driver,WordPressUserHomePageUI.POST_BODY_TEXT, postBody);
	}
}
