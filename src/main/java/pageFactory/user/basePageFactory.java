package pageFactory.user;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class basePageFactory {
	/* Web Browser */
	// 1 -  Access Modifier: public/ default/ private...
	// 2 - Kiểu trả về của hàm
	// 2.1 - void: Action (Click/ clear/ sendkey/ open/...)
	// 2.2 - Lấy dữ liệu ra (return): khác void: String/ int/ boolean/ Object...
	// getXXX: getCurrentUrl/ getTitle/ getCssValue/ getText/....
	// 3 - Tên hàm
	// 3.1 - Tính năng này dùng làm gì -> Tên
	// 3.2 - Convention (Camel case)
	// homNayAnGi()
	// homNayDiDau()
	// 4 - Tham số truyền vào
	// Khai báo 1 biến bên trong: Kiểu dữ liệu - Tên dữ liệu: String addressName, String pageUrl
	// 5 - Kiểu dữ liệu trả về trong hàm - tương ứng với kiểu trả về của hàm
	// 5.1 - void: không cần return
	// 5.2 - Khác void: thì return đúng capacity
	
	// Note: 
	// 1 - Tham số đầu tiên bắt buộc của 1 hàm tương tác với Web Browser là "WebDriver driver"
	
	// Chính bản thân hàm này có thể khởi tạo 1 đối tượng của class BasePage lên
	// Sau đó trả về đối tượng này ở 1 class khác
	// Static trong hàm: thuộc phạm vi của class (không cần thông qua 1 object/ instance vẫn gọi ra dùng được), không thuộc phạm vi của Object
	public static basePageFactory getBasePageInstance(){
		return new basePageFactory();
	}
	
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageSourseCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshToPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		 return new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getTextAlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String sendkeyToAlert) {
		waitForAlertPresence(driver).sendKeys(sendkeyToAlert);
	}
	
	// Chỉ đúng cho duy nhất 2 tab/window
	public void switchToTabByID(WebDriver driver, String expectedID) {
		// Lấy ra ID của tất cả các tab/window đang có
		Set<String> alltabIDs = driver.getWindowHandles();

		// Dùng vòng lặp để duyệt qua từng ID
		for (String id : alltabIDs) {
			if (!id.equals(expectedID)) { // Nếu như ID của tab/window mới khác với expectedID thì switch tới ID đó
				driver.switchTo().window(id);
				break;
			}
		}
	}

	// Đúng với tất cả trường hợp: 2 hoặc nhiều hơn 2 tab/ window đều đúng
	public void switchToTabByTitle(WebDriver driver, String expectedTitle) {
		// Lấy ra ID của tất cả các tab/window đang có
		Set<String> alltabIDs = driver.getWindowHandles();

		// Dùng vòng lặp để duyệt qua từng ID
		for (String id : alltabIDs) {
			// Switch vào từng tab/ window trước rồi kiểm tra sau
			driver.switchTo().window(id);

			// Lấy ra title của tab/ window vừa switch vào
			String actualTitle = driver.getTitle();

			// Kiểm tra nếu title bằng với expected title
			if (actualTitle.equals(expectedTitle)) {
				// Thoát khỏi vòng lặp
				break;
			}
		}

	}
	
	public boolean closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows =  driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();	
			}
		}
		driver.switchTo().window(parentID);
		if (driver.getWindowHandles().size() == 1) {
			return true;
		}else
			return false;
	}

	/* Web Element */
	// Note:
	// 1 - Tham số đầu tiên bắt buộc của 1 hàm tương tác với Web Browser là "WebDriver driver"
	// 2 - Tham số thứ hai bắt buộc của 1 hàm tương tác với Web Element là "String locator"
	// Locator: Thao tác với Element nào (dùng Xpath/ Css/ Id/ Name/ Class/...)
	// Ưu tiên dùng Xpath vì nó handle được các case text/ Xpath Axes
	// 3 - Những step nào có dùng element lại >=2 lần trở lên -> Khai báo 1 biến local
	// 4 - Verify true/ false
	// Các hàm trả về kiểu boolean luôn có tiền tố là is (isDisplayed/ isEnabled/ isSelected/ isMultiple/...)
	private long longTimeOut = 30;
	
	public By getByXpath(String locator) {
		return By.xpath(locator);
	}
	
	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}
	
	public List<WebElement> getListWebElement(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String valueToInput) {
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(valueToInput);
	}
	
	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemByText) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(itemByText);
	}
	
	public String getFirstSelectedTextItem(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdownList(WebDriver driver, String parentLocator, String childLocator, String ExpectedTextItem) {
		// Step 1: Click vào 1 element để nó xổ hết ra các item
		getWebElement(driver, parentLocator).click();
		sleepInSecond(2);

		// Step 2: Chờ cho các item load hết ra thành công
		// Wait visible: not cover all cases
		// Wait presence: cover all cases
		// Lưu ý 1: Locator chứa hết tất cả các item
		// Lưu ý 2: Locator phải đến node cuối cùng chứa text
		// Lấy hết tất cả các element (item) ra sau đó duyệt qua từng item
		List<WebElement> allItems = new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));

		// Duyệt qua từng item getText của item đó ra
		for (WebElement item : allItems) {
			String actualText = item.getText();
			System.out.println("Actual text = " + actualText);

			// Nếu text = text mình mong muốn (item cần click vào)
			if (actualText.trim().equals(ExpectedTextItem)) {
				// + B1: Nếu Item cần chọn nằm trong vùng nhìn thấy thì cũng scroll đến element
				// đó rồi chọn
				// + B2: Nếu item cần chọn nằm ở dưới thì scroll xuống đến element đó rồi chọn
				// -> cả 2 cách đều scroll để handle tất cả các cases
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				// Step 4: Click vào item đó
				item.click();
				sleepInSecond(1);
				// Thoát khỏi vòng lặp không có kiểm tra element tiếp theo nữa
				break;
			}
		}
					
	}
	
	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String locator, String propertyName ) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}
	
	public int getListElementSize(WebDriver driver, String locator) {
		return getListWebElement(driver, locator).size();
	}
	
	public void checkForCheckboxOrRadio(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckForCheckbox(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}
	
	public void switchToIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.contextClick(getWebElement(driver, locator)).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.doubleClick(getWebElement(driver, locator)).perform();
	}
	
	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		Actions action = new Actions(driver);
		action.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator) ).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locator), key).perform();
	}
	
	public void hightlightElement(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void scrollToElementOnTop(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void scrollToElementOnDown(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
		if (status) {
			return true;
		}
		return false;
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}
	
	public void sleepInSecond(long timeInSecond ) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForElementVisible(WebDriver driver,  WebElement element) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.visibilityOf(element));
	}
	
	public void sendkeyToElement(WebDriver driver, WebElement element, String valueToInput) {
		element.clear();
		element.sendKeys(valueToInput);
	}
	
	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}
	
	public String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}

}
