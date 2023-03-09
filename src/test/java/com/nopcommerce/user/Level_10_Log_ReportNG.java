package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_10_Log_ReportNG extends BaseTest {
	
  @Parameters("browser")
  @BeforeMethod

  public void setUP(@Optional("chrome")String browsername) {
	  driver = getBrowserDriver(browsername);	  
	  homePage = PageGeneratorManager.getUserHomePage(driver);
	  
	  firstName = "Auto";
	  lastName ="Fc";
	  emailAddress = "afc" + generateFakeNumber() + "@gmail.com";
	  validPassword ="123456";
	 
	  }
  
 @Test
  public void User_01_Register() {
	  log.info("Register - Step 01: Navigate to 'Register' page");
	  registerPage = homePage.openRegisterPage();
	  
	  log.info("Register - Step 02: Enter to Firtsname textbox with value is '" + firstName + "'");
	  registerPage.inputToFirstnameTextbox(firstName);
	  
	  log.info("Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
	  registerPage.inputToLastnameTextbox(lastName);
	  
	  log.info("Register - Step 04: Enter to EmailAddress textbox with value is '" + emailAddress + "'");
	  registerPage.inputToEmailTextbox(emailAddress);
	  
	  log.info("Register - Step 05: Enter to Password textbox with value is '" + validPassword + "'");
	  registerPage.inputToPasswordTextbox(validPassword);
	  
	  log.info("Register - Step 06: Enter to ConfirmPassword textbox with value is '" + validPassword + "'");
	  registerPage.inputToConfirmPasswordTextbox(validPassword);
	  
	  log.info("Register - Step 07: Click to ' Register' button");
	  registerPage.clickToRegisterButton();
	  
	  log.info("Register - Step 08: Verify Register success message is displayed");
	  verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
	  
	  log.info("Register - Step 09: Click to Logout link");
	  homePage = registerPage.clickToLogoutLink();
	  
  }
 @Test
 public void User_01_Login() {
	 log.info("Login - Step 01: Navigate to Login page");
	 loginPage = homePage.openLoginPage();
	 
	 log.info("Login - Step 02: Enter to Email textbox with value is '" + emailAddress + "'");
	 loginPage.inputToEmailTextbox(emailAddress);
	 
	 log.info("Login - Step 03: Enter to Password textbox with value is '" + validPassword + "'");
	 loginPage.inputPasswordTextbox(validPassword);
	 
	 log.info("Login - Step 04: Click to 'Login' button");
	 homePage = loginPage.clickToLoginButton();
	 
	 log.info("Login - Step 05: Verify 'My Account' link is displayed");
	 verifyTrue(homePage.isMyAccoutDisplayed());
	 
	 log.info("Login - Step 06: Navigate to 'My Account' page");
	 customerInforPage = homePage.openMyAccountPage();
	 
	 log.info("Login - Step 07: Verify 'Customer Infor' page is displayed");
	 verifyTrue(customerInforPage.isCustomerInforPageDisplayed());
 
 }
  
	  
  @AfterMethod
  public void afterClass() {
	 
  }
  
  	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInforPageObject customerInforPage;
	private String firstName, lastName, validPassword, emailAddress;
}

