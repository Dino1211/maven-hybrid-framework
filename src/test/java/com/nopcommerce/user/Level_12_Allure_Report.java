package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_12_Allure_Report extends BaseTest {
	
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
 @Description("Register to System")
 @Severity(SeverityLevel.NORMAL)
 @Test
  public void User_01_Register() {
	  registerPage = homePage.openRegisterPage();
	  
	  registerPage.inputToFirstnameTextbox(firstName);
	  
	  registerPage.inputToLastnameTextbox(lastName);
	  
	  registerPage.inputToEmailTextbox(emailAddress);
	  
	  registerPage.inputToPasswordTextbox(validPassword);
	  
	  registerPage.inputToConfirmPasswordTextbox(validPassword);
	  
	  registerPage.clickToRegisterButton();
	  
	  Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
  }
 
 @Description("Login to System")
 @Severity(SeverityLevel.NORMAL)
 @Test
 public void User_02_Login() {
	 
	 homePage = registerPage.clickToLogoutLink();
	  
	 loginPage = homePage.openLoginPage();
	 
	 loginPage.inputToEmailTextbox(emailAddress);
	 
	 loginPage.inputPasswordTextbox(validPassword);
	 
	 homePage = loginPage.clickToLoginButton();
	 
	 Assert.assertFalse(homePage.isMyAccoutDisplayed());
	 
	 customerInforPage = homePage.openMyAccountPage();
	 
	 Assert.assertFalse(customerInforPage.isCustomerInforPageDisplayed());
 
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

