package com.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.actions.*;
import com.qa.base.*;

public class AccordionPage extends TestBase {

	WebDriver driver;
	ActionDriver actionDriver;

	@FindBy(xpath = "//button[contains(text(), '#1')]")
	WebElement clickOnAccordionOne;

	@FindBy(xpath = "//div[contains(text(),'This is the first item accordion body.')]")
	WebElement alertAccordionText;

	public AccordionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actionDriver = new ActionDriver();

	}

	public void clickAccordion() throws InterruptedException {
		clickOnAccordionOne.click();
		actionDriver.implicitWait(driver, 10);

	}

	public String verifyAccord() {
		return alertAccordionText.getText();
	}

}
