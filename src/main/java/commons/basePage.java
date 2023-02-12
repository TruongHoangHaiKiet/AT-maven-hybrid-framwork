package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.Navigation.FooterContainerPageObjects;
import pageObjects.Navigation.PageGeneratorManager;
import pageObjects.admin.AdminLoginPageObjects;
import pageObjects.user.UserHomePageObjects;
import pageUIs.Jquery.HomePageUIs;
import pageUIs.admin.AdminBasePageUI;
import pageUIs.user.UserBasePageUI;

public class basePage {
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
	public static basePage getBasePageInstance(){
		return new basePage();
	}
	
	public Set<Cookie> getCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setCookies(WebDriver driver,Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
		refreshCurrentPage(driver);
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
	
	public void refreshCurrentPage(WebDriver driver) {
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
	private long longTimeOut = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeOut = GlobalConstants.SHORT_TIMEOUT;

	public String castRestParameter(String locator, String... values) {
		// String.format dùng để chèn dữ liệu bên ngoài vào %s của 1 rest parameter
		locator = String.format(locator, (Object[]) values);
		return locator;
	}
	
	// Locator này nhận tham số đầu vào là id/ class/ name/ xpath/ css
	// Quy ước convention của by locator là: id=/ class=/ name=/ xpath=/ css=
	// Hàm này chỉ sử dụng cho class này thôi nên có thể để access modifier là private
	private By getByLocator(String locator) {
		By by = null;
		if (locator.startsWith("id=") || locator.startsWith("ID=") || locator.startsWith("Id=")) {
			by = By.id(locator.substring(3));
		}
		else if (locator.startsWith("class=") || locator.startsWith("CLASS=") || locator.startsWith("Class=")) {
			by = By.className(locator.substring(6));
		}
		else if (locator.startsWith("name=") || locator.startsWith("NAME=") || locator.startsWith("Name=")) {
			by = By.name(locator.substring(5));
		}
		else if (locator.startsWith("css=") || locator.startsWith("CSS=") || locator.startsWith("Css=")) {
			by = By.cssSelector(locator.substring(4));
		}
		else if (locator.startsWith("xpath=") || locator.startsWith("XPATH=") || locator.startsWith("XPath=") || locator.startsWith("Xpath=")) {
			by = By.xpath(locator.substring(6));
		}else {
			throw new RuntimeException("Locator type is not valid !");
		}
		return by;
	}
	
	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}
	
	public List<WebElement> getListWebElement(WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locator);
			sleepInSecond(4);
		}else {
			getWebElement(driver, locator).click();
		}
	}
	
	public void clickToElement(WebDriver driver, String locator, String... dynamicLocator) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locator, dynamicLocator);
			sleepInSecond(4);
		}else {
			getWebElement(driver, castRestParameter(locator, dynamicLocator)).click();
		}
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String valueToInput) {
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(valueToInput);
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String valueToInput, String... dynamicLocator) {
		WebElement element = getWebElement(driver, castRestParameter(locator, dynamicLocator));
		element.clear();
		element.sendKeys(valueToInput);
	}
	
	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}
	
	public String getElementText(WebDriver driver, String locator, String... dynamicLocator) {
		return getWebElement(driver, castRestParameter(locator, dynamicLocator)).getText();
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemByText) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(itemByText);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemByText, String... dynamicLocator) {
		Select select = new Select(getWebElement(driver, castRestParameter(locator, dynamicLocator)));
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
		List<WebElement> allItems = new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));

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
	
	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName, String... dynamicLocator) {
		return getWebElement(driver, castRestParameter(locator, dynamicLocator)).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String locator, String propertyName ) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}
	
	public int getListElementSize(WebDriver driver, String locator) {
		return getListWebElement(driver, locator).size();
	}
	
	public int getListElementSize(WebDriver driver, String locator, String... dynamicLocator) {
		return getListWebElement(driver, castRestParameter(locator, dynamicLocator)).size();
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
	
	public void uncheckForCheckbox(WebDriver driver, String locator, String... dynamicLocator) {
		WebElement element = getWebElement(driver, castRestParameter(locator, dynamicLocator));
		if (element.isSelected()) {
			element.click();
		}
	}
	
	// Element visible in DOM
	// Element invisible in DOM
	public boolean isElementDisplayedInDOM(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}
	
	// Element visible in DOM
	// Element invisible in DOM
	public boolean isElementDisplayedInDOM(WebDriver driver, String locator, String... dynamicLocator) {
		return getWebElement(driver, castRestParameter(locator, dynamicLocator)).isDisplayed();
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		setImplicitWait(driver, shortTimeOut);
		List<WebElement> elements = getListWebElement(driver, locator);
		setImplicitWait(driver, longTimeOut);
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()){
			System.out.println("Element in DOM but not visible/ displayed");
			System.out.println("End time = " + new Date().toString());
			return true;		
		}else {
			System.out.println("Element in DOM and visible");
			return false;
		}

	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locator, String... dynamicLocator) {
		setImplicitWait(driver, shortTimeOut);
		List<WebElement> elements = getListWebElement(driver, castRestParameter(locator, dynamicLocator));
		setImplicitWait(driver, longTimeOut);
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()){
			System.out.println("Element in DOM but not visible/ displayed");
			System.out.println("End time = " + new Date().toString());
			return true;		
		}else {
			System.out.println("Element in DOM and visible");
			return false;
		}

	}
	
	public void setImplicitWait(WebDriver driver, long timeInSecond) {
		// Khi set 1 lần nó sẽ apply từ đó trở đi cho đến hết Class testcase đó (findElement/findElements)
		// Set lại thì nó gán lại
		driver.manage().timeouts().implicitlyWait(timeInSecond, TimeUnit.SECONDS);
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator, String... dynamicLocator) {
		return getWebElement(driver, castRestParameter(locator, dynamicLocator)).isSelected();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator, String... dynamicLocator) {
		return getWebElement(driver, castRestParameter(locator, dynamicLocator)).isEnabled();
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
	
	public void hoverMouseToElement(WebDriver driver, String locator, String... dynamicLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, castRestParameter(locator, dynamicLocator))).perform();
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
	
	public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... dynamicLocator) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, castRestParameter(locator, dynamicLocator)), key).perform();
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
	
	public void clickToElementByJS(WebDriver driver, String locator, String... dynamicLocator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, castRestParameter(locator, dynamicLocator)));
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
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}
	public void waitForAllElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locator, String... dynamicLocator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castRestParameter(locator, dynamicLocator))));
	}
	
	public void waitForElementInvisibleInDOM(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}
	
	public void waitForElementInvisibleInDOM(WebDriver driver, String locator, String... dynamicLocator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castRestParameter(locator, dynamicLocator))));
	}
	
	public void waitForElementInvisibleNotInDOM(WebDriver driver, String locator) {
		setImplicitWait(driver, shortTimeOut);
		new WebDriverWait(driver, shortTimeOut).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
		setImplicitWait(driver, longTimeOut);
	}
	
	public void waitForElementInvisibleNotInDOM(WebDriver driver, String locator, String... dynamicLocator) {
		setImplicitWait(driver, shortTimeOut);
		new WebDriverWait(driver, shortTimeOut).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castRestParameter(locator, dynamicLocator))));
		setImplicitWait(driver, longTimeOut);
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}
	
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator, String... dynamicLocator) {
		new WebDriverWait(driver, longTimeOut).until(ExpectedConditions.elementToBeClickable(getByLocator(castRestParameter(locator, dynamicLocator))));
	}

	public void sleepInSecond(long timeInSecond ) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void uploadMultipleFiles (WebDriver driver, String... fileNames) {
		String uploadFilePath = GlobalConstants.UPLOAD_PATH;
		String fullFileName = "";
		
		for (String file : fileNames) {
			fullFileName = fullFileName + uploadFilePath + file + "\n";
			// 1 - fullFileName =  "" + ".../uploadFiles/" + "apple.png" + "\n";
			// 2 - "" + ".../uploadFiles/" + "apple.png" + "\n" + ".../uploadFiles/" + "lenovo.png" + "\n";
			// 3 - "" + ".../uploadFiles/" + "apple.png" + "\n" + ".../uploadFiles/" + "lenovo.png" + "\n" + ".../uploadFiles/" + "microsoft.png" + "\n"; 	
		}
		
		//fullFileName = "" + ".../uploadFiles/" + "apple.png" + "\n" + ".../uploadFiles/" + "lenovo.png" + "\n" + ".../uploadFiles/" + "microsoft.png" + "\n";
		fullFileName = fullFileName.trim();
		// Chuỗi sau khi dùng trim(); fullFileName = ".../uploadFiles/" + "apple.png" + "\n" + ".../uploadFiles/" + "lenovo.png" + "\n" + ".../uploadFiles/" + "microsoft.png"
		getWebElement(driver, HomePageUIs.UPLOAD_FILE).sendKeys(fullFileName);
	}

	
	public FooterContainerPageObjects getFooterContainerPage(WebDriver driver) {
		return new FooterContainerPageObjects(driver);
	}
	
	public AdminLoginPageObjects clickOnAdminLogOutLink(WebDriver driver) {
		waitForElementVisible(driver, AdminBasePageUI.LOGOUT_lINK);
		clickToElement(driver, AdminBasePageUI.LOGOUT_lINK);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}
	
	public UserHomePageObjects clickOnUserLogOutLink(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.HEADER_ACCOUNT_TEXT);
		clickToElement(driver, UserBasePageUI.HEADER_ACCOUNT_TEXT);
		waitForElementClickable(driver, UserBasePageUI.HEADER_LOGOUT_LINK);
		clickToElement(driver, UserBasePageUI.HEADER_LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	public AdminLoginPageObjects openAdminLoginPage(WebDriver driver, String adminUrl) {
		openPageUrl(driver, adminUrl);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}
	
	public UserHomePageObjects openUserHomePage(WebDriver driver, String userUrl) {
		openPageUrl(driver, userUrl);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	/** Common function for Web Component **/
	public void inputToTextboxByID(WebDriver driver, String textboxID, String valueToInput) {
		waitForElementVisible(driver, AdminBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver,  AdminBasePageUI.DYNAMIC_TEXTBOX_BY_ID, valueToInput, textboxID);
	}
	
	public void clickButtonByTitle(WebDriver driver, String titleValue) {
		waitForElementClickable(driver,AdminBasePageUI.DYNAMIC_BUTTON_BY_TITLE, titleValue);
		clickToElement(driver, AdminBasePageUI.DYNAMIC_BUTTON_BY_TITLE, titleValue);
	}
	
	public String getFieldErrorMessageByID(WebDriver driver, String fieldID) {
		waitForElementVisible(driver, AdminBasePageUI.DYNAMIC_FIELD_ERROR_MESSAGE_BY_ID, fieldID);
		return getElementText(driver, AdminBasePageUI.DYNAMIC_FIELD_ERROR_MESSAGE_BY_ID, fieldID);
	}
	
	public String getPageErrorMessage(WebDriver driver) {
		waitForElementVisible(driver, AdminBasePageUI.PAGE_ERROR_MESSAGE);
		return getElementText(driver, AdminBasePageUI.PAGE_ERROR_MESSAGE);
	}
	
	public String getPageSuccessMessage(WebDriver driver, String pageID) {
		waitForElementVisible(driver, AdminBasePageUI.PAGE_SUCCESS_MESSAGE);
		return getElementText(driver, AdminBasePageUI.PAGE_SUCCESS_MESSAGE);
	}
	
	public void selectDefaultDropdownByID(WebDriver driver, String dropdownID, String itemText) {
		waitForElementClickable(driver, AdminBasePageUI.DYNAMIC_DROPDOWN_BY_ID, dropdownID);
		selectItemInDefaultDropdown(driver,AdminBasePageUI.DYNAMIC_DROPDOWN_BY_ID, itemText, dropdownID);
		
	}

}




