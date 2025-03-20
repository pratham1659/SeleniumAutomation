package com.mac.cExcelUtility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

	public static void main(String[] args) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("Empinfo");

		ArrayList<Object[]> arrList = new ArrayList<Object[]>();
		arrList.add(new Object[] { "EmpId", "name", "Job" });
		arrList.add(new Object[] { 101, "David", "Engineer" });
		arrList.add(new Object[] { 102, "Rahul", "Accountant" });
		arrList.add(new Object[] { 103, "Isha", "Database" });
		arrList.add(new Object[] { 104, "Rupa", "Manager" });

//		Object empData[][] = { { "EmpId", "Name", "Job" }, { 101, "David", "Engineer" }, { 102, "Rahul", "Accountant" },
//				{ 103, "Isha", "Database" }, { 104, "Rupa", "Manager" } };

		/*
		 * int rows = empData.length; int cols = empData[0].length;
		 * 
		 * System.out.println(rows); System.out.println(cols);
		 * 
		 * for (int r = 0; r < rows; r++) {
		 * 
		 * XSSFRow row = sheet.createRow(r);
		 * 
		 * for (int c = 0; c < cols; c++) {
		 * 
		 * XSSFCell cell = row.createCell(c); Object value = empData[r][c];
		 * 
		 * if (value instanceof String) { cell.setCellValue((String) value); } if (value
		 * instanceof Integer) { cell.setCellValue((Integer) value); } if (value
		 * instanceof Boolean) { cell.setCellValue((Boolean) value); }
		 * 
		 * } }
		 * 
		 */

		// Using For each loop

		int rowCount = 0;

		for (Object emp[] : arrList) {

			XSSFRow row = sheet.createRow(rowCount++);
			int columnCount = 0;

			for (Object value : emp) {

				XSSFCell cell = row.createCell(columnCount++);

				if (value instanceof String) {
					cell.setCellValue((String) value);
				}
				if (value instanceof Integer) {
					cell.setCellValue((Integer) value);
				}
				if (value instanceof Boolean) {
					cell.setCellValue((Boolean) value);
				}
			}

		}

		String filePath = "./src/test/java/TestData.xlsx";
		FileOutputStream outStream = new FileOutputStream(filePath);
		workbook.write(outStream);

		outStream.close();
		workbook.close();

		System.out.println("Employed Files Written Successfully");

	}

}
