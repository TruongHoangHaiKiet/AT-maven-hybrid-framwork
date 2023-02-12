package com.jquery.data_table;

import org.testng.annotations.Test;

import commons.baseTest;
import pageObjects.Jquery.HomePageObjects;
import pageObjects.Jquery.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Level_12_Data_Table_Part_II extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	HomePageObjects homePageObjects;
	
	// Khai báo + Khởi tạo = Declare + Initial
	String osName = System.getProperty("os.name");
	String emailAddress;
  
	@Parameters({"browser", "AppendGridUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePageObjects = PageGeneratorManager.getHomePageObjects(driver);
	 }
	
	@Test
	public void TC_01_Header_Textbox() {
		// Làm sao để thao tác ở 1 ô bất kỳ
		// Dựa vào thứ tự của cột (nằm ở thứ mấy)
		// Dựa vào thứ tự của hàng (nằm ở thứ mấy)
		// Giao giữa hàng và cột sẽ là ô mình cần
		
		homePageObjects.clickOnLoadData();
		
		homePageObjects.enterToTextboxByHeaderNameAndRowName("Artist", "2", "Automation FC");
		homePageObjects.sleepInSecond(3);
		
		homePageObjects.enterToTextboxByHeaderNameAndRowName("Album", "4", "Ready for Automation");
		homePageObjects.sleepInSecond(3);
		
		homePageObjects.enterToTextboxByHeaderNameAndRowName("Price", "5", "1505");
		homePageObjects.sleepInSecond(3);
	
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

