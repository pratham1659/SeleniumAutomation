package com.qa.test;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.*;
import com.qa.utils.*;

public class HomePageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	TestUtils testUtil;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initializeBrowser();
		testUtil = new TestUtils();
	
//		loginPage = new LoginPage();
//		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyHomePageTitle() {
//		String title = homePage.verifyHomePageTitle();
//		Assert.assertEquals(title, "CRMPRO", "Homepage Title is not Matched");
	}

	@Test(priority = 2, enabled = false)
	public void VerifyUserNameTest() {
		testUtil.switchToFrame();
//		Assert.assertTrue(homePage.userNameVerify());

	}

	@Test(priority = 3, enabled = false)
	public void clickContactLink() {
		testUtil.switchToFrame();
//		contactPage = homePage.clickOnContactLink();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
