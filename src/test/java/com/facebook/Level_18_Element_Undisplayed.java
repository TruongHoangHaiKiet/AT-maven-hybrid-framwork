package com.facebook;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.baseTest;
import pageObjects.facebook.LoginPageObjects;
import pageObjects.facebook.PageGeneratorManager;

public class Level_18_Element_Undisplayed extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	
	// Khai báo + Khởi tạo = Declare + Initial
	String osName = System.getProperty("os.name");
	String emailAddress;
	LoginPageObjects loginPage;
  
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		loginPage = PageGeneratorManager.getLoginPageObjects(driver);
	  }

	@Test
	public void TC_01_Visible() {
		// Có trong cây HTML
		// Có trên UI
		// Có tìm thấy trong element nên không cần chờ hết timeout của implicitWait
		// Click vao register button
		loginPage.clickToRegisterButton();
		
		// Nhập vào email field
		loginPage.inputToEmailTextbox("abcautomation@gmail.com");
		
		System.out.println("Wait Visible Start time: " + getCurrentTime());
		loginPage.waitForReEnterVisible();
		System.out.println("Wait Visible End time: " + getCurrentTime());
		
		System.out.println("Check displayed Start time: " + getCurrentTime());
		Assert.assertTrue(loginPage.isReEnterDisplayed());
		System.out.println("Verify displayed End time: " + getCurrentTime());
	}

	@Test
	public void TC_02_Invisible_in_DOM() {
		// Có trong cây HTML
		// Không có trên UI
		// Có tìm thấy trong element nên không cần chờ hết timeout của implicitWait
		// Clear values in Email textbox
		loginPage.inputToEmailTextbox("");

		System.out.println("Wait Invisible in DOM Start time: " + getCurrentTime());
		loginPage.waitForReEnterInvisibleInDOM();
		System.out.println("Wait Invisible in DOM End time: " + getCurrentTime());

		System.out.println("Check undisplayed in DOM Start time: " + getCurrentTime());
		Assert.assertTrue(loginPage.isReEnterUndisplayed());
		System.out.println("Verify undisplayed in DOM End time: " + getCurrentTime());
		
	}
	
	@Test
	public void TC_03_Invisible_Not_in_DOM() {
		// Click để close form đăng ký đi
		loginPage.closeSignUpForm();
		
		// Không có trong cây HTML
		// Không có trên UI
		// Không có element trong cây HTML/ DOM
		// Chờ hết timeout (Trong thời gian chờ vẫn tìm đi tìm lại)
		// Khi hết timeout rồi sẽ đánh fail test case
		
		// Wait mất ít nhất là 15s
		System.out.println("Wait Invisible not in DOM Start time: " + getCurrentTime());
		loginPage.waitForReEnterInvisibleNotInDOM();
		System.out.println("Wait Invisible not in DOM End time: " + getCurrentTime());

		// Fail sau 15s timeout
		// Chờ hết timeout (Trong thời gian chờ vẫn tìm đi tìm lại)
		// Khi hết timeout rồi sẽ đánh fail testcase
		System.out.println("Check undisplayed not in DOM Start time: " + getCurrentTime());
		Assert.assertTrue(loginPage.isReEnterUndisplayed());
		System.out.println("Verify undisplayed not in DOM End time: " + getCurrentTime());	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

