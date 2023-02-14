package com.automation.rcbteam.tests;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.common.BaseClass;
import com.automation.common.utilities.Asserts;
import com.automation.rcbteam.json.team.pojo.Player;
import com.automation.rcbteam.json.team.pojo.Team;
import com.automation.rcbteam.json.utilities.TeanmReader;

public class TestForeignPlayers extends BaseClass{
	
	
	  private static final int MAX_FOREIGN_PLAYERS = 4;
	  
	  @BeforeMethod(alwaysRun=true) 
	  public void initiateTest() {
          Asserts.verificationErrors=Asserts.verificationErrors.delete(0, Asserts.verificationErrors.length()); 
          TCName.delete(0, TCName.length()); 
          }
	  
	  @Test(description = "TC0001 - Test Team for Max 4 Foreign Players")
	  public void testMaxForeignPlayers() throws Throwable {
	    Team team = TeanmReader.getTeam();
	    List<Player> foreignPlayers = team.getPlayer().stream()
	        .filter(player -> !player.getCountry().equals("India"))
	        .collect(Collectors.toList());
	    Asserts.assertTrue(foreignPlayers.size() <= MAX_FOREIGN_PLAYERS,
	        "There are more than 4 foreign players");
	    Asserts.AssertAll("TC0001", "NA", "NA");
	  }


}
