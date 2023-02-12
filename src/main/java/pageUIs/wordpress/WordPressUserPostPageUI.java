package pageUIs.wordpress;

public class WordPressUserPostPageUI {
	public static final String POST_TITLE_DETAIL = "xpath=//h1[contains(text(),'%s')]";
	public static final String POST_AUTHOR_DETAIL = "xpath=//i[contains(@class,'fa-user')]/parent::span[contains(text(),'%s')]";
	public static final String POST_BODY_DETAIL = "xpath=//div[@class='post-content']/p[contains(text(),'%s')]";
	public static final String POST_TAG_DETAIL = "xpath=//div[@class='post-tags']//a[text()='%s']";
}
