package com.automation.rcbteam.json.team.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("country")
    private String country;
    
    @JsonProperty("role")
    private String role;
    
    @JsonProperty("price-in-crores")
    private String priceInCrores;

    public Player() {
    }

    public Player(String name, String country, String role, String priceInCrores) {
        this.name = name;
        this.country = country;
        this.role = role;
        this.priceInCrores = priceInCrores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPriceInCrores() {
        return priceInCrores;
    }

    public void setPriceInCrores(String priceInCrores) {
        this.priceInCrores = priceInCrores;
    }
}
