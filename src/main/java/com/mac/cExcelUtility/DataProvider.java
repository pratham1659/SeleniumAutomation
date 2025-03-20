package com.mac.cExcelUtility;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataProvider {

	@SuppressWarnings("incomplete-switch")
	public static void getSheetUsingForLoop(XSSFWorkbook workbook) {

//		XSSFSheet sheet = workbook.getSheet("login");
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Using For loop
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		for (int r = 0; r <= rows; r++) {

			XSSFRow row = sheet.getRow(r);

			for (int c = 0; c < cols; c++) {

				XSSFCell cell = row.getCell(c);

				switch (cell.getCellType()) {

				case STRING:
					System.out.print(cell.getStringCellValue());
					break;

				case NUMERIC:
			        if (DateUtil.isCellDateFormatted(cell)) {
			        	
			            // Check if the numeric value is a date
			            if (DateUtil.isCellDateFormatted(cell)) {
			                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			                System.out.print(dateFormat.format(cell.getDateCellValue()));
			            }
			        } else {
			            System.out.print(cell.getNumericCellValue());
			        }
			        break;

				case BOOLEAN:
					System.out.print(cell.getBooleanCellValue());
					break;

				case FORMULA:
					System.out.print(cell.getNumericCellValue());
					break;
				}

				System.out.print(" | ");
			}
			System.out.println();
		}

	}

	@SuppressWarnings("incomplete-switch")
	public static void getSheetUsingIterator(XSSFWorkbook workbook) {

		// XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFSheet sheet = workbook.getSheet("Sheet1");

		Iterator<?> it = sheet.iterator();

		while (it.hasNext()) {
			XSSFRow row = (XSSFRow) it.next();

			Iterator<?> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {

				XSSFCell cell = (XSSFCell) cellIterator.next();

				switch (cell.getCellType()) {

				case STRING:
					System.out.print(cell.getStringCellValue());
					break;

				case NUMERIC:
					System.out.print(cell.getNumericCellValue());
					break;

				case BOOLEAN:
					System.out.print(cell.getBooleanCellValue());
					break;
				case FORMULA:
					System.out.print(cell.getNumericCellValue());
					break;
				}
				System.out.print(" | ");
			}
			System.out.println();
		}

	}

	public static void readPassWordExcel(XSSFWorkbook workbook) {

		XSSFSheet sheet = workbook.getSheetAt(0);
		
		int rows = sheet.getLastRowNum();
		System.out.println(rows);
		
		int cols = sheet.getRow(0).getLastCellNum();
		System.out.println(cols);
		
		getSheetUsingForLoop(workbook);
		
		

	}

	public static void main(String[] args) throws Exception {

		String value = "passwordexcel";

		if (value.equalsIgnoreCase("readexcel")) {
			String excelFilePath = "./datafiles/readformula.xlsx";

			FileInputStream ip = new FileInputStream(excelFilePath);

			XSSFWorkbook workbook = new XSSFWorkbook(ip);

			getSheetUsingIterator(workbook);

			// getSheetUsingForLoop(workbook);
		}

		if (value.equalsIgnoreCase("passwordexcel")) {

			String filePath = "./datafiles/customers.xlsx";
			String password = "test123";

			FileInputStream inputStream = new FileInputStream(filePath);

			XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(inputStream, password);

			readPassWordExcel(workbook);

		}

	}
}
