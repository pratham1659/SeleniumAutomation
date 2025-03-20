//package com.mac.cExcelUtility;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.Test;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class Excel1Form {
//
//	WebDriver driver;
//
//	String baseUrl = "https://selenium-test-react.vercel.app/";
//
//	@BeforeSuite
//	public void setUp() throws InterruptedException {
//
//		ChromeOptions op = new ChromeOptions();
//		op.addArguments("--remote-allow-origins=*");
//
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver(op);
//
//		driver.get(baseUrl);
//
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//	}
//
//	@Test(priority = 0)
//	public void testExcelContactForm() throws Exception {
//
//		driver.findElement(By.linkText("CONTACT US FORM TEST")).click();
//
//		// ExcelXLSXReader reader = new
//		// ExcelXLSXReader("./src/test/java/DemoExcel.xlsx");
//		// String RegSheet = "Registration";
//
//		// int rowCount = reader.getRowCount(RegSheet);
//
//		// reader.addColumn(RegSheet, "Status");
//
//		// Data Driven Approach (Parameterization) -- used to create data driven
//		// framework: driving the test data from excel files
//
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0, 400)");
//
//		DataProvider excel = new DataProvider();
//		Object object[][] = excel.getData("./src/test/java/DemoExcel.xlsx");
//
//		for (int rowNum = 2; rowNum <= object[0].length; rowNum++) {
//
//			System.out.println("------------");
//			String firstName = (String) object[rowNum][0];
//			System.out.println(firstName);
//
//			String lastName = (String) object[rowNum][1];
//			System.out.println(lastName);
//
//			String username = (String) object[rowNum][2];
//			System.out.println(username);
//
//			String email = (String) object[rowNum][3];
//			System.out.println(email);
//
//			String dateOfBirth = (String) object[rowNum][4];
//			System.out.println(dateOfBirth);
//
//			/****
//			 * String originalDate = dateOfBirth; SimpleDateFormat originalFormat = new
//			 * SimpleDateFormat("dd-MM-yyyy"); SimpleDateFormat targetFormat = new
//			 * SimpleDateFormat("dd-MM-yyyy"); Date date =
//			 * originalFormat.parse(originalDate); String formattedDate =
//			 * targetFormat.format(date);
//			 *****/
//
//			String contact = (String) object[rowNum][5];
//			System.out.println(contact);
//
//			driver.findElement(By.name("firstName")).sendKeys(firstName);
//
//			driver.findElement(By.name("lastName")).sendKeys(lastName);
//
//			driver.findElement(By.name("username")).sendKeys(username);
//
//			driver.findElement(By.name("email")).sendKeys(email);
//
//			driver.findElement(By.name("dob")).sendKeys(dateOfBirth);
//
//			driver.findElement(By.name("contact")).sendKeys(contact);
//
//			driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
//
////			reader.setCellData(RegSheet, "Status", rowNum, "Pass"); // write the data into cell
//		}
//
//		Thread.sleep(5000);
//
//	}
//
//	@Test(enabled = false)
//	public void testExcelLogin() throws Exception {
//
//		driver.findElement(By.linkText("LOGIN PORTAL TEST")).click();
//
//		DataProvider reader = new DataProvider();
//		Object object[][] = reader.getData("./src/test/java/DemoExcel.xlsx");
//
//		for (int rowNum = 2; rowNum <= object[0].length; rowNum++) {
//
//			System.out.println("------------");
//			String username = (String) object[rowNum][0];
//			System.out.println(username);
//
//			String password = (String) object[rowNum][1];
//			System.out.println(password);
//
//			driver.findElement(By.name("username")).sendKeys(username);
//			driver.findElement(By.name("password")).sendKeys(password);
//			driver.findElement(By.name("rememberMe")).click();
//			driver.findElement(By.xpath("//button[@type='submit']")).click();
//			
//			Thread.sleep(3000);
//			
//			driver.findElement(By.xpath("//button[text()='Logout']")).click();
//		}
//		
//	}
//
//	@AfterSuite
//	public void tearDown() {
//		driver.quit();
//	}
//
//}
