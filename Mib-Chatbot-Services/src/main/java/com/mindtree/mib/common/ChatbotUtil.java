/**
 * 
 */
package com.mindtree.mib.common;

import org.apache.log4j.Logger;

import com.mindtree.mib.CricketerStatistic;
import com.mindtree.mib.PersonalInfo;
import com.mindtree.mib.constant.CricketerStatisticEnum;
import com.mindtree.mib.constant.PersonalInfoEnum;

/**
 * @author M1038389
 * 
 */
public class ChatbotUtil {
	
	private static final Logger LOGGER = Logger.getLogger(ChatbotUtil.class);

	public static String getResult(CricketerStatistic cricketerStatistic,
			String entity) {
		LOGGER.info("Entering method getResult ---> ");
		if (cricketerStatistic != null && entity != null) {
			try {
				entity = mapValueToEnumValue(entity);
				CricketerStatisticEnum statisticEnum = CricketerStatisticEnum
						.valueOf(entity);
				switch (statisticEnum) {

				case profile:
					return cricketerStatistic.getProfile();
				case imageURL:
					return cricketerStatistic.getImageURL();

				case battingStyle:
					return cricketerStatistic.getBattingStyle();

				case bowlingStyle:
					return cricketerStatistic.getBowlingStyle();
				case born:
					return cricketerStatistic.getBorn();
				case name:
					return cricketerStatistic.getName();
				case country:
					return cricketerStatistic.getCountry();
				case playingRole:
					return cricketerStatistic.getPlayingRole();
				case SR:
					return ("Bowling : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBowling().getT20Is()
									.getSR()
							+ "ODI : "
							+ cricketerStatistic.getData().getBowling().getODIs()
									.getSR()
							+ "Tests : "
							+ cricketerStatistic.getData().getBowling().getTests()
									.getSR()
							+ " Batting : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBatting().getT20Is()
									.getSR()
							+ " ODI : "
							+ cricketerStatistic.getData().getBatting().getODIs()
									.getSR() + " Tests : " + cricketerStatistic
							.getData().getBatting().getTests().getSR());
				case Econ:
					return ("Bowling : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBowling().getT20Is()
									.getEcon()
							+ "ODI : "
							+ cricketerStatistic.getData().getBowling().getODIs()
									.getEcon() + "Tests : " + cricketerStatistic
							.getData().getBowling().getTests().getEcon());
				case Ave:
					return ("Bowling : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBowling().getT20Is()
									.getAve()
							+ " ODI : "
							+ cricketerStatistic.getData().getBowling().getODIs()
									.getAve()
							+ " Tests : "
							+ cricketerStatistic.getData().getBowling().getTests()
									.getAve()
							+ " Batting : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBatting().getT20Is()
									.getAve()
							+ " ODI : "
							+ cricketerStatistic.getData().getBatting().getODIs()
									.getAve() + " Tests : " + cricketerStatistic
							.getData().getBatting().getTests().getAve());
				case Wkts:
					return ("Bowling : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBowling().getT20Is()
									.getWkts()
							+ " ODI : "
							+ cricketerStatistic.getData().getBowling().getODIs()
									.getWkts() + " Tests : " + cricketerStatistic
							.getData().getBowling().getTests().getWkts());
				case Runs:
					return ("Bowling : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBowling().getT20Is()
									.getRuns()
							+ " ODI : "
							+ cricketerStatistic.getData().getBowling().getODIs()
									.getRuns()
							+ " Tests : "
							+ cricketerStatistic.getData().getBowling().getTests()
									.getRuns()
							+ " Batting : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBatting().getT20Is()
									.getRuns()
							+ " ODI : "
							+ cricketerStatistic.getData().getBatting().getODIs()
									.getRuns() + " Tests : " + cricketerStatistic
							.getData().getBatting().getTests().getRuns());
				case Balls:
					return ("Bowling : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBowling().getT20Is()
									.getBalls()
							+ " ODI : "
							+ cricketerStatistic.getData().getBowling().getODIs()
									.getBalls() + " Tests : " + cricketerStatistic
							.getData().getBowling().getTests().getBalls());
				case Inns:
					return ("Bowling : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBowling().getT20Is()
									.getInns()
							+ " ODI : "
							+ cricketerStatistic.getData().getBowling().getODIs()
									.getInns()
							+ " Tests : "
							+ cricketerStatistic.getData().getBowling().getTests()
									.getInns()
							+ " Batting : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBatting().getT20Is()
									.getInns()
							+ " ODI : "
							+ cricketerStatistic.getData().getBatting().getODIs()
									.getInns() + " Tests : " + cricketerStatistic
							.getData().getBatting().getTests().getInns());
				case Mat:
					return ("Bowling : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBowling().getT20Is()
									.getMat()
							+ " ODI : "
							+ cricketerStatistic.getData().getBowling().getODIs()
									.getMat()
							+ " Tests : "
							+ cricketerStatistic.getData().getBowling().getTests()
									.getMat()
							+ " Batting : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBatting().getT20Is()
									.getMat()
							+ " ODI : "
							+ cricketerStatistic.getData().getBatting().getODIs()
									.getMat() + " Tests : " + cricketerStatistic
							.getData().getBatting().getTests().getMat());
				case St:
					return ("Batting : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBatting().getT20Is()
									.getSt()
							+ " ODI : "
							+ cricketerStatistic.getData().getBatting().getODIs()
									.getSt() + " Tests : " + cricketerStatistic
							.getData().getBatting().getTests().getSt());

				case Ct:
					return ("Batting : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBatting().getT20Is()
									.getCt()
							+ " ODI : "
							+ cricketerStatistic.getData().getBatting().getODIs()
									.getCt() + " Tests : " + cricketerStatistic
							.getData().getBatting().getTests().getCt());

				case HS:
					return ("Batting : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBatting().getT20Is()
									.getHS()
							+ " ODI : "
							+ cricketerStatistic.getData().getBatting().getODIs()
									.getHS() + " Tests : " + cricketerStatistic
							.getData().getBatting().getTests().getHS());
				case NO:
					return ("Batting : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBatting().getT20Is()
									.getNO()
							+ " ODI : "
							+ cricketerStatistic.getData().getBatting().getODIs()
									.getNO() + " Tests : " + cricketerStatistic
							.getData().getBatting().getTests().getNO());

				case BF:
					return cricketerStatistic.getData().getBatting().getTests()
							.getBF();
				case _10:
					return ("Bowling : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBowling().getT20Is()
									.get10()
							+ " ODI : "
							+ cricketerStatistic.getData().getBowling().getODIs()
									.get10() + " Tests : " + cricketerStatistic
							.getData().getBowling().getTests().get10());
				case _5w:
					return ("Bowling : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBowling().getT20Is()
									.get5w()
							+ " ODI : "
							+ cricketerStatistic.getData().getBowling().getODIs()
									.get5w() + " Tests : " + cricketerStatistic
							.getData().getBowling().getTests().get5w());
				case _4w:
					return ("Bowling : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBowling().getT20Is()
									.get4w()
							+ " ODI : "
							+ cricketerStatistic.getData().getBowling().getODIs()
									.get4w() + " Tests : " + cricketerStatistic
							.getData().getBowling().getTests().get4w());
				case _50:
					return ("Batting : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBatting().getT20Is()
									.get50()
							+ " ODI : "
							+ cricketerStatistic.getData().getBatting().getODIs()
									.get50() + " Tests : " + cricketerStatistic
							.getData().getBatting().getTests().get50());
				case _100:
					return ("Batting : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBatting().getT20Is()
									.get100()
							+ " ODI : "
							+ cricketerStatistic.getData().getBatting().getODIs()
									.get100() + " Tests : " + cricketerStatistic
							.getData().getBatting().getTests().get100());
				case _6s:
					return ("Batting : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBatting().getT20Is()
									.get6s()
							+ " ODI : "
							+ cricketerStatistic.getData().getBatting().getODIs()
									.get6s() + " Tests : " + cricketerStatistic
							.getData().getBatting().getTests().get6s());
				case _4s:
					return ("Batting : "
							+ "T20 : "
							+ cricketerStatistic.getData().getBatting().getT20Is()
									.get4s()
							+ " ODI : "
							+ cricketerStatistic.getData().getBatting().getODIs()
									.get4s() + " Tests : " + cricketerStatistic
							.getData().getBatting().getTests().get4s());

				default:
					break;
				}
			} catch (Exception e) {
				LOGGER.error("No enum present as entity type");
			}
		}
		LOGGER.info("Exiting method getResult ---> ");
		return null;
	}
	
