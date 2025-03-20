package com.mac.cExcelUtility;

import java.io.FileInputStream;
import java.io.IOException;
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

public class ExcelWriting {

	WebDriver driver;
	String automateUrl = "https://selenium-test-react.vercel.app/contactus";

	@BeforeSuite
	public void setUp() {

		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		op.addArguments("window-size=1400,800");
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver(op);

		driver.get(automateUrl);

		driver.manage().deleteAllCookies(); // delete all the cookies

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@Test
	public void test() throws InterruptedException, IOException {

		String filePath = "/Users/prathamkumar/eclipse-workspace/MainTest/src/main/java/com/excel/utility/DemoExcel.xlsx";

		// File input stream which needs the input as the file location
		FileInputStream fileStream = new FileInputStream(filePath);

		try (// Workbook reference of the excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fileStream)) {
			// Sheet which needs to be accessed from within the workbook
			XSSFSheet sheet = workbook.getSheet("Sheet1");

			// Count the number of rows
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

			for (int i = 1; i <= rowCount; i++) {
				
				
				// Pass the row number and the cell number from where the value has to be

				driver.findElement(By.name("firstName")).sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());

				driver.findElement(By.name("lastName")).sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
				
				driver.findElement(By.name("username")).sendKeys(sheet.getRow(i).getCell(2).getStringCellValue());
				
				driver.findElement(By.name("email")).sendKeys(sheet.getRow(i).getCell(3).getStringCellValue());
				
//			driver.findElement(By.name("dob")).sendKeys(sheet.getRow(i).getCell(4).getStringCellValue());
				
//			driver.findElement(By.name("contact")).sendKeys(sheet.getRow(i).getCell(5).getStringCellValue());

				driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

			}
		}

	}

	@AfterSuite
	public void tearDown() throws Exception {
		System.out.println("Excel Successfully");
		driver.quit();
	}

}
