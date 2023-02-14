package com.automation.common.utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import com.automation.common.constant.Config;


public class ExcelReport {
	
	private static HSSFWorkbook ExcelWBook;
	private static HSSFSheet ExcelWSheet;	
	private static HSSFCell Cell;	
	private static String emptyString = "empty string";
	
	
	public static String[] getExeutedTestCaseName(String Description) {
		String[] TcArray = Description.split("_");
		return TcArray;
	}
	
	public static HSSFWorkbook get_workbook(FileInputStream File) throws Throwable {
		   ExcelWBook = new HSSFWorkbook(File);
		   return ExcelWBook;
	}
	
	public static HSSFSheet get_Sheet(HSSFWorkbook WorkBook,String Sheet) {
		ExcelWSheet = WorkBook.getSheet("Regression Sheet");
		return ExcelWSheet;
	}
	
	
	public static int readfromexcel(HSSFSheet sheet,int ColNum,String value) throws Throwable {	
		IntBuffer ib = IntBuffer.allocate(10);
		int rowCount= sheet.getPhysicalNumberOfRows();
		for(int i=1;i<rowCount;i++) {
		Cell = sheet.getRow(i).getCell(ColNum);
		String cellValue = Cell.getStringCellValue();
		if (cellValue.equalsIgnoreCase(value)) { 
			ib.put(i);
			break;
		}
		}		
		return ib.get(0);
	}
	
    public static void UpdateRowWithString(HSSFSheet Sheet,int RowNum,int ColNum,String value) {
    	try {
    	Cell = Sheet.getRow(RowNum).getCell(ColNum);
    	Cell.setCellValue(value);
    	}catch(NullPointerException e) {
    		Cell = Sheet.getRow(RowNum).createCell(ColNum);
        	Cell.setCellValue(value);	
    	}
	}
    
	
	
	public static void UpdateTestCaseStatus(String File,HSSFSheet Sheet,String TCNo,String Status) throws Throwable {
		int colnum= 0;
		int rownum = readfromexcel(Sheet,colnum,TCNo);
		UpdateRowWithString(Sheet,rownum,colnum+6,Status);
		FileOutputStream out = 
				new FileOutputStream(new File(File));
		ExcelWBook.write(out);
		out.close();		
	}
	
	public static void UpdateTestCaseError(String File,HSSFSheet Sheet,String TCNo,String Error) throws Throwable {
		int colnum= 0;
		int rownum = readfromexcel(Sheet,colnum,TCNo);
		UpdateRowWithString(Sheet,rownum,colnum+7,Error);
		FileOutputStream out = 
				new FileOutputStream(new File(File));
		ExcelWBook.write(out);
		out.close();
	}
	
	
	
	public static void UpdateTestCaseRequest(String File,HSSFSheet Sheet,String TCNo,String Request) throws Throwable {
		int colnum= 0;
		int rownum = readfromexcel(Sheet,colnum,TCNo);
		UpdateRowWithString(Sheet,rownum,colnum+9,Request);
		FileOutputStream out = 
				new FileOutputStream(new File(File));
		ExcelWBook.write(out);
		out.close();
	}

	public static void UpdateTestCaseResponse(String File,HSSFSheet Sheet,String TCNo,String Response) throws Throwable {
		int colnum= 0;
		int rownum = readfromexcel(Sheet,colnum,TCNo);
		UpdateRowWithString(Sheet,rownum,colnum+10,Response);
		FileOutputStream out = 
				new FileOutputStream(new File(File));
		ExcelWBook.write(out);
		out.close();
	}

