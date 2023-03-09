package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.nopComerce.admin.AdminDashboardPageObject;
import pageObjects.nopComerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;

public class Level_07_Switch_Role extends BaseTest {

	@Parameters("browser")
	@BeforeMethod

	public void setUP(@Optional("chrome") String browsername) {
		driver = getBrowserDriver(browsername);

		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		userEmailAddress = "afc" + generateFakeNumber() + "@gmail.com";
		userPassword = "123456";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";

	}

	@Test
	public void Role_01_User_To_Admin() {
		userLoginPage = userHomePage.openLoginPage();

		// Login as User role
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccoutDisplayed());

		// home Page => CustomerInforPage
		userCustomerInforPage = userHomePage.openMyAccountPage();

		// customerinforPage click logout => homePage
		userHomePage = userCustomerInforPage.clickToLogoutAtUserPage(driver);

		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_DEV_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		// Login as Admin Role
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

		// Dashboard Page => click Logout => login Page(Admin)
		adminLoginPage = adminDashboardPage.clickToLogoutAtAdminPage(driver);

	}

	@Test
  public void Role_02_Admin_To_User() {
	  //Login Page (Admin ) =>Opne User URL => Home Page 
	  adminLoginPage.openPageUrl(driver, GlobalConstants.PORTAL_DEV_URL);
	  userHomePage = PageGeneratorManager.getUserHomePage(driver);
	
	  //home Page => Login Page ( User )
	  userLoginPage = userHomePage.openLoginPage();
	  
	  // Login as User Role
	  userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
	  Assert.assertTrue(userHomePage.isMyAccoutDisplayed());
	  
  }

	@AfterMethod
	public void afterClass() {

	}

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInforPageObject userCustomerInforPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private String userPassword, userEmailAddress, adminEmailAddress, adminPassword;

}
