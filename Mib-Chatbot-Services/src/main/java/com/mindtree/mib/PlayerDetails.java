package com.mindtree.mib;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pid",
    "name",
    "born",
    "playingRole",
    "data"
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
	
	@JsonProperty("data")
    private Data data;
	
	
	
	
	
	 
	public PlayerDetails(Integer pid, String name, String imageURL, String born, String playingRole) {
		super();
		this.pid = pid;
		this.name = name;
		this.imageURL = imageURL;
		this.born = born;
		this.playingRole = playingRole;
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
    
    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }
	
}
