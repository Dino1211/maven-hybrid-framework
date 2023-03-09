//package com.nopcommerce.user;
//
//import java.lang.reflect.Method;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Optional;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//import com.relevantcodes.extentreports.LogStatus;
//
//import commons.BaseTest;
//import pageObjects.nopCommerce.user.PageGeneratorManager;
//import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
//import pageObjects.nopCommerce.user.UserHomePageObject;
//import pageObjects.nopCommerce.user.UserLoginPageObject;
//import pageObjects.nopCommerce.user.UserRegisterPageObject;
//import reportConfig.ExtentTestManager;
//
//public class Level_12_ExtentV2_ScreenShot extends BaseTest {
//	@Parameters("browser")
//	@BeforeMethod
//
//	public void setUP(@Optional("chrome") String browsername) {
//		driver = getBrowserDriver(browsername);
//		homePage = PageGeneratorManager.getUserHomePage(driver);
//
//		firstName = "Auto";
//		lastName = "Fc";
//		emailAddress = "afc" + generateFakeNumber() + "@gmail.com";
//		validPassword = "123456";
//
//	}
//
//	@Test
//	public void User_01_Register(Method method) {
//		ExtentTestManager.startTest(method.getName(), "User_01_Register");
//
//		ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 01: Navigate to 'Register' page");
//		registerPage = homePage.openRegisterPage();
//
//		ExtentTestManager.getTest().log(LogStatus.INFO,
//				"Register - Step 02: Enter to Firtsname textbox with value is '" + firstName + "'");
//		registerPage.inputToFirstnameTextbox(firstName);
//
//		ExtentTestManager.getTest().log(LogStatus.INFO,
//				"Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
//		registerPage.inputToLastnameTextbox(lastName);
//
//		ExtentTestManager.getTest().log(LogStatus.INFO,
//				"Register - Step 04: Enter to EmailAddress textbox with value is '" + emailAddress + "'");
//		registerPage.inputToEmailTextbox(emailAddress);
//
//		ExtentTestManager.getTest().log(LogStatus.INFO,
//				"Register - Step 05: Enter to Password textbox with value is '" + validPassword + "'");
//		registerPage.inputToPasswordTextbox(validPassword);
//
//		ExtentTestManager.getTest().log(LogStatus.INFO,
//				"Register - Step 06: Enter to ConfirmPassword textbox with value is '" + validPassword + "'");
//		registerPage.inputToConfirmPasswordTextbox(validPassword);
//
//		ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 07: Click to ' Register' button");
//		registerPage.clickToRegisterButton();
//
//		ExtentTestManager.getTest().log(LogStatus.INFO,
//				"Register - Step 08: Verify Register success message is displayed");
//		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
//		ExtentTestManager.endTest();
//	}
//
//	@Test
//	public void User_02_Login(Method method) {
//		ExtentTestManager.startTest(method.getName(), "User_02_Login");
//
//		ExtentTestManager.getTest().log(LogStatus.INFO, "Register - Step 09: Click to Logout link");
//		homePage = registerPage.clickToLogoutLink();
//
//		ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 01: Navigate to Login page");
//		loginPage = homePage.openLoginPage();
//
//		ExtentTestManager.getTest().log(LogStatus.INFO,
//				"Login - Step 02: Enter to Email textbox with value is '" + emailAddress + "'");
//		loginPage.inputToEmailTextbox(emailAddress);
//
//		ExtentTestManager.getTest().log(LogStatus.INFO,
//				"Login - Step 03: Enter to Password textbox with value is '" + validPassword + "'");
//		loginPage.inputPasswordTextbox(validPassword);
//
//		ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 04: Click to 'Login' button");
//		homePage = loginPage.clickToLoginButton();
//
//		ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 05: Verify 'My Account' link is displayed");
//		Assert.assertFalse(homePage.isMyAccoutDisplayed());
//
//		ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 06: Navigate to 'My Account' page");
//		customerInforPage = homePage.openMyAccountPage();
//
//		ExtentTestManager.getTest().log(LogStatus.INFO, "Login - Step 07: Verify 'Customer Infor' page is displayed");
//		Assert.assertFalse(customerInforPage.isCustomerInforPageDisplayed());
//		ExtentTestManager.endTest();
//
//	}
//
//	@AfterMethod
//	public void afterClass() {
//		driver.quit();
//
//	}
//
//	private WebDriver driver;
//	private UserHomePageObject homePage;
//	private UserLoginPageObject loginPage;
//	private UserRegisterPageObject registerPage;
//	private UserCustomerInforPageObject customerInforPage;
//	private String firstName, lastName, validPassword, emailAddress;
//}
