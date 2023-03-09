package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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

public class Level_14_Pattern_Object extends BaseTest {
	
  @Parameters("browser")
  @BeforeMethod

  public void setUP(@Optional("chrome")String browsername) {
	  driver = getBrowserDriver(browsername);	  
	  homePage = PageGeneratorManager.getUserHomePage(driver);
	  
	  firstName = "Auto";
	  lastName ="Fc";
	  emailAddress = "afc" + generateFakeNumber() + "@gmail.com";
	  validPassword ="123456";
	  date = "12";
	  month = "November";
	  year = "1997";
	 
	  }
  
 @Test
  public void User_01_Register() {
	  log.info("Register - Step 01: Navigate to 'Register' page");
	  registerPage = homePage.openRegisterPage();
	  
	  registerPage.clickToRadioButtonByLabel(driver, "Female");
	  
	  log.info("Register - Step 02: Enter to Firtsname textbox with value is '" + firstName + "'");
	  registerPage.inputToTextboxByID(driver, "Firtsname", "firstName");
	  
	  log.info("Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
	  registerPage.inputToTextboxByID(driver, "LastName", "lastName");
	  
	  registerPage.selectToDropdownByName(driver, "DateOfBirthDay", date);
	  registerPage.selectToDropdownByName(driver, "DateOfBirthMonth", month);
	  registerPage.selectToDropdownByName(driver, "DateOfBirthYear", year);
	  
	  log.info("Register - Step 04: Enter to EmailAddress textbox with value is '" + emailAddress + "'");
	  registerPage.inputToTextboxByID(driver, "Email", "emailAddress");
	  
	  registerPage.clickToCheckboxByLabel(driver, "Newsletter");
	  
	  log.info("Register - Step 05: Enter to Password textbox with value is '" + validPassword + "'");
	  registerPage.inputToTextboxByID(driver, "Password", "validPassword");
	  
	  log.info("Register - Step 06: Enter to ConfirmPassword textbox with value is '" + validPassword + "'");
	  registerPage.inputToTextboxByID(driver, "ConfirmPassword", "validPassword");
	  
	  log.info("Register - Step 07: Click to 'Register' button");
	  registerPage.clickToButtonByText(driver, "Register");
	  
	  log.info("Register - Step 08: Verify Register success message is displayed");
	  verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
	  
	  
  }
 @Test
 public void User_02_Login() {
	 log.info("Login - Step 01: Navigate to Login page");
	 homePage = registerPage.clickToLogoutLink();
	 loginPage = homePage.openLoginPage();
	 
	 log.info("Login - Step 02: Enter to Email textbox with value is '" + emailAddress + "'");
	 loginPage.inputToTextboxByID(driver, "Email", emailAddress);
	 
	 log.info("Login - Step 03: Enter to Password textbox with value is '" + validPassword + "'");
	 loginPage.inputToTextboxByID(driver, "Password", validPassword);
	 
	 log.info("Login - Step 04: Click to 'Login' button");
	 loginPage.clickToButtonByText(driver, "Log in");
	 homePage = PageGeneratorManager.getUserHomePage(driver);
	 
	 log.info("Login - Step 05: Verify 'My Account' link is displayed");
	 verifyTrue(homePage.isMyAccoutDisplayed());
 }
 @Test
 public void User_03_Account() {
	 log.info("My Account - Step 01: Navigate to 'My Account' page");
	 customerInforPage = homePage.openMyAccountPage();
	 
	 log.info("My Account - Step 02: Verify 'Customer Infor' page is displayed");
	 verifyTrue(customerInforPage.isCustomerInforPageDisplayed());
	 
	 log.info("My Account - Step 03: Verify ' First Name' value is correctly");
	 Assert.assertEquals( customerInforPage.getTextboxValueByID(driver, "Firtsname"), firstName);
	 
	 log.info("My Account - Step 04: Verify ' Last Name' value is correctly");
	 Assert.assertEquals( customerInforPage.getTextboxValueByID(driver, "LastName"), lastName);
	 
	 log.info("My Account - Step 05: Verify 'Email' value is correctly");
	 Assert.assertEquals( customerInforPage.getTextboxValueByID(driver, "Email"), emailAddress);
	 
 }
  
	  
  @AfterMethod(alwaysRun = true)
  public void afterClass() {
	 closeBrowserDriver();
  }
  
  	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInforPageObject customerInforPage;
	private String firstName, lastName, validPassword, emailAddress;
	private String date, month, year;
}

