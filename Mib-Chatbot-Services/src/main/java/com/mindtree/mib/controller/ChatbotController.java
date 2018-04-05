/**
 * 
 */
package com.mindtree.mib.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
import com.mindtree.mib.CricketerStatistic;
import com.mindtree.mib.PersonalInfo;
import com.mindtree.mib.PlayerDetails;
import com.mindtree.mib.common.ChatbotUtil;
import com.mindtree.mib.exception.ChatbotCustomGoogleSearchExcetion;
import com.mindtree.mib.exception.ChatbotFetchException;
import com.mindtree.mib.exception.ChatbotNoDataFoundException;
import com.mindtree.mib.handler.ChatHistoryHandler;
import com.mindtree.mib.model.ChatHistory;
import com.mindtree.mib.model.CricketerResponse;
import com.mindtree.mib.service.CricketerService;
import com.mindtree.mib.service.PersonalInfoService;

/**
 * @author M1038389
 * 
 */

@RestController
@RequestMapping("/mib/chatbot")
public class ChatbotController {
	
	public static final String YES = "Yes";
	
	public static final String NO = "No";
	
	private static final Logger LOGGER = Logger.getLogger(ChatbotController.class);

	final private String API_KEY = "AIzaSyD8pPAEzBdAih9WSpEiARqNVWEP9Ar1waU";
	final private String SEARCH_ENGINE_ID = "013036536707430787589:_pqjad5hr1a";
	//
    final private String uri = "https://westus.api.cognitive.microsoft.com/luis/v2.0/apps/5d9e50e9-ef49-4ed6-8b45-bd37e70fe9fa?subscription-key=25e264b3bf9c44f884245432a10033bc&verbose=true&timezoneOffset=0&q=";

	private final CricketerService cricketerService;
	
	private final PersonalInfoService personalInfo;
	
	@Autowired
	private ChatHistoryHandler chatHistoryHandler;
	
	private String[] greetings = new String[] {"hi" , "hello" , "Whats up" , "Good morning" , "Good after noon","good evening" , "Hi" , "Hello" ,"good morning" , "good after noon" , "Good evening"};
	
	
	private String[] greetingsQuestions = new String[] {"how do you do" , "how are you" , "how you feeling today" , "how do you do?",
            "how are you?" , "how you feeling today?" , "How are you?" , "How are you" , "How you feeling today?" , "How you feeling today"};


	@Autowired
	public ChatbotController(CricketerService cricketerService , PersonalInfoService personalInfo) {
		this.cricketerService = cricketerService;
		this.personalInfo = personalInfo;
	}
	
