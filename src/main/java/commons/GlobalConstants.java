package commons;

import java.io.File;

public class GlobalConstants {
	// System info
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	// App info user
	public static final String DEV_USER_URL = "https://dev.techpanda.org/";
	public static final String STAGING_USER_URL = "https://staging.techpanda.org/";
	public static final String LIVE_USER_URL = "https://live.techpanda.org/";
	
	// App info admin
	public static final String DEV_ADMIN_URL = "http://dev.techpanda.org/index.php/backendlogin";
	public static final String STAGING_ADMIN_URL = "http://staging.techpanda.org/index.php/backendlogin";
	public static final String LIVE_ADMIN_URL = "http://live.techpanda.org/index.php/backendlogin";
	
	public static final String ADMIN_USERNAME = "user01";
	public static final String ADMIN_PASSWORD = "guru99com";
	
	// Wait info
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 10;
	
	// Download/ Upload file
	public static final String UPLOAD_PATH = getFolderSeperator("uploadFiles");
	public static final String DOWNLOAD_PATH = getFolderSeperator("downloadFiles");
	
	// Data test
	public static final String DATA_PATH = getFolderSeperator("dataTest"); 
	
	// Retry Case failed
	public static final int RETRY_NUMBER = 3;
	
	//Browser logs/ Extension
	public static final String BROWSER_LOG_PATH = getFolderSeperator("browserLogs");
	public static final String BROWSER_EXTENSION_PATH = getFolderSeperator("browserExtensions");
	
	//HTML Report Folder
	public static final String REPORTNG_SCREENSHOT_PATH = getFolderSeperator("screenshotReportNG");
	public static final String EXTENT_PATH = getFolderSeperator("htmlExtent");
	public static final String ALLURE_PATH = getFolderSeperator("htmlAllure");
	
	private static String getFolderSeperator(String foldername) {
		return PROJECT_PATH + File.separator + foldername + File.separator;
	}
}
