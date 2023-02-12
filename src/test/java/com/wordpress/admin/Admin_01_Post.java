package com.wordpress.admin;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.baseTest;
import pageObject.wordpress.WordPressAdminDashboardPageObject;
import pageObject.wordpress.WordPressAdminLoginPageObject;
import pageObject.wordpress.WordPressAdminPostPageObject;
import pageObject.wordpress.WordPressPageGeneratorManager;
import pageObject.wordpress.WordPressUserHomePageObject;
import pageObject.wordpress.WordPressUserPostPageObject;
import pageObject.wordpress.WordPressUserSearchPageObject;
import reportConfig.ExtentManager;

public class Admin_01_Post extends baseTest { // Dùng kế thừa sẽ không cần thông qua object/ instance để call data nữa
	// Khai báo = Declare
	WebDriver driver;
	String userUrl, adminUrl;
	WordPressAdminDashboardPageObject adminDashboardPage;
	WordPressAdminLoginPageObject adminLoginPage;
	WordPressAdminPostPageObject adminPostPage;
	WordPressUserHomePageObject userHomePage;
	WordPressUserPostPageObject userPostPage;
	WordPressUserSearchPageObject userSearchPage;
	String postTitle, postBody, postCategory, postTagName, authorName;
	String editPostTitle, editPostBody, editPostTagname, editPostCategory;
	int randomNumber = getRandomNumber();

	// Khai báo + Khởi tạo = Declare + Initial
	String osName = System.getProperty("os.name");
	String browserName, username, password;

