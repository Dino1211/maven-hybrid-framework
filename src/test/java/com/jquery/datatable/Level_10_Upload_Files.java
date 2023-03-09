package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGeneratorManager;

public class Level_10_Upload_Files extends BaseTest {
	String csharpFileName = "CSharp.png";
	String javaFileName = "Java.png";
	String pythonFileName = "Python.png";
	String rubyFileName = "Ruby.png";
	
	String[] multipleFileNames = {csharpFileName, javaFileName, pythonFileName, rubyFileName };
	
  @Parameters({ "browser", "url" })
  @BeforeMethod

  public void setUP(@Optional("chrome")String browserName, String appUrl) {
	  driver = getBrowserDriver(browserName, appUrl);
	  
	 homePage = PageGeneratorManager.getHomePage(driver);
	  
	
	  }
  
 @Test
  public void Upload_01_One_File_Per_Time() {
	 // Step 1 - Load single file
	 homePage.uploadMultipleFiles(driver, csharpFileName);
	 
	 // Step 2 - Verify single file 
	 Assert.assertTrue(homePage.isFileLoadedByName(csharpFileName));
	 
	 //Step 3 - click to start button
	 homePage.clickToStartButton();
	 
	 //Step 4 - Verify link upload success
	 Assert.assertTrue(homePage.isFileLinkUpLoadedByName(csharpFileName));
	 
	 //Step 5 - Verify image upload success
	 Assert.assertTrue(homePage.isFileImageUpLoadedByName(csharpFileName));
  }
  
 @Test
  public void  Upload_02_Multiple_File_Per_Time() {
	 homePage.refrechCurrentPage(driver);
	 
	 homePage.uploadMultipleFiles(driver, multipleFileNames);
	 
	 Assert.assertTrue(homePage.isFileLoadedByName(csharpFileName));
	 Assert.assertTrue(homePage.isFileLoadedByName(javaFileName));
	 Assert.assertTrue(homePage.isFileLoadedByName(pythonFileName));
	 Assert.assertTrue(homePage.isFileLoadedByName(rubyFileName));
	 
	 
	 homePage.clickToStartButton();
	 
	 Assert.assertTrue(homePage.isFileLinkUpLoadedByName(csharpFileName));
	 Assert.assertTrue(homePage.isFileLinkUpLoadedByName(javaFileName));
	 Assert.assertTrue(homePage.isFileLinkUpLoadedByName(pythonFileName));
	 Assert.assertTrue(homePage.isFileLinkUpLoadedByName(rubyFileName));
	 
	 
	 Assert.assertTrue(homePage.isFileImageUpLoadedByName(csharpFileName));
	 Assert.assertTrue(homePage.isFileImageUpLoadedByName(javaFileName));
	 Assert.assertTrue(homePage.isFileImageUpLoadedByName(pythonFileName));
	 Assert.assertTrue(homePage.isFileImageUpLoadedByName(rubyFileName));
  
  }
 
  @AfterMethod
  public void afterClass() {
  driver.quit();
	 
  }
  
  	private WebDriver driver;
  	private HomePageObject homePage;
}

