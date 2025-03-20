package com.mac.cExcelUtility;

public class Excel2UtilTest {
	
	public static void main(String[] args) {
		
		ExcelXLSXReader reader = new ExcelXLSXReader("./src/test/java/DemoExcel.xlsx");
		String sheetName = "login";
		
		
		String data =  reader.getCellData(sheetName, 0, 2);
		System.out.println(data);
		
		int rowCount =  reader.getRowCount(sheetName);
		System.out.println("Total Rows: " + rowCount);
		
//		reader.addColumn(sheetName, "status");
		
		if(!reader.isSheetExist("Registration")) {
			reader.addSheet("Registration");
		}
		
		int colCount = reader.getColumnCount(sheetName);
		System.out.println("Total Column: " + colCount);
		
		
		reader.setCellData(sheetName, "status", 2, "PASS");
		
		String rowString = reader.getCellData(sheetName, "username", 3);
		System.out.println(rowString);
		
		System.out.println(reader.getColumnCount(sheetName));
		
//		reader.removeColumn("Registration", 0);
		
		System.out.println(reader.getCellRowNum(sheetName, "username", "johndoe"));
		
		System.out.println(reader.getCellData("Registration", "mob", 2));
		System.out.println(reader.getCellData("Registration", "age", 2));
		
	}
	
}
 