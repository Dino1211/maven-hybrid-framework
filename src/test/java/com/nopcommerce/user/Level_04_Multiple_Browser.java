package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_04_Multiple_Browser extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, password, emailAddress;

	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private String lastEmail = null;

	@Parameters("broser")
	@BeforeMethod
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc@gmail.com";
		password = "123456";

	}

	@Test
	public void Register_01_Emty_Data() {
		System.out.println("Register_01 - Step 01: Click to Register link");
		homePage.openRegisterPage();

		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_01 - Step 02: Click to Register button");
		registerPage.clickRegisterButton();

		System.out.println("Register_01 - Step 03: Verify error message displayed");

		Assert.assertEquals(registerPage.getErrorMessageFirstnameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessagePasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void Register_02_Invaild_Email() {
		System.out.println("Register_02 - Step 01: Click to Register link");
		homePage.openRegisterPage();

		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_02 - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox("123@123");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register_02 - Step 03: Click to Register button");
		registerPage.clickRegisterButton();

		System.out.println("Register_02 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "Wrong email");
	}

	@Test
	public void Register_03_Success() {
		System.out.println("Register_03 - Step 01: Click to Register link");
		homePage.openRegisterPage();
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_03 - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		lastEmail = emailAddress;
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register_03 - Step 03: Click to Register button");
		registerPage.clickRegisterButton();

		System.out.println("Register_03 - Step 04:Verify succes message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Register_03  - Step 05: Click to Logout link");
		registerPage.clickToLogoutLink();
	}

	@Test
	public void Register_04_Exiisting_Email() {
		System.out.println("Register_04 - Step 01: Click to Register link");
		homePage.openRegisterPage();

		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_04 - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register_04 - Step 03: Click to Register button");
		registerPage.clickRegisterButton();

		System.out.println("Register_04 - Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");

	}

	@Test
	public void Register_05_PassWord_Less_Than_6_Char() {
		System.out.println("Register_05- Step 01: Click to Register link");
		homePage.openRegisterPage();

		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_05 - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");

		System.out.println("Register_05 - Step 03: Click to Register button");
		registerPage.clickRegisterButton();

		System.out.println("Register_05 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessagePasswordTextbox(),
				"Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void Register_06_Invaild_ConFirm_PassWord() {
		System.out.println("Register_06 - Step 01: Click to Register link");
		homePage.openRegisterPage();

		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_06 - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(lastName);

		System.out.println("Register_06 - Step 03: Click to Register button");
		registerPage.clickRegisterButton();

		System.out.println("Register_06 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageConfirmPasswordTextbox(),
				"The password and confirmation password do not match.");
	}

	@AfterMethod
	public void afterClass() {
		driver.quit();

	}
//  public int generateFakeNumber() {
//	  Random rand = new Random();
//	  return rand.nextInt(9999);
//  }
// 
}
