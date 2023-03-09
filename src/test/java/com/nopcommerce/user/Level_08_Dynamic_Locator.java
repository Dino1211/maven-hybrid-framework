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

public class Level_08_Dynamic_Locator extends BaseTest {
	private String firstName, lastName, validPassword, emailAddress;
	
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
  public void User_01_Register_Login() {
	  registerPage = homePage.openRegisterPage();
	  
	  registerPage.inputToFirstnameTextbox(firstName);
	  registerPage.inputToLastnameTextbox(lastName);
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox(validPassword);
	  registerPage.inputToConfirmPasswordTextbox(validPassword);
	  registerPage.clickToRegisterButton();
	  verifyEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
	  
	  homePage = registerPage.clickToLogoutLink();
  
	  loginPage = homePage.openLoginPage();
	 	  
	  loginPage.inputToEmailTextbox(emailAddress);
	  loginPage.inputPasswordTextbox(validPassword);
	  
	  homePage = loginPage.clickToLoginButton();
	  
	  verifyFalse(customerInforPage.isCustomerInforPageDisplayed());
 
	  customerInforPage = homePage.openMyAccountPage();
	  verifyFalse(customerInforPage.isCustomerInforPageDisplayed());
  }
  
  @Test
  public void User_02_Switch_Page() {
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
	  
  }
  @Test
  public void User_03_Dynamic_Page_01() {
	  
	  // My product review -> Reward point
	  rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPagesAtMyaccountByName(driver, "Reward points");
	  
	 // Reward point -> Address
	  addressPage = (UserAddressPageObject) rewardPointPage.openPagesAtMyaccountByName(driver, "Addresses");
	  
	  //Address -> Reward point 
	  rewardPointPage = (UserRewardPointPageObject) addressPage.openPagesAtMyaccountByName(driver, "Reward points");
	  
	  // Reward point -> My product review 
	  myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPagesAtMyaccountByName(driver, "My product reviews");
	  
	  // My product review -> Customer info
	  customerInforPage = (UserCustomerInforPageObject) myProductReviewPage.openPagesAtMyaccountByName(driver, "Customer info");
	  
  }
  @Test
  public void User_03_Dynamic_Page_02() {
	  
      //Customer Info - > my product review 
	  customerInforPage.openPagesAtMyaccountByPageName(driver, "My product reviews");
	  myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
	  
	  //My product review -> Reward point 
	  myProductReviewPage.openPagesAtMyaccountByPageName(driver, "Reward points");
	  rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
	  
	  //Reward point - Address 
	  rewardPointPage.openPagesAtMyaccountByPageName(driver, "Addresses");
	  addressPage = PageGeneratorManager.getUserAddressPage(driver);
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