	private static String mapValueToEnumValue(String entity) {
		LOGGER.info("Entering method mapValueToEnumValue ---> ");
		if (entity.equalsIgnoreCase("10")) {
			return "_10";
		} else if (entity.equalsIgnoreCase("5w")) {
			return "_5w";
		} else if (entity.equalsIgnoreCase("4w")) {
			return "_4w";
		} else if (entity.equalsIgnoreCase("50")) {
			return "_50";
		} else if (entity.equalsIgnoreCase("100")) {
			return "_100";
		} else if (entity.equalsIgnoreCase("6s")) {
			return "_6s";
		} else if (entity.equalsIgnoreCase("4s")) {
			return "_4s";
		}
		LOGGER.info("Exiting method mapValueToEnumValue ---> ");
		return entity;
	}
	public static String getResult(PersonalInfo personainfo, String entity) {
		entity = mapValueToEnumValue(entity);
		PersonalInfoEnum statisticEnum = PersonalInfoEnum
				.valueOf(entity);
		switch(statisticEnum) {
			case father :
				return personainfo.getFather();
			case mother :
				return personainfo.getMother();
			case wife :
				return personainfo.getWifename();
			case girlfriend :
				return personainfo.getGirlfriend();
			case kids:
				return personainfo.getKids();
			case school:
				return personainfo.getSchool();
			case dob:
				return personainfo.getDob();
			case college :
				return personainfo.getCollege();
			case hobbies :
				return personainfo.getHobbies();
			case studies :
				return personainfo.getStudies();
			case placeOfbirth:
				return personainfo.getPlaceOfbirth();
			default:
				break;
		}
		return null;
	}

}
