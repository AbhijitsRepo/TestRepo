package com.automation.common.utilities.reports;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReportWriter {
	
	static BufferedWriter writer = null;
	
	public static void WriteReports(String FilePath,String WriteOut) {
	try
	{
	    writer = new BufferedWriter( new FileWriter( FilePath));
	    writer.write(WriteOut);

	}
	catch ( IOException e)
	{
	}
	finally
	{
	    try
	    {
	        if ( writer != null)
	        writer.close( );
	        writer.flush();
	    }
	    catch ( IOException e)
	    {
	    }
	}
	}


}