    public static void UpdateRowWithSnap(String File,HSSFSheet Sheet,String TCNo,String Status) throws Throwable {   	
    	int colnum= 0;
		int rownum = readfromexcel(Sheet,colnum,TCNo);
		UpdateRowWithString(Sheet,rownum,colnum+8,Status);
		FileOutputStream out = 
				new FileOutputStream(new File(File));
		ExcelWBook.write(out);
		out.close();
		
	}
    
    
    
	
	public static void GenerateExcelReport(String TCs,String Status,String Error,String Request, String Response) throws Throwable {
    	String fileName = Config.Test_Cases;
    	FileInputStream ExcelFile = new FileInputStream(fileName);
    	ExcelWBook= get_workbook(ExcelFile);
    	ExcelWSheet = get_Sheet(ExcelWBook, Config.Test_Cases_Sheet);
//    	String TCs= "TC01_TC02_TC03_TC04::ValidateFlows";
    	String[] TcArray = getExeutedTestCaseName(TCs);
    	for(String str:TcArray) {		
    		if(str.contains("::")) {
    			str= str.split("::")[0];
    			UpdateTestCaseStatus(fileName, ExcelWSheet, str,Status);
    			UpdateTestCaseError(fileName, ExcelWSheet, str,Error);
    			UpdateTestCaseRequest(fileName, ExcelWSheet, str,Request);
    			UpdateTestCaseResponse(fileName, ExcelWSheet, str,Response);
    		}else {
    			System.out.println(str);
    			UpdateTestCaseStatus(fileName,ExcelWSheet, str,Status);
    			UpdateTestCaseError(fileName, ExcelWSheet, str,Error);
    			UpdateTestCaseRequest(fileName, ExcelWSheet, str,Request);
    			UpdateTestCaseResponse(fileName, ExcelWSheet, str,Response);
    		}
    	}
    	ExcelFile.close();
	}
	
	public static void ClearStatus() throws Throwable {
    	String fileName = Config.Test_Cases;
    	FileInputStream ExcelFile = new FileInputStream(fileName);
    	ExcelWBook= get_workbook(ExcelFile);
    	ExcelWSheet = get_Sheet(ExcelWBook, Config.Test_Cases_Sheet);
    	int totalRows = ExcelWSheet.getLastRowNum();
    	for (int i = 2; i < totalRows; i++) {
    		UpdateRowWithString(ExcelWSheet,i,6,"NotRun");
    		UpdateRowWithString(ExcelWSheet,i,7,"NA");
    		UpdateRowWithString(ExcelWSheet,i,8,"NA");
    		UpdateRowWithString(ExcelWSheet,i,9,"NA");
    		UpdateRowWithString(ExcelWSheet,i,10,"NA");
		}
    	FileOutputStream out = 
				new FileOutputStream(new File(fileName));
		ExcelWBook.write(out);
		out.close();
//    	String TCs= "TC01_TC02_TC03_TC04::ValidateFlows";
    	ExcelFile.close();
	}
	
	//
	
	public static void GenerateExcelReport(String TCs,String Status,String Error,String Type) throws Throwable {
    	String fileName = Config.Test_Cases;
    	FileInputStream ExcelFile = new FileInputStream(fileName);
    	ExcelWBook= get_workbook(ExcelFile);
    	ExcelWSheet = get_Sheet(ExcelWBook, Config.Test_Cases_Sheet);
//    	String TCs= "TC01_TC02_TC03_TC04::ValidateFlows";
    	String[] TcArray = getExeutedTestCaseName(TCs);
    	for(String str:TcArray) {		
    		if(str.contains("::")) {
    			str= str.split("::")[0];
    			UpdateTestCaseStatus(fileName, ExcelWSheet, str,Status);
    			UpdateTestCaseError(fileName, ExcelWSheet, str,Error);
    			UpdateTestCaseRequest(fileName, ExcelWSheet, str,Type);
    		
    		}else {
    			System.out.println(str);
    			UpdateTestCaseStatus(fileName,ExcelWSheet, str,Status);
    			UpdateTestCaseError(fileName, ExcelWSheet, str,Error);
    			UpdateTestCaseRequest(fileName, ExcelWSheet, str,Type);
    			
    		}
    	}
    	ExcelFile.close();
	}
	
	
	
	
	
    public static int GetRowCount(HSSFSheet sheet,int ColNum) throws Throwable {	
    	int rowCount= sheet.getPhysicalNumberOfRows();	
    	return rowCount;
    }

    public static int GetRowCountForStatus(HSSFSheet sheet,int ColNum,String value) throws Throwable {	
    	int rowCount= sheet.getPhysicalNumberOfRows();
    	int count=0;
    	for(int i=1;i<rowCount;i++) {
    	Cell = sheet.getRow(i).getCell(ColNum);
    	if (Cell != null && Cell.getStringCellValue().equalsIgnoreCase(value)) { 
    		count++;
    	}
    	}		
    	return count;
    }
	
