/**
 * 
 */
package com.mindtree.mib.builder;

import com.mindtree.mib.Batting;
import com.mindtree.mib.CricketerStatistic;
import com.mindtree.mib.Data;
import com.mindtree.mib.ODIs_;
import com.mindtree.mib.T20Is_;
import com.mindtree.mib.Tests_;

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
	
	public CricketerStatisticBuilder add100(String odi100, String t20100, String test100) {
		Data data = new Data();
		Batting batting = new Batting();
		ODIs_ oDIs = new ODIs_();
		oDIs.set100(odi100);
		batting.setODIs(oDIs );
		T20Is_ t20Is = new T20Is_();
		t20Is.set100(t20100);
		batting.setT20Is(t20Is );
		Tests_ tests = new Tests_();
		tests.set100(test100);
		batting.setTests(tests );
		data.setBatting(batting );
		cricketerStatistic.setData(data);
		return this;
	}
	
	
    
    public CricketerStatistic build() {
    	return cricketerStatistic;
    }

}
