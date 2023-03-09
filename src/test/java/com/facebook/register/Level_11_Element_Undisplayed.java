package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;

public class Level_11_Element_Undisplayed extends BaseTest {
	private LoginPageObject loginPage;
	
  @Parameters({ "browser", "url" })
  @BeforeMethod

  public void setUP(@Optional("chrome")String browserName, String appUrl) {
	  driver = getBrowserDriver(browserName, appUrl);
	  
	  loginPage = PageGeneratorManager.getLoginPage(driver);
	  
	  }
  
 @Test
  public void TC_01_Verify_Element_Displayed() {
	 loginPage.clickToCreatNewAccountButton();
	 
	 // Verify True - mong đợi Confirm email displayed (true)
	 loginPage.enterToEmailAddressTextbox("automationfc@gmail.com");
	 loginPage.sleepInSecond(3);
	 verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());
  }
 
 @Test
 public void TC_02_Verify_Element_Undisplayed_In_Dom() {
	 loginPage.clickToCreatNewAccountButton();
	 
	// Verify False - mong đợi Confirm email Undisplayed (false)
	 loginPage.enterToEmailAddressTextbox("");
	 loginPage.sleepInSecond(3);
	 verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
 }
 
 @Test
 public void TC_03_Verify_Element_Undisplayed_Not_In_Dom() {
	 loginPage.clickToCreatNewAccountButton();
	 
	 loginPage.clickCloseIconAtRegisterForm();
	 loginPage.sleepInSecond(3);
	 
//	 verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
	 
	 verifyTrue(loginPage.isConfirmEmailAdressTextboxUndisplayed());
	 
	 
 }
  
 
  @AfterMethod
  public void afterClass() {
  driver.quit();
	 
  }
  
  	private WebDriver driver;
}

