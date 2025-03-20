package com.store.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.*;
import com.qa.pageobject.*;
import com.qa.utils.TestUtils;
import com.qa.dataprovider.*;

public class ContactTest extends TestBase {

	WebDriver driver;
	IndexPage indexPage;
	ContactPage contactPage;
	ExcelDataProvider excel;

	public ContactTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		indexPage = new IndexPage(driver);
		contactPage = indexPage.navigateToContactPage();
	}

	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] dataObject = TestUtils.getTestDataFromExcel("User");
		return dataObject;
	}

	@Test(enabled = false)
	public void contactFormDataProvider() throws IOException {

		excel = new ExcelDataProvider();
		Object object[][] = excel.getData("./src/main/java/com/store/testdata/DemoExcel.xlsx");

		for (int rowNum = 2; rowNum <= object[0].length; rowNum++) {

			System.out.println("------------");
			String firstName = (String) object[rowNum][0];
			System.out.println(firstName);

			String lastName = (String) object[rowNum][1];
			System.out.println(lastName);

			String username = (String) object[rowNum][2];
			System.out.println(username);

			String email = (String) object[rowNum][3];
			System.out.println(email);

			String dateOfBirth = (String) object[rowNum][4];
			System.out.println(dateOfBirth);

			String contact = (String) object[rowNum][5];
			System.out.println(contact);

			driver.findElement(By.name("firstName")).sendKeys(firstName);

			driver.findElement(By.name("lastName")).sendKeys(lastName);

			driver.findElement(By.name("username")).sendKeys(username);

			driver.findElement(By.name("email")).sendKeys(email);

			driver.findElement(By.name("dob")).sendKeys(dateOfBirth);

			driver.findElement(By.name("contact")).sendKeys(contact);

			driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

		}

	}

	@Test
	public void contactFormData(String firstName, String lastName, String userName, String email, String dob,
			String contact) throws IOException {
		contactPage.ContactFormTest(firstName, lastName, userName, email, dob, contact);
	}

	@AfterMethod
	public void tearDown() {
//		driver.quit();
	}
}
