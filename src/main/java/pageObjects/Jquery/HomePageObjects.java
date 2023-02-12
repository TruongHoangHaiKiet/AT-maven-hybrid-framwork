package pageObjects.Jquery;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.basePage;
import pageUIs.Jquery.HomePageUIs;

public class HomePageObjects extends basePage {
	WebDriver driver;
	
	public HomePageObjects (WebDriver driver){
		this.driver = driver;
	}

	public void enterToTextboxByHeaderName(String headerName, String value) {
		waitForElementVisible(driver, HomePageUIs.HEADER_TEXTBOX_BY_HEADER_NAME, headerName);
		sendkeyToElement(driver, HomePageUIs.HEADER_TEXTBOX_BY_HEADER_NAME, value, headerName);
		pressKeyToElement(driver, HomePageUIs.HEADER_TEXTBOX_BY_HEADER_NAME, Keys.ENTER, headerName);
	}

	public boolean isRowValuesDisplay(String femalesValue, String countryValue, String maleValue, String totalValue) {
		waitForElementVisible(driver, HomePageUIs.ROW_VALUES, femalesValue, countryValue, maleValue, totalValue);
		return isElementDisplayedInDOM(driver, HomePageUIs.ROW_VALUES, femalesValue, countryValue, maleValue, totalValue);
	}

	public void clickOnActionIconByCountryName(String countryName, String actionName) {
		waitForElementClickable(driver, HomePageUIs.ACTION_ICON_BY_COUNTRY_NAME, countryName, actionName);
		clickToElement(driver, HomePageUIs.ACTION_ICON_BY_COUNTRY_NAME, countryName, actionName);
	}

	public void clickToPageByNumber(String pageNumber) {
		waitForElementClickable(driver, HomePageUIs.PAGE_NUMBER, pageNumber);
		clickToElement(driver, HomePageUIs.PAGE_NUMBER, pageNumber);
	}

	public boolean isPageNumberActived(String pageNumberActived) {
		waitForElementVisible(driver, HomePageUIs.PAGE_NUMBER_ACTIVED, pageNumberActived);
		return isElementDisplayedInDOM(driver, HomePageUIs.PAGE_NUMBER_ACTIVED, pageNumberActived);
	}

	public void enterToTextboxByHeaderNameAndRowName(String headerName, String rowIndex, String valueToInput) {
		// Lấy ra thứ tự của cột dựa vào tên cột
		int headerIndex = getListElementSize(driver, HomePageUIs.COLUMN_INDEX_BY_NAME, headerName) + 1;
		
		// Khi đã biết được vị trí của cột rồi
		// Vị trí của hàng rồi
		// Giao index giữa hàng + cột là ô mình cần
		waitForElementVisible(driver, HomePageUIs.CELL_TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowIndex, String.valueOf(headerIndex) );
		sendkeyToElement(driver, HomePageUIs.CELL_TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, valueToInput, rowIndex, String.valueOf(headerIndex));
	}

	public void clickOnLoadData() {
		waitForElementClickable(driver, HomePageUIs.DEMO_LOAD_DATA_BTN);
		clickToElement(driver, HomePageUIs.DEMO_LOAD_DATA_BTN);
	}

	public boolean isFileNameLoadedSuccess(String fileName) {
		waitForElementVisible(driver, HomePageUIs.IMAGE_FILE_NAME_LOADED, fileName);
		return isElementDisplayedInDOM(driver, HomePageUIs.IMAGE_FILE_NAME_LOADED, fileName);
	}

	public void clickOnStartButton() {
		List<WebElement> startButtonElements = getListWebElement(driver, HomePageUIs.START_UPLOAD_BTN);
		for (WebElement startButton : startButtonElements) {
			waitForElementClickable(driver, startButton);
			startButton.click();
			sleepInSecond(2);
		}
		
	}

	public boolean isFileNameUploadedSuccess(String fileName) {
		waitForElementVisible(driver, HomePageUIs.IMAGE_FILE_NAME_UPLOADED, fileName);
		return isElementDisplayedInDOM(driver, HomePageUIs.IMAGE_FILE_NAME_UPLOADED, fileName);
	}
	
	
}
