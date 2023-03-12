package commons;

import java.io.File;

public class GlobalConstants {
	// System info
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");

	// App info user
	public static final String DEV_USER_URL = "https://dev.techpanda.org/";
	public static final String TESTING_USER_URL = "https://testing.techpanda.org/";
	public static final String STAGING_USER_URL = "https://staging.techpanda.org/";
	public static final String LIVE_USER_URL = "https://live.techpanda.org/";

	// App info admin
	public static final String DEV_ADMIN_URL = "http://dev.techpanda.org/index.php/backendlogin";
	public static final String TESTING_ADMIN_URL = "http://testing.techpanda.org/index.php/backendlogin";
	public static final String STAGING_ADMIN_URL = "http://staging.techpanda.org/index.php/backendlogin";
	public static final String LIVE_ADMIN_URL = "http://live.techpanda.org/index.php/backendlogin";

	public static final String ADMIN_USERNAME = "user01";
	public static final String ADMIN_PASSWORD = "guru99com";

	// Wait info
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 10;

	// Download/ Upload file
	public static final String UPLOAD_PATH = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_PATH = PROJECT_PATH + "/downloadFiles/";

	// Data test
	public static final String DATA_PATH = PROJECT_PATH + File.separator + "dataTest" + File.separator;

	// Retry Case failed
	public static final int RETRY_NUMBER = 3;

	// Browser logs/ Extension
	public static final String BROWSER_LOG_PATH = PROJECT_PATH + File.separator + "browserLogs" + File.separator;
	public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + File.separator + "browserExtensions" + File.separator;

	// HTML Report Folder
	public static final String REPORTNG_SCREENSHOT_PATH = PROJECT_PATH + File.separator + "screenshotReportNG" + File.separator;
	public static final String EXTENT_PATH = PROJECT_PATH + File.separator + "htmlExtent" + File.separator;
	public static final String ALLURE_PATH = PROJECT_PATH + File.separator + "htmlAllure" + File.separator;

}
