package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.Navigation.FooterContainerPageObjects;

public class MyAccountPageObjects extends FooterContainerPageObjects {
	WebDriver driver;
	
	public MyAccountPageObjects(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
