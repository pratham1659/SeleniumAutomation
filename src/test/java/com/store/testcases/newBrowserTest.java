package com.store.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.*;
import com.qa.pageobject.*;

public class newBrowserTest extends TestBase {

	WebDriver driver;
	IndexPage indexPage;
	NewBrowserPage newBrowserPage;

	public newBrowserTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		indexPage = new IndexPage(driver);
		newBrowserPage = indexPage.navigateToNewBrowserMenu();
	}

	@Test
	public void SwitchToNewTab() {
		newBrowserPage.clickOnNewWindow();
		
		newBrowserPage.switchToNewWindow();
		
		Assert.assertEquals(driver.getTitle(), "Google");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
