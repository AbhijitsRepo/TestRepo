package com.automation.rcbteam.tests;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.rcbteam.json.team.pojo.Player;
import com.automation.rcbteam.json.team.pojo.Team;
import com.automation.rcbteam.json.utilities.TeanmReader;

public class RCBTeamTests {
	
	
	  private static final int MAX_FOREIGN_PLAYERS = 4;
	  private static final String WICKET_KEEPER = "Wicket-keeper";
	  
	  

	  @Test
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

	  @Test
	  public void testWicketKeeperPresent() {
	    Team team = TeanmReader.getTeam();
	    List<Player> wicketKeepers = team.getPlayer().stream()
	        .filter(player -> player.getRole().equals(WICKET_KEEPER))
	        .collect(Collectors.toList());
	    SoftAssert softAssert = new SoftAssert();
	    softAssert.assertTrue(!wicketKeepers.isEmpty(),
	        "No wicket keeper found in the team");
	    softAssert.assertAll();
	  }
	

}
