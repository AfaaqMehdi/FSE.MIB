package com.mindtree.mib.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mindtree.mib.CricketerStatistic;
import com.mindtree.mib.builder.CricketerStatisticBuilder;
import com.mindtree.mib.config.TestContext;
import com.mindtree.mib.config.WebAppContext;
import com.mindtree.mib.service.CricketerService;
import com.mindtree.mib.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class ChatbotControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private CricketerService cricketerServiceMock;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(cricketerServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
	
	@Test
	public void getPlayerInfo() throws Exception {
		
		CricketerStatistic cricketerStatistic 
		= new CricketerStatisticBuilder().addPid(35320).addImageURL("http://image:8080/im")
		  .addBattingStyle("left-handed").addProfile("Sachine").build();
		
		Mockito.when(cricketerServiceMock.findByPid(35320)).thenReturn(cricketerStatistic);
		
		this.mockMvc.perform(get("/mib/chatbot/playerresult").param("pid", "35320")
				.param("entity", "profile").param("intent", "cricketerStatistic")
				.param("question", "abc"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.pid", is(35320)))
		.andExpect(jsonPath("$.result", is("Sachine")));
		
		Mockito.verify(cricketerServiceMock, Mockito.times(1)).findByPid(35320);
		Mockito.verifyNoMoreInteractions(cricketerServiceMock);
        
		
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
