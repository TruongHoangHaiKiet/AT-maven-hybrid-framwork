package pageUIs.wordpress;

public class WordPressAdminPostPageUI {
	public static final String ADD_NEW_BUTTON = "xpath=//div[@id='wpbody-content']//a[text()='Add New']";
	public static final String POST_TITLE_TEXT_BOX = "css=#titlewrap #title";
	public static final String POST_CONENT_IFRAME = "css=iframe#content_ifr";
	public static final String POST_CONTENT_TEXT_AREA = "css=#tinymce>p";
	public static final String POST_CATEGORIES_CHECKBOX = "xpath=//label[contains(text(),'%s')]/input";
	public static final String POST_TAG_TEXTBOX = "css=input#new-tag-post_tag";
	public static final String ADD_POST_TAG_BUTTON = "css=input.tagadd";
	public static final String POST_PUBLISH_BUTTON = "css=input#publish";
	public static final String POST_MESSAGE = "xpath=//div[@id='message']/p[contains(text(),'%s')]";
	public static final String POST_SEARCH_TEXTBOX = "css=input#post-search-input";
	public static final String POST_SEARCH_BUTTON = "css=input#search-submit";
	public static final String POST_DETAIL_COLUMN = "xpath=//td[@data-colname='%s' and contains(string(),'%s')]";
	public static final String REMOVE_TAG_BUTTON = "xpath=//span[contains(text(),'%s')]/preceding-sibling::span";
	public static final String POST_TITLE_ROW = "xpath=//a[@class='row-title' and text()='%s']";
	public static final String ACTION_ROW_BY_TITLE_NAME = "xpath=//a[@class='row-title' and text()='%s']/parent::strong/following-sibling::div[@class='row-actions']//*[text()='%s']";
	public static final String POST_MESSAGE_IN_TABLE = "xpath=//td[text()='No posts found.']";
	
			

}
