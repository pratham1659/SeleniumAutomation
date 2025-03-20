package com.mac.aBasic;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Handle13Calender {

	WebDriver driver;
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

	@Test(priority = 0)
	public void calenderHandle() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(text(),'DATE PICKER')]")).click();


		WebElement datetime_input = driver.findElement(By.xpath("//input[contains(@type, 'datetime-local')]"));

		// -- Clear any existing value in the input (optional)
		datetime_input.clear();

		Thread.sleep(3000);

		LocalDateTime dateTime = LocalDateTime.now();

		System.out.println(dateTime);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy,HH:mm");
		String formattedDateTime[] = dateTime.format(formatter).split(",");
		System.out.println(formattedDateTime);

		WebElement element = driver.findElement(By.cssSelector("input[type ='datetime-local']"));
		element.sendKeys(formattedDateTime[0]);
		element.sendKeys(Keys.TAB);
		element.sendKeys(formattedDateTime[1]);

//		 String newDate = "2021-02-22T11:11";
//	     //((JavascriptExecutor)driver).executeScript("arguments[0].value = arguments[1]", datetime_input, newDate);
//	     datetime_input.sendKeys(newDate);

		WebElement startDate = driver.findElement(By.xpath("//input[@id='start']"));
		startDate.sendKeys("01/02/2024");

		WebElement endDate = driver.findElement(By.xpath("//input[@id='end']"));
		endDate.sendKeys("10/02/2024");

		WebElement time_input = driver.findElement(By.cssSelector("input[type='time']"));

		time_input.clear();

		String new_time = "09:30AM";
		time_input.sendKeys(new_time);
		
		Thread.sleep(5000);

	}

	@Test(priority = 1)
	public void testCalender() throws InterruptedException {

		driver.navigate().to("https://classic.freecrm.com/index.html");

		driver.findElement(By.name("username")).sendKeys("pratham1659");
		driver.findElement(By.name("password")).sendKeys("Saitama@143");

		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='submit']")).click(); // login button

		driver.switchTo().frame("mainpanel");

		String date = "31-September-2017";
		String dateArr[] = date.split("-"); // {18, September, 2017}
		String day = dateArr[0];
		String month = dateArr[1];
		String year = dateArr[2];

		Select selectM = new Select(driver.findElement(By.name("slctMonth")));
		selectM.selectByVisibleText(month);

		Select selectY = new Select(driver.findElement(By.name("slctYear")));
		selectY.selectByVisibleText(year);

		// *[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]
		// *[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]
		// *[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[6]

		String beforeString = "//*[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr[";
		String afterString = "]/td[";

		final int totaWeekdays = 7;

		// 1/2-1 2-2 2-3 2-4 2-5 2-6 2-7
		// 1/3-2 3-2 3-3 3-4 3-5 3-6 3-7

		Boolean flag = false;
		String dayVal = null;
		for (int rowNum = 2; rowNum <= totaWeekdays; rowNum++) {
			for (int columnNum = 1; columnNum <= totaWeekdays; columnNum++) {

				try {

					dayVal = driver.findElement(By.xpath(beforeString + rowNum + afterString + columnNum + "]"))
							.getText();
					System.out.println(dayVal);
				} catch (NoSuchElementException e) {
					System.out.println("Please enter a Correct Date: ");
					flag = false;
					break;
				}

				if (dayVal.equals(day)) {
					driver.findElement(By.xpath(beforeString + rowNum + afterString + columnNum + "]")).click();
					flag = true;
					break;
				}

			}

			if (flag) {
				break;
			}
		}
	}

	public static void selectDateByJs(WebDriver driver, WebElement element, String dateVal) {
		JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + dateVal + "');", element);
		System.out.println("Calender Chand Successfully selected");

	}

	@Test(enabled = false)
	public void testCalenderUsingJS() {

		driver.navigate().to("https://www.makemytrip.com/");

		WebElement date = driver.findElement(By.xpath("//input[@id='departure']"));

		String dateJString = "10-03-2024";

		selectDateByJs(driver, date, dateJString);

	}

	@AfterSuite
	public void aftersuit() throws InterruptedException {
		System.out.println("Execution ends");
		driver.quit();
	}
}
