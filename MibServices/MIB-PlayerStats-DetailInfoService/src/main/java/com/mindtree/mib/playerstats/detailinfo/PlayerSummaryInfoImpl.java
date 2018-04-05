package com.mindtree.mib.playerstats.detailinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import com.mindtree.mib.playerstats.detailinfo.dao.CricketerStatisticRepository;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayerFetchException;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayersNoDataFoundException;
import com.mindtree.mib.playerstats.dto.PlayerDetails;


@Configuration
@EnableAutoConfiguration
@ComponentScan
public class PlayerSummaryInfoImpl  implements PlayerSummaryInfo{

	@Autowired
	private CricketerStatisticRepository repository;
	

	@Override
	public List<PlayerDetails> fetchAllPlayers() throws PlayerFetchException {
		List<PlayerDetails> playerDetails = null;
		try {

			playerDetails = repository.fetchAllPlayers();

		} catch (Exception ex) {
			throw new PlayerFetchException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (playerDetails == null || playerDetails.isEmpty()) {
			throw new PlayersNoDataFoundException(HttpStatus.NO_CONTENT);
		}

		return playerDetails;
	}
	public CricketerStatisticRepository getRepository() {
		return repository;
	}

	public void setRepository(CricketerStatisticRepository repository) {
		this.repository = repository;
	}

}
