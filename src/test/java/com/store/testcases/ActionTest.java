package com.store.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.actions.ActionDriver;
import com.qa.base.*;
import com.qa.pageobject.*;

public class ActionTest extends TestBase {

	public WebDriver driver;
	IndexPage indexPage;
	ActionPage actionPage;
	ActionDriver actionDriver;

	public ActionTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowser();
		indexPage = new IndexPage(driver);
		actionDriver = new ActionDriver();
		actionPage = indexPage.navigateToActionPage();

	}

	@Test(priority = 1)
	public void actionDragElement() throws InterruptedException {

		actionPage.dragElementToAnotherBox();
		actionDriver.implicitWait(driver, 10);
		String vDragString = actionPage.verifyDrag();
		Assert.assertEquals(vDragString, "Drag me(A)");

	}

	@Test(priority = 2)
	public void actionHoldKey() throws InterruptedException {

		actionPage.holdMouseAction();

		String vHold = actionPage.verifyHold();
		Assert.assertEquals(vHold, "Keep holding down");

	}

	@Test(priority = 3)
	public void actionDoubleClick() throws InterruptedException {

		actionPage.doubleClickOnBox();

		String vDoubleString = actionPage.verifyDoubleClick();
		Assert.assertEquals(vDoubleString, "Double click here");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
