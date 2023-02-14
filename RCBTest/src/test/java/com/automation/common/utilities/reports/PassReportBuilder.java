package com.automation.common.utilities.reports;

public class PassReportBuilder {
	
	
	public static String HTML_Header_Builder(String Summary) {
		
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
		
		
		htmlBuilder.append("</body>");
		htmlBuilder.append("</html>");
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
