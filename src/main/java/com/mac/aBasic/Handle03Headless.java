package com.mac.aBasic;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Handle03Headless {
	
	WebDriver driver;
	String baseUrl = "https://omayo.blogspot.com/";
	String automateUrl = "https://www.automationtesting.co.uk/popups.html";
	String myUrl = "https://selenium-test-react.vercel.app/";

	@BeforeSuite
	public void beforesuit() {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		op.addArguments("window-size=1400,800");
		op.addArguments("headless");
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver(op);
		
		
		
		driver.get(myUrl);

		driver.manage().deleteAllCookies(); // delete all the cookies
		
	
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		System.out.println(driver.getTitle());

	}
	
	
	@Test
	public void testCalculatorHeadless() throws InterruptedException {
		
		driver.findElement(By.xpath("//a[contains(text(),'CALCULATOR')]")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'7')]")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'+')]")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'3')]")).click();
		
		driver.findElement(By.xpath("//button[contains(text(),'=')]")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		System.out.println("Title: " + driver.getCurrentUrl());
		System.out.println("Current URL: " + driver.getTitle());
		
		
		
		
		WebElement element =  driver.findElement(By.xpath("//div[contains(text(),'10')]"));
		
		element.getText();
		
		if(element.getText().equals("10")) {
			System.out.println("Calculation Successfully");
		}else {
			System.out.println("Error in Program");
		}
		
		Thread.sleep(2000);
		

	}
	
	@AfterSuite
	public void aftersuit() throws InterruptedException {
		driver.quit();
		System.out.println("Execution ends");
	}
	
	

}
