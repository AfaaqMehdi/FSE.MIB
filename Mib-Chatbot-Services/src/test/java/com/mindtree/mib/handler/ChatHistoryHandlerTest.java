package com.mindtree.mib.handler;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.mindtree.mib.handler.ChatHistoryHandler;
import com.mindtree.mib.model.ChatHistory;
import com.mindtree.mib.repository.ChatHistoryRepository;

public class ChatHistoryHandlerTest {

	ChatHistoryHandler chatHistoryHandler;
	
	ChatHistoryRepository chatHistoryRepository;
	
	@Before
	public void intialize() {
		chatHistoryHandler = new ChatHistoryHandler();
		chatHistoryRepository = mock(ChatHistoryRepository.class);
		chatHistoryHandler.setChatHistoryRepository(chatHistoryRepository);
		chatHistoryHandler.getChatHistoryRepository();
	}
	
	@Test
	public void testRetrieveChatHistory() {
		
		List<ChatHistory> chatList = new ArrayList<ChatHistory>();
		ChatHistory chatHistory = new ChatHistory("34320", "Sachin", "MIBUSER", "How many centuries",
				"49", "Yes", "No");
		chatList.add(chatHistory);
		
		Mockito.doReturn(chatList).when(chatHistoryRepository).findAll();
		
		List<ChatHistory> chatHistoryList = chatHistoryHandler.retrieveChatHistory();
		
		Assert.assertNotNull(chatHistoryList);
		Assert.assertEquals(1, chatHistoryList.size());

		Assert.assertEquals("34320", chatHistoryList.get(0).getPid());
		Assert.assertEquals("Sachin", chatHistoryList.get(0).getPlayerName());
		Assert.assertEquals("MIBUSER", chatHistoryList.get(0).getUserName());
		Assert.assertEquals("How many centuries", chatHistoryList.get(0).getQuestion());
		Assert.assertEquals("49", chatHistoryList.get(0).getResponse());
		Assert.assertEquals("Yes", chatHistoryList.get(0).getIsAnswerValid());
		Assert.assertEquals("No", chatHistoryList.get(0).getIsAnswerfromGoogle());
		Assert.assertNotNull(chatHistoryList.get(0).getTimeStamp());
		
	}
	
	@Test
	public void testStoreChatHistory() {
		chatHistoryHandler.storeChatInfo("34320", "How many centuries",
				"49", "Yes", "No");
		
		ArgumentCaptor<ChatHistory> chatHistoryCaptor = ArgumentCaptor.forClass(ChatHistory.class);
		Mockito.verify(chatHistoryRepository, times(1)).insert(chatHistoryCaptor.capture());
		
		ChatHistory chatHistoryData = chatHistoryCaptor.getValue();
		
		Assert.assertEquals("34320", chatHistoryData.getPid());
		Assert.assertEquals("playerName", chatHistoryData.getPlayerName());
		Assert.assertEquals("MIBUSER", chatHistoryData.getUserName());
		Assert.assertEquals("How many centuries", chatHistoryData.getQuestion());
		Assert.assertEquals("49", chatHistoryData.getResponse());
		Assert.assertEquals("Yes", chatHistoryData.getIsAnswerValid());
		Assert.assertEquals("No", chatHistoryData.getIsAnswerfromGoogle());
		Assert.assertNotNull(chatHistoryData.getTimeStamp());
		Assert.assertTrue(chatHistoryData.getAdditionalProperties().isEmpty());
	}
	
	@Test
	public void testChatHistorySetter() {
		ChatHistory chatHistory = new ChatHistory("34320", "Sachin", "MIBUSER", "How many centuries",
				"49", "Yes", "No");
		
		chatHistory.setPid("1234");
		chatHistory.setPlayerName("Virat");
		chatHistory.setUserName("MIBUser");
		chatHistory.setIsAnswerfromGoogle("Yes");
		chatHistory.setIsAnswerValid("Yes");
		chatHistory.setQuestion("Hi");
		chatHistory.setResponse("Hello");
		chatHistory.setAdditionalProperty("Key", "Value");
		
		Assert.assertEquals("1234", chatHistory.getPid());
		Assert.assertEquals("Virat", chatHistory.getPlayerName());
		Assert.assertEquals("MIBUser", chatHistory.getUserName());
		Assert.assertEquals("Hi", chatHistory.getQuestion());
		Assert.assertEquals("Hello", chatHistory.getResponse());
		Assert.assertEquals("Yes", chatHistory.getIsAnswerValid());
		Assert.assertEquals("Yes", chatHistory.getIsAnswerfromGoogle());
		Assert.assertNotNull(chatHistory.getTimeStamp());
		Assert.assertNotNull(chatHistory.getAdditionalProperties());
	}
}
