package com.jquery.datatable;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager;

public class Level_09_Datatable_DataGrid extends BaseTest {
	HomePageObject homePage;
	List<String> actualAllCountryValues;
	List<String> expectedAllCountryValues;
	
  @Parameters({ "browser", "url" })
  @BeforeMethod

  public void setUP(@Optional("chrome")String browserName, String appUrl) {
	  driver = getBrowserDriver(browserName, appUrl);
	  
	  homePage =PageGeneratorManager.getHomePage(driver);
	
	  }
  
 //@Test
  public void Table_01_Paging() {
	 
	 homePage.openPagingByPageNumber("10");
	 homePage.sleepInSecond(1);
	 Assert.assertTrue(homePage.isPageNumberActived("10"));
	 
	 homePage.openPagingByPageNumber("15");
	 homePage.sleepInSecond(1);
	 Assert.assertTrue(homePage.isPageNumberActived("15"));
	 
	 homePage.openPagingByPageNumber("20");
	 homePage.sleepInSecond(1);
	 Assert.assertTrue(homePage.isPageNumberActived("20"));
	 
  }
  
 //@Test
  public void Table_02_Enter_To_Header() {
	 homePage.refrechCurrentPage(driver);
	 
	 homePage.EnterToHeaderTextboxByLabel("Country", "Angola");
	 homePage.EnterToHeaderTextboxByLabel("Females", "276880");
	 homePage.EnterToHeaderTextboxByLabel("Males", "276472");
	 homePage.EnterToHeaderTextboxByLabel("Total", "553353");
	 homePage.sleepInSecond(1);
	 
	 homePage.EnterToHeaderTextboxByLabel("Country", "Argentina");
	 homePage.EnterToHeaderTextboxByLabel("Females", "338282");
	 homePage.EnterToHeaderTextboxByLabel("Males", "349238");
	 homePage.EnterToHeaderTextboxByLabel("Total", "687522");
	 homePage.sleepInSecond(1);
  
  }
 //@Test
 public void Table_03_Enter_To_Header() {
	 // Đọc dữ liêu của file country.txt
	 // Lưu vào 1 List<String> = Expected Value = expectedAllCountryValues
	 
	 
	 //Actual value
	 actualAllCountryValues = homePage.getValueEachRowAllPage();
			 
	 
	 Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);
 }
 @Test
 public void Table_04_Action_at_Any_Row() {
	 homePage.clickToLoadButton();
	 homePage.sleepInSecond(5);
	 
	 // Value để nhập dữ liệu
	 // Row number 
	 // Ex: Nhap vao textbox tai dong so 1/2/3
	 // column name : Company name / contact person / country
//	 homePage.enterToTextboxByColumnNameAtRowNumber("Company", "2", "John Wick");
//	 homePage.sleepInSecond(3);
//	 
//	 homePage.enterToTextboxByColumnNameAtRowNumber("Contact Person", "3", "Dino");
//	 homePage.sleepInSecond(3);
//	 
//	 homePage.selectDropdownbyColumnNameAtRowNumber("Country", "1", "Hong Kong");
//	 homePage.sleepInSecond(3);
//	 
//	 homePage.enterToTextboxByColumnNameAtRowNumber("Order Placed", "4", "2");
//	 homePage.sleepInSecond(3);
//	 
//	 homePage.checkToCheckBoxByColumnNameAtRowNumber("NPO?", "2");
//	 homePage.checkToCheckBoxByColumnNameAtRowNumber("NPO?", "4");
//	
//	 homePage.unCheckToCheckBoxByColumnNameAtRowNumber("NPO?", "1");
//	 homePage.unCheckToCheckBoxByColumnNameAtRowNumber("NPO?", "3");
	 
	 homePage.clickToByRowName("1", "Remove Current Row");
	 homePage.sleepInSecond(3);
	 
	 homePage.clickToByRowName("2", "Insert Row Above");
	 homePage.sleepInSecond(3);
	 
	 homePage.clickToByRowName("3", "Move Up");
	 homePage.sleepInSecond(3);
	 
	 homePage.clickToByRowName("5", "Remove Current Row");
	 homePage.sleepInSecond(3);
	 
	 homePage.clickToByRowName("4", "Remove Current Row");
	 homePage.sleepInSecond(3);
	 
	 homePage.clickToByRowName("3", "Remove Current Row");
	 homePage.sleepInSecond(3);
	 
	 homePage.clickToByRowName("2", "Remove Current Row");
	 homePage.sleepInSecond(3);
	 
	 
	 
 }
  
	
  @AfterMethod
  public void afterClass() {
	  driver.quit();
	 
  }
  
  	private WebDriver driver;
}

