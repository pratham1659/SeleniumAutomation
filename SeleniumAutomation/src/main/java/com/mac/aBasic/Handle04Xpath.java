package com.mac.aBasic;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Handle04Xpath {

	WebDriver driver;
	String baseUrl = "https://classic.freecrm.com/index.html";
	String myUrl = "https://selenium-test-react.vercel.app/";

	@BeforeSuite
	public void beforesuit() {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");
		op.addArguments("headless");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(op);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(myUrl);

		System.out.println(driver.getCurrentUrl());

	}

	@Test(enabled = false)
	public void testFindElement() {
		// Extract Elements text from this Syntax

		List<WebElement> linkList = driver.findElements(By.tagName("a"));

		System.out.println("Total number of Links: " + linkList.size());

		for (int i = 0; i < linkList.size(); i++) {
			String linkText = linkList.get(i).getText();
			System.out.println(linkText);
		}
	}
	
	

	/* 
	 
	  
	 -- 1st Set of xpath
	 
	 //p[@id='para1'], 
	 //p[@id='para2'], 
	 //p[@class], 
	 //p[@class='main'],
	 //p[@class='sub'], 
	 //p[@id='para1'][@class='main'],
	 //p[@id='para2'][@class='sub'], 
	 //p[@id='para1'][@class='sub'],
	 //p[@id='para1'or @id='para2'],


	-- 2nd Set of xpath
	 
	 //input, 
	 (//input)[1], 
	 (//input)[2], 
	 (//input)[8], 
	 //input[@name],
	 //input[@value='orange'], 
	 //input[@name='color'][@value='blue'],
	 //input[@name='color' and @value='blue'], 
	 //input[@checked]
	 

	-- 3rd Set of Xpath
	 
	 //img, 
	 //img[@height='200px'], XPath Expression1 | XPath Expression,
	 //select[@class='combobox'], 
	 //a[@value='link'], 
	 //select[@class='combobox'],
	 //a[@value='link'], 
	 //a[@value='link2'], 
	 //a[@value='link1'] or @value='link2'],
	 

	-- 4th Set of Xpath
	 
	 Locating Button2 Locating all the tags having id attribute value as but2
	 Locating all input tags --> //*[@id='but2'],
	 
	 Locating button tags having any attribute value as but2 -->
	 //button[@*='but2'],
	 
	 Locating button tags having id attribute value as anything --> //button[@id],
	 
	 Find all the hyperlinks on the page that have the href attribute value as
	 http://www.Selenium143.blogspot.com,
	 
	 //a[@href='http://www.selenium143.blogspot.com']
	 

	 Xpath Of Table Locating a specific table
	 //table[@id='table1'],
	 
	 Locate all the rows in the table  
	 //table[@id='table1']//tr 
	 
	 Locate all the table headings in the table  
	 //table[@id='table1']//th
	  
	 Locate all the table data in the table  
	 //table[@id='table1']//td 
	 
	 Find all the cells in the table(i.e. table headings + table data): 
	 //table[@id='table1]//th
	 
	 //table[@id='table1']//td  
	 //table[@id='table1]//th 
	 //table[@id='table1]//td
	 
	 
	 Find the second data row and third column  
	 //table[@id='table1']//tr 
	 //table[@id='table1]//tr[2]
	 //table[@id='table1]//tr[2]//td[3]
	 
	 
	 Locate Practice AutomationHere text using text() XPath function 
	 //p[text)='PracticeAutomationHere']
	 
	 //p[.='PracticeAutomationHere']
	 
	 //a[text()='SeleniumTutorial']
	 //a[text()='Page One']
	 //button[text()='Button2']
	 //li[text()='One']
	 
	 
	 //p[text()='PracticeAutomation'],
	 //p[contains(text(), 'PracticeAutomation)],
	 //p[contains(.,'PracticeAutomation')]
	 //input[contains(@value,'Submit)]
	
	 
	 //p[starts-with(text(),'Practice')],
	 //p[starts-with(. 'Practice)]
	 //input[starts-with(@value, 'or')]
	 
	 
	 //last() XPath Function
	 //p[1],
	 //p[last()],
	 //p[last()-1],
	 //body/*[1],
	 //body/*[last)],
	 // (//input)[1],
	 // (//input)[last()],
	 // (//input)[last()-1],
	 // (//input)[last()-2],
	 //p[1][@class='main'],
	 //p[last][@class='sub']
	 
	
	-- position() XPath Function
	 //p[position()=1],
	 //p[position()=2],
	 (//input)[position=1],
	 (//input)[position=8]
	 
	 
	 -- following xpath Function
	 //head/following::title
	 //head/following::body
	 
	 -- Using the above Relative XPath Expression and preceding XPath Axes, locate the first p tag
	 //p[@id='para2']/preceding:p
	 //p[@id='para2']/preceding:title
	 //p[@id='para2']/preceding::body
	 
	 
	 -- Using Parent Xpath
	 Locate the parent tag of "body' tag
	 //body/parent::html
	 
	 -- locate the parent of title' tag
	 //title/parent::head
	 
	 -- locate the parent of "p'" tags
	 //p/parent::body
	 
	 
	 -- locate the 'p' child tags with the help of 'body' tag
	 //body/child:p
	 //body/child::p[1]
	 
	 
	 -- Ancestors Xpath
	 locate the 'html' parent tag with the help of 'head' tag
	 //head/ancestor::html
	 //p/ancestor::body
	 
	 
	 -- Descendant XPath
	 locate the 'body' child tag with the help of 'html' tag
	 //html/descendant::body
	 //html/descendant::p
	 
	 
	 */
	

	@Test(priority = 0)
	public void testXpathTable() throws Exception {

		driver.findElement(By.linkText("TABLES")).click();

		System.out.println("Name: " + driver.findElement(By.xpath("//td[text()='Emily Brown']")).getText());

		System.out.println("Age: "
				+ driver.findElement(By.xpath("(//td[text()='Emily Brown']/following-sibling::td) [1]")).getText());

		System.out.println("Grade: "
				+ driver.findElement(By.xpath("(//td[text()='Emily Brown']/following-sibling::td) [2]")).getText());

		List<WebElement> nameAll = driver.findElements(By.xpath("//tr/td [2]"));

		List<WebElement> ageText = driver.findElements(By.xpath("//tr/td [text()='20']"));

		List<WebElement> nameText = driver.findElements(By.xpath("//tr/td [text()='20']/preceding-sibling::td[1]"));

		List<WebElement> gradeText = driver.findElements(By.xpath("//tr/td [text()='20']/following-sibling::td"));

		System.out.println("Total number of Name: " + nameAll.size());

		System.out.println("Names in Tables: ");
		for (int i = 0; i < nameAll.size(); i++) {
			System.out.println(nameAll.get(i).getText());
		}

		System.out.println("Age equals to 20: ");
		for (int i = 0; i < ageText.size(); i++) {
			System.out.print(nameText.get(i).getText() + " ");
			System.out.print(ageText.get(i).getText() + " ");
			System.out.println(gradeText.get(i).getText());
		}
	

	}
	
	@Test(priority = 1)
	public void checkBoxTest() throws Exception {
		
		
		driver.findElement(By.xpath("//tr/following::td[text()='Sarah Jones']/preceding-sibling::td/input")).click();
		
		Boolean checkTest = driver.findElement(By.xpath("//tr/following::td[text()='Sarah Jones']/preceding-sibling::td/input")).isSelected();
		
		Assert.assertTrue(checkTest);
		
	}
	
	@Test(priority = 2)
	public void testParentXpath() throws Exception {
		
		driver.findElement(By.linkText("NESTED XPATH")).click();
		
		System.out.println(driver.findElement(By.xpath("(//p/parent::article)[1]")).getText());
		
		System.out.println(driver.findElement(By.xpath("//p[text()='Child B']/ancestor::ul/li/p[text()='Parent']")).getText());
		
		System.out.println(driver.findElement(By.xpath("(//p[text()='Great Grand Child'])[1]/ancestor::ul/preceding-sibling::p[text()='Grand Child B2']")).getText());
		
		System.out.println(driver.findElement(By.xpath("(//p[text()='Child B']/ancestor::ul/li/p)[2]")).getText());
		
	}

	@Test(enabled = false)
	public void testLoginXpath() throws InterruptedException {

		driver.findElement(By.name("username")).sendKeys("pratham1659");
		driver.findElement(By.name("password")).sendKeys("Saitama@143");

		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='submit']")).click(); // login button

		driver.switchTo().frame("mainpanel");

		driver.findElement(By.xpath("//a[contains(text(),'Contacts')]")).click();

		driver.findElement(By.xpath(
				"//a[text()='Python Sharma']//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@type='checkbox']"))
				.click();

		driver.findElement(By.xpath(
				"//a[text()='Macbook Roy']//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@type='checkbox']"))
				.click();
	}
	
	
	@Test(priority = 3)
	public void testNestedTags() throws Exception{	
		
		System.out.println(driver.findElement(By.xpath("//p[contains(text(), 'second')]/ancestor::main/preceding-sibling::header/h1")).getText());
		
		System.out.println(driver.findElement(By.xpath("//h1[text()='Nested Tags Demo']/following-sibling::nav/descendant::a[text()='Home']")).getText());
	
	}
	

	@Test(enabled = false)
	public void learnXpath() throws InterruptedException {

//		WebElement searchBox = driver.findElement(By.xpath("//input[@id='search-user1']"));

		WebElement searchBox = driver.findElement(By.xpath("//input[contains(@id, 'search-user1')]"));

		// a[text()='Features']
		// a [contains (text(), 'Features')] -recommended
		// a[text ()='Alerts']

		// input[@id='search-user1']
		// input[@type='submit' and @value='Login']
		// button[®type='button' and @class='btn']

		// div[@class='dropdown']//button[@type='button' and @class='btn bin-secondary
		// dropdown-toggle' and @id='dropdownMenuButton' ]

//	<tr>
//        <td align="left" align="top" class="datalistrow" width="1">
//        <input type="checkbox" name="contact_id" value="52977251">
//        </td>
//        <td align="left" align="top" class="datalistrow">
//        <a href="" context="contact" _id="52977251" _name="Python Sharma" cid="" _haseml="N">Python Sharma</a>
//        &nbsp;</td>
//  </tr>

		// This is the nested xpath
		// a[text()='Python
		// Sharma']//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@type='checkbox']

		// dynamic id: input
		// id = test_123
		// By.id("test_123")

		// starts-with
		// id - test_456
		// id = test_789
		// id - test_test_7890_test

		// ends-with
		// id = 1234_test_t
		// id = 23456_test_t
		// id = 6789_test_t

//		WebElement searchBox = driver.findElement(By.xpath("//input[starts-with(@class, 'sea')]"));

//		WebElement searchBox = driver.findElement(By.xpath("//input[ends-with(@class, 'sea')]"));

		searchBox.sendKeys("Wooden Sofas");

		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();

//		WebElement element = driver.findElement(By.xpath("//a[contains(text(), 'LIVING')]"));

		action.moveToElement(driver.findElement(By.linkText("Living"))).build().perform();

//		driver.findElement(By.xpath("//a[contains(text(),'Bookshelves')]")).click();

		driver.findElement(By.xpath("//header/nav[1]/ul[1]/li[8]")).click();

		System.out.println("Element got clicked");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			wait.until(ExpectedConditions.alertIsPresent());

			Alert alert = driver.switchTo().alert();

			String textString = alert.getText();
			System.out.println(textString);

			alert.dismiss();
		} catch (Exception e) {
			System.out.println("Alert did not appear within the specified timeout period.");
		}

		// get the count of links on the page
		// get the text of each link on the page
		// all the links are represented by <a› html tag:

	}

	@AfterSuite
	public void AfterTest() {
		System.out.println("Execuation End");
		driver.quit();
	}
}
