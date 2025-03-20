package com.mac.dDataDriven;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTableToExcel {

	public static void main(String[] args) throws IOException {
	
		WebDriver driver;
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--remote-allow-origins=*");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(op);


		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://en.wikipedia.org/wiki/List_of_countries_and_dependencies_by_population");
		
		String path="./datafiles/population.xlsx";
		XLSReader xlutil=new XLSReader(path);
		
		//Write headers in excel sheet
		xlutil.setCellData("Sheet1", 0,0, "Country");
		xlutil.setCellData("Sheet1", 0,1, "Population");
		xlutil.setCellData("Sheet1", 0,2, "% of world");
		xlutil.setCellData("Sheet1", 0,3, "Date");
		xlutil.setCellData("Sheet1", 0,4, "Source");
		
		//capture table rows
		
		WebElement table=driver.findElement(By.xpath("//table[@class='wikitable sortable sticky-header sort-under mw-datatable col2left col6left jquery-tablesorter']/tbody"));
		int rows=table.findElements(By.xpath("tr")).size(); // rows present in web table
		
		for(int r=1;r<=rows;r++)
		{
			//read data from web table
			String country=table.findElement(By.xpath("tr["+r+"]/td[1]")).getText();
			String population=table.findElement(By.xpath("tr["+r+"]/td[2]")).getText();
			String perOfWorld=table.findElement(By.xpath("tr["+r+"]/td[3]")).getText();
			String date=table.findElement(By.xpath("tr["+r+"]/td[4]")).getText();
			String source=table.findElement(By.xpath("tr["+r+"]/td[5]")).getText();
			
			System.out.println(country+population+perOfWorld+date+source);
			
			//writing the data in excel sheet
			xlutil.setCellData("Sheet1", r, 0, country);
			xlutil.setCellData("Sheet1", r, 1, population);
			xlutil.setCellData("Sheet1", r, 2, perOfWorld);
			xlutil.setCellData("Sheet1", r, 3, date);
			xlutil.setCellData("Sheet1", r, 4, source);
			
		}
		
		System.out.println("Web scrapping is done succesfully.....");
		driver.quit();
		
	}

}