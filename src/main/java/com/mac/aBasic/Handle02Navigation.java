package com.mac.aBasic;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Handle02Navigation {

	WebDriver driver;

	String baseUrl = "https://selenium-test-react.vercel.app/";

	@BeforeSuite
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get(baseUrl);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		System.out.println(driver.getTitle());
	}

	public static void clickOn(WebDriver driver, WebElement element, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		}
	}

	@Test(priority = 0)
	public void beforeTestPage() throws InterruptedException {

		driver.navigate().to("https://www.facebook.com");
		driver.findElement(By.name("email")).sendKeys("pratham");
		driver.findElement(By.id("pass")).sendKeys("kumar");

		WebElement fbButton = driver.findElement(By.name("login"));
		clickOn(driver, fbButton, 5); // explicit wait

		driver.navigate().back();

		Thread.sleep(1000);

		driver.navigate().to(baseUrl);

		String pageTitle = driver.getTitle();
		Assert.assertEquals("Selenium Web", pageTitle);

	}

	@Test(priority = 1)
	public void takeScreenshots() throws IOException {

		// Screenshots in Selenium
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("/Users/prathamkumar/eclipse-workspace/MainTest/screenshots/test.png"));

		driver.navigate().forward();
		System.out.println(driver.getTitle());

		driver.navigate().refresh();

	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}