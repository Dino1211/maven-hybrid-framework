package pageObjects.sauce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.sauceLab.ProductPageUI;

public class ProductPageObject extends BasePage {
	WebDriver driver;
	
	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInProductSortDropdown(String textItem) {
		waitForElementClickable(driver, ProductPageUI.SELECT_NAME);
		selectItemInDefaultDropdown(driver, ProductPageUI.SELECT_NAME, textItem);
		
	}

}
