package com.facebook;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Element_Status_Knowledge {
	// Khai báo 1 biến đại diện cho Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By reEnter = By.cssSelector("input[name='reg_email_confirmation__']");
	WebDriverWait explicitWait;

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
		explicitWait = new WebDriverWait(driver, 10);
		
		// Set thời gian chờ để tìm được element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com");
		
	}

	@Test
	public void TC_01_Visible() {
		// Có trong cây HTML
		// Có trên UI
		// Có tìm thấy trong element nên không cần chờ hết timeout của implicitWait
		// Click vao register button
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		
		// Nhập vào email field
		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("abcautomation@gmail.com");
		
		System.out.println("Wait Visible Start time: " + getCurrentTime());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reEnter));
		System.out.println("Wait Visible End time: " + getCurrentTime());
		
		System.out.println("Check displayed Start time: " + getCurrentTime());
		Assert.assertTrue(driver.findElement(reEnter).isDisplayed());
		System.out.println("Verify displayed End time: " + getCurrentTime());
	}

	@Test
	public void TC_02_Invisible_in_DOM() {
		// Có trong cây HTML
		// Không có trên UI
		// Có tìm thấy trong element nên không cần chờ hết timeout của implicitWait
		// Click vao register button
		driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();

		System.out.println("Wait Invisible Start time: " + getCurrentTime());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reEnter));
		System.out.println("Wait Invisible End time: " + getCurrentTime());

		System.out.println("Check undisplayed Start time: " + getCurrentTime());
		Assert.assertFalse(driver.findElement(reEnter).isDisplayed());
		System.out.println("Verify undisplayed End time: " + getCurrentTime());
		
	}
	
	@Test
	public void TC_03_Invisible_Not_in_DOM() {
		// Click để close form đăng ký đi
		driver.findElement(By.xpath("//div[text()= 'Đăng ký']/parent::div/preceding-sibling::img")).click();
		
		// Không có trong cây HTML
		// Không có trên UI
		// Không có element trong cây HTML/ DOM
		// Chờ hết timeout (Trong thời gian chờ vẫn tìm đi tìm lại)
		// Khi hết timeout rồi sẽ đánh fail test case
		
		// Wait mất ít nhất là 15s
		System.out.println("Wait Invisible Start time: " + getCurrentTime());
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reEnter));
		System.out.println("Wait Invisible End time: " + getCurrentTime());

		// 
		System.out.println("Check undisplayed Start time: " + getCurrentTime());
		Assert.assertFalse(driver.findElement(reEnter).isDisplayed());
		System.out.println("Verify undisplayed End time: " + getCurrentTime());	
	}
	
	public String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long second ) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}