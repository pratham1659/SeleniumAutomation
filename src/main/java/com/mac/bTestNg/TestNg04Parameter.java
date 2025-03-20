package com.mac.bTestNg;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNg04Parameter {

	WebDriver driver;

	@Parameters({ "env", "browser", "url", "emailId", "password" })
	@Test
	public void setUp(@Optional("https://tutorialsninja.com/demo/") String env, String Browser, String url, String emailId, String password) {
		if (Browser.equalsIgnoreCase("Chrome")) {
			ChromeOptions op = new ChromeOptions();
			op.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(op);

		} else if (Browser.equalsIgnoreCase("firefox")) {
			FirefoxOptions op = new FirefoxOptions();
			op.addArguments("--remote-allow-origins=*");
			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver(op);
		}

		driver.get(url);

		driver.manage().window().maximize(); // maximize window
		driver.manage().deleteAllCookies(); // delete all the cookies

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();

		assertTrue(driver.findElement(By.xpath("//h2[text()='Returning Customer']")).isDisplayed());
		
		driver.findElement(By.id("input-email")).sendKeys(emailId);
		driver.findElement(By.id("input-password")).sendKeys(password);
		
		driver.findElement(By.xpath("//input[@value='Login']")).click();

	}

}
