package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_03_Page_Object_02_Login extends BasePage {
	
	private WebDriver driver;
	private String firstName, lastName, validPassword, existingEmail , invalidEmail, notFoundEmail, incorrectPassword;
	
	private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	
  @BeforeMethod
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  
	  driver.get("https://demo.nopcommerce.com/");
	  homePage = new UserHomePageObject(driver);
	  
	  
	  firstName = "Auto";
	  lastName ="Fc";
	  invalidEmail ="123@123@gmai.com";
	  notFoundEmail = "afc" + generateFakeNumber() + "@gmail.com";
	  existingEmail = "afc" + generateFakeNumber() + "@gmail.vn";
	  validPassword ="123456";
	  incorrectPassword = "654321";
	  
	  homePage.openRegisterPage();
	  registerPage = new UserRegisterPageObject(driver);
	  
	  registerPage.inputToFirstnameTextbox(firstName);
	  registerPage.inputToLastnameTextbox(lastName);
	  registerPage.inputToEmailTextbox(existingEmail);
	  registerPage.inputToPasswordTextbox(validPassword);
	  registerPage.inputToConfirmPasswordTextbox(validPassword);
	  
	  registerPage.clickToRegisterButton();
	  
	  Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
	  
	  registerPage.clickToLogoutLink();
	  
	  homePage = new UserHomePageObject(driver);
	  }
  
  @Test
  public void Login_01_Emty_Data() {
	  homePage.openLoginPage();
	  loginPage = new UserLoginPageObject(driver);
	  loginPage.clickToLoginButton();
	  Assert.assertEquals(loginPage.getErrorMessageAtEmailTexbox(), "Please enter your email");
  }
  
  @Test
  public void Login_02_Invaild_Email() {
	  homePage.openLoginPage();
	  loginPage = new UserLoginPageObject(driver);
	  
	  loginPage.inputToEmailTextbox(invalidEmail);
	  loginPage.clickToLoginButton();
	  
	  Assert.assertEquals(loginPage.getErrorMessageAtEmailTexbox(), "Wrong email");
  }
  
  @Test
  public void Login_03_Email_Not_Found() {
	  homePage.openLoginPage();
	  loginPage = new UserLoginPageObject(driver);
	  
	  loginPage.inputToEmailTextbox(notFoundEmail);
	  loginPage.clickToLoginButton();
	  
	  Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(),"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found"); 	  
  }
  
  @Test
  public void Login_04_Exiisting_Email_Empty_Password() {
	  homePage.openLoginPage();
	  loginPage = new UserLoginPageObject(driver);
	  
	  loginPage.inputToEmailTextbox(existingEmail);
	  loginPage.inputPasswordTextbox("");
	  
	  loginPage.clickToLoginButton();
	  
	  Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	  
  }
	  
  @Test
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
	  homePage.openLoginPage();
	  loginPage = new UserLoginPageObject(driver);
	  
	  loginPage.inputToEmailTextbox(existingEmail);
	  loginPage.inputPasswordTextbox(validPassword);
	  
	  loginPage.clickToLoginButton();
	  
	  homePage = new UserHomePageObject(driver);
	  Assert.assertTrue(homePage.isMyAccoutDisplayed());
  }
	 

  @AfterMethod
  public void afterClass() {
	 driver.quit();
  }
  public int generateFakeNumber() {
	  Random rand = new Random();
	  return rand.nextInt(9999);
  }
 
}

