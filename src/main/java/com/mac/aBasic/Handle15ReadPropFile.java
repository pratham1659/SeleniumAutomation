package com.mac.aBasic;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Handle15ReadPropFile {
	
	static  WebDriver driver;
	
	public static void main(String[] args) throws IOException {
		
		
		Properties prop = new Properties();

		FileInputStream ip = new FileInputStream(
				"/Users/prathamkumar/eclipse-workspace/MainTest/src/main/java/com/pratham/config.properties");

		prop.load(ip);
		
		String browsername = prop.getProperty("browser");
		
		if(browsername.equalsIgnoreCase("chrome")) {
			ChromeOptions op = new ChromeOptions();
			op.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			
			driver = new ChromeDriver(op);
		
		}else if (browsername.equalsIgnoreCase("firefox")) {
			FirefoxOptions op = new FirefoxOptions();
			op.addArguments("--remote-allow-origins=*");
			WebDriverManager.firefoxdriver().setup();
			
			driver = new FirefoxDriver(op);
			
		}else if(browsername.equalsIgnoreCase("edge")) {
			EdgeOptions op = new EdgeOptions();
			op.addArguments("--remote-allow-origins=*");
			WebDriverManager.edgedriver().setup();
			
			driver = new EdgeDriver(op);
			
		}
		
		String url = prop.getProperty("URL");

		System.out.println(url);
		
		driver.get(url);
	

		driver.manage().window().maximize(); // maximize window
		driver.manage().deleteAllCookies(); // delete all the cookies
		
	
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		System.out.println(driver.getTitle());
		
		System.out.println(prop.getProperty("name"));

		System.out.println(prop.getProperty("age"));
		
		
		driver.findElement(By.name(prop.getProperty("firstname_xpath"))).sendKeys("Pratham");
		
		driver.findElement(By.name(prop.getProperty("lastname_xpath"))).sendKeys("Kumar");
		
		driver.findElement(By.name(prop.getProperty("email_xpath"))).sendKeys("prathamkumar@gmail.com");
		
		driver.findElement(By.name(prop.getProperty("message_xpath"))).sendKeys("Hii I missed You");
		
		driver.findElement(By.xpath(prop.getProperty("submit_xpath"))).click();
		
		
	

	}

}
