package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import pageUIs.wordpress.WordPressUserPostPageUI;

public class WordPressUserPostPageObject  extends WordPressAdminMenuItemPageObject {
	WebDriver driver;
	
	public WordPressUserPostPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void isPostTitleDetailDisplayed(String postTitle) {
		waitForElementVisible(driver,WordPressUserPostPageUI.POST_TITLE_DETAIL , postTitle);
		isElementDisplayedInDOM(driver,WordPressUserPostPageUI.POST_TITLE_DETAIL , postTitle);	
	}

	public void isPostBodytDetailDisplayed(String postBody) {
		waitForElementVisible(driver,WordPressUserPostPageUI.POST_BODY_DETAIL , postBody);
		isElementDisplayedInDOM(driver,WordPressUserPostPageUI.POST_BODY_DETAIL , postBody);	
	}
	
	public void isPostTagNameDetailDisplayed(String postTagName) {
		waitForElementVisible(driver,WordPressUserPostPageUI.POST_TAG_DETAIL , postTagName);
		isElementDisplayedInDOM(driver,WordPressUserPostPageUI.POST_TAG_DETAIL , postTagName);
	}

	public void isPostAuthorDetailDisplayed(String authorName) {
		waitForElementVisible(driver,WordPressUserPostPageUI.POST_AUTHOR_DETAIL , authorName);
		isElementDisplayedInDOM(driver,WordPressUserPostPageUI.POST_AUTHOR_DETAIL , authorName);
	}


	
}
