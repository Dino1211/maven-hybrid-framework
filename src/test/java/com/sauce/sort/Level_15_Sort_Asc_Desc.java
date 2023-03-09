package com.sauce.sort;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.sauce.LoginPageObject;
import pageObjects.sauce.PageGeneratorManager;
import pageObjects.sauce.ProductPageObject;

public class Level_15_Sort_Asc_Desc extends BaseTest {
	String userName = "standard_user";
	String passWord = "secret_sauce";
  @Parameters({"browser", "appUrl"})
  @BeforeMethod

  public void setUP(@Optional("chrome")String browsername, String saucelabUrl ) {
	  driver = getBrowserDriver(browsername, saucelabUrl);
	  
	  loginPage = PageGeneratorManager.getLoginPage(driver);
	  
	  loginPage.enterToUserNameTextbox(userName);
	  
	  loginPage.enterToPasswordTextbox(passWord);
	  
	  productPage = loginPage.clickToLoginButton();
	  
	  }
  
 @Test
  public void Sort_01_Name() {
	 productPage.selectItemInProductSortDropdown("Name (A to Z)");
	 productPage.sleepInSecond(3);
	 
	 productPage.selectItemInProductSortDropdown("Name (Z to A)"); 
	 productPage.sleepInSecond(3);
//  }
// @Test
// public void Sort_02_Price() {
	 productPage.selectItemInProductSortDropdown("Price (low to high)");
	 productPage.sleepInSecond(3);
	 
	 productPage.selectItemInProductSortDropdown("Price (high to low)");
	 productPage.sleepInSecond(3);
 }
  @AfterMethod(alwaysRun = true)
  public void afterClass() {
	 closeBrowserDriver();
  }
  
  	WebDriver driver;
  	LoginPageObject loginPage;
  	ProductPageObject productPage;
}

