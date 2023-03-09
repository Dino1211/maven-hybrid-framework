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

public class Level_13_Share_Data extends BaseTest {

	@Parameters("browser")
	@BeforeMethod

	public void setUP(@Optional("chrome") String browsername) {
		driver = getBrowserDriver(browsername);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		emailAddress = "Common_01_Register.emailAddress";
		validPassword = "Common_01_Register.password";
		

		log.info("Login - Step 01: Navigate to Login page");
		loginPage = homePage.openLoginPage();

		log.info("Login - Step 02: Enter to Email textbox with value is '" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);

		log.info("Login - Step 03: Enter to Password textbox with value is '" + validPassword + "'");
		loginPage.inputPasswordTextbox(validPassword);

		log.info("Login - Step 04: Click to 'Login' button");
		homePage = loginPage.clickToLoginButton();

	}

	@Test
	public void Search_01_EmtyData() {

	}
	@Test
	public void Search_02_Relative_Product_Name() {

	}
	@Test
	public void Search_03_Absolute_Product_Name() {

	}
	@Test
	public void Search_04_Parent_Category() {

	}
	@Test
	public void Search_05_Inconrrect_Manufacturer() {

	}
	@Test
	public void Search_06_Conrrect_Manufacturer() {

	}
	
	@AfterMethod
	public void afterClass() {

	}

	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private String validPassword, emailAddress;
}
