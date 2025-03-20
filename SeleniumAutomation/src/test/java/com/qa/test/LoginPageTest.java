package com.qa.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.*;


public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initializeBrowser();
//		loginPage = new LoginPage();
	}

	@Test(priority=1, enabled = false)
	public void loginPageTitleTest(){
//		String title = loginPage.validateLoginPageTitle();
//		Assert.assertEquals(title, "Free CRM software for customer relationship management, sales, and support.");
	}

	@Test(priority=2, enabled = false)
	public void crmLogoImageTest(){
//		boolean flag = loginPage.validateCRMImage();
//		Assert.assertTrue(flag);
	}

	@Test(priority=3)
	public void loginTest(){
//		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
