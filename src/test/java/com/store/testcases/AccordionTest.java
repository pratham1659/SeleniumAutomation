package com.store.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pageobject.AccordionPage;
import com.qa.pageobject.IndexPage;

public class AccordionTest extends TestBase {

	public WebDriver driver;
	IndexPage indexPage;
	AccordionPage accordionPage;

	public AccordionTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowser();
		indexPage = new IndexPage(driver);
		accordionPage = indexPage.navigateToAccordion();

	}

	@Test(priority = 1)
	public void clickOnAccordion() throws InterruptedException {
		accordionPage.clickAccordion();

		String vAccord = accordionPage.verifyAccord();
		Assert.assertEquals(vAccord, dataProp.get("accordion1"));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
