package com.mindtree.mib.playerstats.detailinfo;

import static org.mockito.Mockito.mock;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mindtree.mib.playerstats.detailinfo.controller.PlayerStatisticsController;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayerFetchException;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayersNoDataFoundException;
import com.mindtree.mib.playerstats.dto.CricketerStatistic;

@SuppressWarnings("deprecation")
public class PlayerStatisticsControllerTest {

	PlayerStatisticsController playerDetailInfoController;

	@Before
	public void initialize() {
		playerDetailInfoController = new PlayerStatisticsController();
	}

	@Test
	public void testFetchPlayerInfo() {

		PlayerDetailInfoImpl playerDetailInfo = mock(PlayerDetailInfoImpl.class);
		playerDetailInfoController.setPlayerDetailInfo(playerDetailInfo);
		CricketerStatistic mockStats = new CricketerStatistic();
		mockStats.setPid(35320);

		Mockito.doReturn(mockStats).when(playerDetailInfo).fetchPlayerInfo(Mockito.anyInt());

		ResponseEntity<CricketerStatistic> playerInfo = playerDetailInfoController.fetchPlayerInfo(35320);

		Assert.assertNotNull(playerInfo);
		Assert.assertEquals(new Integer(35320), playerInfo.getBody().getPid());
		Assert.assertEquals(HttpStatus.OK, playerInfo.getStatusCode());

	}

	@Test
	public void testFetchPlayerInfo_PlayerFetchException() {

		PlayerDetailInfoImpl playerDetailInfo = mock(PlayerDetailInfoImpl.class);
		playerDetailInfoController.setPlayerDetailInfo(playerDetailInfo);
		Mockito.doThrow(new PlayerFetchException(HttpStatus.INTERNAL_SERVER_ERROR)).when(playerDetailInfo).fetchPlayerInfo(Mockito.anyInt());

		ResponseEntity<CricketerStatistic> response = playerDetailInfoController.fetchPlayerInfo(35320);

		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		Assert.assertEquals(500, response.getStatusCode().value());
	}
	
	@Test
	public void testFetchPlayerInfo_PlayersNoDataFoundException() {

		PlayerDetailInfoImpl playerDetailInfo = mock(PlayerDetailInfoImpl.class);
		playerDetailInfoController.setPlayerDetailInfo(playerDetailInfo);
		Mockito.doThrow(new PlayersNoDataFoundException(HttpStatus.NO_CONTENT)).when(playerDetailInfo).fetchPlayerInfo(Mockito.anyInt());

		ResponseEntity<CricketerStatistic> response = playerDetailInfoController.fetchPlayerInfo(35320);

		Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		Assert.assertEquals(204, response.getStatusCode().value());
	}

}
