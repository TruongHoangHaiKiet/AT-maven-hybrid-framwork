package pageObjects.user;

import org.openqa.selenium.WebDriver;
import pageObjects.Navigation.SideBarMyAccountPageObjects;

public class MyProductReviewPageObjects extends SideBarMyAccountPageObjects {
	WebDriver driver;
	
	public MyProductReviewPageObjects(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
