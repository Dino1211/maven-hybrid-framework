package pageObjects.sauce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.sauceLab.LoginPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void enterToUserNameTextbox(String userName) {
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		senkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, userName);
		
	}
	
	public void enterToPasswordTextbox(String passWord) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passWord);
		
		
	}
	public ProductPageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.CLICK_LOGIN_BUTTON);
		clickToElement(driver,  LoginPageUI.CLICK_LOGIN_BUTTON);
		return PageGeneratorManager.getProductPage(driver);
	}
}
