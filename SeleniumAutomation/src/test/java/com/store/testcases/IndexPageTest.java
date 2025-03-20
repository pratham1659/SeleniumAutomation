package com.store.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.qa.base.*;
import com.qa.pageobject.*;

public class IndexPageTest extends TestBase {

	public WebDriver driver;
	IndexPage indexPage;

	public IndexPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowser();
		indexPage = new IndexPage(driver);

	}

	@Test(priority = 1)
	public void verifyHome() {
		String vHome = indexPage.verfiyHomePages();
		Assert.assertEquals(vHome, "Welcome to HomePage");
	}


	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