	@Parameters({ "browser", "userUrl", "adminUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String userUrl, String adminUrl) {
		this.userUrl = userUrl;
		this.adminUrl = adminUrl;
		this.browserName = browserName.toUpperCase();
		driver = getBrowserDriver(browserName, adminUrl);
		username = "kietthh@automation";
		password = "kietthh@automation";

		adminLoginPage = WordPressPageGeneratorManager.getAdminLoginPage(driver);
		adminLoginPage.inputToUsernameTextbox(username);
		adminLoginPage.inputToPasswordTextbox(password);
		
		adminDashboardPage = adminLoginPage.clickOnLoginButton();
		
		postTitle = "Selenium title " + randomNumber;
		postBody = "Selenium Content " + randomNumber;
		postTagName = postTitle.replace(" ", "_").toLowerCase();
		postCategory = "Selenium";
		authorName = "Kietthh@Automation";
		
		editPostTitle = "Appium title " + randomNumber;
		editPostBody = "Appium version " + randomNumber;
		editPostTagname = editPostTitle.replace(" ", "_").toLowerCase();
		editPostCategory = "Appium";
	}

	@Test
	public void Post_01_Create_New_Post(Method method) {
		ExtentManager.startTest(method.getName() + "_" + browserName, "Post_01_Create_New_Post");
		ExtentManager.getTest().log(Status.INFO, "Post_01 - Step 01: Click on Post link on Menu");
		adminPostPage = adminDashboardPage.clickOnPostLink();
		
		ExtentManager.getTest().log(Status.INFO, "Post_01 - Step 02: Click to Add New button");
		adminPostPage.clickOnAddNewButton();

		ExtentManager.getTest().log(Status.INFO, "Post_01 - Step 03: Enter to Post title");
		adminPostPage.enterToPostTitle(postTitle);

		ExtentManager.getTest().log(Status.INFO, "Post_01 - Step 04: Enter to Post body");
		adminPostPage.enterToPostBody(postBody);
		
		ExtentManager.getTest().log(Status.INFO, "Post_01 - Step 05: Select Categories checkbox");
		adminPostPage.checkCategoriesCheckbox(postCategory);
		
		ExtentManager.getTest().log(Status.INFO, "Post_01 - Step 06: Enter to Tag textbox");
		adminPostPage.enterToTagTextbox(postTagName);
		
		ExtentManager.getTest().log(Status.INFO, "Post_01 - Step 07: Click on Add tag button");
		adminPostPage.clickToAddTagbutton();
		
		ExtentManager.getTest().log(Status.INFO, "Post_01 - Step 07: Click on Publish button");
		adminPostPage.clickOnPublishButton();

		ExtentManager.getTest().log(Status.INFO, "Post_01 - Step 08: Verify 'Post published.' message is displayed ");
		verifyTrue(adminPostPage.isPostCreatedMesageDisplay("Post published."));

	}

	@Test
	public void Post_02_Search_View_Post(Method method) {
		ExtentManager.startTest(method.getName() + "_" + browserName, "Post_02_Search_View_Post");
		ExtentManager.getTest().log(Status.INFO, "Post_02 - Step 01: Click on Post link on sidebar");
		adminPostPage.clickOnPostLink();

		ExtentManager.getTest().log(Status.INFO, "Post_02 - Step 02: Enter to Search textbox");
		adminPostPage.enterToSearchTextbox(postTitle);

		ExtentManager.getTest().log(Status.INFO, "Post_02 - Step 03: Click on Search Posts button");
		adminPostPage.clickOnSearchPostsButton();
		
		ExtentManager.getTest().log(Status.INFO, "Post_02 - Step 04: Verify the Created Post is displayed");
		verifyTrue(adminPostPage.isPostInfoDisplayed("Title",postTitle));
		verifyTrue(adminPostPage.isPostInfoDisplayed("Author",authorName));
		verifyTrue(adminPostPage.isPostInfoDisplayed("Categories",postCategory));
		verifyTrue(adminPostPage.isPostInfoDisplayed("Tags",postTagName));
		
		ExtentManager.getTest().log(Status.INFO, "Post_02 - Step 05: Open User site");
		userHomePage = adminPostPage.openWordPressUserHomePage(userUrl);

		ExtentManager.getTest().log(Status.INFO,
				"Post_02 - Step 06: Verify the Created Post is displayed at Home Page");
		verifyTrue(userHomePage.isPostTitleDisplayed(postTitle));
		verifyTrue(userHomePage.isPostContentDisplayed(postBody));

		ExtentManager.getTest().log(Status.INFO, "Post_02 - Step 07: Open Post detail");
		userPostPage = userHomePage.openPostDetail(postTitle);

		ExtentManager.getTest().log(Status.INFO, "Post_02 - Step 08: Verify Post title and body info");
		userPostPage.isPostTitleDetailDisplayed(postTitle);
		userPostPage.isPostAuthorDetailDisplayed(authorName);
		userPostPage.isPostBodytDetailDisplayed(postBody);
		userPostPage.isPostTagNameDetailDisplayed(postTagName);
	}

	@Test
	public void Post_03_Edit_Post(Method method) {
		ExtentManager.startTest(method.getName() + "_" + browserName, "Post_03_Edit_Post");
		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 01: Open Admin site");
		adminDashboardPage = userPostPage.openWordPressAdminDashboardPage(adminUrl);

		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 02: Click on Post link on sidebar");
		adminPostPage = adminDashboardPage.clickOnPostLink();
		
		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 03: Enter to Search textbox");
		adminPostPage.enterToSearchTextbox(postTitle);
		
		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 04: Click on Search Posts button");
		adminPostPage.clickOnSearchPostsButton();
		
		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 05: Click on Edit button");
		adminPostPage.clickOnActionByTitle(postTitle, "Edit");

		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 06: Enter to Post title");
		adminPostPage.enterToPostTitle(editPostTitle);
		
		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 07: Enter to Post body");
		adminPostPage.enterToPostBody(editPostBody);

		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 08: Check new Categories checkbox");
		// Uncheck cái cũ
		adminPostPage.uncheckCategoriesCheckbox(postCategory);
		// Check cái mới
		adminPostPage.checkCategoriesCheckbox(editPostCategory);

		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 09: Remove Tag by name");
		adminPostPage.removeTagByName(postTagName);
		
		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 10: Enter to Tag textbox");
		adminPostPage.enterToTagTextbox(editPostTagname);
		
		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 11: Click on Add tag button");
		adminPostPage.clickToAddTagbutton();

		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 12: Click on Update button");
		adminPostPage.clickOnPublishButton();

		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 13: Verify 'Post updated.' message is displayed ");
		verifyTrue(adminPostPage.isPostCreatedMesageDisplay("Post updated."));
		
		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 14: Click on Post link on Menu");
		adminPostPage.clickOnPostLink();

		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 15: Enter to Search textbox");
		adminPostPage.enterToSearchTextbox(editPostTitle);

		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 16: Click on Search Posts button");
		adminPostPage.clickOnSearchPostsButton();
		
		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 17: Verify the Updated Post is displayed");
		verifyTrue(adminPostPage.isPostInfoDisplayed("Title",editPostTitle));
		verifyTrue(adminPostPage.isPostInfoDisplayed("Author",authorName));
		verifyTrue(adminPostPage.isPostInfoDisplayed("Categories",editPostCategory));
		verifyTrue(adminPostPage.isPostInfoDisplayed("Tags",editPostTagname));
		
		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 18: Open User site");
		userHomePage = adminPostPage.openWordPressUserHomePage(userUrl);
		
		ExtentManager.getTest().log(Status.INFO,
				"Post_03 - Step 17: Verify the Updated Post is displayed at Home Page");
		verifyTrue(userHomePage.isPostTitleDisplayed(editPostTitle));
		verifyTrue(userHomePage.isPostContentDisplayed(editPostBody));

		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 19: Open Post detail");
		userPostPage = userHomePage.openPostDetail(editPostTitle);

		ExtentManager.getTest().log(Status.INFO, "Post_03 - Step 20: Verify Post title and body info");
		userPostPage.isPostTitleDetailDisplayed(editPostTitle);
		userPostPage.isPostAuthorDetailDisplayed(authorName);
		userPostPage.isPostBodytDetailDisplayed(editPostBody);
		userPostPage.isPostTagNameDetailDisplayed(editPostTagname);
	}

	@Test
	public void Post_04_Delete_Post(Method method) {
		ExtentManager.startTest(method.getName() + "_" + browserName, "Post_04_Delete_Post");
		ExtentManager.getTest().log(Status.INFO, "Post_04 - Step 01: Open Admin site");
		adminDashboardPage = userPostPage.openWordPressAdminDashboardPage(adminUrl);
		
		ExtentManager.getTest().log(Status.INFO, "Post_04 - Step 02: Click on Post link on sidebar");
		adminPostPage = adminDashboardPage.clickOnPostLink();
		
		ExtentManager.getTest().log(Status.INFO, "Post_04 - Step 03: Enter to Search textbox");
		adminPostPage.enterToSearchTextbox(editPostTitle);

		ExtentManager.getTest().log(Status.INFO, "Post_04 - Step 04: Click on Search Posts button");
		adminPostPage.clickOnSearchPostsButton();

		ExtentManager.getTest().log(Status.INFO, "Post_04 - Step 05: Click on Trash button");
		adminPostPage.clickOnActionByTitle(editPostTitle, "Trash");
		
		ExtentManager.getTest().log(Status.INFO, "Post_04 - Step 06: Verify '1 post move to the Trash' message is displayed ");
		adminPostPage.sleepInSecond(2);
		verifyTrue(adminPostPage.isPostCreatedMesageDisplay("1 post moved to the Trash."));
		
		ExtentManager.getTest().log(Status.INFO, "Post_04 - Step 07: Enter to Search textbox");
		adminPostPage.enterToSearchTextbox(editPostTitle);

		ExtentManager.getTest().log(Status.INFO, "Post_04 - Step 08: Click on Search Posts button");
		adminPostPage.clickOnSearchPostsButton();
		
		ExtentManager.getTest().log(Status.INFO, "Post_04 - Step 09: Verify 'No posts found ' message is displayed in Post table ");
		verifyTrue(adminPostPage.isPostDeletedMesageDisplay("No posts found."));
		
		ExtentManager.getTest().log(Status.INFO, "Post_04 - Step 10: Open User site");
		userHomePage= adminPostPage.openWordPressUserHomePage(userUrl);
		
		ExtentManager.getTest().log(Status.INFO,
				"Post_04 - Step 11: Verify the Deleted Post is not displayed at Home Page");
		verifyTrue(userHomePage.isPostTitleUnDisplayed(editPostTitle));
		verifyTrue(userHomePage.isPostContentUnDisplayed(editPostBody));
		
//		ExtentManager.getTest().log(Status.INFO, "Post_04 - Step 12: Enter Delete Post to Search textbox at Home Page");
//		
//		ExtentManager.getTest().log(Status.INFO, "Post_04 - Step 13: Click on Search button");
//		
//		ExtentManager.getTest().log(Status.INFO, "Post_04 - Step 14: Verify 'Sorry, no post found on this archive.' message is displayed");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
