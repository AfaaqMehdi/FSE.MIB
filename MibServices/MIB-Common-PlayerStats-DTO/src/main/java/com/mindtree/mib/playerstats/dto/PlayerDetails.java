package com.mindtree.mib.playerstats.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pid",
    "name",
    "born",
    "playingRole",
    "currentAge"
     
})
public class PlayerDetails {

	@JsonProperty("pid")
    private Integer pid;
	
	@JsonProperty("name")
    private String name;
    
	@JsonProperty("imageURL")
	 private String imageURL;
	
	@JsonProperty("born")
	 private String born;
	
	@JsonProperty("playingRole")
	 private String playingRole;
	
	@JsonProperty("currentAge")
	 private String currentAge;
	
	
	public PlayerDetails(Integer pid, String name, String imageURL, String born, String playingRole,String currentAge) {
		super();
		this.pid = pid;
		this.name = name;
		this.imageURL = imageURL;
		this.born = born;
		this.playingRole = playingRole;
		this.currentAge = currentAge;
	}

	
	
	public String getCurrentAge() {
		return currentAge;
	}
	public void setCurrentAge(String currentAge) {
		this.currentAge = currentAge;
	}
	public String getBorn() {
		return born;
	}
	public void setBorn(String born) {
		this.born = born;
	}
	public String getPlayingRole() {
		return playingRole;
	}
	public void setPlayingRole(String playingRole) {
		this.playingRole = playingRole;
	}
	@JsonProperty("imageURL")
    public String getImageURL() {
		return imageURL;
	}
	@JsonProperty("imageURL")
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@JsonProperty("pid")
    public Integer getPid() {
        return pid;
    }

    @JsonProperty("pid")
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    } 
	
}
