package com.mindtree.mib.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.mib.CricketerStatistic;
import com.mindtree.mib.PersonalInfo;
import com.mindtree.mib.PlayerDetails;
import com.mindtree.mib.builder.CricketerStatisticBuilder;
import com.mindtree.mib.config.TestContext;
import com.mindtree.mib.config.WebAppContext;
import com.mindtree.mib.exception.ChatbotCustomGoogleSearchExcetion;
import com.mindtree.mib.exception.ChatbotFetchException;
import com.mindtree.mib.exception.ChatbotNoDataFoundException;
import com.mindtree.mib.handler.ChatHistoryHandler;
import com.mindtree.mib.repository.ChatHistoryRepository;
import com.mindtree.mib.service.CricketerService;
import com.mindtree.mib.service.PersonalInfoService;
import com.mindtree.mib.util.TestUtil;

import org.springframework.http.HttpStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class ChatbotControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private CricketerService cricketerServiceMock;
	
	@Autowired
	private ChatHistoryHandler chatHistoryHandler;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@InjectMocks
	ChatbotController chatbotController;
	
	@Autowired
	ChatHistoryRepository chatHistoryRepository;
	
	@Mock
	RestTemplate restTemplate;
	@Autowired
	private PersonalInfoService personalInfo;

	
	@Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(cricketerServiceMock);
        Mockito.reset(personalInfo);
        Mockito.reset(chatHistoryHandler);
        MockitoAnnotations.initMocks(this);

        //mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc = MockMvcBuilders
                .standaloneSetup(chatbotController)
                .addFilters(new CorsFilter())
                .build();
    }
	@Test
	public void testGetChatResponseForGreeting() throws Exception {
		mockMvc.perform(get("/mib/chatbot/chatresponse").param("pid", "35320")
				.param("question", "hello"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.pid", is("35320")))
		.andExpect(jsonPath("$.result", is("Hello")));;
	}
	@Test
	public void testGetChatResponseForGreetingQuestions() throws Exception {
		mockMvc.perform(get("/mib/chatbot/chatresponse").param("pid", "35320")
				.param("question", "how are you"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.pid", is("35320")))
				.andExpect(jsonPath("$.result", is("I'm doing good, Thank you")));;
	}
	@Test
	public void testGetChatResponseOtherThanGreeting() throws Exception {
		PersonalInfo personlaInfo1 = new PersonalInfo();
		personlaInfo1.setWifename("Anjali");
		Mockito.when(personalInfo.findByPid(Mockito.any())).thenReturn(personlaInfo1);
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any() )).thenReturn("{query: wife name, topScoringIntent: {intent: FamilyInfo,},entities: [{entity: wife name,type: wife}]}");
		mockMvc.perform(get("/mib/chatbot/chatresponse").param("pid", "35320")
				.param("question", "wife name"))
				.andExpect(jsonPath("$.pid", is("35320")))
				.andExpect(jsonPath("$.result", is("Anjali")));;
	}
	@Test
	public void testGetChatResponseForStatics() throws Exception {
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any() )).thenReturn("{query: battingStyle, topScoringIntent: {intent: cricketerStatistic,},entities: [{entity: battingStyle,type: battingStyle}]}");
		CricketerStatistic cricketerStatistic 
		= new CricketerStatisticBuilder().addPid(35320).addImageURL("http://image:8080/im")
		  .addBattingStyle("left-handed").addProfile("Sachine").build();
		Mockito.when(cricketerServiceMock.findByPid(35320)).thenReturn(cricketerStatistic);
		mockMvc.perform(get("/mib/chatbot/chatresponse").param("pid", "35320")
				.param("question", "abc"))
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.pid", is("35320")))
				.andExpect(jsonPath("$.result", is("left-handed")));
				
				Mockito.verify(cricketerServiceMock, Mockito.times(1)).findByPid(35320);
				Mockito.verifyNoMoreInteractions(cricketerServiceMock);
	}
	@Test
	public void testPlayerProfileInfoIntentEntityNull() throws Exception {
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any() )).thenReturn("{query: battingStyle, topScoringIntent: {intent: cricketerStatistic,},entities: [{entity: battingStyle,type: battingStyle}]}");
		CricketerStatistic cricketerStatistic 
		= new CricketerStatisticBuilder().addPid(35320).addImageURL("http://image:8080/im")
		  .addBattingStyle("left-handed").addProfile("Sachine").build();
		
		Mockito.when(cricketerServiceMock.findByPid(35320)).thenReturn(cricketerStatistic);
		
		mockMvc.perform(get("/mib/chatbot/playerresult").param("pid", "35320")
				.param("question", "abc"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.pid", is("35320")));
		Mockito.verify(cricketerServiceMock, Mockito.times(1)).findByPid(35320);
		Mockito.verifyNoMoreInteractions(cricketerServiceMock);
	}
	
	@Test
	public void testPlayerProfileInfoChatbotFetchException() throws Exception {
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any() )).thenReturn("{query: battingStyle, topScoringIntent: {intent: cricketerStatistic,},entities: [{entity: battingStyle,type: battingStyle}]}");
		CricketerStatistic cricketerStatistic 
		= new CricketerStatisticBuilder().addPid(35320).addImageURL("http://image:8080/im")
		  .addBattingStyle("left-handed").addProfile("Sachine").build();
		
		//Mockito.doThrow(ChatbotFetchException.class).when(cricketerServiceMock.findByPid(35320));
		Mockito.when(cricketerServiceMock.findByPid(35320)).thenThrow(new ChatbotFetchException(HttpStatus.INTERNAL_SERVER_ERROR));
		
		mockMvc.perform(get("/mib/chatbot/playerresult").param("pid", "35320")
				.param("question", "abc"));
	}
	
	@Test
	public void testPlayerProfileInfoChatbotCustomGoogleSearchExcetion() throws Exception {
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any() )).thenReturn("{query: battingStyle, topScoringIntent: {intent: cricketerStatistic,},entities: [{entity: battingStyle,type: battingStyle}]}");
		CricketerStatistic cricketerStatistic 
		= new CricketerStatisticBuilder().addPid(35320).addImageURL("http://image:8080/im")
		  .addBattingStyle("left-handed").addProfile("Sachine").build();
		
		//Mockito.doThrow(ChatbotFetchException.class).when(cricketerServiceMock.findByPid(35320));
		Mockito.when(cricketerServiceMock.findByPid(35320)).thenThrow(new ChatbotCustomGoogleSearchExcetion(HttpStatus.INTERNAL_SERVER_ERROR));
		
		mockMvc.perform(get("/mib/chatbot/playerresult").param("pid", "35320")
				.param("question", "abc"));
	}
	
	@Test
	public void testPlayerProfileInfoChatbotNoDataFoundException() throws Exception {
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any() )).thenReturn("{query: battingStyle, topScoringIntent: {intent: cricketerStatistic,},entities: [{entity: battingStyle,type: battingStyle}]}");
		CricketerStatistic cricketerStatistic 
		= new CricketerStatisticBuilder().addPid(35320).addImageURL("http://image:8080/im")
		  .addBattingStyle("left-handed").addProfile("Sachine").build();
		
		//Mockito.doThrow(ChatbotFetchException.class).when(cricketerServiceMock.findByPid(35320));
		Mockito.when(cricketerServiceMock.findByPid(35320)).thenThrow(new ChatbotNoDataFoundException(HttpStatus.INTERNAL_SERVER_ERROR));
		
		mockMvc.perform(get("/mib/chatbot/playerresult").param("pid", "35320")
				.param("question", "abc"));
	}
	
	@Test
	public void testPlayerProfileInfoException() throws Exception {
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any() )).thenReturn("{query: battingStyle, topScoringIntent: {intent: cricketerStatistic,},entities: [{entity: battingStyle,type: battingStyle}]}");
		CricketerStatistic cricketerStatistic 
		= new CricketerStatisticBuilder().addPid(35320).addImageURL("http://image:8080/im")
		  .addBattingStyle("left-handed").addProfile("Sachine").build();
		
		//Mockito.doThrow(ChatbotFetchException.class).when(cricketerServiceMock.findByPid(35320));
		Mockito.when(cricketerServiceMock.findByPid(35320)).thenThrow(new Exception());
		
		mockMvc.perform(get("/mib/chatbot/playerresult").param("pid", "35320")
				.param("question", "abc"));
	}
	
	@Test
	public void testPlayerProfileInfo() throws Exception {
		
		CricketerStatistic cricketerStatistic 
		= new CricketerStatisticBuilder().addPid(35320).addImageURL("http://image:8080/im")
		  .addBattingStyle("left-handed").addProfile("Sachine").build();
		
		Mockito.when(cricketerServiceMock.findByPid(35320)).thenReturn(cricketerStatistic);
		
		mockMvc.perform(get("/mib/chatbot/playerresult").param("pid", "35320")
				.param("entity", "profile").param("intent", "cricketerStatistic")
				.param("question", "abc"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.pid", is("35320")))
		.andExpect(jsonPath("$.result", is("Sachine")));
		
		Mockito.verify(cricketerServiceMock, Mockito.times(1)).findByPid(35320);
		Mockito.verifyNoMoreInteractions(cricketerServiceMock);
	}
	
	@Test
	public void testPlayer100Info() throws Exception {
		
		CricketerStatistic cricketerStatistic 
		= new CricketerStatisticBuilder().addPid(35320).addImageURL("http://image:8080/im")
		  .addBattingStyle("left-handed").add100("95", "220", "50").build();
		
		Mockito.when(cricketerServiceMock.findByPid(35320)).thenReturn(cricketerStatistic);
		
		mockMvc.perform(get("/mib/chatbot/playerresult").param("pid", "35320")
				.param("entity", "100").param("intent", "cricketerStatistic")
				.param("question", "abc"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.pid", is("35320")))
		.andExpect(jsonPath("$.result", is("Batting : T20 : 220 ODI : 95 Tests : 50")));
		
		Mockito.verify(cricketerServiceMock, Mockito.times(1)).findByPid(35320);
		Mockito.verifyNoMoreInteractions(cricketerServiceMock);
        
		
	}
	
	@Test
	public void testPlayerByNameAndPid() throws Exception {
	    List<com.mindtree.mib.PlayerDetails> players =  new ArrayList<com.mindtree.mib.PlayerDetails>();
	    com.mindtree.mib.PlayerDetails playerDetails1 = new PlayerDetails(35320, "Sachine", "http://sachine", "10-Jan-1965", "Batsman");
	    com.mindtree.mib.PlayerDetails playerDetails2 = new PlayerDetails(35321, "Ram", "http://sachine", "10-Jan-1965", "Batsman");
	    players.add(playerDetails1);
	    players.add(playerDetails2);
	    Mockito.when(cricketerServiceMock.fetchPlayerIdAndName()).thenReturn(players);
	    
	    mockMvc.perform(get("/mib/chatbot/fetchPlayerList"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$[0].pid", is(35320)))
		.andExpect(jsonPath("$[0].name", is("Sachine")))
		.andExpect(jsonPath("$[0].imageURL", is("http://sachine")))
	    .andExpect(jsonPath("$[0].born", is("10-Jan-1965")))
	    .andExpect(jsonPath("$[0].playingRole", is("Batsman")))
	    .andExpect(jsonPath("$[1].pid", is(35321)))
	    .andExpect(jsonPath("$[1].name", is("Ram")));
	             
	    Mockito.verify(cricketerServiceMock, Mockito.times(1)).fetchPlayerIdAndName();
	    Mockito.verifyNoMoreInteractions(cricketerServiceMock);
	}
	
	@Test
	public void testNoDatFoundPlayerByNameAndPid() throws Exception {
	    List<com.mindtree.mib.PlayerDetails> players =  new ArrayList<com.mindtree.mib.PlayerDetails>();
	    Mockito.when(cricketerServiceMock.fetchPlayerIdAndName()).thenThrow(new ChatbotNoDataFoundException(HttpStatus.NO_CONTENT));
	    
	    try {
			mockMvc.perform(get("/mib/chatbot/fetchPlayerList"))
			        .andExpect(status().isNoContent());
		} catch (Exception e) {
		}
	             
	             
	    Mockito.verify(cricketerServiceMock, Mockito.times(1)).fetchPlayerIdAndName();
	    Mockito.verifyNoMoreInteractions(cricketerServiceMock);
	}

	@Test
	public void testDownloadChatHistory() {

		Model model = new ExtendedModelMap();
		 
		ChatbotController chatbotController = new ChatbotController(cricketerServiceMock, personalInfo);
		chatbotController.setChatHistoryHandler(chatHistoryHandler);
		
		ModelAndView modelAndView = chatbotController.downloadChatHistory(model);
		
		Assert.assertNotNull(modelAndView);
		chatbotController.getChatHistoryHandler();
	}
	
	/*@Test
	public void getAnswerForNumberOfCenturyByCricketer() {
		final String url = "http://localhost:8080/mib/chatbot/playerresult?pid=35320&entity=100&intent=cricketerStatistic";
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
		HttpEntity requestEntity = new HttpEntity("", headers);
		ResponseEntity<String> result = restTemplate.exchange(url,
				HttpMethod.GET, requestEntity, String.class);
		assertNotNull(result);
		assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void getAnswerForNumberOfMatchesPlayedByCricketer() {
		final String url = "http://localhost:8080/mib/chatbot/playerresult?pid=35320&entity=Mat&intent=cricketerStatistic";
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
		HttpEntity requestEntity = new HttpEntity("", headers);
		ResponseEntity<String> result = restTemplate.exchange(url,
				HttpMethod.GET, requestEntity, String.class);
		assertNotNull(result);
		assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void getSutaibleReferenceLinkWhenAnswerNotAvailableInRecord() {
		final String url = "http://localhost:8080/mib/chatbot/playerresult?pid=35320&entity=unknown&intent=cricketerStatistic&question";
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
		HttpEntity requestEntity = new HttpEntity("", headers);
		ResponseEntity<String> result = restTemplate.exchange(url,
				HttpMethod.GET, requestEntity, String.class);
		assertNotNull(result);
		assertEquals(200, result.getStatusCodeValue());
	}*/

}
