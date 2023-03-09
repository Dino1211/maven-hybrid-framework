package com.wordpress.admin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.AdminLoginPO;
import pageObjects.wordpress.AdminPostAddNewPO;
import pageObjects.wordpress.AdminPostSearchPO;
import pageObjects.wordpress.PageGeneratorManager;
import pageObjects.wordpress.UserHomePO;
import pageObjects.wordpress.UserPostDetailPO;
import pageObjects.wordpress.UserSearchPostPO;

public class Post_01_Creat_Red_Update_Delete_Search extends BaseTest {
	String adminUsername = "hautravel";
	String adminPassword = "Hn@123456";
	String searchPostUrl ;
	
	int randomNumber = generateFakeNumber();
	String postTitle = "Live Colding Title" + randomNumber;
	String postBody = "Live Colding Body" + randomNumber;
	String editPostTitel = "Edit Colding Title" + randomNumber;
	String editPostbody = "Edit Colding Body" + randomNumber;
	String authorName = "Hautravel Admin";
	String amindUrl, endUserUrl;
	String currentDay = getCurrentDay() ;

	@Parameters({ "browser", "urlAdmin", "urlUser"})
	@BeforeMethod
	public void setUP(@Optional("chrome") String browsername, String adminUrl, String endUserUrl) {
		log.info("Pre-condition - Step 01: Open browser and Admin site");
		this.amindUrl = adminUrl;
		this.endUserUrl = endUserUrl;
		driver = getBrowserDriver(browsername, this.amindUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		log.info("Pre-condition - Step 02: Enter to Username textbox with value: " + adminUsername);
		adminLoginPage.enterToUsernameTextbox(adminUsername);

		log.info("Pre-condition - Step 03: Enter to Password textbox with value: " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);

		log.info("Pre-condition - Step 04: Click to Login button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();
	}
	@Test
	public void Post_01_Creat_New_Post() {
		log.info("Creat_Post - Step 01: Click to 'Posts' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Creat_Post - Step 02: Get 'Search Posts' page Url");
		searchPostUrl = adminPostSearchPage.getPageUrl(driver);
		
		log.info("Creat_Post - Step 03: Click to 'Add new' button");
		adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton();
		
		log.info("Creat_Post - Step 04: Enter to post title");
		adminPostAddNewPage.enterToAddNewPostTitle(postTitle);
		
		log.info("Creat_Post - Step 05: Enter to body");
		adminPostAddNewPage.enterToAddNewPostBody(postBody);
		
		log.info("Creat_Post - Step 06: Click to 'Publish'button");
		adminPostAddNewPage.clickToPublishOrUpdateButton();

		log.info("Creat_Post - Step 08: Verify 'Post published.' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post published."));
//	}
//	@Test
//	public void Post_02_Search_Post() {		
		log.info("Search_Post - Step 01: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
		
		log.info("Search_Post - Step 02: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Search_Post - Step 03: Click 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Search_Post - Step 04: Verify Search table contains '" + postTitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("tiltle", postTitle));
		
		log.info("Search_Post - Step 05: Verify Search table contains '" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));
		
		log.info("Search_Post - Step 06: Open End User site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Search_Post - Step 07: Verify Post infor displayed at Home page");
		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostAuthor(postTitle, authorName));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostCurrentDay(postTitle, currentDay));
		
		log.info("Search_Post - Step 08: Click to Post Title");
		userPostDetailPage = userHomePage.clickToPostTitle(postTitle);
		
