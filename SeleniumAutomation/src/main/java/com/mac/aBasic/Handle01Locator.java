package com.mac.aBasic;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
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

public class Handle01Locator {

	WebDriver driver;

	String baseUrl = "https://selenium-test-react.vercel.app/";

	@BeforeSuite
	public void setUp() throws InterruptedException {

		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(op);

		driver.get(baseUrl);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@Test(priority = 0)
	public void verifyWebPage() {

		String titleString = driver.getTitle();
		assertEquals(titleString, "Selenium Web");
	}

	@Test(priority = 1)
	public void contactFormTest() throws InterruptedException {

		driver.findElement(By.linkText("CONTACT US FORM TEST")).click();

		driver.findElement(By.name("firstName")).sendKeys("Pratham");
		driver.findElement(By.name("lastName")).sendKeys("Kumar");
		driver.findElement(By.name("username")).sendKeys("pratham1659");
		driver.findElement(By.name("email")).sendKeys("prathamkumar1985@gmail.com");
		driver.findElement(By.name("dob")).sendKeys("12-12-2001");
		driver.findElement(By.name("contact")).sendKeys("9508688008");

		driver.findElement(By.tagName("button")).click();

		Thread.sleep(2000);

	}

	@Test(priority = 2)
	public void navigateBack() throws InterruptedException {

		driver.navigate().to(baseUrl);

		driver.findElement(By.linkText("ACTIONS")).click();

		WebElement dragA = driver.findElement(By.id("dragElement1"));
		WebElement box2 = driver.findElement(By.name("box2"));

		Actions actions = new Actions(driver);
		actions.clickAndHold(dragA).moveToElement(box2).release().build().perform();

		Thread.sleep(2000);

	}

	@Test(priority = 3)
	public void test() throws Exception {

		System.out.println("Execution Start");

		driver.findElement(By.linkText("PREDICTIVE SEARCH")).click();

		driver.findElement(By.xpath("//form/input")).sendKeys("India");

		driver.findElement(By.xpath("//form/button[text()='Search']")).click();

		String checkIndia = driver
				.findElement(By.xpath("//div[@class='predictDialoge']/descendant::li[text()='India']")).getText();

		Assert.assertEquals(checkIndia, "India");

		String pageTitleString = driver.getTitle();
		System.out.println(pageTitleString);
		System.out.println("Title Length: " + pageTitleString.length());

		String titleWeb = driver.getTitle();

		Assert.assertEquals(titleWeb, "Selenium Web");

		String pageSourceString = driver.getPageSource();
		int sourceLength = driver.getPageSource().length();

		System.out.println(pageSourceString);
		System.out.println("Source Length: " + sourceLength);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 4)
	public void testLogin() throws Exception {

		driver.findElement(By.linkText("LOGIN PORTAL TEST")).click();

		// Locating web element
		WebElement uName = driver.findElement(By.name("username"));
		WebElement pswd = driver.findElement(By.name("password"));
		WebElement remElement = driver.findElement(By.name("rememberMe"));
		WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));

		uName.sendKeys("testuser");
		pswd.sendKeys("Password@123");
		remElement.click();
		loginBtn.click();

		try {

			WebElement logoutBtn = driver.findElement(By.xpath("//button[text()='Logout']"));

			if (logoutBtn.isDisplayed()) {
				logoutBtn.click();
				System.out.println("LogOut Successful!");
			}
		} catch (Exception e) {
			System.out.println("Incorrect login....");
		}

		System.err.println("Execution End");
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
