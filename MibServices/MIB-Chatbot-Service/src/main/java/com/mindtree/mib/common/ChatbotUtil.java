/**
 * 
 */
package com.mindtree.mib.common;

import com.mindtree.mib.CricketerStatistic;
import com.mindtree.mib.constant.CricketerStatisticEnum;

/**
 * @author M1038389
 *
 */
public class ChatbotUtil {
	
	public static String getResult(CricketerStatistic cricketerStatistic, String entity) {
		if(cricketerStatistic != null && entity != null) {
			CricketerStatisticEnum statisticEnum = CricketerStatisticEnum.valueOf(entity);
			
			switch (statisticEnum) {
			
			case profile: return cricketerStatistic.getProfile();
			case imageURL: return cricketerStatistic.getImageURL(); 
			
			case battingStyle: return cricketerStatistic.getBattingStyle(); 
			
			case bowlingStyle: return cricketerStatistic.getBowlingStyle(); 
			case born: return cricketerStatistic.getBorn(); 
			case name: return cricketerStatistic.getName(); 
			case country: return cricketerStatistic.getCountry(); 
			case playingRole: return cricketerStatistic.getPlayingRole(); 
			//case _10: return ("T20 : " + cricketerStatistic.getData().getBowling().getT20Is().get10() + "ODI : " + cricketerStatistic.getData().getBowling().getODIs().get10() + "Tests : " + cricketerStatistic.getData().getBowling().getTests().get10()); 
			//case _5w: return cricketerStatistic.getImageURL(); 
			//case _4w: return cricketerStatistic.getImageURL(); 
			case SR: 
				return ("Bowling : "  + "T20 : " + cricketerStatistic.getData().getBowling().getT20Is().getSR() + 
						"ODI : " + cricketerStatistic.getData().getBowling().getODIs().getSR() + 
						"Tests : " + cricketerStatistic.getData().getBowling().getTests().getSR() +
						" Batting : "  + "T20 : " + cricketerStatistic.getData().getBatting().getT20Is().getSR() + 
						" ODI : " + cricketerStatistic.getData().getBatting().getODIs().getSR() + 
						" Tests : " + cricketerStatistic.getData().getBatting().getTests().getSR());
			case Econ: 
				return ("Bowling : "  + "T20 : " + cricketerStatistic.getData().getBowling().getT20Is().getEcon() + 
						"ODI : " + cricketerStatistic.getData().getBowling().getODIs().getEcon() + 
						"Tests : " + cricketerStatistic.getData().getBowling().getTests().getEcon());
			case Ave: 
				return ("Bowling : "  + "T20 : " + cricketerStatistic.getData().getBowling().getT20Is().getAve() + 
						" ODI : " + cricketerStatistic.getData().getBowling().getODIs().getAve() + 
						" Tests : " + cricketerStatistic.getData().getBowling().getTests().getAve() +
						" Batting : "  + "T20 : " + cricketerStatistic.getData().getBatting().getT20Is().getAve() + 
						" ODI : " + cricketerStatistic.getData().getBatting().getODIs().getAve() + 
						" Tests : " + cricketerStatistic.getData().getBatting().getTests().getAve());
			case Wkts: 
				return ("Bowling : "  + "T20 : " + cricketerStatistic.getData().getBowling().getT20Is().getWkts() + 
						" ODI : " + cricketerStatistic.getData().getBowling().getODIs().getWkts() + 
						" Tests : " + cricketerStatistic.getData().getBowling().getTests().getWkts()); 
			case Runs: 
				return ("Bowling : "  + "T20 : " + cricketerStatistic.getData().getBowling().getT20Is().getRuns() + 
						" ODI : " + cricketerStatistic.getData().getBowling().getODIs().getRuns() + 
						" Tests : " + cricketerStatistic.getData().getBowling().getTests().getRuns() + 
						" Batting : "  + "T20 : " + cricketerStatistic.getData().getBatting().getT20Is().getRuns() + 
						" ODI : " + cricketerStatistic.getData().getBatting().getODIs().getRuns() + 
						" Tests : " + cricketerStatistic.getData().getBatting().getTests().getRuns()); 
			case Balls: 
				return ("Bowling : "  + "T20 : " + cricketerStatistic.getData().getBowling().getT20Is().getBalls() + 
						" ODI : " + cricketerStatistic.getData().getBowling().getODIs().getBalls() + 
						" Tests : " + cricketerStatistic.getData().getBowling().getTests().getBalls());
			case Inns: 
				return ("Bowling : "  + "T20 : " + cricketerStatistic.getData().getBowling().getT20Is().getInns() + 
						" ODI : " + cricketerStatistic.getData().getBowling().getODIs().getInns() + 
						" Tests : " + cricketerStatistic.getData().getBowling().getTests().getInns() + 
						" Batting : "  + "T20 : " + cricketerStatistic.getData().getBatting().getT20Is().getInns() + 
						" ODI : " + cricketerStatistic.getData().getBatting().getODIs().getInns() + 
						" Tests : " + cricketerStatistic.getData().getBatting().getTests().getInns()); 
			case Mat: 
				return ("Bowling : "  + "T20 : " + cricketerStatistic.getData().getBowling().getT20Is().getMat() + 
						" ODI : " + cricketerStatistic.getData().getBowling().getODIs().getMat() + 
						" Tests : " + cricketerStatistic.getData().getBowling().getTests().getMat() + 
						" Batting : "  + "T20 : " + cricketerStatistic.getData().getBatting().getT20Is().getMat() + 
						" ODI : " + cricketerStatistic.getData().getBatting().getODIs().getMat() + 
						" Tests : " + cricketerStatistic.getData().getBatting().getTests().getMat());
			//case _50: return cricketerStatistic.getImageURL();
			//case _100: return cricketerStatistic.getImageURL();
			case St: 
				return ("Batting : "  + "T20 : " + cricketerStatistic.getData().getBatting().getT20Is().getSt() + 
						" ODI : " + cricketerStatistic.getData().getBatting().getODIs().getSt() + 
						" Tests : " + cricketerStatistic.getData().getBatting().getTests().getSt());
			
			case Ct: 
				return ("Batting : "  + "T20 : " + cricketerStatistic.getData().getBatting().getT20Is().getCt() + 
						" ODI : " + cricketerStatistic.getData().getBatting().getODIs().getCt() + 
						" Tests : " + cricketerStatistic.getData().getBatting().getTests().getCt());
			
			//case _6s: return cricketerStatistic.getImageURL();
			//case _4s: return cricketerStatistic.getImageURL();
			case HS: 
				return ("Batting : "  + "T20 : " + cricketerStatistic.getData().getBatting().getT20Is().getHS() + 
						" ODI : " + cricketerStatistic.getData().getBatting().getODIs().getHS() + 
						" Tests : " + cricketerStatistic.getData().getBatting().getTests().getHS());
			case NO: 
				return ("Batting : "  + "T20 : " + cricketerStatistic.getData().getBatting().getT20Is().getNO() + 
						" ODI : " + cricketerStatistic.getData().getBatting().getODIs().getNO() + 
						" Tests : " + cricketerStatistic.getData().getBatting().getTests().getNO());
				
			case BF: return cricketerStatistic.getData().getBatting().getTests().getBF();

			default:
				break;
			}
		}
		return null;
		
	}

}