	public static List<HashMap<String,String>> GetStatusDetails(HSSFSheet sheet,int ColNum,String value) throws Throwable {	
		List<HashMap<String,String>> AllTCErrors= new ArrayList<HashMap<String,String>>();
		int rowCount= sheet.getPhysicalNumberOfRows();
		for(int i=1;i<rowCount;i++) {
		Cell = sheet.getRow(i).getCell(ColNum);
		if (Cell != null && Cell.getStringCellValue().equalsIgnoreCase(value)) { 
			HashMap<String,String> MapError = new HashMap<String,String>();
			HSSFCell Cell1 = sheet.getRow(i).getCell(1);
			String TCDescription = Cell1.getStringCellValue();
			HSSFCell Cell2 = sheet.getRow(i).getCell(7);
			String TCError = Cell2.getStringCellValue();
			String Request = sheet.getRow(i).getCell(9).getStringCellValue();
			String Response = sheet.getRow(i).getCell(10).getStringCellValue();
			MapError.put("TCName", TCDescription);
			MapError.put("Type", "Failure");
			MapError.put("TCError", TCError);
			MapError.put("Request", Request);
			MapError.put("Response", Response);
			AllTCErrors.add(MapError);
		}
		}		
		return AllTCErrors;
	}
	
	public static int GenerateTotalCases() throws Throwable {
		String fileName = Config.Test_Cases;
		FileInputStream ExcelFile = new FileInputStream(fileName);
		ExcelWBook= get_workbook(ExcelFile);
		ExcelWSheet = get_Sheet(ExcelWBook, Config.Test_Cases_Sheet);
//		String TCs= "TC01_TC02_TC03_TC04::ValidateFlows";
		int cases = GetRowCount (ExcelWSheet,0);
		ExcelFile.close();
		return cases;
	}

	public static int GeneratePassedCases() throws Throwable {
		String fileName = Config.Test_Cases;
		FileInputStream ExcelFile = new FileInputStream(fileName);
		ExcelWBook= get_workbook(ExcelFile);
		ExcelWSheet = get_Sheet(ExcelWBook, Config.Test_Cases_Sheet);
//		String TCs= "TC01_TC02_TC03_TC04::ValidateFlows";
		int cases = GetRowCountForStatus (ExcelWSheet,6,"PASSED");
		ExcelFile.close();
		return cases;
	}

	public static int GenerateFailedCases() throws Throwable {
		String fileName = Config.Test_Cases;
		FileInputStream ExcelFile = new FileInputStream(fileName);
		ExcelWBook= get_workbook(ExcelFile);
		ExcelWSheet = get_Sheet(ExcelWBook, Config.Test_Cases_Sheet);
//		String TCs= "TC01_TC02_TC03_TC04::ValidateFlows";
		int cases = GetRowCountForStatus (ExcelWSheet,6,"FAILED");
		ExcelFile.close();
		return cases;
	}

	public static int GenerateNotRunCases() throws Throwable {
		String fileName = Config.Test_Cases;
		FileInputStream ExcelFile = new FileInputStream(fileName);
		ExcelWBook= get_workbook(ExcelFile);
		ExcelWSheet = get_Sheet(ExcelWBook, Config.Test_Cases_Sheet);
//		String TCs= "TC01_TC02_TC03_TC04::ValidateFlows";
		int cases = 0;
		
		cases = GetRowCountForStatus (ExcelWSheet,6,"NotRun");
		
		ExcelFile.close();
		return cases;
	}

	public static List<HashMap<String, String>> CreateFailureMap() throws Throwable {
		String fileName = Config.Test_Cases;
		FileInputStream ExcelFile = new FileInputStream(fileName);
		ExcelWBook= get_workbook(ExcelFile);
		ExcelWSheet = get_Sheet(ExcelWBook, Config.Test_Cases_Sheet);
//		String TCs= "TC01_TC02_TC03_TC04::ValidateFlows";
		List<HashMap<String, String>> cases = null;
		try {
		cases = GetStatusDetails(ExcelWSheet,6,"FAILED");
		}catch(Exception e) {
			
		}
		ExcelFile.close();
		return cases;
	}


}
