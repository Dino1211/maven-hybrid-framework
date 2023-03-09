package com.nopcommerce.common;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Common_01_Register extends BaseTest {
	
 
 @Parameters("browser")
 @BeforeTest(description = "Creat New Common Users for all Class Test")
  public void Register(@Optional("chrome")String browsername) {
	 
	  driver = getBrowserDriver(browsername);	  
	  homePage = PageGeneratorManager.getUserHomePage(driver);
	  
	  firstName = "Auto";
	  lastName ="Fc";
	  emailAddress = "afc" + generateFakeNumber() + "@gmail.com";
	  password ="123456";
	  
	  log.info("Register - Step 01: Navigate to 'Register' page");
	  registerPage = homePage.openRegisterPage();
	  
	  log.info("Register - Step 02: Enter to Firtsname textbox with value is '" + firstName + "'");
	  registerPage.inputToFirstnameTextbox(firstName);
	  
	  log.info("Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
	  registerPage.inputToLastnameTextbox(lastName);
	  
	  log.info("Register - Step 04: Enter to EmailAddress textbox with value is '" + emailAddress + "'");
	  registerPage.inputToEmailTextbox(emailAddress);
	  
	  log.info("Register - Step 05: Enter to Password textbox with value is '" + password + "'");
	  registerPage.inputToPasswordTextbox(password);
	  
	  log.info("Register - Step 06: Enter to ConfirmPassword textbox with value is '" + password + "'");
	  registerPage.inputToConfirmPasswordTextbox(password);
	  
	  log.info("Register - Step 07: Click to ' Register' button");
	  registerPage.clickToRegisterButton();
	  
	  log.info("Register - Step 08: Verify Register success message is displayed");
	  verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
	  
	  log.info("Register - Step 09: Click to Logout link");
	  homePage = registerPage.clickToLogoutLink();
	  
  }
  
	  
  @AfterTest
  public void afterClass() {
	 
  }
  
  	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private String firstName, lastName;
	public static String password, emailAddress;
}


