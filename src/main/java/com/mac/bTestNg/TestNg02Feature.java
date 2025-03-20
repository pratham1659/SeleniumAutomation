package com.mac.bTestNg;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.Date;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNg02Feature {

	WebDriver driver;
	String automateUrl = "https://tutorialsninja.com/demo/";
	int count = 1;

	@BeforeMethod(groups = {"webdriver", "all"})
	public void setUp() {

		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		op.addArguments("window-size=1400,800");
		//op.addArguments("headless");
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver(op);

		driver.get(automateUrl);

		driver.manage().deleteAllCookies();

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}

	@Test(priority = 1, groups = {"regression", "login", "all"})
	public void shopTitleTest() throws Exception {
		
		System.out.println("Level - " + count++ + " ShopTitleTest");

		String title = driver.getTitle();


		String titleString = "Your Store";

		assertEquals(title, titleString, "Title is not Matched");

		assertTrue(driver.findElement(By.linkText("Qafox.com")).isDisplayed());

	}

	public String generateEmailTimeStamp() {

		Date date = new Date(0);
		return date.toString().replace(" ", " ").replace(":", " ") + "@gmail.com";
	}
	
	@Test(groups = {"all","sanity"})
	public void sampleTestOne() {
		
		System.out.println("Level - " + count++ + " NoPriority");
	}
	
	

	@Test(priority = 2)
	public void verifyLoginWithPage() {
		
		System.out.println("Level - " + count++ + " VerifyLoginPage");

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();

		assertTrue(driver.findElement(By.xpath("//h2[text()='Returning Customer']")).isDisplayed());
			
	}
	
	@Test(priority = 0)
	public void sampleTestTwo() {
		
		System.out.println("Level - " + count++ + " PriorityZero");
	}
	
	@Test(priority = 3)
	public void loginWithInvalidCredentials() {
		
		
		System.out.println("Level - " + count++ + " LoginInvalidCredentials");
		
		verifyLoginWithPage();
		
		driver.findElement(By.id("input-email")).sendKeys("pratham@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("pratham123");
		
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		//assertTrue(driver.findElement(By.xpath("//div[text()='Warning: No match for E-Mail Address and/or Password.']")).isDisplayed());
	}
	
	

	@Test(priority = 3, groups = "smoke")
	public void googleLogoTest() {

		boolean a = driver.findElement(By.linkText("Qafox.com")).isDisplayed();
		Assert.assertTrue(a);
	}

	@Test(priority = 4, groups = "smoke")
	public void gmailLinkTest() {

		boolean b = driver.findElement(By.linkText("Desktops")).isDisplayed();
		Assert.assertTrue(b);

	}

	@Test(priority = 5, groups = {"smoke", "sanity"})
	public void test1() {
		System.out.println("test1");
	}

	@Test(priority = 6, groups = "sanity")
	public void test2() {
		System.out.println("test2");
	}

	@Ignore
	@Test
	public void test3() {
		System.out.println("I am Ignored");
	}

	@AfterMethod(groups = "webdriver")
	public void tearDown() {
		driver.quit();
	}
}
