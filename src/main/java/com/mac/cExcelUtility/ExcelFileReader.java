package com.mac.cExcelUtility;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelFileReader {

	WebDriver driver;

	@BeforeSuite
	public void setUp() throws Exception {

		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		op.addArguments("window-size=1400,800");
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver(op);

		driver.get("https://selenium-test-react.vercel.app/loginportal");

		driver.manage().deleteAllCookies(); // delete all the cookies

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(priority = 0)
	public void test() throws Exception {

		String filePath = "./src/main/java/com/mac/cExcelTest/DemoExcel.xlsx";

		// File input stream which needs the input as the file location
		FileInputStream fileStream = new FileInputStream(filePath);

		int rowCount = 0;


		try (XSSFWorkbook workbook = new XSSFWorkbook(fileStream)) {
			// Sheet which needs to be accessed from within the workbook
			XSSFSheet sheet = workbook.getSheet("login");

			rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			System.out.println(rowCount);

			for (int i = 2; i <= rowCount; i++) {

				driver.findElement(By.name("username")).sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());

				driver.findElement(By.name("password")).sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());

				driver.findElement(By.name("rememberMe")).click();

				driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

				Thread.sleep(2000);

				driver.findElement(By.xpath("//button[contains(text(),'Logout')]")).click();

			}
		}

	}

	

	@AfterSuite
	public void tearDown() throws Exception {
		driver.quit();
	}

}
