package com.mac.aBasic;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Handle07Popup {

	WebDriver driver;
	String baseUrl = "https://selenium-test-react.vercel.app/";

	@BeforeSuite
	public void beforesuit() {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(op);
		driver.get(baseUrl);

		driver.manage().window().maximize(); // maximize window
		driver.manage().deleteAllCookies(); // delete all the cookies

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		System.out.println(driver.getTitle());

	}

	@Test(priority = 0)
	public void testAlertAccept() throws Exception {
		driver.findElement(By.linkText("POP UPS & ALERTS")).click();

		driver.findElement(By.xpath("(//button[@class='popupButton'])[3]")).click();

		Alert alert = driver.switchTo().alert();

		String textString = alert.getText();

		if (textString.equals("Alert Button clicked!")) {
			System.out.println("Correct Alert Message");
		} else {
			System.out.println("Incorrect Message");
		}

		Assert.assertEquals(alert.getText(), "Alert Button clicked!");

		alert.accept();

	}

	@Test(priority = 1)
	public void testAlertDismiss() throws Exception {
		driver.findElement(By.xpath("//button[text()='Dismiss Alert']")).click();

		Alert alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "Alert dismissed!");

		alert.dismiss();
	}

	@Test(priority = 2)
	public void testPopupAlertOne() throws Exception {

		String defaultWindowHandle = driver.getWindowHandle();

		driver.findElement(By.xpath("(//button[@class='popupButton'])[1]")).click();

		Thread.sleep(1000);

		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println("Number of opened windows: " + windowHandles.size());

		for (String str : windowHandles) {
			driver.switchTo().window(str);

			if (driver.getTitle().equals("Google")) {
				driver.close();
			}
		}

		driver.switchTo().window(defaultWindowHandle);

		System.out.println(driver.getTitle());

		Assert.assertEquals(driver.getTitle(), "Selenium Web");

	}

	@Test(priority = 3)
	public void testPopupAlertTwo() throws Exception {

		driver.findElement(By.xpath("(//button[@class='popupButton'])[1]")).click();

		Set<String> handle = driver.getWindowHandles();

		Iterator<String> it = handle.iterator();

		String parentWindowId = it.next();
		System.out.println("Parent Window Id: " + parentWindowId);

		String childWindowId = it.next();

		driver.switchTo().window(childWindowId);

		System.out.println("Child Window Id: " + childWindowId);

		Assert.assertEquals(driver.getTitle(), "Google");

		driver.close();

		driver.switchTo().window(parentWindowId);

		Assert.assertEquals(driver.getTitle(), "Selenium Web");

	}

	@Test(priority = 4)
	public void testFileUplaod() throws Exception {

		driver.findElement(By.xpath("//a[contains(text(),'FILE UPLOAD')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.name("FileUpload")).sendKeys("/Users/prathamkumar/Downloads/Testing/SDETHandbook.pdf");

		driver.findElement(By.xpath("//button[text()='Submit']")).click();

		Thread.sleep(1000);

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
		System.out.println("Execution End");
	}

}
