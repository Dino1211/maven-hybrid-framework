package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
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

public class Level_06_Switch_Page extends BaseTest {
	private String firstName, lastName, validPassword, emailAddress;
	
  @Parameters("browser")
  @BeforeMethod

  public void setUP(@Optional("chrome")String browsername) {
	  driver = getBrowserDriver(browsername);
	  
	  homePage = PageGeneratorManager.getUserHomePage(driver);
	  
	
	  firstName = "Auto";
	  lastName ="Fc";
	  emailAddress = "afc1912@gmail.com";
//	  emailAddress = "afc" + generateFakeNumber() + "@gmail.com";
	  validPassword ="123456";
	  
	 
	  
	  }
  
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
	  
	  homePage = registerPage.clickToLogoutLink();
  }
  
  //@Test
  public void User_02_Login() {
	  loginPage = homePage.openLoginPage();
	 	  
	  loginPage.inputToEmailTextbox(emailAddress);
	  loginPage.inputPasswordTextbox(validPassword);
	  
	  homePage = loginPage.clickToLoginButton();
	  
	  Assert.assertTrue(homePage.isMyAccoutDisplayed());
  }
  
 // @Test
  	public void User_03_Customer_Infor() {
	  customerInforPage = homePage.openMyAccountPage();
	  Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
  }
  
 // @Test
  public void User_04_Switch_Page() {
	  // Customer infor => Address
	  addressPage = customerInforPage.openAddressPage(driver);
	  
	  // Address => My product review
	  myProductReviewPage = addressPage.openMyProductReviewPage(driver);
	  
	  // My product review => Reward point
	  rewardPointPage =  myProductReviewPage.openRewardPointPage(driver);
	  
	  
	  // Reward point => Address 
	  addressPage = rewardPointPage.openAddressPage(driver);
	
	  
	  // Address => Reward point
	  rewardPointPage =	addressPage.openRewardPointPage(driver);
	
	  
	  // Reward point => My product review 
	  myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
	  
	  // My product review => Address
	  addressPage = myProductReviewPage.openAddressPage(driver);
	  
	  customerInforPage = addressPage.openCustomerInforPage(driver);
	  
	  myProductReviewPage = customerInforPage.openMyProductReviewPage(driver);
	  
  }
   
  @AfterMethod
  public void afterClass() {
	 
  }
  
  	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserAddressPageObject addressPage;
	private UserRewardPointPageObject rewardPointPage;
}

