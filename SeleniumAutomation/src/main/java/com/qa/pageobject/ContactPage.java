package com.qa.pageobject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.*;
import com.qa.dataprovider.*;

public class ContactPage extends TestBase {

	WebDriver driver;
	ExcelDataProvider excel;

	@FindBy(xpath = "//input[@name='firstName']")
	WebElement firstNameFeild;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement lastNameFeild;

	@FindBy(xpath = "//input[@name='username']")
	WebElement userNameFeild;

	@FindBy(xpath = "//input[@name='email']")
	WebElement emailNameFeild;

	@FindBy(xpath = "//input[@name='dob']")
	WebElement dateOfBirthFeild;

	@FindBy(xpath = "//input[@name='contact']")
	WebElement contactFeild;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitBtn;

	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		

	}

	public void ContactFormTest(String firstName, String lastName, String userName, String email, String dob,
			String contact) throws IOException {

		excel = new ExcelDataProvider();
		Object object[][] = excel.getData("./src/main/java/com/store/testdata/DemoExcel.xlsx");
		for (int rowNum = 2; rowNum <= object[0].length; rowNum++) {
			firstNameFeild.sendKeys(firstName);
			lastNameFeild.sendKeys(lastName);
			userNameFeild.sendKeys(userName);
			emailNameFeild.sendKeys(email);
			dateOfBirthFeild.sendKeys(dob);
			contactFeild.sendKeys(contact);
			submitBtn.click();
		}
	}

}
