
package com.mindtree.mib.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "pid", "playerName", "userName", "timeStamp", "question", "response", "isAnswerValid",
		"isAnswerfromGoogle" })

public class ChatHistory {

	@JsonProperty("pid")
	private String pid;
	@JsonProperty("playerName")
	private String playerName;
	@JsonProperty("userName")
	private String userName;
	@JsonProperty("timeStamp")
	private String timeStamp;
	@JsonProperty("question")
	private String question;
	@JsonProperty("response")
	private String response;
	@JsonProperty("isAnswerValid")
	private String isAnswerValid = "No";
	@JsonProperty("isAnswerfromGoogle")
	private String isAnswerfromGoogle = "No";
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	
	
	public ChatHistory(String pid, String playerName, String userName, String question,
			String response, String isAnswerValid, String isAnswerfromGoogle) {
		this.pid = pid;
		this.playerName = playerName;
		this.userName = userName;
		this.question = question;
		this.response = response;
		this.isAnswerValid = isAnswerValid;
		this.isAnswerfromGoogle = isAnswerfromGoogle;
		populateDate();
	}

	@JsonProperty("pid")
	public String getPid() {
		return pid;
	}

	@JsonProperty("pid")
	public void setPid(String pid) {
		this.pid = pid;
	}

	@JsonProperty("playerName")
	public String getPlayerName() {
		return playerName;
	}

	@JsonProperty("playerName")
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@JsonProperty("userName")
	public String getUserName() {
		return userName;
	}

	@JsonProperty("userName")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonProperty("timeStamp")
	public String getTimeStamp() {
		return timeStamp;
	}

	@JsonProperty("timeStamp")
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@JsonProperty("question")
	public String getQuestion() {
		return question;
	}

	@JsonProperty("question")
	public void setQuestion(String question) {
		this.question = question;
	}

	@JsonProperty("response")
	public String getResponse() {
		return response;
	}

	@JsonProperty("response")
	public void setResponse(String response) {
		this.response = response;
	}

	@JsonProperty("isAnswerValid")
	public String getIsAnswerValid() {
		return isAnswerValid;
	}

	@JsonProperty("isAnswerValid")
	public void setIsAnswerValid(String isAnswerValid) {
		this.isAnswerValid = isAnswerValid;
	}

	@JsonProperty("isAnswerfromGoogle")
	public String getIsAnswerfromGoogle() {
		return isAnswerfromGoogle;
	}

	@JsonProperty("isAnswerfromGoogle")
	public void setIsAnswerfromGoogle(String isAnswerfromGoogle) {
		this.isAnswerfromGoogle = isAnswerfromGoogle;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	
	public void populateDate(){
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");  
        String strDate = dateFormat.format(date);  
        setTimeStamp(strDate);
	}

	@Override
	public String toString() {
		return "ChatHistory [pid=" + pid + ", playerName=" + playerName + ", userName=" + userName + ", timeStamp="
				+ timeStamp + ", question=" + question + ", response=" + response + ", isAnswerValid=" + isAnswerValid
				+ ", isAnswerfromGoogle=" + isAnswerfromGoogle + "]";
	}
	
	

}