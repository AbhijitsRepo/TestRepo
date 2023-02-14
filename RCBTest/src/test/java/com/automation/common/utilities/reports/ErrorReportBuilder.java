package com.automation.common.utilities.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class ErrorReportBuilder {
	
	public static String HTML_Header_Builder(HashMap <Integer,HashMap<String,List<HashMap<String,String>>>> Map1,String Summary) {
		
		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder.append("<html>");
		htmlBuilder.append("<head>");
		htmlBuilder.append("<style>");
		htmlBuilder.append("table, th, td {\r\n" + 
				"  border: 1px solid black;\r\n" + 
				"  border-collapse: collapse;\r\n" + 
				"}");
		htmlBuilder.append("th, td {\r\n" + 
				"  padding: 5px;\r\n" + 
				"  text-align: left;\r\n" + 
				"}");
		htmlBuilder.append("</style>");
		htmlBuilder.append("</head>");
		htmlBuilder.append("<body>");
		
		htmlBuilder.append("<h2>Test Summary Report</h2>");
		htmlBuilder.append(Summary);
		
		htmlBuilder.append("<h2>Summary Of Errors/Failures</h2>");
		
		htmlBuilder.append(htmlTableBuilder_Final((Map1)));
		
		htmlBuilder.append("</body>");
		htmlBuilder.append("</html>");
		String html = htmlBuilder.toString();
		return html;
	}
	

	public static String htmlTableBuilder_Final(HashMap <Integer,HashMap<String,List<HashMap<String,String>>>> Map1) {
		StringBuilder htmlBuilder = new StringBuilder();
		
		
		htmlBuilder.append("<table>");
		
		
		htmlBuilder.append(String.format("<tr><th>%s</th><th>%s</th><th>%s</th><th>%s</th><th>%s</th></tr>",
	            "SlNo", "TC","Type","Errors","Page"));

		for (Entry<Integer, HashMap<String, List<HashMap<String,String>>>> entry : Map1.entrySet()) {
			htmlBuilder.append("</tr>");
			
			for (Entry<String, List<HashMap<String,String>>> entry1 : entry.getValue().entrySet()) {
				System.out.println(entry1.getKey());
				
				htmlBuilder.append(String.format("<td rowspan=%d>%d</td>",
						entry1.getValue().size(),entry.getKey()));
				
				htmlBuilder.append(String.format("<td rowspan=%d>%s</td>",
						entry1.getValue().size(),entry1.getKey()));
				
				int Count =0;
				for(HashMap<String,String> error:entry1.getValue()) {
					System.out.println(error);
					if(error.containsKey("Error")) {
					if(Count==0)
					htmlBuilder.append(String.format("<td>%s</td><td>%s</td><td>%s</td>",
				            "Error",error.get("Error"),error.get("Page")));
					else if(Count>0)
						htmlBuilder.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
								"Error",error.get("Error"),error.get("Page")));
					Count++;
				}else if(error.containsKey("Exception")) {
					if(Count==0)
					htmlBuilder.append(String.format("<td>%s</td><td>%s</td><td>%s</td>",
				            "Exception",error.get("Exception"),error.get("Page")));
					else if(Count>0)
						htmlBuilder.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
								"Exception",error.get("Exception"),error.get("Page")));
					Count++;
				
				}
					
				}
			}
			htmlBuilder.append("</tr>");
		}

		htmlBuilder.append("</table>");

		String html = htmlBuilder.toString();
		return html;
	}

	public static String htmlSummaryTable_Final(int Total, int passed, int failed, int skipped) {
		StringBuilder htmlBuilder = new StringBuilder();
		
		
		htmlBuilder.append("<table>");
		
		
		htmlBuilder.append(String.format("<tr><th>%s</th><th>%s</th><th>%s</th><th>%s</th></tr>",
	            "Total Tests", "Passed Tests","Failed Tests","Skipped Tests"));
		htmlBuilder.append(String.format("<tr><td>%d</td><td>%d</td><td>%d</td><td>%d</td></tr>",
				Total, passed,failed,skipped));


		htmlBuilder.append("</table>");

		String html = htmlBuilder.toString();
		return html;
	}


	public void html_final() {
		HashMap<String, List<HashMap<String, String>>> mapError1 = new HashMap<String,List<HashMap<String,String>>>();
		HashMap<String, List<HashMap<String, String>>> mapError2 = new HashMap<String,List<HashMap<String,String>>>();
		
		ArrayList<HashMap<String,String>> Errors1 = new ArrayList<>();
		HashMap<String,String> errormap =new HashMap<>();
		errormap.put("Error","Unable To Click");
		errormap.put("Page", "Login Page");
		Errors1.add(errormap);
		HashMap<String,String> errormap1 =new HashMap<>();
		errormap1.put("Error","Unable To Add");
		errormap1.put("Page", "Post Login Page");
		Errors1.add(errormap1);
		HashMap<String,String> errormap2 =new HashMap<>();
		errormap2.put("Error","Unable To Submit");
		errormap2.put("Page", "Post Login Page");
		Errors1.add(errormap2);
		
		ArrayList<HashMap<String,String>> Errors2 = new ArrayList<>();
		Errors2.add(errormap);
		Errors2.add(errormap1);
		Errors2.add(errormap2);
		
		mapError1.put("Login Test", Errors1);
		mapError2.put("Login Failure Test", Errors2);
		
		HashMap <Integer,HashMap<String,List<HashMap<String,String>>>> MapFinal = new HashMap <Integer,HashMap<String,List<HashMap<String,String>>>>();
		MapFinal.put(1, mapError1);
		MapFinal.put(2, mapError2);
		
		System.out.println(MapFinal);
		String Report = HTML_Header_Builder(MapFinal,htmlSummaryTable_Final(5,5,5,5));
		System.out.println(Report);
		ReportWriter.WriteReports("Reports/ErrorReport.html", Report);
		
	}

}
