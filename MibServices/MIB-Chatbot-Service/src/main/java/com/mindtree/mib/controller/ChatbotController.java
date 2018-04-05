/**
 * 
 */
package com.mindtree.mib.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
import com.mindtree.mib.CricketerStatistic;
import com.mindtree.mib.PlayerDetails;
import com.mindtree.mib.common.ChatbotUtil;
import com.mindtree.mib.model.CricketerResponse;
import com.mindtree.mib.service.CricketerService;

/**
 * @author M1038389
 *
 */

@RestController
@RequestMapping("/mib/chatbot")
public class ChatbotController {
	
	
	//api key
    final private String API_KEY = "AIzaSyAAOvSy2rjJ5ItP0_eCXMqyYX20-ixrj90";
    //custom search engine ID
    final private String SEARCH_ENGINE_ID = "013036536707430787589:_pqjad5hr1a";
    
	private final CricketerService cricketerService;
	
	@Autowired
	public ChatbotController(CricketerService cricketerService) {
		this.cricketerService = cricketerService;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/playerresult")
	public CricketerResponse getPlayerInfo(@RequestParam(value="pid") String pid,
			@RequestParam(value="entity", defaultValue = "unknown") String entity,
			@RequestParam(value="intent", defaultValue = "unknown") String intent,
			@RequestParam(value="question", defaultValue = "unknown") String question) {
		CricketerResponse response = new CricketerResponse();
		if(pid != null) {
			//query into cricketerStatistic collections
			if (intent.equalsIgnoreCase("cricketerStatistic")) {
				CricketerStatistic cricketerStatistic = cricketerService.findByPid(Integer.parseInt(pid));
				
				System.out.println("Customer found with findByPid(35320):");
				System.out.println("--------------------------------");
				System.out.println(cricketerStatistic);
				
				response.setPid(cricketerStatistic.getPid());
				String answer = getOtherEntityResults(cricketerStatistic, entity);
				if(answer == null) {
					answer = ChatbotUtil.getResult(cricketerStatistic, entity);
				}
				if(answer != null) {
					response.setResult(answer);
				}
				else {
					//google custom search for answer
					System.out.println("Question : " + cricketerStatistic.getName()+question);
					List<Result> resultList = getSearchResult(cricketerStatistic.getName()+question);
					if(resultList != null && resultList.size() > 0){
			               for(Result result: resultList){
			            	   if(urlIsAccessable(result.getLink())) {
			            		   System.out.println(result.getLink());
				                   response.setUrl(result.getLink());
				                   break;
			            	   }
			               }
			        }
					else {
						response.setResult("Rephrase your question");
					}
				}
				
			}
			
		}
		return response;
		
	}
	
	@CrossOrigin
	@RequestMapping("/fetchPlayerList")
	public List<PlayerDetails> fetchPlayerList() {
		List<PlayerDetails> players= null;
		try {
		  players =	cricketerService.fetchPlayerIdAndName();
		}
		catch(Exception ex) {
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,"Details can't be fetched at the moment");
		}
		 
		if(players == null || players.isEmpty()) {
			//throw new PlayersNoDataFoundException(HttpStatus.NO_CONTENT);
		}
		 
		 
		return players;
	}

	private String getOtherEntityResults(final CricketerStatistic cricketerStatistic,
			final String entity) {
		if(cricketerStatistic != null && entity != null) {
			if(entity.equalsIgnoreCase("10")) {
				return ("Bowling : "  + "T20 : " + cricketerStatistic.getData().getBowling().getT20Is().get10() + 
						" ODI : " + cricketerStatistic.getData().getBowling().getODIs().get10() + 
						" Tests : " + cricketerStatistic.getData().getBowling().getTests().get10()); 
			}
			else if(entity.equalsIgnoreCase("5w")) {
				return ("Bowling : "  + "T20 : " + cricketerStatistic.getData().getBowling().getT20Is().get5w() + 
						" ODI : " + cricketerStatistic.getData().getBowling().getODIs().get5w() + 
						" Tests : " + cricketerStatistic.getData().getBowling().getTests().get5w()); 
			}
			else if(entity.equalsIgnoreCase("4w")) {
				return ("Bowling : "  + "T20 : " + cricketerStatistic.getData().getBowling().getT20Is().get4w() + 
						" ODI : " + cricketerStatistic.getData().getBowling().getODIs().get4w() + 
						" Tests : " + cricketerStatistic.getData().getBowling().getTests().get4w()); 
			}
			else if(entity.equalsIgnoreCase("50")) {
				return ("Batting : "  + "T20 : " + cricketerStatistic.getData().getBatting().getT20Is().get50() + 
						" ODI : " + cricketerStatistic.getData().getBatting().getODIs().get50() + 
						" Tests : " + cricketerStatistic.getData().getBatting().getTests().get50()); 
			}
			else if(entity.equalsIgnoreCase("100")) {
				return ("Batting : "  + "T20 : " + cricketerStatistic.getData().getBatting().getT20Is().get100() + 
						" ODI : " + cricketerStatistic.getData().getBatting().getODIs().get100() + 
						" Tests : " + cricketerStatistic.getData().getBatting().getTests().get100());
			}
			else if(entity.equalsIgnoreCase("6s")) {
				return ("Batting : "  + "T20 : " + cricketerStatistic.getData().getBatting().getT20Is().get6s() + 
						" ODI : " + cricketerStatistic.getData().getBatting().getODIs().get6s() + 
						" Tests : " + cricketerStatistic.getData().getBatting().getTests().get6s());
			}
			else if(entity.equalsIgnoreCase("4s")) {
				return ("Batting : "  + "T20 : " + cricketerStatistic.getData().getBatting().getT20Is().get4s() + 
						" ODI : " + cricketerStatistic.getData().getBatting().getODIs().get4s() + 
						" Tests : " + cricketerStatistic.getData().getBatting().getTests().get4s());
			}
		}
		return null;
	}

	private boolean urlIsAccessable(String url) {
		try {
			url = url.replaceFirst("https", "http");
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setConnectTimeout(300);
			connection.setReadTimeout(300);
			connection.setRequestMethod("HEAD");
			if(connection.getResponseCode() != 200) {
				return false;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	public List<Result> getSearchResult(String keyword){
        // Set up the HTTP transport and JSON factory
       HttpTransport httpTransport = new NetHttpTransport();
       JsonFactory jsonFactory = new JacksonFactory();
       //HttpRequestInitializer initializer = (HttpRequestInitializer)new CommonGoogleClientRequestInitializer(API_KEY);
       Customsearch customsearch = new Customsearch(httpTransport, jsonFactory,null);

       List<Result> resultList = null;
       try {
               Customsearch.Cse.List list = customsearch.cse().list(keyword);
               list.setKey(API_KEY);
               list.setCx(SEARCH_ENGINE_ID);
               //num results per page
               //list.setNum(2L);

               //for pagination
               list.setStart(10L);
               Search results = list.execute();
               resultList = results.getItems();

       }catch (Exception e) {
               e.printStackTrace();
       }

       return resultList;
   }

}
