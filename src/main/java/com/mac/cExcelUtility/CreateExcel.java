package com.mac.cExcelUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateExcel {

	public static void writeFormulaCell(XSSFSheet sheet) {

	

		XSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue(4);
		row.createCell(1).setCellValue(5);
		row.createCell(2).setCellValue(6);

		row.createCell(3).setCellFormula("A1*B1*C1");
	}

	public static void generateCellFormula(XSSFSheet sheet) {

		sheet.getRow(7).getCell(2).setCellFormula("SUM(C2:C6)");
	}

	public static void main(String[] args) throws Exception {

		String value = "generate";

		if (value.equalsIgnoreCase("create")) {

			String filePath = "./datafiles/calc.xlsx";
			FileOutputStream outstream = new FileOutputStream(filePath);

			XSSFWorkbook workbook = new XSSFWorkbook();
			
			XSSFSheet sheet = workbook.createSheet("Numbers");

			writeFormulaCell(sheet);

			workbook.write(outstream);

			System.out.println("Data Inserted Successfully");

			workbook.close();
			outstream.close();
		}
		
		if(value.equalsIgnoreCase("generate")) {
			
			String filePath = "./datafiles/books.xlsx";
			FileInputStream inputStream = new FileInputStream(filePath);
			
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			generateCellFormula(sheet);
			
			inputStream.close();
			
			FileOutputStream outputStream = new FileOutputStream(filePath);
			workbook.write(outputStream);
			
			workbook.close();
			outputStream.close();
			
			System.out.println("Write Formula Successfully");
					
		}

	}

}
