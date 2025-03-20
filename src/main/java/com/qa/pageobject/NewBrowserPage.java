package com.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;

import com.qa.actions.*;
import com.qa.base.*;

public class NewBrowserPage extends TestBase {

	WebDriver driver;
	ActionDriver actionDriver;
	WebDriverWait wait;
	Actions actions;
	
	@FindBy(xpath = "//button[contains(text(),'Open New Tab')]")
	WebElement newTabElement;
	

	public NewBrowserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
		actionDriver = new ActionDriver();
	}
	
	public void clickOnNewWindow() {
		newTabElement.click();
	}
	
	public void switchToNewWindow() {
		// Get the window handles
        Set<String> windowHandles = driver.getWindowHandles();
        
        // Switch to the new tab
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

	}
	
	

}
