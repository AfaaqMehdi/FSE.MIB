/**
 * 
 */
package com.mindtree.mib.builder;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mindtree.mib.CricketerStatistic;
import com.mindtree.mib.Data;
import com.mindtree.mib.Provider;

/**
 * @author M1038389
 *
 */
public class CricketerStatisticBuilder {
	
	
	/*private Integer pid;
    private String profile;
    private String imageURL;
    private String battingStyle;
    private String bowlingStyle;
    private String majorTeams;
    private String currentAge;
    private String born;
    private String fullName;
    private String name;
    private String country;
    private String playingRole;
    private String v;
    private Data data;
    private Integer ttl;
    private Provider provider;
    private Integer creditsLeft;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();*/
    
    private CricketerStatistic cricketerStatistic;
	
	public CricketerStatisticBuilder() {
		cricketerStatistic = new CricketerStatistic();
	}
	
	public CricketerStatisticBuilder addPid(Integer pid) {
		cricketerStatistic.setPid(pid);
		return this;
	}
	
	public CricketerStatisticBuilder addProfile(String profile) {
		cricketerStatistic.setProfile(profile);
		return this;
	}
	
	public CricketerStatisticBuilder addImageURL(String imageURL) {
		cricketerStatistic.setImageURL(imageURL);
		return this;
	}
	public CricketerStatisticBuilder addBattingStyle(String battingStyle) {
		cricketerStatistic.setBattingStyle(battingStyle);
		return this;
	}
	
	
    
    public CricketerStatistic build() {
    	return cricketerStatistic;
    }

}
