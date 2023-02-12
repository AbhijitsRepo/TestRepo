package com.automation.common.utilities;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Level;
import org.testng.Reporter;

import io.qameta.allure.Step;
/**
 * Class for logging
 * @author Abhijit Bhattacharyya
 *
 */
public abstract class Logger { 
	 static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("devpinoyLogger"); 
	 
	/**
	 * Logs a message to the console, the allure reports, and the testng reports.
	 * @param inMessage
	 */
	@Step("Step Description: {0}")
    public static void logMessage(String message) {
		Reporter.log(message, false);
		logConsoleMessage(message);
        log.info(message);
    }
	
	/**
	 * Logs a message to the console only.
	 * @param inMessage
	 */
	public static void logConsoleMessage(String message) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date()) + " " + message);
    }
	
	/**
	 * Disables log4j console output. Helpful if you don't want a lot of console clutter.
	 */
	public static void disableLog4JConsoleOutput() {
		Logger.logConsoleMessage("Disabling log4j console output.");
		org.apache.log4j.Logger.getLogger("org.BIU.utils.logging.ExperimentLogger").setLevel(Level.OFF);
    	org.apache.log4j.Logger.getRootLogger().setLevel(Level.OFF);
	}
	
	/**
	 * Enables log4j console output.
	 */
	public static void enableLog4JConsoleOutput() {
		Logger.logConsoleMessage("Enabling log4j console output.");
		org.apache.log4j.Logger.getLogger("org.BIU.utils.logging.ExperimentLogger").setLevel(Level.ALL);
    	org.apache.log4j.Logger.getRootLogger().setLevel(Level.ALL);
	}
}
