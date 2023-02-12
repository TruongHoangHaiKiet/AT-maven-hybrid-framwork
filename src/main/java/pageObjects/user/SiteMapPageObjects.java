package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.Navigation.FooterContainerPageObjects;

public class SiteMapPageObjects extends FooterContainerPageObjects {
	WebDriver driver;
	
	public SiteMapPageObjects(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
