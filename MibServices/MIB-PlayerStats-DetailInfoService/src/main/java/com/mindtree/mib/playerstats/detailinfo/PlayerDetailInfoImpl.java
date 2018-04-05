package com.mindtree.mib.playerstats.detailinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import com.mindtree.mib.playerstats.detailinfo.dao.CricketerStatisticRepository;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayerFetchException;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayersNoDataFoundException;
import com.mindtree.mib.playerstats.dto.CricketerStatistic;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class PlayerDetailInfoImpl implements PlayerDetailInfo {

	@Autowired
	private CricketerStatisticRepository repository;
	
	public CricketerStatistic fetchPlayerInfo(final int playerID) throws PlayerFetchException, PlayersNoDataFoundException {
		CricketerStatistic playerInfo = null;
			try {
			playerInfo = repository.findByPid(playerID);
			
			} catch (Exception ex) {
				throw new PlayerFetchException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			if(playerInfo == null) {
				throw new PlayersNoDataFoundException(HttpStatus.NO_CONTENT);
			}
		return playerInfo;
	}
	public CricketerStatisticRepository getRepository() {
		return repository;
	}

	public void setRepository(CricketerStatisticRepository repository) {
		this.repository = repository;
	}	
}
