package com.mindtree.mib.handler;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.mib.model.ChatHistory;
import com.mindtree.mib.repository.ChatHistoryRepository;

@Service
public class ChatHistoryHandler {

	private static final Logger LOGGER = Logger.getLogger(ChatHistoryHandler.class);

	@Autowired
	private ChatHistoryRepository chatHistoryRepository;
	
	public void storeChatInfo(String pid, String question, String answer, String isAnswerValid, String isGoogleAnswer) {

		ChatHistory chatHistory = new ChatHistory(pid, "playerName", "MIBUSER", question, answer, isAnswerValid,
				isGoogleAnswer);
		
		chatHistoryRepository.insert(chatHistory);

		LOGGER.info(chatHistory.toString());
	}

	public List<ChatHistory> retrieveChatHistory() {
		List<ChatHistory> chatLst = chatHistoryRepository.findAll();
		for (ChatHistory chatHistory : chatLst) {
			LOGGER.info(chatHistory.toString());
		}
		return chatLst;
	}

	public ChatHistoryRepository getChatHistoryRepository() {
		return chatHistoryRepository;
	}

	public void setChatHistoryRepository(ChatHistoryRepository chatHistoryRepository) {
		this.chatHistoryRepository = chatHistoryRepository;
	}
	
}
