package com.techpanda.account;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import pageObjects.user.UserHomePageObjects;
import pageObjects.user.UserLoginPageObjects;
import pageObjects.user.MyDashboardPageObjects;

public class Level_03_Page_Object_Pattern { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	UserHomePageObjects homePage;
	UserLoginPageObjects loginPage;
	MyDashboardPageObjects myDashboardPage;
	
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
		
		// Set thời gian chờ để tìm được element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/");
		emailAddress = "afc" + randomNumber() + "@mail.vn";
		
		// Khởi tạo HomePageObject để dùng
		homePage = new UserHomePageObjects(driver);
	  }

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		// Home Page > click My account > Login Page
		homePage.clickToMyAccountLink();
		
		// Khởi tạo LoginPageObject để dùng
		loginPage = new UserLoginPageObjects(driver);
		
		loginPage.inputToEmailTextbox("");
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();
	
		assertEquals(loginPage.getEmailEmptyErrorMessage(), "This is a required field.");
		assertEquals(loginPage.getPasswordEmptyErrorMessage(), "This is a required field.");
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		// Home Page > click My account > Login Page
		homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("123@456.789");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();
		
		assertEquals(loginPage.getEmailInvalidErrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_LoginWithIncorrectEmail() {
		homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();

		assertEquals(loginPage.getEmailIncorrectErrorMessage(), "Invalid login or password.");
	}

	@Test
	public void TC_04_LoginWithInvalidPassword() {
		homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToLoginButton();

		assertEquals(loginPage.getPasswordInvalidErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("auto_test" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox(randomNumber() + "");
		loginPage.clickToLoginButton();
		
		assertEquals(loginPage.getPasswordIncorrectErrorMessage(), "Invalid login or password.");
	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailTextbox("automationfc.vn@gmail.com");
		loginPage.inputToPasswordTextbox("123123");
		loginPage.clickToLoginButton();	
		
		myDashboardPage = new MyDashboardPageObjects(driver);
		assertTrue(myDashboardPage.getInfoContactText("Automation FC"));
		assertTrue(myDashboardPage.getInfoContactText("automationfc.vn@gmail.com"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
}

