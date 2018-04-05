package com.mindtree.mib.playerstats.detailinfo;

import java.util.List;

import com.mindtree.mib.playerstats.dto.PlayerDetails;

public interface PlayerSummaryInfo {
	public List<PlayerDetails> fetchAllPlayers();
}
