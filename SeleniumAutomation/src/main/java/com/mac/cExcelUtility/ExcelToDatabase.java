package com.mac.cExcelUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelToDatabase {

	public static void main(String[] args) throws SQLException, IOException {

		String value = "databaseToExcel";

		if (value.equalsIgnoreCase("excelToDatabase")) {

			// Database connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/product", "dev.user",
					"Macbook@143");
			Statement stmt = con.createStatement();

			// create a new table in the database 'places'
			String sql = "create table places (LOCATION_ID decimal(4,0), STREET_ADDRESS varchar(40),POSTAL_CODE varchar(12),CITY varchar(30),STATE_PROVINCE varchar(25),COUNTRY_ID varchar(2))";
			stmt.execute(sql);

			// Excel
			FileInputStream fis = new FileInputStream("./datafiles/locations.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Locations Data");

			int rows = sheet.getLastRowNum();

			for (int r = 1; r <= rows; r++) {
				XSSFRow row = sheet.getRow(r);
				double locId = row.getCell(0).getNumericCellValue();
				String streatAdd = row.getCell(1).getStringCellValue();
				String postalCode = row.getCell(2).getStringCellValue();
				String city = row.getCell(3).getStringCellValue();
				String stateProv = row.getCell(4).getStringCellValue();
				String countryId = row.getCell(5).getStringCellValue();

				sql = "insert into places values('" + locId + "', '" + streatAdd + "', '" + postalCode + "', '" + city
						+ "', '" + stateProv + "', '" + countryId + "')";
				stmt.execute(sql);
				stmt.execute("commit");
			}

			workbook.close();
			fis.close();
			con.close();

			System.out.println("Done!!");
		}

		if (value.equalsIgnoreCase("databaseToExcel")) {

			// Database connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/product", "dev.user",
					"Macbook@143");
			Statement stmt = con.createStatement();
			
			
			ResultSet rs = stmt.executeQuery("select * from places");

			// Excel
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Locations Data");

			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("LOCATION_ID");
			row.createCell(1).setCellValue("STREET_ADDRESS");
			row.createCell(2).setCellValue("POSTAL_CODE");
			row.createCell(3).setCellValue("CITY");
			row.createCell(4).setCellValue("STATE_PROVINCE");
			row.createCell(5).setCellValue("COUNTRY_ID");

			int r = 1;

			while (rs.next()) {
				double locId = rs.getDouble("LOCATION_ID");
				String streatAdd = rs.getString("STREET_ADDRESS");
				String postalCode = rs.getString("POSTAL_CODE");
				String city = rs.getString("CITY");
				String stateProv = rs.getString("STATE_PROVINCE");
				String countryId = rs.getString("COUNTRY_ID");

				row = sheet.createRow(r++);

				row.createCell(0).setCellValue(locId);
				row.createCell(1).setCellValue(streatAdd);
				row.createCell(2).setCellValue(postalCode);
				row.createCell(3).setCellValue(city);
				row.createCell(4).setCellValue(stateProv);
				row.createCell(5).setCellValue(countryId);

			}

			FileOutputStream fos = new FileOutputStream("./datafiles/locations.xlsx");
			workbook.write(fos);

			workbook.close();
			fos.close();
			con.close();

			System.out.println("Done!!!");
		}

	}

}
