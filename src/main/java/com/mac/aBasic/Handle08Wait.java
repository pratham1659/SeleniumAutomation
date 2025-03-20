package com.mac.aBasic;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Handle08Wait {

	WebDriver driver;
	String baseUrl = "https://selenium-test-react.vercel.app/";
	String nextUrl = "https://omayo.blogspot.com/";

	@BeforeSuite
	public void beforesuit() {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(op);
		driver.get(baseUrl);

		driver.manage().window().maximize(); // maximize window
		driver.manage().deleteAllCookies(); // delete all the cookies

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		System.out.println(driver.getTitle());

	}

	@Test(priority = 0)
	public void testSimpleButton() throws Exception{
		
		driver.findElement(By.linkText("BUTTONS")).click();	
		driver.findElement(By.xpath("//button[contains(text(), 'Button Click')]")).click();
		
		
		Thread.sleep(2000);
	
	}
	
	@Test(priority = 1)
	public void testCssButton() throws Exception{
		
		 WebElement jsButton = driver.findElement(By.xpath("//button[text()='Button JS Click']"));
		 
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].click()", jsButton);
		 
		 Thread.sleep(2000);
	}
	
	@Test(priority = 2)
	public void testActionButton() throws Exception{
		
		
		 WebElement actButton = driver.findElement(By.xpath("//button[text()='Button Action Click']"));
		 
		 Actions actions = new Actions(driver);
		 actions.clickAndHold(actButton).click().build().perform();
		 
		 Thread.sleep(2000);
	}
	
	@Test(priority = 3)
	public void testDelayedDropdown() throws Exception{
		
		WebElement dropBtn = driver.findElement(By.xpath("//button[text()='Dropdown Menu']"));
		
		
		Actions actions = new Actions(driver);
		
		actions.moveToElement(dropBtn).click().build().perform();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[text()='Link 1']")));
		
		
		Boolean checkBoolean = driver.findElement(By.xpath("//li[text()='Link 1']")).isDisplayed();
		
		driver.findElement(By.xpath("//li[text()='Link 1']")).click();
		
		Assert.assertTrue(checkBoolean);
		
		Thread.sleep(3000);
	}
	
	@Test(priority = 4)
	public void testDisabledButton() throws Exception {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight)");

		Thread.sleep(3000);

		Boolean checkEnable = driver.findElement(By.xpath("//button[text()='Disabled button']")).isEnabled();

		Assert.assertFalse(checkEnable);
		
		driver.findElement(By.xpath("//button[text()='Enable Button']")).click();
		
		Boolean checkAgain = driver.findElement(By.xpath("//button[text()='Disabled button']")).isEnabled();
		
		Assert.assertTrue(checkAgain);
		
		Thread.sleep(3000);
		
		
	}
	
	@Test(priority = 5)
	public void testDoubleClick() throws Exception{
		
		WebElement doubleBtn = driver.findElement(By.xpath("//button[text()='Double Click Button']"));
		
		Actions actions = new Actions(driver);
		
		actions.doubleClick(doubleBtn).build().perform();
		
		Thread.sleep(2000);
	}

	@Test(priority = 6)
	public void testDisabledExplicitWait() throws Exception {

		driver.findElement(By.xpath("//input[@type='checkbox']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.name("checkDisabledButton")));
		
		boolean checkEnable = button.isEnabled();

		Assert.assertTrue(checkEnable);
		
		Thread.sleep(2000);

	}
	
	@Test(priority = 7)
	public void testHiddenButton() throws Exception{
		
		driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
		
		Boolean checkHidden = driver.findElement(By.name("checkHiddenButton")).isDisplayed();
	
		
		Assert.assertFalse(checkHidden);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(By.name("checkHiddenButton")));
		

		Boolean checkAgain = driver.findElement(By.name("checkHiddenButton")).isDisplayed();
		
		
		Assert.assertTrue(checkAgain);
		Thread.sleep(2000);
		
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
