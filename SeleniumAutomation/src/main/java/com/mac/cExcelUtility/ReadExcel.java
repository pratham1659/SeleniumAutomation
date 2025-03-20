package com.mac.cExcelUtility;

import java.util.ArrayList;

public class ReadExcel {

	static ExcelXLSXReader reader;

	public static ArrayList<Object[]> getDataFromExcel() {

		ArrayList<Object[]> myData = new ArrayList<Object[]>();

		try {
			reader = new ExcelXLSXReader("./src/test/java/DemoExcel.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int rowNum = 2; rowNum <= reader.getRowCount("login"); rowNum++) {

			String username = reader.getCellData("login", "username", rowNum);
			String password = reader.getCellData("login", "password", rowNum);

			Object ob[] = { username, password };
			myData.add(ob);

		}
		return myData;

	}
}