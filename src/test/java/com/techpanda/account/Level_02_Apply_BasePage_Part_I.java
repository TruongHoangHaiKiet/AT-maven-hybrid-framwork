package com.techpanda.account;

import org.testng.annotations.Test;

import commons.basePage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_Apply_BasePage_Part_I {
	// Khai báo = Declare
	WebDriver driver;
	basePage Basepage;
	// Khai báo + Khởi tạo = Declare + Initial
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress;
  
	@BeforeClass
	public void beforeClass() {
		if (osName.startsWith("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else if (osName.startsWith("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_mac");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_linux");
		}
		
		// Khởi tạo Browser
		driver = new FirefoxDriver();
		
		// Đối tượng = Instance 
		Basepage = new basePage();
		
		// Set thời gian chờ để tìm được element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
	  }
	
	@Test
	public void TC_01_Register_Empty_Data() {
		Basepage.clickToElement(driver, "//a[@class='ico-register']");
		Basepage.clickToElement(driver, "//button[@id='register-button']" );

		Assert.assertEquals(Basepage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(Basepage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(Basepage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(Basepage.getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(Basepage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		Basepage.clickToElement(driver, "//a[@class='ico-register']");
		
		Basepage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		Basepage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		Basepage.sendkeyToElement(driver, "//input[@id='Email']", "123@456#%*");
		Basepage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
		Basepage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		Basepage.clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(Basepage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");

	}

	@Test
	public void TC_03_Register_Success() {
		Basepage.clickToElement(driver, "//a[@class='ico-register']");

		Basepage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		Basepage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		Basepage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		Basepage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
		Basepage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		
		Basepage.clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(Basepage.getElementText(driver, "//div[@class='result']"), "Your registration completed" );
		Basepage.clickToElement(driver, "//a[@class='ico-logout']");

	}

	@Test
	public void TC_04_Register_Existing_Email() {
		Basepage.clickToElement(driver, "//a[@class='ico-register']");

		Basepage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		Basepage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		Basepage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		Basepage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
		Basepage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");

		Basepage.clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(Basepage.getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {
		Basepage.clickToElement(driver, "//a[@class='ico-register']");
		
		Basepage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		Basepage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		Basepage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		Basepage.sendkeyToElement(driver, "//input[@id='Password']", "123");
		Basepage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
		
		Basepage.clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(Basepage.getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		Basepage.clickToElement(driver, "//a[@class='ico-register']");
		
		Basepage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		Basepage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		Basepage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		Basepage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
		Basepage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "654321");

		Basepage.clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(Basepage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}

