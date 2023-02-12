package com.automation.rcbteam.tests;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.rcbteam.json.team.pojo.Player;
import com.automation.rcbteam.json.team.pojo.Team;
import com.automation.rcbteam.json.utilities.TeanmReader;

public class TestForeignPlayers {
	
	
	  private static final int MAX_FOREIGN_PLAYERS = 4;
	  
	  
	  @Test(description = "Test Team for Max 4 Foreign Players")
	  public void testMaxForeignPlayers() {
	    Team team = TeanmReader.getTeam();
	    List<Player> foreignPlayers = team.getPlayer().stream()
	        .filter(player -> !player.getCountry().equals("India"))
	        .collect(Collectors.toList());
	    SoftAssert softAssert = new SoftAssert();
	    softAssert.assertTrue(foreignPlayers.size() <= MAX_FOREIGN_PLAYERS,
	        "There are more than 4 foreign players");
	    softAssert.assertAll();
	  }


}
