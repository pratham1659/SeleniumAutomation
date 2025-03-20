package com.qa.pageobject;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.actions.*;

public class ActionPage {

	WebDriver driver;
	Actions actions;
	ActionDriver actionDriver;
	WebDriverWait wait;

	// Action Button

	@FindBy(id = "dragElement1")
	WebElement dragElement1;

	@FindBy(id = "dragElement2")
	WebElement dragElement2;

	@FindBy(name = "box1")
	WebElement boxElement1;

	@FindBy(name = "box2")
	WebElement boxElement2;

	@FindBy(xpath = "//p[@id='dragElement1']")
	WebElement verifyDragElement;

	@FindBy(className = "hold-box")
	WebElement holdBox;

	@FindBy(xpath = "//div[contains(text(),'Keep holding down')]")
	WebElement mouseHold;

	@FindBy(xpath = "//div[contains(text(),'Double click here')]")
	WebElement doubleClickElement;

	public ActionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
		actionDriver = new ActionDriver();
	}

	public void dragElementToAnotherBox() {
		actions.clickAndHold(dragElement1).moveToElement(boxElement2).release().build().perform();
	}

	public String verifyDrag() {
		return verifyDragElement.getText();
	}

	public void holdMouseAction() {
		actions.clickAndHold(holdBox).build().perform();

		actionDriver.explicitWait(driver, holdBox, 30);

	}

	public String verifyHold() {
		return mouseHold.getText();
	}

	public void doubleClickOnBox() {
		actions.doubleClick(doubleClickElement).perform();
		
		actionDriver.explicitWait(driver, doubleClickElement, 20);
		
	}
	
	public String verifyDoubleClick() {
		return doubleClickElement.getText();
	}


}
