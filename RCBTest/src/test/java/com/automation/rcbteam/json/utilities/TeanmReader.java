package com.automation.rcbteam.json.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.qameta.allure.Step;

import java.io.File;
import java.io.IOException;

import com.automation.common.constant.Config;
import com.automation.rcbteam.json.team.pojo.Team;

public class TeanmReader {
	
	/**
	 * Read JsonFIle and Parse to POJO Objects
	 * @return
	 */
	@Step("Parse json File to java object")
	public static Team getTeam() {
//	    ClassLoader classLoader = getClass().getClassLoader();
		System.out.println(Config.JsonPath);
	    File file = new File(Config.JsonPath);
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	        return objectMapper.readValue(file, Team.class);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}


}
