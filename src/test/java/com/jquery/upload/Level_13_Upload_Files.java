package com.jquery.upload;

import org.testng.annotations.Test;

import commons.baseTest;
import pageObjects.Jquery.HomePageObjects;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_13_Upload_Files extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	HomePageObjects homePageObjects;
	String appleFileName = "apple.png";
	String lenovoFileName = "lenovo.png";
	String microsoftFileName = "microsoft.png";
	String samsungFileName = "samsung.png";
	
	// Khai báo + Khởi tạo = Declare + Initial
	String osName = System.getProperty("os.name");
	String emailAddress;
  
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePageObjects = new HomePageObjects (driver);
	 }
	
	@Test
	public void TC_01_Upload_a_file() {
		homePageObjects.uploadMultipleFiles(driver, appleFileName);
		Assert.assertTrue(homePageObjects.isFileNameLoadedSuccess(appleFileName));
		homePageObjects.clickOnStartButton();
		Assert.assertTrue(homePageObjects.isFileNameUploadedSuccess(appleFileName));
	}
	
	@Test
	public void TC_02_Upload_multiple_files() {
		homePageObjects.refreshCurrentPage(driver);
		homePageObjects.uploadMultipleFiles(driver, appleFileName, lenovoFileName, samsungFileName, microsoftFileName);
		
		Assert.assertTrue(homePageObjects.isFileNameLoadedSuccess(appleFileName));
		Assert.assertTrue(homePageObjects.isFileNameLoadedSuccess(lenovoFileName));
		Assert.assertTrue(homePageObjects.isFileNameLoadedSuccess(samsungFileName));
		Assert.assertTrue(homePageObjects.isFileNameLoadedSuccess(microsoftFileName));
		
		homePageObjects.clickOnStartButton();
		Assert.assertTrue(homePageObjects.isFileNameUploadedSuccess(appleFileName));
		Assert.assertTrue(homePageObjects.isFileNameUploadedSuccess(lenovoFileName));
		Assert.assertTrue(homePageObjects.isFileNameUploadedSuccess(samsungFileName));
		Assert.assertTrue(homePageObjects.isFileNameUploadedSuccess(microsoftFileName));
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

