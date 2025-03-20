package com.mac.aBasic;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Handle10JavaScript {

	WebDriver driver;
	String baseUrl = "https://selenium-test-react.vercel.app/";

	@BeforeSuite
	public void beforesuit() {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(op);
		driver.get(baseUrl);

		driver.manage().window().maximize(); // maximize window
		driver.manage().deleteAllCookies(); // delete all the cookies

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		System.out.println(driver.getTitle());

	}

	public static void flash(WebElement element, WebDriver driver) {
		String bgColor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 10; i++) {
			changeColor("rgb(225, 0, 255)", element, driver);
			changeColor(bgColor, element, driver);
		}
	}

	public static void changeColor(String color, WebElement element, WebDriver driver) {

		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			System.out.println("Error in jsExecution");
		}
	}

	public static void drawBorder(WebElement element, WebDriver driver) {
		JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
		jsExecutor.executeScript("arguments[0].style.border='3px solid Red'", element);
	}

	public static void generateAlert(WebDriver driver, String message) {
		JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
		jsExecutor.executeScript("alert('" + message + "')");
	}

	public static void clickElementByJsElement(WebElement element, WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public static void refreshBrowserByJS(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("history.go(0)");
	}

	public static String getTitleByJS(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String titleString = js.executeScript("return document.title;").toString();
		return titleString;
	}

	public String getPageInnerText(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String pageText = js.executeScript("return document.documentElement.innerText;").toString();
		return pageText;
	}

	public static void scrollPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
		System.out.println("Scroll Done");
	}

	public static void scrollIntoView(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	@Test(priority = 0)
	public void testFlashJavascript() throws Exception {
		
		
		List<WebElement> flashElement = driver.findElements(By.xpath("//li"));
		
		for(int i = 0; i < flashElement.size(); i++) {
			flash(flashElement.get(i), driver);
			if(i == 10) {
				scrollIntoView(flashElement.get(20), driver);
			}
		}
		
		scrollIntoView(flashElement.get(0), driver);
		
		Thread.sleep(2000);
		
		List<WebElement> borderElement = driver.findElements(By.xpath("//li"));
		
		for(int i = 0; i < borderElement.size(); i++) {
			drawBorder(borderElement.get(i), driver);
			if(i == 10) {
				scrollIntoView(borderElement.get(20), driver);
			}
		}
		
		WebElement flashElement2 = driver.findElement(By.xpath("//ul/li/*"));
		
		flash(flashElement2, driver);
	
		
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void testAlert() throws Exception {

		generateAlert(driver, "Their is an issue with the login button");
		
		Alert alert = driver.switchTo().alert();
		alert.dismiss(); // click on accept button

		WebElement navElement = driver.findElement(By.xpath("//a[contains(text(), 'ACCORDION')]"));

		clickElementByJsElement(navElement, driver);
	}

	@Test(enabled = false)
	public void testJavaScript() throws Exception {

		generateAlert(driver, "Their is an issue with the login button");

		;

		driver.navigate().refresh();

		clickElementByJsElement(driver.findElement(By.xpath("//button[contains(text(), 'Accordion Item #1')]")),
				driver);

		refreshBrowserByJS(driver);

		System.out.println(getTitleByJS(driver));

		System.out.println(getPageInnerText(driver));

		// scrollPage(driver);

		scrollIntoView(driver.findElement(By.xpath("//p[contains(@class, leftPara)]")), driver);

	}

	@AfterSuite
	public void aftersuit() throws InterruptedException {
		driver.quit();

	}

}
