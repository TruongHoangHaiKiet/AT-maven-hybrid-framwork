package pageFactory.user;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects extends basePageFactory {
	WebDriver driver;
	
	public HomePageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//@FindBy: Nó sẽ chỉ nhận 1 tham số locator
	//@FindBys: Nó sẽ nhận 2 tham số locator (AND)
	// @FindAll: Nó sẽ nhận 2 tham số locator (OR)
	
	// Cách 1 định nghĩa 1 element
	@FindBy(xpath = "//div[@class='footer']//a[@title='My Account']") WebElement myAccountLink;
	
	// Cách 2 định nghĩa 1 element
	@FindBy(how = How.XPATH, using = "//div[@class='footer']//a[@title='My Account']") List<WebElement> myAccountLinks;
	
	
	// Khi WebElement được gọi thì nó sẽ tự đi tìm element (tương tự như findElement/ findElements)
	
	public void clickToMyAccountLink() {
		waitForElementClickable(driver, myAccountLink);
		clickToElement(driver, myAccountLink);	
	}

}
