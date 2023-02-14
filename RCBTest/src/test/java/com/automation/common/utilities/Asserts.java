package com.automation.common.utilities;

import static org.testng.Assert.fail;

import java.util.HashMap;

import org.testng.Assert;

import com.automation.common.BaseClass;

public class Asserts extends BaseClass{
	
	public static StringBuffer verificationErrors = new StringBuffer();
	public static HashMap<String,String> Error = new HashMap<>();

	public static void assertEqual(Object Actual, Object Expected, String Message) {
		try {
		Assert.assertEquals(Actual, Expected, Message);
		Logger.logMessage(Message);
		} catch(Error E1) {
			verificationErrors.append(E1.toString());			
			Logger.logMessage(Message+ " :Failed");
			Error.put("Error", Message+ " :Failed");
			AllErrors.add(Error);
			System.out.println(AllErrors);
		}
	}
	
	
	
	public static void assertEqualIgnoreSpaces(Object Actual, Object Expected, String Message) {
		Actual= Actual.toString().replaceAll("\\s+", "");
		Expected= Expected.toString().replaceAll("\\s+", "");
		try {
		Assert.assertEquals(Actual, Expected, Message);
		Logger.logMessage(Message);
		} catch(Error E1) {
			verificationErrors.append(E1.toString());			
			Logger.logMessage(Message+ " :Failed");
			Error.put("Error", Message+ " :Failed");
				AllErrors.add(Error);
			System.out.println(AllErrors);
		}
	}
	
	public static void assertTrue(Boolean Flag, String Message) {
		try {
		Assert.assertTrue(Flag,Message);
		Logger.logMessage(Message);
		} catch(Error E1) {
			verificationErrors.append(E1.toString());			
			Logger.logMessage(Message+ " :Failed");
			Error.put("Error", Message+ " :Failed");
			AllErrors.add(Error);
		}
	}
	
	public static void assertNotNull(Object Actual, String Message) {
		try {
			Assert.assertNotNull(Actual);
		Logger.logMessage(Message);
		} catch(Error E1) {
			verificationErrors.append(E1.toString());			
			Logger.logMessage(Message+" :Failed");
			Error.put("Error", Message+" :Failed");
			AllErrors.add(Asserts.Error);
		}
	}
	
	public static void assertNull(Object Actual, String Message) {
		try {
			Assert.assertNull(Actual, Message);
		Logger.logMessage(Message);
		} catch(Error E1) {
			verificationErrors.append(E1.toString());			
			Logger.logMessage(Message+ " :Failed");
			Error.put("Error", Message+ " :Failed");
			AllErrors.add(Asserts.Error);
		}
	}
	
	
	public static void assertContains(String Actual, String SubString, String Message) {
		try {
			Assert.assertTrue(Actual.contains(SubString),Message);
		Logger.logMessage(Message);
		} catch(Error E1) {
			verificationErrors.append(E1.toString());			
			Logger.logMessage(Message+ " :Failed");
			Error.put("Error", Message+ " :Failed");
			AllErrors.add(Asserts.Error);
		}
	}
	
	public static void AssertAll() {
		
		String verificationErrorString = Asserts.verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	public static void AssertAll(String TCID,String Request,String Response) throws Throwable {
		
		String verificationErrorString = Asserts.verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			ExcelReport.GenerateExcelReport(TCID, "FAILED",verificationErrorString,Request,Response);
			fail(verificationErrorString);
			
		}
		else {
			ExcelReport.GenerateExcelReport(TCID, "PASSED","NA","","");
		}
	}
	
	public static void AssertAll(String TCID,String Type) throws Throwable {
		
		String verificationErrorString = Asserts.verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			ExcelReport.GenerateExcelReport(TCID, "FAILED",verificationErrorString,Type);
			fail(verificationErrorString);
			
		}
		else {
			ExcelReport.GenerateExcelReport(TCID, "PASSED","NA","","");
		}
	}
	
	
	
}