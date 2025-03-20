package com.qa.pageobject;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.actions.*;
import com.qa.base.*;

public class ButtonPage extends TestBase {

	WebDriver driver;
	IndexPage indexPage;
	ButtonPage buttonPage;
	ActionDriver actionDriver;
	Actions actions;
	WebDriverWait wait;
	JavascriptExecutor js;

	@FindBy(xpath = "//button[contains(text(),'Button Click')]")
	WebElement buttonClick;

	@FindBy(xpath = "//button[contains(text(),'Button JS Click')]")
	WebElement buttonJsClick;

	@FindBy(xpath = "//button[contains(text(),'Button Action Click')]")
	WebElement buttonActionClick;

	@FindBy(xpath = "//button[@disabled='']")
	WebElement disabledButton;

	@FindBy(xpath = "//button[contains(text(),'Double Click Button')]")
	WebElement doubleClickButton;

	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement checkBoxButtonEnable;

	public ButtonPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		actionDriver = new ActionDriver();
		actions = new Actions(driver);
	}

	public void NormalButtonClick() {
		buttonClick.click();
	}

	public void jsButtonClick() {
		js.executeScript("arguments[0].click();", buttonJsClick);
	}

	public void actionMoveAndClick() {
		actions.click(buttonActionClick).build().perform();

	}

	public void disabledButtonToEnable() {
		js.executeScript("arguments[0].removeAttribute('disabled');", disabledButton);

		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(disabledButton));

	}

	public Boolean checkDisabled() {
		return disabledButton.isEnabled();

	}

	public void doubleClickOnButton() {
		actions.doubleClick(doubleClickButton).build().perform();
	}

	public void clickOnCheckBox() {
		checkBoxButtonEnable.click();

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(checkBoxButtonEnable));

	}

}
