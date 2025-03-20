package com.store.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.*;
import com.qa.pageobject.*;

public class ButtonTest extends TestBase{
	
	WebDriver driver;
	IndexPage indexPage;
	ButtonPage buttonPage;
	
	public ButtonTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		indexPage = new IndexPage(driver);
		buttonPage = indexPage.navigateToButtonPage();
		
	}
	
	@Test(priority = 1)
	public void clickOnButtons() throws InterruptedException {
		
		buttonPage.NormalButtonClick();
		
		buttonPage.jsButtonClick();
		
		buttonPage.actionMoveAndClick();
		
		//buttonPage.disabledButtonToEnable();	
		//Assert.assertTrue(buttonPage.checkDisabled());
		
		buttonPage.doubleClickOnButton();
		
		buttonPage.clickOnCheckBox();
		
		Thread.sleep(5000);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
