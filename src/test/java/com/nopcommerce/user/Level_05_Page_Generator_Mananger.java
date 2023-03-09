package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_05_Page_Generator_Mananger extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, validPassword, existingEmail , invalidEmail, notFoundEmail, incorrectPassword;
	
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInforPageObject myAccountPage;
	
  @Parameters("browser")
  @BeforeMethod
  public void setUP(@Optional("chrome")String browserName) {
	  driver = getBrowserDriver(browserName);
	  
	  homePage = PageGeneratorManager.getUserHomePage(driver);
	  
	
	  firstName = "Auto";
	  lastName ="Fc";
	  invalidEmail ="123@123@gmai.com";
	  notFoundEmail = "afc" + generateFakeNumber() + "@gmail.com";
	  existingEmail = "afc" + generateFakeNumber() + "@gmail.vn";
	  validPassword ="123456";
	  incorrectPassword = "654321";
	  
	  registerPage = homePage.openRegisterPage();
	  
	  registerPage.inputToFirstnameTextbox(firstName);
	  registerPage.inputToLastnameTextbox(lastName);
	  registerPage.inputToEmailTextbox(existingEmail);
	  registerPage.inputToPasswordTextbox(validPassword);
	  registerPage.inputToConfirmPasswordTextbox(validPassword);
	  
	  registerPage.clickToRegisterButton();
	  
	  Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
	  
	  homePage = registerPage.clickToLogoutLink();
	  
	  }
  
  //@Test
  public void Login_01_Emty_Data() {
	  loginPage = homePage.openLoginPage();
	  
	  loginPage.clickToLoginButton();
	  Assert.assertEquals(loginPage.getErrorMessageAtEmailTexbox(), "Please enter your email");
  }
  
  //@Test
  public void Login_02_Invaild_Email() {
	  loginPage = homePage.openLoginPage();
	  
	  loginPage.inputToEmailTextbox(invalidEmail);
	  loginPage.clickToLoginButton();
	  
	  Assert.assertEquals(loginPage.getErrorMessageAtEmailTexbox(), "Wrong email");
  }
  
  //@Test
  public void Login_03_Email_Not_Found() {
	  loginPage = homePage.openLoginPage();
	  
	  loginPage.inputToEmailTextbox(notFoundEmail);
	  loginPage.clickToLoginButton();
	  
	  Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(),"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found"); 	  
  }
  
  //@Test
  public void Login_04_Exiisting_Email_Empty_Password() {
	  loginPage = homePage.openLoginPage();
	  
	  loginPage.inputToEmailTextbox(existingEmail);
	  loginPage.inputPasswordTextbox("");
	  
	  loginPage.clickToLoginButton();
	  
	  Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	  
  }
	  
  //@Test
  public void Login_05_Exiisting_Email_Incorrect_Password() {
	  homePage.openLoginPage();
	  loginPage = new UserLoginPageObject(driver);
	  
	  loginPage.inputToEmailTextbox(existingEmail);
	  loginPage.inputPasswordTextbox(incorrectPassword);
	  
	  loginPage.clickToLoginButton();
	  
	  Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");  
  }
  @Test
  public void Login_06_Invaild_Email_PassWord() {
	  loginPage = homePage.openLoginPage();
	  
	  loginPage.inputToEmailTextbox(existingEmail);
	  loginPage.inputPasswordTextbox(validPassword);
	  
	  homePage = loginPage.clickToLoginButton();
	  
	  Assert.assertTrue(homePage.isMyAccoutDisplayed());
	  
	  myAccountPage = homePage.openMyAccountPage();
	  
//	  myAccountPage.clickToNewsletterCheckbox();
	  
	  
  }
	 

  @AfterMethod
  public void afterClass() {
	 driver.quit();
	 
  }
}

