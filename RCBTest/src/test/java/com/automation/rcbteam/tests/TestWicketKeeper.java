package com.automation.rcbteam.tests;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.rcbteam.json.team.pojo.Player;
import com.automation.rcbteam.json.team.pojo.Team;
import com.automation.rcbteam.json.utilities.TeanmReader;

public class TestWicketKeeper {
	
	  
	  private static final String WICKET_KEEPER = "Wicket-keeper";
	  
	  

	  @Test(description = "Test Team for atleast 1 WK")
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
