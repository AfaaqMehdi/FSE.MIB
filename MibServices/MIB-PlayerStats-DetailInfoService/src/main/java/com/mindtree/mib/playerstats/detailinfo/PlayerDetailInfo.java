package com.mindtree.mib.playerstats.detailinfo;

import com.mindtree.mib.playerstats.detailinfo.exception.PlayerFetchException;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayersNoDataFoundException;
import com.mindtree.mib.playerstats.dto.CricketerStatistic;


public interface PlayerDetailInfo {

	public CricketerStatistic fetchPlayerInfo(final int playerID) throws PlayerFetchException, PlayersNoDataFoundException;
	
}
