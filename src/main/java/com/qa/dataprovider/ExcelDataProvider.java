package com.qa.dataprovider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	public Object[][] getData(String filePath) throws IOException {
		DataFormatter formatter = new DataFormatter();
		FileInputStream file = new FileInputStream(filePath);
		try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
			XSSFSheet sheet = workbook.getSheetAt(1);
			XSSFRow row = sheet.getRow(0);
			int rowCount = sheet.getLastRowNum();
			int columnCount = row.getLastCellNum();
			Iterator<Row> rowIterator = sheet.rowIterator();

			Object object[][] = new Object[rowCount + 1][columnCount];
			int i = 0;
			while (rowIterator.hasNext()) {
				Row currentRow = rowIterator.next();
				Iterator<Cell> cellIterator = currentRow.cellIterator();
				int j = 0;
				while (cellIterator.hasNext()) {
					object[i][j] = formatter.formatCellValue(cellIterator.next());
					j++;
				}

				i++;
			}
			return object;
		}
	}

	public static void main(String[] args) throws IOException {
		String filePath = "./src/main/java/com/store/testdata/DemoExcel.xlsx";
		ExcelDataProvider reader = new ExcelDataProvider();
		Object object[][] = reader.getData(filePath);
		for (int i = 0; i < object.length; i++) {
			for (int j = 0; j < object[j].length; j++) {
				System.out.print(object[i][j] + " ");
			}
			System.out.println();
		}
	}

}
