package com.mac.aBasic;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Handle12Dynamic {

	WebDriver driver;
	String baseUrl = "https://omayo.blogspot.com/";
	String google = "https://www.google.com/";
	String myUrl = "https://selenium-test-react.vercel.app/";

	@BeforeSuite
	public void beforesuit() {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		op.addArguments("headless");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(op);
		driver.get(google);

		driver.manage().window().maximize(); // maximize window
		driver.manage().deleteAllCookies(); // delete all the cookies

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		System.out.println(driver.getTitle());

	}

	@Test
	public void test() {

		driver.findElement(By.xpath("//textarea[@id='APjFqb']")).sendKeys("testing");

		List<WebElement> list = driver
				.findElements(By.xpath("//ul[@role='listbox']//li/descendant::div[@class='eIPGRd']"));

		System.out.println("Total number of Suggestion: " + list.size());

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());

			if (list.get(i).getText().contains("testing")) {
				list.get(i).click();
				break;
			}
		}

	}

	@AfterSuite
	public void aftersuit() throws InterruptedException {
		driver.quit();
		System.out.println("Execution ends");
	}
}
