package com.automation.common.utilities.reports;

import java.util.HashMap;
import java.util.List;

public class ConsolidatedReportBuilder {
	
	public static String HTML_Header_Builder(List<HashMap<String,String>> Map1,String Summary) {
		
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
	

	public static String htmlTableBuilder_Final(List<HashMap<String,String>> Map1) {
		StringBuilder htmlBuilder = new StringBuilder();
		
		
		htmlBuilder.append("<table>");
		
		htmlBuilder.append(String.format("<tr><th>%s</th><th>%s</th><th>%s</th><th>%s</th><th>%s</th><th>%s</th><th>%s</th></tr>",
	            "SlNo", "TC","Type","Errors","Page","Request","Response"));
		for(int i=0;i<Map1.size();i++) {
			htmlBuilder.append("</tr>");
				htmlBuilder.append(String.format("<td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>",
						Integer.toString(i+1),Map1.get(i).get("TCName"),Map1.get(i).get("Type"),Map1.get(i).get("TCError"),"NA",
						Map1.get(i).get("Request"),Map1.get(i).get("Response")));
			htmlBuilder.append("</tr>");
		}
/*		for (Entry<Integer, HashMap<String, List<HashMap<String,String>>>> entry : Map1.entrySet()) {
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
		}*/

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



}
