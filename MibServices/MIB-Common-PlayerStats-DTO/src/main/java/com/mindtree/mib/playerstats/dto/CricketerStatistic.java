
package com.mindtree.mib.playerstats.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pid",
    "profile",
    "imageURL",
    "battingStyle",
    "bowlingStyle",
    "majorTeams",
    "currentAge",
    "born",
    "fullName",
    "name",
    "country",
    "playingRole",
    "v",
    "data",
    "ttl",
    "provider",
    "creditsLeft"
})
public class CricketerStatistic {

    @JsonProperty("pid")
    private Integer pid;
    @JsonProperty("profile")
    private String profile;
    @JsonProperty("imageURL")
    private String imageURL;
    @JsonProperty("battingStyle")
    private String battingStyle;
    @JsonProperty("bowlingStyle")
    private String bowlingStyle;
    @JsonProperty("majorTeams")
    private String majorTeams;
    @JsonProperty("currentAge")
    private String currentAge;
    @JsonProperty("born")
    private String born;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private String country;
    @JsonProperty("playingRole")
    private String playingRole;
    @JsonProperty("v")
    private String v;
    @JsonProperty("data")
    private Data data;
    @JsonProperty("ttl")
    private Integer ttl;
    @JsonProperty("provider")
    private Provider provider;
    @JsonProperty("creditsLeft")
    private Integer creditsLeft;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("pid")
    public Integer getPid() {
        return pid;
    }

    @JsonProperty("pid")
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @JsonProperty("profile")
    public String getProfile() {
        return profile;
    }

    @JsonProperty("profile")
    public void setProfile(String profile) {
        this.profile = profile;
    }

    @JsonProperty("imageURL")
    public String getImageURL() {
        return imageURL;
    }

    @JsonProperty("imageURL")
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @JsonProperty("battingStyle")
    public String getBattingStyle() {
        return battingStyle;
    }

    @JsonProperty("battingStyle")
    public void setBattingStyle(String battingStyle) {
        this.battingStyle = battingStyle;
    }

    @JsonProperty("bowlingStyle")
    public String getBowlingStyle() {
        return bowlingStyle;
    }

    @JsonProperty("bowlingStyle")
    public void setBowlingStyle(String bowlingStyle) {
        this.bowlingStyle = bowlingStyle;
    }

    @JsonProperty("majorTeams")
    public String getMajorTeams() {
        return majorTeams;
    }

    @JsonProperty("majorTeams")
    public void setMajorTeams(String majorTeams) {
        this.majorTeams = majorTeams;
    }

    @JsonProperty("currentAge")
    public String getCurrentAge() {
        return currentAge;
    }

    @JsonProperty("currentAge")
    public void setCurrentAge(String currentAge) {
        this.currentAge = currentAge;
    }

    @JsonProperty("born")
    public String getBorn() {
        return born;
    }

    @JsonProperty("born")
    public void setBorn(String born) {
        this.born = born;
    }

    @JsonProperty("fullName")
    public String getFullName() {
        return fullName;
    }

    @JsonProperty("fullName")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("playingRole")
    public String getPlayingRole() {
        return playingRole;
    }

    @JsonProperty("playingRole")
    public void setPlayingRole(String playingRole) {
        this.playingRole = playingRole;
    }

    @JsonProperty("v")
    public String getV() {
        return v;
    }

    @JsonProperty("v")
    public void setV(String v) {
        this.v = v;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    @JsonProperty("ttl")
    public Integer getTtl() {
        return ttl;
    }

    @JsonProperty("ttl")
    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    @JsonProperty("provider")
    public Provider getProvider() {
        return provider;
    }

    @JsonProperty("provider")
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @JsonProperty("creditsLeft")
    public Integer getCreditsLeft() {
        return creditsLeft;
    }

    @JsonProperty("creditsLeft")
    public void setCreditsLeft(Integer creditsLeft) {
        this.creditsLeft = creditsLeft;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
