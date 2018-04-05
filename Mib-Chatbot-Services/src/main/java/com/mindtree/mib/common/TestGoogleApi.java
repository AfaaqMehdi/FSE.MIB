package com.mindtree.mib.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Query;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;

public class TestGoogleApi {
	
	final private static String GOOGLE_SEARCH_URL = "https://www.googleapis.com/customsearch/v1?";
	 
	 
	private static final String GOOGLE_API_KEY = "AIzaSyAAOvSy2rjJ5ItP0_eCXMqyYX20-ixrj90";
	private static final String SEARCH_ENGINE_ID = "013036536707430787589:_pqjad5hr1a";


	protected static final int HTTP_REQUEST_TIMEOUT = 3 * 600000;
	static String QUERY = "Android";
	
	final static String FINAL_URL= GOOGLE_SEARCH_URL + "key=" + GOOGLE_API_KEY + "&cx=" + SEARCH_ENGINE_ID + "&q=" + QUERY + "&alt=json";
	
	public static void main(String[] args) {
		/*List<Result> results = new ArrayList<Result>();
	    try {
	    	System.out.println(FINAL_URL);
	        results = search(FINAL_URL);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    if(results != null && results.size() > 0){
            for(Result result: results){
                System.out.println(result.getHtmlTitle());
                System.out.println(result.getFormattedUrl());
                //System.out.println(result.getHtmlSnippet());
                System.out.println("----------------------------------------");
            }
     }*/
		String key="AIzaSyAAOvSy2rjJ5ItP0_eCXMqyYX20-ixrj90";
	    String qry="Yuvraj_singh_school";
		URL url;
		try {
			url = new URL(
			        "https://www.googleapis.com/customsearch/v1?key="+key+"&cx=013036536707430787589:_pqjad5hr1a&q=" + qry + "&alt=json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (conn.getInputStream())));

	        String output;
	        System.out.println("Output from Server .... \n");
	        while ((output = br.readLine()) != null) {
	            System.out.println(output);
	        }
	        conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public static List<Result> search(String keyword) {
	    Customsearch customsearch= null;
	    try {
	        customsearch = new Customsearch(new NetHttpTransport(),new JacksonFactory(), new HttpRequestInitializer() {
	            public void initialize(HttpRequest httpRequest) {
	                try {
	                    // set connect and read timeouts
	                    httpRequest.setConnectTimeout(HTTP_REQUEST_TIMEOUT);
	                    httpRequest.setReadTimeout(HTTP_REQUEST_TIMEOUT);

	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    List<Result> resultList=null;
	    try {
	        Customsearch.Cse.List list=customsearch.cse().list(keyword);
	        list.setKey(GOOGLE_API_KEY);
	        list.setCx(SEARCH_ENGINE_ID);
	        list.execute();
	        Search results=list.execute();
	        resultList=results.getItems();
	    }
	    catch (  Exception e) {
	        e.printStackTrace();
	    }
	    return resultList;
	}*/

}
