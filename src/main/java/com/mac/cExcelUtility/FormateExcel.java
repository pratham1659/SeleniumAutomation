package com.mac.cExcelUtility;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FormateExcel {

	public static void main(String[] args) throws Exception {

		String excelFilePath = "./datafiles/styles.xlsx";

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("Style1");

		XSSFRow row = sheet.createRow(1);

		// setting Background Colour

		XSSFCellStyle style = workbook.createCellStyle();

		style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		style.setFillPattern(FillPatternType.BIG_SPOTS);

		XSSFCell cell = row.createCell(1);

		cell.setCellValue("WelCome");
		cell.setCellStyle(style);
		
		
		//setting foreground Colour
		
		style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		
		cell = row.createCell(2);
		cell.setCellValue("Automation");
		cell.setCellStyle(style);
		
		
		FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		
		workbook.write(outputStream);
		workbook.close();
		
		outputStream.close();
		
		System.out.println("Style Implement Successfully");

	}

}
