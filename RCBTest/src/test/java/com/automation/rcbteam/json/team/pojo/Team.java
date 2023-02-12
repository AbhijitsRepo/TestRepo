package com.automation.rcbteam.json.team.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Team {

  @JsonProperty("name")
  private String name;

  @JsonProperty("location")
  private String location;

  @JsonProperty("player")
  private List<Player> player;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public List<Player> getPlayer() {
    return player;
  }

  public void setPlayer(List<Player> player) {
    this.player = player;
  }
}

