package com.automation.common.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.automation.common.constant.Config;

import io.qameta.allure.Attachment;
/**
 * Class for Allure methods
 * @author Abhijit Bhattacharyya
 *
 */
public class AllureManager {
	//private static String reportDir = "./target/allure-results";
	private static String emptyString = "empty string";
	
	/**
	 * Gets the screenshot of the browser window and attaches it to the test case in the allure report.
	 * @return
	 */
    @Attachment(value = "Test Log", type = "text/plain")
    public static byte[] attachLog(String pathToLogFile) {
    	byte[] logContent = emptyString.getBytes();
    	try {
    		logContent = Files.readAllBytes(Paths.get(pathToLogFile));
    	} catch (Exception e) {
    		Logger.logConsoleMessage("Failed to get log data.");
    		e.printStackTrace();
    	}
    	return logContent;
    }
    
   /**
    * Creates environment.properties file 
    * @param propertyFileName
    * @param allureProps
    */
   	public static void createAllurePropertyFile(String propertyFileName, LinkedHashMap<String, String> allureProps) {
       	File propertyFile = new File(Config.ALLURE_ENV_PATH + propertyFileName);
           
           propertyFile.mkdirs();
           
           if (propertyFile.exists()) {
           	propertyFile.delete();
           } else {
           	try {
   				propertyFile.createNewFile();
   			} catch (IOException e) {
   				Logger.logConsoleMessage("Failed to generate allure properties file at: " 
   			        + propertyFile.getAbsolutePath());
   				e.printStackTrace();
   			}
           }
           
           BufferedWriter writer = null;
           try {
   			writer = new BufferedWriter(new FileWriter(propertyFile));
   			
   			String props = "";
   			Iterator iterator = allureProps.entrySet().iterator();
   			while (iterator.hasNext()) {
   				Map.Entry pair = (Map.Entry) iterator.next();
   				String prop = pair.getKey() + "=" + pair.getValue() + System.getProperty("line.separator");
   			    props += prop;
   			}
   			writer.write(props);
   			writer.close();
   		} catch (IOException e) {
   			Logger.logConsoleMessage("Failed to write properties to prop file.");
   			e.printStackTrace();
   		}
           //return this;
       }
       
}