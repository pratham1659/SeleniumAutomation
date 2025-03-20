package com.mac.aBasic;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Handle06Mouse {

	WebDriver driver;

	String baseUrl = "https://selenium-test-react.vercel.app/";

	@BeforeSuite
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get(baseUrl);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

		System.out.println(driver.getTitle());

	}

	@Test(priority = 0)
	public void testDragAndDrop() throws Exception {

		driver.findElement(By.linkText("ACTIONS")).click();

		WebElement dragA = driver.findElement(By.xpath("//p[text()='Drag me(A)']"));
		WebElement dragB = driver.findElement(By.xpath("//p[text()='Drag me(B)']"));
		WebElement box1 = driver.findElement(By.name("box1"));
		WebElement box2 = driver.findElement(By.cssSelector("div[name='box2']"));

		Actions actions = new Actions(driver);
		actions.clickAndHold(dragA).moveToElement(box2).release().build().perform();

		Thread.sleep(1000);

		actions.clickAndHold(dragB).moveToElement(box1).release().build().perform();

		Thread.sleep(1000);

		String verifyBoxA = driver.findElement(By.xpath("(//div[@class='container']//div)[1]")).getText();

		String verifyBoxB = driver.findElement(By.xpath("(//div[@class='container']//div)[2]")).getText();

		Assert.assertEquals(verifyBoxA, "Drag me(B)");

		Assert.assertEquals(verifyBoxB, "Drag me(A)");

	}

	@Test(priority = 1)
	public void testMouseHold() throws Exception {

		Thread.sleep(1000);

//		if their are any one Frame inFrame application
//		driver.switchTo().frame(0);

		Actions actions = new Actions(driver);

		WebElement holdBox = driver.findElement(By.className("hold-box"));

		actions.clickAndHold(holdBox).build().perform();

		String boxText = driver.findElement(By.xpath("(//div[@class='container']//div)[3]")).getText();

		Assert.assertEquals(boxText, "Keep holding down");

		Thread.sleep(5000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(driver -> {
			actions.release().perform();
			return true;
		});

		String boxText1 = driver.findElement(By.xpath("(//div[@class='container']//div)[3]")).getText();

		Assert.assertEquals(boxText1, "No, don't let go :(");

	}

	@Test(priority = 2)
	public void testDoubleMouseHold() throws Exception {

		Thread.sleep(1000);

		Actions action = new Actions(driver);

		WebElement doubleBox = driver.findElement(By.xpath("(//div[@class='outer-container']//div)[1]"));

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0, 500)");
		System.out.println("Scroll to Double Click Button");

		action.doubleClick(doubleBox).build().perform();

		Thread.sleep(1000);

		String verifyBox = driver.findElement(By.xpath("//div[text()='Well done']")).getText();

		Assert.assertEquals(verifyBox, "Well done");
	}

	@Test(priority = 3)
	public void testKeyWithMouseHold() throws Exception {

		Actions actions = new Actions(driver);

		WebElement shiftBox = driver.findElement(By.xpath("(//div[@class='outer-container']//div)[2]"));

		Thread.sleep(2000);

		actions.keyDown(Keys.SHIFT).pause(5000).click(shiftBox).keyUp(Keys.SHIFT).build().perform();

		String boxText = driver.findElement(By.xpath("(//div[@class='outer-container']//div)[2]")).getText();

		Assert.assertEquals(boxText, "");

		Thread.sleep(2000);

	}

	@Test(priority = 4)
	public void testMouseMovement() throws Exception {

		driver.findElement(By.linkText("MOUSE MOVEMENT")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 10)");

		driver.findElement(By.linkText("Home")).click();

		Actions actions = new Actions(driver);

		WebElement News = driver.findElement(By.xpath("//button[text()='News']"));

		actions.moveToElement(News).build().perform();

		driver.findElement(By.xpath("//a[contains(text(),'Technology')]")).click();

		Thread.sleep(3000);

	}

	@Test(priority = 5)
	public void testMouseSelect() throws Exception {

		Thread.sleep(2000);

		WebElement dropDown = driver.findElement(By.xpath("//button[text()='Dropdown']"));

		Actions actions = new Actions(driver);
		actions.moveToElement(dropDown).build().perform();

		driver.findElement(By.xpath("//a[contains(text(),'Link 1')]")).click();

	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}