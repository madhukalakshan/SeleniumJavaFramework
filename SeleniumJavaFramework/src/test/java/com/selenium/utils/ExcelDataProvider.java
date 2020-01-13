package com.selenium.utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	private static XSSFWorkbook workBook;
	private static XSSFSheet sheet;
	
	public ExcelDataProvider(String excelPath, String sheetName) {
		try {
			System.out.println("excelPath--"+excelPath);
			System.out.println("sheetName--"+sheetName);
			workBook = new XSSFWorkbook(excelPath);
			sheet = workBook.getSheet(sheetName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int rowCount() {
		int rowCount = 0;
		try {
			rowCount = sheet.getPhysicalNumberOfRows();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public int columnCount() {
		int columnCount = 0;
		try {
			columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return columnCount;
	}

	public String getCellDataString(int rowNumber,int columnNumber) {
		String cellValue = null;
		try {
			cellValue = sheet.getRow(rowNumber).getCell(columnNumber).getStringCellValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellValue;

	}
	
	public double getCellDataNumber(int rowNumber,int columnNumber) {
		double cellValue = 0;
		try {
			cellValue = sheet.getRow(rowNumber).getCell(columnNumber).getNumericCellValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellValue;

	}
	
	public Object[][] testData(){
		int rowCount = rowCount();
		int colCount = columnCount();
		
		Object data[][] = new Object[rowCount-1][colCount];
		for(int i=1; i<rowCount; i++) {
			for(int j=0; j<colCount; j++) {
				String cellData = getCellDataString(i, j);
				data[i-1][j]=cellData;
			}
		}
		return data;	
	}

}