		log.info("Search_Post - Step 09: Verify Post infor displayed at Post detail page");
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostAuthor(postTitle, authorName));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostCurrentDay(postTitle, currentDay));
//	}
//	@Test
//	public void User_03_Edit_Post() {
		log.info("Edit_Post - Step 01: Open Admin site");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.amindUrl);
		
		log.info("Edit_Post - Step 02: click to 'Posts' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Edit_Post - Step 03: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Edit_Post - Step 04: Click 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Edit_Post - Step 05: Click to Post title link and navigate to Edit post page");
		adminPostAddNewPage = adminPostSearchPage.clickToPostTiltleLink(postTitle);
		
		log.info("Edit_Post - Step 06: Enter to post title");
		adminPostAddNewPage.enterToAddNewPostTitle(editPostTitel);
		
		log.info("Edit_Post - Step 07: Enter to post body");
		adminPostAddNewPage.enterToEditPostBody(editPostbody);
	
		log.info("Edit_Post - Step 08: Click to 'Update' button");
		adminPostAddNewPage.clickToPublishOrUpdateButton();
		
		log.info("Edit_Post - Step 09: Verify 'Post updated.' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post updated."));
		
		log.info("Search_Post - Step 10: Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);
		
		log.info("Search_Post - Step 11: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitel);
		
		log.info("Search_Post - Step 12: Click 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Search_Post - Step 13: Verify Search table contains '" + editPostTitel + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("tiltle", editPostTitel));
		
		log.info("Search_Post - Step 14: Verify Search table contains '" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));
		
		log.info("Search_Post - Step 15: Open End User site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Search_Post - Step 16: Verify Post infor displayed at Home page");
		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(editPostTitel));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(editPostTitel, editPostbody));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostAuthor(editPostTitel, authorName));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostCurrentDay(editPostTitel, currentDay));
		
		log.info("Search_Post - Step 17: Click to Post Title");
		userPostDetailPage = userHomePage.clickToPostTitle(editPostTitel);
		
		log.info("Search_Post - Step 18: Verify Post infor displayed at Post detail page");
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(editPostTitel));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(editPostTitel, editPostbody));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostAuthor(editPostTitel, authorName));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostCurrentDay(editPostTitel, currentDay));
//	}
//	@Test
//	public void User_04_Delete_Post() {
		log.info("Delete_Post - Step 01: Open Admin site");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.amindUrl);
		
		log.info("Delete_Post - Step 02: click to 'Posts' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Delete_Post - Step 03: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(postTitle);
		
		log.info("Delete_Post - Step 04: Click 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Delete_Post - Step 05: Select Post Deatil checkbox");
		adminPostSearchPage.selectPostCheckboxByTitle(editPostTitel);
		
		log.info("Delete_Post - Step 06: Select 'Move to Trash' item in dropdown");
		adminPostSearchPage.selectTextItemActionDropdown("Move to Trash");
		
		log.info("Delete_Post - Step 07: Click to 'Apply' button");
		adminPostSearchPage.clickToApplyButton();
		
		log.info("Delete_Post - Step 08: Verify '1 post moved to the Trash.' message is displayed");
		verifyTrue(adminPostSearchPage.isMoveToTrashMessageDiplayed("1 post moved to the Trash."));
		
		log.info("Delete_Post - Step 09: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitel);
		
		log.info("Delete_Post - Step 10: Click 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Delete_Post - Step 11: Verify 'No posts found.' message is displayed");
		verifyTrue(adminPostSearchPage.isNoPostFoundhMessageDiplayed("No posts found."));
		
		log.info("Delete_Post - Step 12: Open End User site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);
		
		log.info("Delete_Post - Step 13: Verify Post title undisplayed at Home page");
		verifyTrue(userHomePage.isPostInforUnDisplayedWithPostTitle(editPostTitel));
		
		log.info("Delete_Post - Step 14: Enter to Search textbox");
		userHomePage.enterToSearchTextbox(editPostTitel);
		
		log.info("Delete_Post - Step 15: Click 'Search' button");
		userSearchPostPage = userHomePage.clickToSearchPostButton();
		

		log.info("Delete_Post - Step 16: Verify 'Nothing Found.' message is displayed");
		verifyTrue(userSearchPostPage.isNothingFoundMessageDiplayed(editPostTitel));
		
}



	@AfterMethod(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
		//driver.quit();
	}

	WebDriver driver;
	AdminLoginPO adminLoginPage;
	AdminDashboardPO adminDashboardPage;
	AdminPostSearchPO adminPostSearchPage;
	AdminPostAddNewPO adminPostAddNewPage;
	UserHomePO userHomePage;
	UserPostDetailPO userPostDetailPage;
	UserSearchPostPO userSearchPostPage;
	
}
