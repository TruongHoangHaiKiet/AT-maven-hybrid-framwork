package pageUIs.Jquery;

public class HomePageUIs {
	public final static String HEADER_TEXTBOX_BY_HEADER_NAME = "xpath=//div[text()='%s']/parent::div/following-sibling::input"; 
	public final static String ROW_VALUES = "xpath=//td[@data-key='females' and text()='%s']/following-sibling::td[@data-key='country' and text()='%s']/following-sibling::td[@data-key='males' and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']"; 
	public final static String ACTION_ICON_BY_COUNTRY_NAME = "xpath=//td[@data-key='country' and text()='%s']/preceding-sibling::td/button[contains(@class,'%s')]";
	public final static String PAGE_NUMBER = "XPATH=//a[text()='%s']";
	public final static String PAGE_NUMBER_ACTIVED = "XPATH=//a[text()='%s' and contains(@class, 'active')]";
	
	public final static String COLUMN_INDEX_BY_NAME = "xpath=//td[text()='%s']/preceding-sibling::td";
	public final static String CELL_TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tr[%s]//td[%s]/input";
	public final static String DEMO_LOAD_DATA_BTN = "css=button#btnLoad";
	
	public final static String UPLOAD_FILE = "css=input[type='file']";
	public final static String IMAGE_FILE_NAME_LOADED = "XPATH=//p[@class='name' and text()='%s']";
	public final static String IMAGE_FILE_NAME_UPLOADED = "css=p.name>a[title='%s']";
	public final static String START_UPLOAD_BTN = "CSS=table button.start";
}

