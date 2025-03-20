package com.mac.cExcelUtility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDate {

	public static void main(String[] args) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("Date Formate");

		// Date in number format
		XSSFCell cell = sheet.createRow(0).createCell(0);
		cell.setCellValue(new Date());

		XSSFCreationHelper creationHelper = workbook.getCreationHelper();

		// format1 dd-mm-yyyy
		CellStyle style1 = workbook.createCellStyle();
		style1.setDataFormat(creationHelper.createDataFormat().getFormat("dd-mm-yyyy"));

		// Date in number format
		XSSFCell cell1 = sheet.createRow(1).createCell(0);
		cell1.setCellValue(new Date());
		cell1.setCellStyle(style1);
		
		
		//format2 dd-mm-yyyy hh:mm:ss
		CellStyle style2 = workbook.createCellStyle();
		style2.setDataFormat(creationHelper.createDataFormat().getFormat("dd-mm-yyyy hh:mm:ss"));
		
		//Date in Alphanumeric format
		XSSFCell cell2 = sheet.createRow(2).createCell(0);
		cell2.setCellValue(new Date());
		cell2.setCellStyle(style2);
		

		String filePath = "./datafiles/dateformats.xlsx";
		FileOutputStream outputStream = new FileOutputStream(filePath);

		workbook.write(outputStream);
		outputStream.close();
		workbook.close();

		System.out.println("Execute done!!");

	}

}
