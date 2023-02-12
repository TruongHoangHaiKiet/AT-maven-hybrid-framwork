package pageFactory.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MyDashboardPageObjects extends basePageFactory {
	WebDriver driver;
	
	public MyDashboardPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Khi WebElement được gọi thì nó sẽ tự đi tìm element (tương tự như findElement/ findElements)
	@FindBy(xpath = "//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']") WebElement contactInfoText;

	public boolean getInfoContactText(String exptectedInfoContact) {
		waitForElementVisible(driver, contactInfoText);
		String actualInfoContact = getElementText(driver, contactInfoText);
		return actualInfoContact.contains(exptectedInfoContact);
	}

}
