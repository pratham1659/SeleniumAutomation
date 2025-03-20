package com.mac.cExcelUtility;

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

public class LoginExcel {

	WebDriver driver;

	@BeforeSuite
	public void setUp() {

		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		op.addArguments("window-size=1400,800");
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver(op);

		driver.get("https://selenium-test-react.vercel.app/loginportal");

		driver.manage().deleteAllCookies(); // delete all the cookies

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@Test(priority = 0)
	public void readExcel() {

		ExcelXLSXReader reader = new ExcelXLSXReader("./src/test/java/DemoExcel.xlsx");
		String sheetName = "login";

		WebElement userElement = driver.findElement(By.name("username"));
		WebElement passwordElement = driver.findElement(By.name("password"));
		WebElement checkElement = driver.findElement(By.name("rememberMe"));
		WebElement submitElement = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

		int rowCount = reader.getRowCount(sheetName);

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {

			String loginId = reader.getCellData(sheetName, "username", rowNum);
			String password = reader.getCellData(sheetName, "password", rowNum);

			System.out.println(loginId + " " + password);

			userElement.clear();
			userElement.sendKeys(loginId);
			passwordElement.clear();
			passwordElement.sendKeys(password);
			checkElement.click();
			submitElement.click();

		}
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
