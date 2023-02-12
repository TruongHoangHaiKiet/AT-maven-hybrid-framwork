package pageObjects.user;

import org.openqa.selenium.WebDriver;
import pageObjects.Navigation.SideBarMyAccountPageObjects;

public class MyApplicationPageObjects extends SideBarMyAccountPageObjects {
	WebDriver driver;
	
	public MyApplicationPageObjects(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
