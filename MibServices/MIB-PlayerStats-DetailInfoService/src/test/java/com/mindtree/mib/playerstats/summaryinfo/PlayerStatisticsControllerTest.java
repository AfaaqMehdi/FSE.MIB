package com.mindtree.mib.playerstats.summaryinfo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mindtree.mib.playerstats.detailinfo.PlayerSummaryInfoImpl;
import com.mindtree.mib.playerstats.detailinfo.controller.PlayerStatisticsController;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayerFetchException;
import com.mindtree.mib.playerstats.detailinfo.exception.PlayersNoDataFoundException;
import com.mindtree.mib.playerstats.dto.PlayerDetails;

public class PlayerStatisticsControllerTest {

	 private MockMvc mockMvc;

	    @Mock
	    private PlayerSummaryInfoImpl playerSummaryInfo ;

	    @InjectMocks
	    private PlayerStatisticsController playerController;

	    @Before
	    public void init(){
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders
	                .standaloneSetup(playerController)
	                .addFilters(new CorsFilter())
	                .build();
	    }
	
	@Test
	public void testFetchPlayerByNameAndPid() throws Exception {
		List<PlayerDetails> players = Arrays.asList(
				new PlayerDetails(1, "Sachin", "img", "23 nov", "right hand batsman", "30"),
				new PlayerDetails(2, "Virat", "img", "23 nov", "right hand batsman", "35"));
		when(playerSummaryInfo.fetchAllPlayers()).thenReturn(players);
		mockMvc.perform(get("/playerStatistics/playerList")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].pid", is(1)))
				.andExpect(jsonPath("$[0].name", is("Sachin"))).andExpect(jsonPath("$[1].pid", is(2)))
				.andExpect(jsonPath("$[1].name", is("Virat")));
		verify(playerSummaryInfo, times(1)).fetchAllPlayers();
		verifyNoMoreInteractions(playerSummaryInfo);
	}

	@Test
	public void testNoDataFoundPlayerByNameAndPid() throws Exception {
		when(playerSummaryInfo.fetchAllPlayers())
				.thenThrow(new PlayersNoDataFoundException(HttpStatus.INTERNAL_SERVER_ERROR));
		mockMvc.perform(get("/playerStatistics/playerList")).andExpect(status().isNoContent());
		verify(playerSummaryInfo, times(1)).fetchAllPlayers();
		verifyNoMoreInteractions(playerSummaryInfo);
	}

    @Test
	public void testFetchPlayerInfo_PlayerFetchException() throws Exception {
		when(playerSummaryInfo.fetchAllPlayers())
				.thenThrow(new PlayerFetchException(HttpStatus.INTERNAL_SERVER_ERROR));
		mockMvc.perform(get("/playerStatistics/playerList")).andExpect(status().isInternalServerError());
		verify(playerSummaryInfo, times(1)).fetchAllPlayers();
		verifyNoMoreInteractions(playerSummaryInfo);
	}
	
}
