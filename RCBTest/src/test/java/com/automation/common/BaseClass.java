package com.automation.common;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.AfterClass;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlSuite;

import com.automation.common.utilities.AllureManager;
import com.automation.common.utilities.Asserts;
import com.automation.common.utilities.ExcelReport;
import com.automation.common.utilities.Logger;
import com.automation.common.utilities.reports.ConsolidatedReportBuilder;
import com.automation.common.utilities.reports.ErrorReportBuilder;
import com.automation.common.utilities.reports.PassReportBuilder;
import com.automation.common.utilities.reports.ReportWriter;
import com.beust.jcommander.Parameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class BaseClass {
	
	
	
	/**
	 * @author abhijit.bhattacharya
	 */

	protected static StringBuffer TCName = new StringBuffer();
	protected static StringBuffer verificationErrors = new StringBuffer();
	public static List<HashMap<String,String>> AllErrors;
	public static HashMap<String,List<HashMap<String,String>>> TCErrors;
	static HashMap <Integer,HashMap<String,List<HashMap<String,String>>>> ErrorMap;
	protected static String TCType = null;
	
	@BeforeSuite(alwaysRun=true)
	public void InitiateSuite() throws Throwable {
		
		ErrorMap = new HashMap <Integer,HashMap<String,List<HashMap<String,String>>>>();		
//		int idPos = 2;
	
	}
	
	
	@BeforeClass
	public void InitiateClassDB() throws Throwable {
		
		TCErrors= new HashMap<String,List<HashMap<String,String>>>();
		AllErrors = new ArrayList<HashMap<String,String>>();
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void TeardownTest(ITestResult result) throws Throwable {	
		
		
		
				result.getMethod().setDescription(TCType);
				System.out.println("New Description Is "+result.getMethod().getDescription());
				try {
					
					String verificationErrorString = Asserts.verificationErrors.toString();
					   if (!"".equals(verificationErrorString)) {		
						   System.out.print("Error Found for "+TCName.toString());
//					ExcelReport.GenerateExcelReport(result.getMethod().getDescription(), "FAILED", verificationErrorString);			   
						   ExcelReport.GenerateExcelReport(TCName.toString(), "FAILED",verificationErrorString,
									"NA","NA");	    		     
						     LinkedHashSet<HashMap<String,String>> 
					            newList = new LinkedHashSet<>(AllErrors);
						     ArrayList <HashMap<String,String>> finalList = new ArrayList<>(newList);
						     TCErrors.put(result.getParameters()[0]+":"+result.getParameters()[1], finalList);
						     ErrorMap.put(ErrorMap.entrySet().size()+1, TCErrors);
						     result.setStatus(ITestResult.FAILURE);
						     Reporter.setCurrentTestResult(result);
					   }else if(!(result.getStatus()==ITestResult.SUCCESS)) {
						   System.out.println(TCName.toString() + " has failed big time");
						   if (!result.getThrowable().toString().isEmpty()) {
							   try {
							   if(result.getThrowable().getMessage().contains("::Request::")) {
							ExcelReport.GenerateExcelReport(TCName.toString(), "FAILED",result.getThrowable().getMessage().split("::Request::")[0],
									result.getThrowable().getMessage().split("::Request::")[1],"NA");
							   				   System.out.print("Exception Found");
							   }else {
								   ExcelReport.GenerateExcelReport(TCName.toString(), "FAILED",result.getThrowable().toString(),"NA","NA");
								   System.out.print("Exception Found FOR DP Case");
							   }
							   }catch(Exception e1) {
								   ExcelReport.GenerateExcelReport(TCName.toString(), "FAILED",result.getThrowable().toString(),"NA","NA");
								   System.out.print("Exception Found FOR DP Case");
							   }
//					ExcelReport.GenerateExcelReport(result.getMethod().getDescription(), "FAILED", result.getThrowable().getMessage().toString());	   
							   
						   System.out.println(result.getThrowable().getMessage());
								HashMap<String,String> Errors = new HashMap<>();
								try {
								if(result.getThrowable().getMessage().contains("on:")) {
								Errors.put("Exception", result.getThrowable().getMessage().split("on:")[0]);
								Errors.put("Page", result.getThrowable().getMessage().split("on:")[1]);
								AllErrors.add(Errors);	
								} else{
									Errors.put("Exception", result.getThrowable().getMessage());
									Errors.put("Page", "NA");
								}
								}catch(Exception E) {
									Errors.put("Exception", result.getThrowable().getMessage());
									Errors.put("Page", "NA");
								}
								Set<HashMap<String,String>>  newList = Collections.synchronizedSet(new LinkedHashSet<>(AllErrors));
								     ArrayList <HashMap<String,String>> finalList = new ArrayList<>(newList);
								     TCErrors.put(result.getParameters()[0]+":"+result.getParameters()[1], finalList);
								     ErrorMap.put(ErrorMap.entrySet().size()+1, TCErrors);
								     result.setStatus(ITestResult.FAILURE);
								     Reporter.setCurrentTestResult(result);
						   }
					   }else{
						   System.out.print("Test Case Has Passed");
//				   ExcelReport.GenerateExcelReport(result.getMethod().getDescription(), "PASSED", "NA","","");
					   }	
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
	
}
	
	
	
	@AfterClass
	public void TeardownClassAPI(String Browser) throws Throwable {
	//	DriverManager.stopWebDriver(Browser);
	}
	
	
	
	@AfterSuite
	public void CompleteSuite(ITestContext ctx) throws Throwable {	
	
		
		XmlSuite xmlSuite = ctx.getCurrentXmlTest().getSuite();
	    ISuite suite = ctx.getSuite();
	    String suiteName = suite.getName();
	    
        //Getting the results for the said suite
	    int Passed=0;
	    int Failed=0;
	    int Skipped=0;
	    int Total=0;
	    
        //Getting the results for the said suite
        Map<String, ISuiteResult> suiteResults = suite.getResults();
        for (ISuiteResult sr : suiteResults.values()) {
           ITestContext tc = sr.getTestContext();
           Passed =Passed+ tc.getPassedTests().getAllResults().size();
           Failed = Failed+tc.getFailedTests().getAllResults().size();
           Skipped = Skipped+tc.getSkippedTests().getAllResults().size();
        }
        Total = Passed+Failed;
        	   if(!ErrorMap.isEmpty())
        	   {
        String Report = ErrorReportBuilder.HTML_Header_Builder(ErrorMap,ErrorReportBuilder.htmlSummaryTable_Final(Total, Passed, Failed, Skipped));
   		ReportWriter.WriteReports("Reports/ErrorReport.html", Report);
   		
   		
   		
   		System.out.println(ExcelReport.CreateFailureMap().toString());
   		String ConsolidatedReport = ConsolidatedReportBuilder.HTML_Header_Builder(ExcelReport.CreateFailureMap(),ConsolidatedReportBuilder.htmlSummaryTable_Final(
   				ExcelReport.GenerateTotalCases()-ExcelReport.GenerateNotRunCases()-2, ExcelReport.GeneratePassedCases(), ExcelReport.GenerateFailedCases(), 0));
   		ReportWriter.WriteReports("Reports/ConsolidatedErrorReport.html", ConsolidatedReport);
   		System.out.println(ConsolidatedReport);
        	   }
        	   else 
        	   {
        String Report = PassReportBuilder.HTML_Header_Builder(PassReportBuilder.htmlSummaryTable_Final(Total, Passed, Failed, Skipped));
        ReportWriter.WriteReports("Reports/ErrorReport.html", Report);	
        System.out.println("Test passed Without Errors");
        
			
     	String ConsolidatedReport = ConsolidatedReportBuilder.HTML_Header_Builder(ExcelReport.CreateFailureMap(),ConsolidatedReportBuilder.htmlSummaryTable_Final(
    			ExcelReport.GenerateTotalCases()-ExcelReport.GenerateNotRunCases()-2, ExcelReport.GeneratePassedCases(), ExcelReport.GenerateFailedCases(), 0));
   		ReportWriter.WriteReports("Reports/ConsolidatedErrorReport.html", ConsolidatedReport);
			 
        	}
	    }

}
