package pageObjects.nopComerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage{
	WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void inputToUserNameTextbox(String emailAddress ) {
		waitForAllElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		senkeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}
	public void inputTopPasswordTextbox(String password) {
		waitForAllElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
		
	}
	public AdminDashboardPageObject clickToLoginButton() {
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}
	public AdminDashboardPageObject loginAsAdmin(String emailAddress, String password) {
		inputToUserNameTextbox(emailAddress);
		inputTopPasswordTextbox(password);
		return clickToLoginButton();
	}

}
