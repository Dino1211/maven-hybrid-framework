package pageObjects.nopCommerce.user;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.RegisterPageUI;

public class UserRegisterPageObject {
	private WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public void scrollToElementOnTop(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}
	@Step("Click to Register Button")
	public void clickRegisterButton() {
		scrollToElementOnTop(RegisterPageUI.REGISTER_BUTTON);
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);	
	}

	public String getErrorMessageFirstnameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
	}

	public String getErrorMessageLastnameTextbox() {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
	}

	public String getErrorMessageEmailTextbox() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessagePasswordTextbox() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	public String getErrorMessageConfirmPasswordTextbox() {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}
	@Step("Verify 'Register Success'")
	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public UserHomePageObject clickToLogoutLink() {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePage(driver);

	}

	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
	}
	@Step("Enter to firstName textbox with value is {0}")
	public void inputToFirstnameTextbox(String firstName) {
		waitForElementClickable(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
	}

	private void senkeyToElement(WebDriver driver2, String firstNameTextbox, String firstName) {
		WebElement element = getWebElement(driver2, firstNameTextbox);
		element.clear();
		element.sendKeys(firstName);

	}
	@Step("Enter to lastName textbox with value is {0}")
	public void inputToLastnameTextbox(String lastName) {
		waitForElementClickable(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);

	}
	@Step("Enter to emailAddress textbox with value is {0}")
	public void inputToEmailTextbox(String emailAddress) {
		waitForElementClickable(driver, RegisterPageUI.EMAIL_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
	}
	@Step("Enter to password textbox with value is {0}")
	public void inputToPasswordTextbox(String password) {
		waitForElementClickable(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);

	}
	@Step("Enter to confirmPassword textbox with value is {0}")
	public void inputToConfirmPasswordTextbox(String conFirmPassword) {
		waitForElementClickable(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, conFirmPassword);

	}

	public void clickToElement(WebDriver driver2, String registerButton) {
		getWebElement(driver2, registerButton).click();

	}

	private WebElement getWebElement(WebDriver driver2, String registerButton) {
		return driver2.findElement(By.xpath(registerButton));
	}

	private void waitForElementClickable(WebDriver driver2, String registerButton) {
		WebDriverWait explicitWait = new WebDriverWait(driver2, 30);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(registerButton)));

	}

	private By getByXpath(String registerButton) {
		return By.xpath(registerButton);
	}

	private String getElementText(WebDriver driver2, String firstNameErrorMessage) {
		return getWebElement(driver2, firstNameErrorMessage).getText();
	}

	private void waitForElementVisible(WebDriver driver2, String firstNameErrorMessage) {
		WebDriverWait explicitWait = new WebDriverWait(driver2, 30);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(firstNameErrorMessage)));
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public void inputToTextboxByID(WebDriver driver, String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	public void clickToButtonByText(WebDriver driver, String string) {
		// TODO Auto-generated method stub
		
	}

	public void selectToDropdownByName(WebDriver driver, String string, String date) {
		// TODO Auto-generated method stub
		
	}

	public void clickToRadioButtonByLabel(WebDriver driver, String string) {
		// TODO Auto-generated method stub
		
	}

	public void clickToCheckboxByLabel(WebDriver driver, String string) {
		// TODO Auto-generated method stub
		
	}

	

	

	

}
