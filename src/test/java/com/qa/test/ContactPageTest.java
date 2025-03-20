package com.qa.test;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pageobject.ContactPage;
import com.qa.pages.*;
import com.qa.utils.*;

public class ContactPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtils testUtil;
	ContactPage contactPage;

	String sheetName = "contacts";

	public ContactPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		initializeBrowser();
		testUtil = new TestUtils();
//		contactPage = new ContactPage();
//		loginPage = new LoginPage();
//		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
//		TestUtil.runTimeInfo("error", "login successful");
//		testUtil.switchToFrame();
//		contactPage = homePage.clickOnContactLink();

	}

	@Test(priority = 1)
	public void verifyContactsPageLabel() {
//		Assert.assertTrue(contactPage.verifyContactLabel(), "Contact label is Missing");
	}

	@Test(priority = 2, enabled = false)
	public void selectContactCheckBox() {
//		contactPage.selectContact("Python Sharma");
//		contactPage.selectContact("Macbook Roy");
	}

	@Test(priority = 3, enabled = false)
	public void selectMulitpleCheckBox() {
//		contactPage.selectContact("Python Sharma");
	}

	@DataProvider
	public Object[][] getCRMTestData() throws InvalidFormatException{
		Object data[][] = TestUtils.getTestData(sheetName);
		return data;
	}

	@Test(priority=4, dataProvider="getCRMTestData", enabled = false)
	public void validateCreateNewContact(String title, String firstName, String lastName, String company){
//		homePage.clickOnNewContactLink();
//		contactPage.createNewContact(title, firstName, lastName, company);
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
