package com.SeleniumTesting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.SearchPage;
import com.qa.report.ReportGenerator;

public class SearchTest extends TestBase {

	public WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;
	ReportGenerator report;
	public static Logger log;

	public SearchTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		homePage = new HomePage(driver);
		report = new ReportGenerator();
		log = LogManager.getLogger(SearchTest.class);
		log.info("Logged into Website");

	}

//	, retryAnalyzer = com.qa.report.MyRetryAnalyzer.class)
	@Test(priority = 1)
	public void verifySearchWithValidProduct() throws InterruptedException {
		
		log.info("Start the Test");

		searchPage = homePage.selectSearchInput(dataProp.getProperty("validProduct"));

		String availProduct = searchPage.verfiySearchResults();
		Assert.assertEquals(availProduct, dataProp.getProperty("availProduct"), "Product Available");

		log.warn("Search Executed, Product Found");
		

	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {

		searchPage = homePage.selectSearchInput(dataProp.getProperty("invalidProduct"));

		String noProductString = searchPage.noProductFound();
		Assert.assertEquals(noProductString, dataProp.getProperty("noProductMatch"),
				dataProp.getProperty("noProductMatch"));

		log.info("Search Executed, Product not Found");

	}

	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {

		searchPage = homePage.selectSearchInput("");

		String noProductString = searchPage.noProductFound();
		Assert.assertEquals(noProductString, dataProp.getProperty("noProductMatch"),
				dataProp.getProperty("noProductMatch"));

		log.info("Search Executed, Product not Found");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
