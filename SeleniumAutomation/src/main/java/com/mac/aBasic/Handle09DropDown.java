package com.mac.aBasic;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Handle09DropDown {
	
	WebDriver driver;
	String BaseUrl = "https://selenium-test-react.vercel.app/";
	
	
	
	@BeforeSuite
	public void setUp() {
		
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver(op);
	
		driver.get(BaseUrl);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
	}
	
	
	@Test(priority = 0)
	public void testRadioButton() throws Exception{
		
		driver.findElement(By.linkText("DROPDOWN CHECKBOX RADIO")).click();
		
		driver.findElement(By.xpath("//label[text()='Option 1']")).click();
		
		boolean checkOption1 = driver.findElement(By.xpath("//input[@value='option1']")).isSelected();
		
		Assert.assertTrue(checkOption1);
	}
	
	@Test(priority = 1)
	public void textCheckBox() throws Exception{
		
		driver.findElement(By.xpath("//label[text()='Checkbox 1']")).click();
		
		driver.findElement(By.xpath("//label[text()='Checkbox 3']")).click();
		
		
		boolean checkBox = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).isDisplayed();
		boolean checkTwo = driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).isDisplayed();
		
		Assert.assertTrue(checkBox);
		Assert.assertTrue(checkTwo);
		
		Thread.sleep(2000);
	
	}
	
	@Test(priority = 2)
	public void testSelectOption() throws Exception{
		
		WebElement selectBtn = driver.findElement(By.xpath("//h2[text()='Select an Option']/following-sibling::select"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(selectBtn).build().perform();
		
		driver.findElement(By.xpath("//option[@value='option 2']")).click();
		
		boolean checkOption = driver.findElement(By.xpath("//option[@value='option 2']")).isDisplayed();
		
		Assert.assertTrue(checkOption);
		
		Thread.sleep(3000);
		
	}
	
	@Test(priority = 3)
	public void testNavigationMenu() throws Exception{
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500)");
		
		WebElement animal = driver.findElement(By.xpath("//ul[@class='headerMenu']//li[text()='Animal']"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(animal).build().perform();
		
		WebElement tiger = driver.findElement(By.xpath("//li[text()='Tiger']"));
		
		tiger.click();
		
		boolean checkTiger = driver.findElement(By.xpath("//input[@value='Tiger']")).isDisplayed();
		
		Assert.assertTrue(checkTiger);
		
		Thread.sleep(3000);
			
	}
	
	
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
