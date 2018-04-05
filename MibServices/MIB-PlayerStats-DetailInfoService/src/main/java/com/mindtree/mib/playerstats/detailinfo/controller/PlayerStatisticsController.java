package com.mindtree.mib.playerstats.detailinfo.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.mib.playerstats.detailinfo.PlayerDetailInfo;
import com.mindtree.mib.playerstats.detailinfo.PlayerSummaryInfo;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayerFetchException;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayersNoDataFoundException;
import com.mindtree.mib.playerstats.dto.CricketerStatistic;
import com.mindtree.mib.playerstats.dto.PlayerDetails;

@RestController
public class PlayerStatisticsController {

	private static final Logger LOGGER = Logger.getLogger(PlayerStatisticsController.class);

	@Autowired
	private PlayerDetailInfo playerDetailInfo;
	
	@Autowired
	private PlayerSummaryInfo playerSummaryInfo;

	@CrossOrigin
	@RequestMapping(value = "/playerStatistics/playerInfo/{pid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CricketerStatistic> fetchPlayerInfo(@PathVariable("pid") int pid) {
		LOGGER.info("Entered PlayerStatisticsController :: fetchPlayerInfo ");

		CricketerStatistic playerInfo = null;
		try {
			playerInfo = playerDetailInfo.fetchPlayerInfo(pid);

		} catch (PlayerFetchException ex) {
			return new ResponseEntity<CricketerStatistic>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (PlayersNoDataFoundException ex) {
			return new ResponseEntity<CricketerStatistic>(HttpStatus.NO_CONTENT);
		}
		LOGGER.info("Exit PlayerStatisticsController :: fetchPlayerInfo ");
		return new ResponseEntity<CricketerStatistic>(playerInfo, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping("/playerStatistics/playerList")
	public ResponseEntity<List<PlayerDetails>> fetchPlayerList() {
		LOGGER.info("Entered PlayerStatisticsController :: fetchPlayerList ");
	    List<PlayerDetails> players = null;
		try {
			players = playerSummaryInfo.fetchAllPlayers();
		} catch (PlayerFetchException ex) {
			return new ResponseEntity<List<PlayerDetails>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (PlayersNoDataFoundException ex) {
			return new ResponseEntity<List<PlayerDetails>>(HttpStatus.NO_CONTENT);
		}
     	LOGGER.info("Entered PlayerStatisticsController :: fetchPlayerList ");
		return new ResponseEntity<List<PlayerDetails>>(players, HttpStatus.OK);
	}

	public PlayerDetailInfo getPlayerDetailInfo() {
		return playerDetailInfo;
	}

	public void setPlayerDetailInfo(PlayerDetailInfo playerDetailInfo) {
		this.playerDetailInfo = playerDetailInfo;
	}

}
