package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
public String getDataFromExcel(String sheetName ,int rowNum ,int cellNum) throws EncryptedDocumentException, IOException
{
	FileInputStream fis=new FileInputStream("./testScriptData/testscriptdataExcel.xlsx");
	Workbook workbook = WorkbookFactory.create(fis);
	String data = workbook.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
	workbook.close();
	return data;
}

public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException
{
	FileInputStream fis=new FileInputStream("./testScriptData/testscriptdataExcel.xlsx");
	Workbook workbook = WorkbookFactory.create(fis);
	int rowCount = workbook.getSheet(sheetName).getLastRowNum();
	return rowCount;
}

public int getCellCount(String sheetName,int row) throws EncryptedDocumentException, IOException
{
	FileInputStream fis=new FileInputStream("./testScriptData/testscriptdataExcel.xlsx");
	Workbook workbook = WorkbookFactory.create(fis);
	int cellCount = workbook.getSheet(sheetName).getRow(row).getLastCellNum();
	return cellCount;
}

public String getDataFromExcelUsingDataFormatter(String sheetName ,int rowNum ,int cellNum) throws EncryptedDocumentException, IOException
{
	FileInputStream fis=new FileInputStream("./testScriptData/testscriptdataExcel.xlsx");
	Workbook workbook = WorkbookFactory.create(fis);
	Cell cell = workbook.getSheet(sheetName).getRow(rowNum).getCell(cellNum);
	DataFormatter df = new DataFormatter();
	String data = df.formatCellValue(cell);
	workbook.close();
	return data;
}

public void writeDataIntoExcel(String sheetName, int rowNum, int cellNum, String data) throws EncryptedDocumentException, IOException
{
	FileInputStream fis=new FileInputStream("./testScriptData/testscriptdataExcel.xlsx");
	Workbook workbook = WorkbookFactory.create(fis);
	workbook.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
	
	FileOutputStream fos=new FileOutputStream("./testScriptData/testscriptdataExcel.xlsx");
	workbook.write(fos);
	workbook.close();
}
}
