package com.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.*;

public class IndexPage extends TestBase {

	WebDriver driver;

	@FindBy(xpath = "//h1[contains(text(),'Welcome to HomePage')]")
	WebElement verifyHome;

	// Accordion
	@FindBy(xpath = "//a[contains(text(),'ACCORDION')]")
	WebElement clickAccordionMenu;

	//Actions
	@FindBy(xpath = "//a[contains(text(),'ACTIONS')]")
	WebElement clickActionMenu;

	//Browser Tabs
	@FindBy(xpath = "//a[contains(text(),'BROWSER TABS')]")
	WebElement newBrowserMenu;
	
	//Buttons Menu
	@FindBy(xpath = "//a[contains(text(),'BUTTONS')]")
	WebElement buttonMenu;
	
	@FindBy(xpath = "//a[contains(text(),'CALCULATOR')]")
	WebElement calculatorMenu;
	
	@FindBy(xpath = "//a[contains(text(),'CONTACT US FORM TEST')]")
	WebElement contactUsMenu;

	public IndexPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	public String verfiyHomePages() {
		return verifyHome.getText();
	}
	
	public AccordionPage navigateToAccordion() {
		clickAccordionMenu.click();
		return new AccordionPage(driver);
	}

	public ActionPage navigateToActionPage() {
		clickActionMenu.click();
		return new ActionPage(driver);
	}

	public NewBrowserPage navigateToNewBrowserMenu() {
		newBrowserMenu.click();
		return new NewBrowserPage(driver);
	}
	
	public ButtonPage navigateToButtonPage() {
		buttonMenu.click();
		return new ButtonPage(driver);
	}
	
	public ContactPage navigateToContactPage() {
		contactUsMenu.click();
		return new ContactPage(driver);
	}

}
