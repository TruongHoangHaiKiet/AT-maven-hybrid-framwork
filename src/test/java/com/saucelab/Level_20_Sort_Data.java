package com.saucelab;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.baseTest;
import pageObject.SauceLab.InventoryPageObject;
import pageObject.SauceLab.LoginPageObject;
import pageObject.SauceLab.PageGeneratorManager;

public class Level_20_Sort_Data extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	private LoginPageObject loginPage;
	private InventoryPageObject inventoryPage;
	
	// Khai báo + Khởi tạo = Declare + Initial
	String osName = System.getProperty("os.name");
  
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String webUrl) {
		driver = getBrowserDriver(browserName, webUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.enterToUsernameTextbox("standard_user");
		loginPage.enterToPasswordTextbox("secret_sauce");
		inventoryPage = loginPage.clickOnLoginButton();
	  }

	@Test
	public void TC_01_Sort_Name() {
		inventoryPage.selectSortDropdown("Name (A to Z)");
		inventoryPage.sleepInSecond(3);
		Assert.assertTrue(inventoryPage.isProductNameSortAscending_Java8());
		
		inventoryPage.selectSortDropdown("Name (Z to A)");
		inventoryPage.sleepInSecond(3);
		Assert.assertTrue(inventoryPage.isProductNameSortDescending());
	}


	@Test
	public void TC_02_Sort_Price() {
		inventoryPage.selectSortDropdown("Price (low to high)");
		inventoryPage.sleepInSecond(3);
		Assert.assertTrue(inventoryPage.isProductPriceSortAscending());
		
		inventoryPage.selectSortDropdown("Price (high to low)");
		inventoryPage.sleepInSecond(3);
		Assert.assertTrue(inventoryPage.isProductPriceSortDescending());
	}

		

		
	

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

}

