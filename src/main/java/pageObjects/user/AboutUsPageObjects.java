package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.Navigation.FooterContainerPageObjects;

public class AboutUsPageObjects extends FooterContainerPageObjects {
	WebDriver driver;
	
	public AboutUsPageObjects(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
