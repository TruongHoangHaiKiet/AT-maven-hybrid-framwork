package com.jquery.data_table;

import org.testng.annotations.Test;

import commons.baseTest;
import pageObjects.Jquery.HomePageObjects;
import pageObjects.Jquery.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_12_Data_Table_Part_I extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	HomePageObjects homePageObjects;
	
	// Khai báo + Khởi tạo = Declare + Initial
	String osName = System.getProperty("os.name");
	String emailAddress;
  
	@Parameters({"browser", "QuickGridUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePageObjects = PageGeneratorManager.getHomePageObjects(driver);
	 }
	
	@Test
	public void TC_01_Header_Textbox() {
		// Làm sao để nhập liệu vào các textbox trong Header
		// Làm sao để verify dữ liệu của 1 nước nào đó
		// Parameters: Females/ Country/ Males/ Total
		
		homePageObjects.enterToTextboxByHeaderName("Country","Argentina");
		homePageObjects.sleepInSecond(3);
		Assert.assertTrue(homePageObjects.isRowValuesDisplay("338282", "Argentina", "349238", "687522"));
		homePageObjects.refreshCurrentPage(driver);
		
		homePageObjects.enterToTextboxByHeaderName("Total","553353");
		homePageObjects.sleepInSecond(3);
		Assert.assertTrue(homePageObjects.isRowValuesDisplay("276880", "Angola", "276472", "553353"));
		homePageObjects.refreshCurrentPage(driver);
		
		homePageObjects.enterToTextboxByHeaderName("Females","12253515");
		homePageObjects.sleepInSecond(3);
		Assert.assertTrue(homePageObjects.isRowValuesDisplay("12253515", "AFRICA", "12599691", "24853148"));
		homePageObjects.refreshCurrentPage(driver);
		
		
	}
	
	@Test
	public void TC_02_Action_Icon() {
		// Làm sao để click vào icon Edit/ Delete của 1 nước bất kỳ
		homePageObjects.clickOnActionIconByCountryName("Argentina","remove");
		homePageObjects.sleepInSecond(3);
		homePageObjects.clickOnActionIconByCountryName("Angola","remove");
		homePageObjects.sleepInSecond(3);
		
		homePageObjects.refreshCurrentPage(driver);
		
		homePageObjects.clickOnActionIconByCountryName("AFRICA", "edit");
		homePageObjects.refreshCurrentPage(driver);
		homePageObjects.clickOnActionIconByCountryName("Algeria", "edit");
		homePageObjects.refreshCurrentPage(driver);
	}
	
	@Test
	public void TC_03_Paging() {
		// Làm sao để mở đến 1 trang bất kỳ (page number)
		homePageObjects.clickToPageByNumber("12");
		Assert.assertTrue(homePageObjects.isPageNumberActived("12"));
		homePageObjects.sleepInSecond(2);
		
		homePageObjects.clickToPageByNumber("17");
		Assert.assertTrue(homePageObjects.isPageNumberActived("17"));
		homePageObjects.sleepInSecond(2);
		
		homePageObjects.clickToPageByNumber("8");
		Assert.assertTrue(homePageObjects.isPageNumberActived("8"));
		homePageObjects.sleepInSecond(2);
		
		homePageObjects.clickToPageByNumber("1");
		Assert.assertTrue(homePageObjects.isPageNumberActived("1"));
		homePageObjects.sleepInSecond(2);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

