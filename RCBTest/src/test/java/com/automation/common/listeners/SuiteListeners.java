package com.automation.common.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.automation.common.BaseClass;
import com.automation.common.utilities.AllureManager;

import java.util.*;
/**
 * Suite listener
 * @author Savita Tambe
 *
 */

public class SuiteListeners extends BaseClass implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
    }
    
    @Override
    public void onFinish(ISuite suite) {
    	LinkedHashMap<String, String> environmentProps = new LinkedHashMap<String, String>();
    	AllureManager.createAllurePropertyFile("environment.properties", environmentProps);
    }
}