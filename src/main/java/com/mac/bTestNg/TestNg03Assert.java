package com.mac.bTestNg;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNg03Assert {

	WebDriver driver;
	String urlString = "https://tutorialsninja.com/demo/";

	@BeforeSuite
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get(urlString);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		String titleString = driver.getTitle();

		assertEquals(titleString, "Your Store");

	}

	@Test(priority = 0)
	public void sampleMethod() {

		String str1 = "Pratham";
		String str2 = "Kumar";

		assertNotEquals(str1, str2);
	}

	@Test(priority = 1)
	public void testStringEquality() {
		String expected = "Hello, TestNG";
		String actual = "Hello, TestNGs";

		// Asserting equality of two strings
		assertNotEquals(actual, expected, "Strings are not equal");
	}
	
	@Test(priority = 2)
	public void testSoftAssertion() {
		SoftAssert softAssert = new SoftAssert();
		int actual = 10;
		int expected = 5;

		// Soft Assertion: Asserting that actual value is equal to expected value
		softAssert.assertEquals(actual, expected, "Soft assertion failed: Actual value is not equal to expected");

		System.out.println("This line will be printed even if soft assertion fails.");

		// After performing all soft assertions, call assertAll() to assert all
		// failures.
		softAssert.assertAll();
	}

	@Test(priority = 3)
	public void testArrayEquality() {
		int[] expectedArray = { 1, 2, 3, 4, 5 };
		int[] actualArray = { 1, 2, 3, 4, 5 };

		// Asserting equality of two arrays
		assertEquals(actualArray, expectedArray, "Arrays are not equal");
	}


	@Test(priority = 4)
	public void testBoolean() {
		boolean condition = true;

		// Asserting a condition
		assertTrue(condition, "Condition is not true");
	}

	@Test(priority = 5)
	public void testLessThan() {
		int actual = 5;
		int expected = 10;

		// Asserting that actual value is less than expected value
		assertTrue(actual < expected, "Actual value is not less than expected");
	}

	@Test(priority = 6)
	public void testNotNull() {
		Object obj = new Object();

		// Asserting that an object is not null
		assertNotNull(obj, "Object is null");
	}

	@Test(priority = 7)
	public void testListSize() {
		List<String> list = new ArrayList<>();
		list.add("Item 1");
		list.add("Item 2");
		list.add("Item 3");

		// Asserting the size of a list
		assertEquals(list.size(), 3, "List size is not as expected");
	}

	

	@Test(priority = 8)
	public void testHardAssertion() {
		int actual = 10;
		int expected = 10;

		// Hard Assertion: Asserting that actual value is equal to expected value
		Assert.assertEquals(actual, expected, "Hard assertion failed: Actual value is not equal to expected");

		System.out.println("This line will be printed only if hard assertion passes.");
	}
	
	@Test(priority = 9, enabled = false)
	public void testFail() {
		// Forcing a test to fail
		fail("This test intentionally fails");
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
