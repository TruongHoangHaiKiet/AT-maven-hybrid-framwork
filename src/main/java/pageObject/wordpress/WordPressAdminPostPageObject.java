package pageObject.wordpress;

import org.openqa.selenium.WebDriver;

import pageUIs.wordpress.WordPressAdminPostPageUI;

public class WordPressAdminPostPageObject extends WordPressAdminMenuItemPageObject {
	WebDriver driver;
	
	public WordPressAdminPostPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void clickOnAddNewButton() {
		waitForElementClickable(driver, WordPressAdminPostPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, WordPressAdminPostPageUI.ADD_NEW_BUTTON);
	}

	public void enterToPostTitle(String postTitle) {
		waitForElementVisible(driver, WordPressAdminPostPageUI.POST_TITLE_TEXT_BOX);
		sendkeyToElement(driver, WordPressAdminPostPageUI.POST_TITLE_TEXT_BOX, postTitle);
	}

	public void enterToPostBody(String postContent) {
		switchToIframe(driver, WordPressAdminPostPageUI.POST_CONENT_IFRAME);
		
		waitForElementClickable(driver, WordPressAdminPostPageUI.POST_CONTENT_TEXT_AREA);
		clickToElement(driver, WordPressAdminPostPageUI.POST_CONTENT_TEXT_AREA );
		sleepInSecond(2);

		sendkeyToElement(driver, WordPressAdminPostPageUI.POST_CONTENT_TEXT_AREA, postContent);
		switchToDefaultContent(driver);
		
	}

	public void enterToTagTextbox(String tagName) {
		waitForElementVisible(driver, WordPressAdminPostPageUI.POST_TAG_TEXTBOX);
		sendkeyToElement(driver,  WordPressAdminPostPageUI.POST_TAG_TEXTBOX, tagName);	
	}

	public void clickToAddTagbutton() {
		waitForElementClickable(driver, WordPressAdminPostPageUI.ADD_POST_TAG_BUTTON);
		clickToElement(driver,  WordPressAdminPostPageUI.ADD_POST_TAG_BUTTON);
	}

	public void clickOnPublishButton() {
		waitForElementClickable(driver, WordPressAdminPostPageUI.POST_PUBLISH_BUTTON);
		scrollToElementOnTop(driver, WordPressAdminPostPageUI.POST_PUBLISH_BUTTON);
		clickToElement(driver,  WordPressAdminPostPageUI.POST_PUBLISH_BUTTON);
	}

	public boolean isPostCreatedMesageDisplay(String message) {
		waitForElementVisible(driver, WordPressAdminPostPageUI.POST_MESSAGE, message);
		return isElementDisplayedInDOM(driver, WordPressAdminPostPageUI.POST_MESSAGE, message);
	}

	public void enterToSearchTextbox(String postTitle) {
		waitForElementVisible(driver, WordPressAdminPostPageUI.POST_SEARCH_TEXTBOX);
		sendkeyToElement(driver,  WordPressAdminPostPageUI.POST_SEARCH_TEXTBOX, postTitle);	
	}

	public void clickOnSearchPostsButton() {
		waitForElementClickable(driver, WordPressAdminPostPageUI.POST_SEARCH_BUTTON);
		clickToElement(driver,  WordPressAdminPostPageUI.POST_SEARCH_BUTTON);
	}

	public boolean isPostInfoDisplayed(String columnName, String columnValue) {
		waitForElementVisible(driver, WordPressAdminPostPageUI.POST_DETAIL_COLUMN, columnName, columnValue);
		return isElementDisplayedInDOM(driver, WordPressAdminPostPageUI.POST_DETAIL_COLUMN, columnName, columnValue);
	}

	public void checkCategoriesCheckbox(String categoriesName) {
		waitForElementVisible(driver, WordPressAdminPostPageUI.POST_CATEGORIES_CHECKBOX, categoriesName);
		clickToElement(driver, WordPressAdminPostPageUI.POST_CATEGORIES_CHECKBOX, categoriesName);	
	}
	
	public void uncheckCategoriesCheckbox(String categoriesName) {
		waitForElementVisible(driver, WordPressAdminPostPageUI.POST_CATEGORIES_CHECKBOX, categoriesName);
		uncheckForCheckbox(driver, WordPressAdminPostPageUI.POST_CATEGORIES_CHECKBOX, categoriesName);
	}

	public void removeTagByName(String tagName) {
		waitForElementVisible(driver, WordPressAdminPostPageUI.REMOVE_TAG_BUTTON, tagName);
		clickToElement(driver, WordPressAdminPostPageUI.REMOVE_TAG_BUTTON, tagName);	
	}

	public void clickOnActionByTitle(String postTitle, String actionName) {
		// Hover
		waitForElementVisible(driver, WordPressAdminPostPageUI.POST_TITLE_ROW, postTitle);
		hoverMouseToElement(driver, WordPressAdminPostPageUI.POST_TITLE_ROW, postTitle);
		
		waitForElementVisible(driver, WordPressAdminPostPageUI.ACTION_ROW_BY_TITLE_NAME, postTitle, actionName);
		clickToElement(driver, WordPressAdminPostPageUI.ACTION_ROW_BY_TITLE_NAME, postTitle, actionName);
		
	}

	public boolean isPostDeletedMesageDisplay(String message) {
		waitForElementVisible(driver, WordPressAdminPostPageUI.POST_MESSAGE_IN_TABLE, message);
		return isElementDisplayedInDOM(driver, WordPressAdminPostPageUI.POST_MESSAGE_IN_TABLE, message);
	}


}