	RestTemplate restTemplate = new RestTemplate();
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value="/chatresponse")
	public ResponseEntity<CricketerResponse> getChatResponse(@RequestParam(value="pid") String pid,
			@RequestParam(value="question", defaultValue = "unknown") String question) {
		CricketerResponse response = new CricketerResponse();
		response.setPid(pid);
		
		if (Arrays.asList(greetings).contains(question)) {
			final String answer = "Hello";
			response.setResult(answer);
			chatHistoryHandler.storeChatInfo(pid, question, answer, YES, NO);
			return new ResponseEntity<CricketerResponse>(response, HttpStatus.OK);
		}
		else if (Arrays.asList(greetingsQuestions).contains(question)) {
			final String answer = "I'm doing good, Thank you";
			response.setResult(answer);
			chatHistoryHandler.storeChatInfo(pid, question, answer, YES, NO);
			return new ResponseEntity<CricketerResponse>(response, HttpStatus.OK);
		}
		else {
			StringBuilder builder = new StringBuilder();
			builder.append(uri).append(question);
			String resultFromLuis = restTemplate.getForObject(builder.toString(), String.class);
			JSONObject object = new JSONObject(resultFromLuis);
			
			String intent = (String) object.getJSONObject("topScoringIntent").get("intent");
			String entity = identifyEntity(object);
			if (entity == null) {
				intent = null;
			}
			LOGGER.info("Pid --> " + pid + "Intent --> " + intent + " Entity ---> " + entity);
			return getPlayerInfo(pid, entity, intent, question);
		}
		
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value="/downloadChatHistory")
	public ModelAndView downloadChatHistory(Model model) {
		LOGGER.info("Entering method downloadChatHistory ");
	    final List<ChatHistory> chatHistoryList = chatHistoryHandler.retrieveChatHistory();
		model.addAttribute("chatHistory", chatHistoryList);
	    LOGGER.info("Exit method downloadChatHistory ");
	    return new ModelAndView("excelView", "chatHistory", chatHistoryList);
	   // return "";
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/playerresult")
	public ResponseEntity<CricketerResponse> getPlayerInfo(
			@RequestParam(value = "pid") String pid,
			@RequestParam(value = "entity", defaultValue = "unknown") String entity,
			@RequestParam(value = "intent", defaultValue = "unknown", required = false) String intent,
			@RequestParam(value = "question", defaultValue = "unknown", required = false) String question) {
		LOGGER.info("Entering method getPlayerInfo ---> ");
		CricketerResponse response = new CricketerResponse();
		CricketerStatistic cricketerStatistic = null;
		String cricketerName = null;
		if (pid != null) {
			// query into cricketerStatistic collections
			try {
				response.setPid(pid);
				cricketerStatistic = cricketerService
						.findByPid(Integer.parseInt(pid));
				if (cricketerStatistic != null) {
					cricketerName =  cricketerStatistic.getName();
				}
				if (intent != null && intent.equalsIgnoreCase("FamilyInfo")) {
					PersonalInfo personalinfo = personalInfo.findByPid(Integer.parseInt(pid));
					if (personalinfo != null) {
						String answer = ChatbotUtil.getResult(personalinfo, entity);
						response.setResult(answer);
						chatHistoryHandler.storeChatInfo(pid, question, answer, YES, NO);
					}
				}
				if (intent != null && intent.equalsIgnoreCase("cricketerStatistic")) {
					String answer = ChatbotUtil.getResult(cricketerStatistic, entity);
					if (answer != null) {
						response.setResult(answer);
						chatHistoryHandler.storeChatInfo(pid, question, answer, YES, NO);
					} 
				}
				if (response.getResult() == null) {
					// google custom search for answer
					customGoogleSearchIfNoResult(pid, question, response,
							cricketerName);
				}
			}
			catch (ChatbotFetchException ex) {
				LOGGER.error("Error while connecting to database");
				response.setExceptionType("ChatbotFetchException");
				response.setHttpStatus("NO_CONTENT");
				chatHistoryHandler.storeChatInfo(pid, question, "ChatbotFetchException", YES, NO);
				return new ResponseEntity<CricketerResponse>(response, HttpStatus.NO_CONTENT);
			}
			catch (ChatbotCustomGoogleSearchExcetion ex) {
				LOGGER.error("Error while trying to get get answer from google");
				response.setExceptionType("ChatbotCustomGoogleSearchExcetion");
				response.setHttpStatus("NOT_FOUND");
				chatHistoryHandler.storeChatInfo(pid, question, "ChatbotCustomGoogleSearchExcetion", YES, NO);
				return new ResponseEntity<CricketerResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			catch (ChatbotNoDataFoundException ex) {
				LOGGER.error("No answer available for question");
				try {
					if (cricketerStatistic != null) {
						customGoogleSearchIfNoResult(pid, question, response, cricketerStatistic.getName());
					}
				} 
				catch (Exception e) {
					LOGGER.error("Error while trying to get get answer from google");
					response.setExceptionType("ChatbotCustomGoogleSearchExcetion");
					response.setHttpStatus("NOT_FOUND");
					chatHistoryHandler.storeChatInfo(pid, question, "ChatbotCustomGoogleSearchExcetion", YES, YES);
					return new ResponseEntity<CricketerResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			catch (Exception ex) {
				LOGGER.error("Error while trying to get details from service");
				response.setExceptionType("Exception");
				response.setHttpStatus("NOT_FOUND");
				chatHistoryHandler.storeChatInfo(pid, question, "ChatbotCustomGoogleSearchExcetion", YES, NO);
				return new ResponseEntity<CricketerResponse>(response, HttpStatus.NOT_FOUND);
			}
		}
		LOGGER.info("Exiting method getPlayerInfo ---> ");
		chatHistoryHandler.storeChatInfo(pid, question, response.getResult(), YES, YES);
		return new ResponseEntity<CricketerResponse>(response, HttpStatus.OK);
	}

	private void customGoogleSearchIfNoResult(String pid, String question,
			CricketerResponse response, String name) throws Exception {
		LOGGER.info("Entering method customGoogleSearchIfNoResult ---> ");
		boolean resultFound = false;
		int lastIndexOf = question.lastIndexOf(" ");
		String pattern = question.substring(lastIndexOf + 1);
		LOGGER.info("Question --> " + question + " Pattern ---> " + pattern);
		List<Result> resultList = getSearchResult(name + " "
				+ question);
		if (resultList != null && resultList.size() > 0) {
			for (Result result : resultList) {
				if (((result.getSnippet() != null && result.getSnippet().contains(pattern)) 
					|| (result.getTitle() != null && result.getTitle().contains(pattern)) 
					|| (result.getDisplayLink() != null && result.getDisplayLink().contains(pattern))
					|| (result.getFormattedUrl() != null && result.getFormattedUrl().contains(pattern))
					|| (result.getHtmlFormattedUrl() != null && result.getHtmlFormattedUrl().contains(pattern))
					|| (result.getHtmlSnippet() != null && result.getHtmlSnippet().contains(pattern))
					|| (result.getHtmlTitle() != null && result.getHtmlTitle().contains(pattern))
					|| (result.getLink() != null && result.getLink().contains(pattern)))) {
					if(urlIsAccessable(result.getLink())) {
						LOGGER.info("Url is accesible");
						response.setResult(result.getTitle());
						chatHistoryHandler.storeChatInfo(pid, question, result.getTitle(), YES, YES);
						response.setUrl(result.getLink());
						resultFound = true;
						break;
					}
				}
			}
		} else {
			response.setResult("Rephrase your question");
			chatHistoryHandler.storeChatInfo(pid, question, "Rephrase your question", NO, YES);
			resultFound = true;
		}
		if(!resultFound) {
			response.setResult("Rephrase your question");
			chatHistoryHandler.storeChatInfo(pid, question, "Rephrase your question", NO, YES);
		}
		LOGGER.info("Exiting method customGoogleSearchIfNoResult ---> ");
	}

	@CrossOrigin
	@RequestMapping("/fetchPlayerList")
	public List<PlayerDetails> fetchPlayerList() throws Exception {
		LOGGER.info("Entering method fetchPlayerList ---> ");
		List<PlayerDetails> players = null;
		try {
			players = cricketerService.fetchPlayerIdAndName();
		} 
		catch (ChatbotFetchException ex) {
			throw new HttpServerErrorException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					"Details can't be fetched at the moment");
		}
		catch (ChatbotNoDataFoundException ex) {
			throw new HttpServerErrorException(
					HttpStatus.NO_CONTENT,
					"No data found at this moment");
		}

		LOGGER.info("Exiting method fetchPlayerList ---> ");
		return players;
	}

	private boolean urlIsAccessable(String url) throws Exception {
		LOGGER.info("Entering method urlIsAccessable ---> ");
		try {
			url = url.replaceFirst("https", "http");
			LOGGER.info("Url ---> " + url);
			HttpURLConnection connection = (HttpURLConnection) new URL(url)
					.openConnection();
			connection.setConnectTimeout(3000);
			connection.setReadTimeout(3000);
			connection.setRequestMethod("HEAD");
			if (connection.getResponseCode() != 200) {
				LOGGER.info("Exiting method fetchPlayerList ---> ");
				return false;
			}
		} catch (MalformedURLException e) {
			throw new ChatbotCustomGoogleSearchExcetion(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			throw new ChatbotCustomGoogleSearchExcetion(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Exiting method urlIsAccessable ---> ");
		return true;
	}

	public List<Result> getSearchResult(String keyword) {
		LOGGER.info("Entering method getSearchResult ---> ");
		// Set up the HTTP transport and JSON factory
		HttpTransport httpTransport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();
		Customsearch customsearch = new Customsearch(httpTransport,
				jsonFactory, null);

		List<Result> resultList = null;
		try {
			Customsearch.Cse.List list = customsearch.cse().list(keyword);
			list.setKey(API_KEY);
			list.setCx(SEARCH_ENGINE_ID);

			// for pagination
			list.setStart(10L);
			Search results = list.execute();
			resultList = results.getItems();

		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("Exiting method getSearchResult ---> ");
		return resultList;
	}
	
	private String identifyEntity(JSONObject infoForEntities) {
		
		JSONArray jsonArray = (JSONArray) infoForEntities.getJSONArray("entities");
		for (int i = 0, size = jsonArray.length(); i < size; i++)
	    {
	      JSONObject objectInArray = jsonArray.getJSONObject(i);
	      return (String) objectInArray.get("type");
	    }
		return null;
	}

	public ChatHistoryHandler getChatHistoryHandler() {
		return chatHistoryHandler;
	}

	public void setChatHistoryHandler(ChatHistoryHandler chatHistoryHandler) {
		this.chatHistoryHandler = chatHistoryHandler;
	}
	
}
