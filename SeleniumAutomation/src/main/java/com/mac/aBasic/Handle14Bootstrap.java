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

public class Handle14Bootstrap {

	WebDriver driver;
	String baseUrl = "https://omayo.blogspot.com/";
	String automateUrl = "http://www.jquery-az.com/boots/demo.php?ex=63.0_2";
	String myUrl = "https://selenium-test-react.vercel.app/";

	@BeforeSuite
	public void beforesuit() {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(op);
		driver.get(automateUrl);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		System.out.println(driver.getCurrentUrl());

	}

	@Test
	public void handleBootstrapXpath() throws InterruptedException {

		driver.findElement(By.xpath(" //button[contains(@class, 'multiselect')]")).click();

		List<WebElement> list = driver
				.findElements(By.xpath("//ul[contains(@class, 'multiselect-container')]//li//a//label"));

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());

			if (list.get(i).getText().contains("Angular")) {
				list.get(i).click();
				break;
			}
		}

	}
	
	
	@Test
	public void handleXpath() throws InterruptedException {
		
		
		driver.navigate().to("");
	}

	@AfterSuite
	public void AfterTest() {
		System.out.println("Execuation End");
	}

}
