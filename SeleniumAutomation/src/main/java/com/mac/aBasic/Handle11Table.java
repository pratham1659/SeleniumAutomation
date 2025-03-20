package com.mac.aBasic;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Handle11Table {
	WebDriver driver;
	String baseUrl = "https://omayo.blogspot.com/";
	String automateUrl = "https://www.automationtesting.co.uk/popups.html";
	String myUrl = "https://selenium-test-react.vercel.app/";

	@BeforeSuite
	public void beforesuit() {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(op);
		driver.get(myUrl);

		driver.manage().window().maximize(); // maximize window
		driver.manage().deleteAllCookies(); // delete all the cookies

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		System.out.println(driver.getTitle());

	}
	
//	How to handle dynamic WebTable In Selenium:
//
//		Method - 1:
//		• Iterate row and column and get the cell value.
//		• Using for loop
//		• Get total rows and iterate table
//		• Put if(string matches) then select the value
//		• Lengthy method
//
//		Method - 2:
//		• Using custom xpath
//		• Using parent and preceding sibling tags
//		• No need to write for loop
//		• No full iteration of table
//		• Single line statement
//		• More dynamic
//		• Efficient and fast
	
	
	@Test
	public void testTable() throws InterruptedException {
		
		driver.findElement(By.xpath("//a[contains(text(),'TABLES')]")).click();
		

//		Xpath for References
		//tbody/tr[1]/td[2]
		//tbody/tr[2]/td[2]
		//tbody/tr[3]/td[2]
		//tbody/tr[4]/td[2]
		//tbody/tr[5]/td[2]
		
		//Method 1
		String beforeXpath = "//tbody/tr[";
		String afterXpath = "]/td[2]";
		
		for(int i = 2; i<= 7; i++) {
			String name = driver.findElement(By.xpath(beforeXpath + i + afterXpath)).getText();
			System.out.println(name);
			
			//tbody/tr[3]/td[1]/input[1]
			
			if(name.contains("David Johnson")){
					driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]/input[1]")).click();
			}
		}
		
		Thread.sleep(2000);
		
		//Method-2:
//		driver.findElement(By.xpath("//a[contains(text), 'test2 test2')]/parent: td//preceding-sibling:: td//input [@name='contact_id']")).clickO;
		
		//Method 2
		driver. findElement(By.xpath("//td[contains(text(), 'David Johnson')]/preceding-sibling::td/input")).click();
		System.out.println("Click Done");
		
		
		
	}
	
	@AfterSuite
	public void aftersuit() throws InterruptedException {
		//driver.quit();
		System.out.println("Execution ends");
	}
}
