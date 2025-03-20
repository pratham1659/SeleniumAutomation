package com.mac.bTestNg;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNg01Basic {

	// Pre-condition annotations -- Starting with @Before
	int count = 1;

	@BeforeSuite 
	public void beforeSuite() {
		System.out.println("Level - " + count++ + " Before Suite");

		// Use @BeforeSuite to annotate a method that should run before all tests in the
		// suite.
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("Level - " + count++ + " After Suite");

		/*
		 * Use @AfterSuite to annotate a method that should run after all tests in the
		 * suite. These methods are useful for setting up and tearing down resources
		 * that are common to all tests in the suite.
		 */
	}

	@BeforeTest 
	public void beforeTest() {
		System.out.println("Level - " + count++ + " Before Test");

		// Use @BeforeTest to annotate a method that should run before all test methods
		// belonging to a <test> tag in the XML suite file.
	}

	@AfterTest
	public void afterTest() {
		System.out.println("Level - " + count++ + " After Test");

		/*
		 * Use @AfterTest to annotate a method that should run after all test methods
		 * belonging to a <test> tag in the XML suite file. These methods are useful for
		 * setting up and tearing down resources that are common to all tests in a
		 * specific <test> tag.
		 */

	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Level - " + count++ + " Before Class");

		// Use @BeforeClass to annotate a method that should run once before any test
		// method in the current class.
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Level - " + count++ + " After Class");

		/*
		 * Use @AfterClass to annotate a method that should run once after all test
		 * methods in the current class. These methods are useful for setting up and
		 * tearing down resources that are common to all test methods in the class.
		 */
	}

	@BeforeMethod 
	public void beforeMethod() {
		System.out.println("Level - " + count++ + " Before Method");

		// Use @BeforeMethod to annotate a method that should run before each test
		// method in the class.
	}

	@AfterMethod 
	public void afterMethod() {
		System.out.println("Level - " + count++ + " After Method");

		/*
		 * Use @AfterMethod to annotate a method that should run after each test method
		 * in the class. These methods are useful for setting up and tearing down
		 * resources specific to each test method.
		 */
	}

	// Define the data provider method
	@DataProvider(name = "testdata")
	public Object[][] testData() {
		return new Object[][] { { "user1", "password1" }, { "user2", "password2" }, { "user3", "password3" } };
	}

	@Test(priority = 1) 
	public void test1() {
		System.out.println("Level - " + count++ + " Test 1");

		/*
		 * Use @Test to mark a method as a test method. TestNG will execute methods
		 * annotated with @Test as test cases. You can specify attributes such as
		 * priority, dataProvider, groups, etc., to control test execution behavior.
		 */
	}

	@Test(priority = 2)
	public void test2() {
		System.out.println("Level - " + count++ + " Test 2");
	}

	@Test(dataProvider = "testdata") // - Test method that uses the data provider
	public void testLogin(String username, String password) {
		System.out.println("Level - " + count++ + " Logging in with username: " + username + ", password: " + password);
		
		// Perform login with the provided username and password
		// Assert login success or failure
	}

}
