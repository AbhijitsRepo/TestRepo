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

public class TestWicketKeeper extends BaseClass {
	
	  
	  private static final String WICKET_KEEPER = "Wicket-keeper";
	  
	  @BeforeMethod(alwaysRun=true) 
	  public void initiateTest() {
          Asserts.verificationErrors=Asserts.verificationErrors.delete(0, Asserts.verificationErrors.length()); 
          TCName.delete(0, TCName.length()); 
          }

	  @Test(description = "TC0002 - Test Team for atleast 1 WK")
	  public void testWicketKeeperPresent() throws Throwable {
	    Team team = TeanmReader.getTeam();
	    List<Player> wicketKeepers = team.getPlayer().stream()
	        .filter(player -> player.getRole().equals(WICKET_KEEPER))
	        .collect(Collectors.toList());
	    Asserts.assertTrue(!wicketKeepers.isEmpty(),
	        "No wicket keeper found in the team");
	    Asserts.AssertAll("TC0002", "NA", "NA");
	  }
	



}
