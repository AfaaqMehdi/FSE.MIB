package com.mindtree.mib.playerstats.summaryinfo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.mindtree.mib.playerstats.detailinfo.PlayerSummaryInfoImpl;
import com.mindtree.mib.playerstats.detailinfo.dao.CricketerStatisticRepository;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayerFetchException;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayersNoDataFoundException;
import com.mindtree.mib.playerstats.dto.PlayerDetails;

@RunWith(MockitoJUnitRunner.class) 
public class PlayerSummaryImplTest {


	PlayerSummaryInfoImpl playerSummaryInfoImpl;
	
	@Before
	public void initialize() {
		playerSummaryInfoImpl = new PlayerSummaryInfoImpl();
	}
 	@Test
	public void testFetchPlayerList() {
		CricketerStatisticRepository repository = mock(CricketerStatisticRepository.class);
		List<PlayerDetails> players = Arrays.asList(
				new PlayerDetails(1, "Sachin", "img", "23 nov", "right hand batsman", "30"),
				new PlayerDetails(2, "Virat", "img", "23 nov", "right hand batsman", "35"));
		playerSummaryInfoImpl.setRepository(repository);
		Mockito.doReturn(players).when(repository).fetchAllPlayers();
		List<PlayerDetails> playerList = playerSummaryInfoImpl.fetchAllPlayers();
		assertNotNull(playerList);
		assertEquals(playerList.size(), 2);
		assertEquals(players, playerList);
	}
	@Test
	public void testFetchPlayerListNoData() {
		CricketerStatisticRepository repository = mock(CricketerStatisticRepository.class);
		List<PlayerDetails> players = null;
		playerSummaryInfoImpl.setRepository(repository);
		Mockito.doReturn(players).when(repository).fetchAllPlayers();
		try {
			playerSummaryInfoImpl.fetchAllPlayers();
		} catch (PlayersNoDataFoundException e) {
			assertTrue(true);
		}
	}
	@Test
	public void testFetchPlayerListEmptyData() {
		CricketerStatisticRepository repository = mock(CricketerStatisticRepository.class);
		List<PlayerDetails> players = new ArrayList<PlayerDetails>();
		playerSummaryInfoImpl.setRepository(repository);
		Mockito.doReturn(players).when(repository).fetchAllPlayers();
		try {
			playerSummaryInfoImpl.fetchAllPlayers();
		} catch (PlayersNoDataFoundException e) {
			assertTrue(true);
		}
	}
	@Test
	public void testFetchPlayerListDBException() {
	 	CricketerStatisticRepository repository = mock(CricketerStatisticRepository.class);
		 playerSummaryInfoImpl.setRepository(repository);
		 when(repository.fetchAllPlayers()).thenThrow( new PlayerFetchException(HttpStatus.INTERNAL_SERVER_ERROR));
	 	try {
	      playerSummaryInfoImpl.fetchAllPlayers();
	 	 }
	 	 catch(PlayerFetchException e) {
	 		assertTrue(true);
	 	}
	}
}
