package com.mac.cExcelUtility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Excel3TestNg {

	WebDriver driver;
	String myUrl = "https://selenium-test-react.vercel.app/loginportal";

	@BeforeSuite
	public void setUp() throws Exception {

		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver(op);

		driver.get(myUrl);

		driver.manage().deleteAllCookies(); // delete all the cookies
		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@DataProvider
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> testData = ReadExcel.getDataFromExcel();
		return testData.iterator();
	}

	@Test(dataProvider = "getTestData")
	public void testContactForm(String username, String password) throws Exception {

		driver.findElement(By.name("username")).sendKeys(username);

		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.name("rememberMe")).click();

		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[contains(text(),'Logout')]")).click();

	}

	@AfterSuite
	public void tearDown() throws Exception {
		driver.quit();
	}

}
