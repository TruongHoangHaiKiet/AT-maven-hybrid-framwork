package commons;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {
	private WebDriver driver;
	protected final Log log;
	String projectPath = GlobalConstants.PROJECT_PATH;
	
	public baseTest() {
		log = LogFactory.getLog(getClass());
	}
	
	
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		
		switch (browserList) {
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case FIREFOX_HEADLESS:
			FirefoxOptions firefoxHeadOptions = new FirefoxOptions();
			firefoxHeadOptions.addArguments("-headless");
			firefoxHeadOptions.addArguments("window-size=1920x1080");
			driver = WebDriverManager.firefoxdriver().capabilities(firefoxHeadOptions).create();
			break;
		case CHROME:
			// Lastest browser driver version - create() auto download and initiate driver of browser
			driver = WebDriverManager.chromedriver().create();
			
			// Specific Browser driver version = browser version
//			WebDriverManager.chromedriver().driverVersion("101.0.4951.67").setup();
			
			// Base on: Browser version
//			WebDriverManager.chromedriver().browserVersion("101.0.4951.67").setup();
			break;
		case CHROME_HEADLESS:
			ChromeOptions chromeHeadOptions = new ChromeOptions();
			chromeHeadOptions.addArguments("--headless");
			chromeHeadOptions.addArguments("window-size=1920x1080");
			driver = WebDriverManager.chromedriver().capabilities(chromeHeadOptions).create();
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		case IE:
			driver = WebDriverManager.iedriver().arch32().create();
			break;	
		default:
			throw new RuntimeException("Browser is not found");
		}
		
		// Set thời gian chờ để tìm được element
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.LIVE_USER_URL);
		return driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName, String urlValue) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		
		switch (browserList) {
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case CHROME:
			// Lastest browser driver version - create() auto download and initiate driver of browser
			driver = WebDriverManager.chromedriver().create();
			
			// Specific Browser driver version = browser version
//			WebDriverManager.chromedriver().driverVersion("101.0.4951.67").setup();
			
			// Base on: Browser version
//			WebDriverManager.chromedriver().browserVersion("101.0.4951.67").setup();
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		default:
			throw new RuntimeException("Browser is not found");
		}
		
		// Set thời gian chờ để tìm được element
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(urlValue);
		return driver;
	}
	
	protected int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
	
	protected String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}
	
	protected boolean verifyTrue(boolean condition) {
		boolean status = true;
		try {
			Assert.assertTrue(condition);
			log.info("-------------------------- Passed -----------------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("-------------------------- Failed -----------------------------");
		}
		return status;
	}
	
	protected boolean verifyFalse(boolean condition) {
		boolean status = true;
		try {
			Assert.assertFalse(condition);
			log.info("-------------------------- Passed -----------------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("-------------------------- Failed -----------------------------");
		}
		return status;
	}
	
	protected boolean verifyEquals(Object actual, Object expected) {
		boolean status = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info("-------------------------- Passed -----------------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("-------------------------- Failed -----------------------------");
		}
		return status;
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	@BeforeSuite
	public void deleteAllFilesInScreenshotFolder() {
		deleteAllFilesInReportNGScreenshotFolder();
	}
	
	public void deleteAllFilesInReportNGScreenshotFolder() {
		try {
			File file = new File(GlobalConstants.REPORTNG_SCREENSHOT_PATH);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	protected void showBrowserConsoleLogs() {
		if (driver.toString().contains("chrome")||driver.toString().contains("edge")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging: logList) {
				if (logging.getLevel().toString().toLowerCase().contains("error")) {
					log.info("----------" + logging.getLevel().toString() + "----------\n" + logging.getMessage());
				}
			}
		}
	}

	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String osName = GlobalConstants.OS_NAME;
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("internetexplorer")) {
				browserDriverName = "IEDriverServer";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else if (driverInstanceName.contains("opera")) {
				browserDriverName = "operadriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("Window")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}




