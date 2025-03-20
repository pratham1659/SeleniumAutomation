package com.mac.aBasic;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Handle05Frame {
	
	WebDriver driver;
	String BaseUrl = "https://selenium-test-react.vercel.app/";

	@BeforeSuite
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get(BaseUrl);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@Test(priority = 0)
	public void navigateToFrame() throws Exception {

		driver.findElement(By.linkText("IFRAMES")).click();

		// Switch to the iFrame
		WebElement iFrame = driver.findElement(By.className("frame-iframe"));
		driver.switchTo().frame(iFrame);

		String siteTest = driver.findElement(By.xpath("//h2[text()='Browser Tabs']")).getText();

		Assert.assertEquals("Browser Tabs", siteTest);

		driver.findElement(By.xpath("//button[text()='Open New Tab']")).click();

		Thread.sleep(2000);
		
	}

	@Test(priority = 1)
	public void navigateToNewWindow() throws Exception {

		// Switch back to the default content (outside of the iframe)
		driver.switchTo().defaultContent();
		
		System.out.println(driver.getCurrentUrl());

		// Get all windows Handles
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(1));

		driver.close();

		// Switch back to the original tab
		driver.switchTo().window(tabs.get(0));

		String mainSite = driver.findElement(By.xpath("//h2[text()='Selenium iFrame Playground']")).getText();
		
		
		System.out.println(driver.getCurrentUrl());

		Assert.assertEquals("Selenium iFrame Playground", mainSite);
		
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
