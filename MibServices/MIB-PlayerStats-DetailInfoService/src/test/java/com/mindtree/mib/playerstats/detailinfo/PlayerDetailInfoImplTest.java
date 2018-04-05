package com.mindtree.mib.playerstats.detailinfo;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import com.mindtree.mib.playerstats.detailinfo.dao.CricketerStatisticRepository;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayerFetchException;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayersNoDataFoundException;
import com.mindtree.mib.playerstats.dto.CricketerStatistic;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class PlayerDetailInfoImplTest {

	PlayerDetailInfoImpl playerDetailInfoImpl;
	
	@Before
	public void initialize() {
		playerDetailInfoImpl = new PlayerDetailInfoImpl();
	}
	
	@Test
	public void testFetchPlayerInfo() {
		
		CricketerStatisticRepository repository = mock(CricketerStatisticRepository.class);
		playerDetailInfoImpl.setRepository(repository);
		CricketerStatistic mockStats = new CricketerStatistic(); 
		mockStats.setPid(35320);
		Mockito.doReturn(mockStats).when(repository).findByPid(Mockito.anyInt());
		
		CricketerStatistic playerInfo = playerDetailInfoImpl.fetchPlayerInfo(35320);
		
		Assert.assertNotNull(playerInfo);
		Assert.assertEquals(new Integer(35320), playerInfo.getPid());
	}
	
	@Test
	public void testFetchPlayerInfo_InvalidPlayerID() {
		CricketerStatistic playerInfo = null;
		try {
		CricketerStatisticRepository repository = mock(CricketerStatisticRepository.class);
		playerDetailInfoImpl.setRepository(repository);
		Mockito.doReturn(null).when(repository).findByPid(Mockito.anyInt());
		
		playerInfo = playerDetailInfoImpl.fetchPlayerInfo(35320);
		} catch(PlayerFetchException ex) {
		Assert.fail();
	}
	catch(PlayersNoDataFoundException ex) {
		Assert.assertEquals("204 NO_CONTENT", ex.getMessage());
	}
	}
	
/*	@Test
	public void testFetchPlayerInfo_DBExceptionThrown() {
		
		CricketerStatisticRepository repository = mock(CricketerStatisticRepository.class);
		playerDetailInfoImpl.setRepository(repository);
		Mockito.doThrow(new Exception()).when(repository).findByPid(Mockito.anyInt());
		
		CricketerStatistic playerInfo = playerDetailInfoImpl.fetchPlayerInfo(35320);
		
		Assert.assertNull(playerInfo);
	}*/

	@Test
	public void testFetchPlayerListDBException() {
	 	CricketerStatisticRepository repository = mock(CricketerStatisticRepository.class);
		 playerSummaryInfoImpl.setRepository(repository);
		 Mockito.doThrow(new Exception()).when(repository).findByPid(Mockito.anyInt());
	 	try {
	      playerSummaryInfoImpl.fetchAllPlayers();
	 	 }
	 	 catch(PlayerFetchException e) {
	 		assertTrue(true);
	 	}
	}
}
