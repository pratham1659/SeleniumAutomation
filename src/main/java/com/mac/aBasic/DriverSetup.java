package com.mac.aBasic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {

	private WebDriver driver;

	public WebDriver getDriver(String browsername) {
		if (browsername.equalsIgnoreCase("Chrome")) {
			ChromeOptions op = new ChromeOptions();
			op.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver(op);
			return driver;
		}
		if (browsername.equalsIgnoreCase("firefox")) {
			FirefoxOptions op = new FirefoxOptions();
			op.addArguments("--remote-allow-origins=*");
			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver(op);
			return driver;
		}
		if (browsername.equalsIgnoreCase("edge")) {
			EdgeOptions op = new EdgeOptions();
			op.addArguments("--remote-allow-origins=*");
			WebDriverManager.edgedriver().setup();

			driver = new EdgeDriver(op);
			return driver;
		}
		return null;

		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "/Users/prathamkumar/eclipse-workspace/MainTest/driver/chromedriver");
		 * WebDriver driver = new ChromeDriver();
		 */

	}

	public void navigateTo(String url) {
		// Navigate to the specified URL
		driver.get(url);
	}

	public void close() {
		// Close the WebDriver instance
		driver.quit();
	}

}
