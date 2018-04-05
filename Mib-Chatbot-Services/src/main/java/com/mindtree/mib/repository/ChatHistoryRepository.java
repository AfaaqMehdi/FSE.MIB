package com.mindtree.mib.repository;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mindtree.mib.model.ChatHistory;

@Document(collection="chatHistory")
public interface ChatHistoryRepository extends  MongoRepository<ChatHistory, String>{

	public ChatHistory findByPid(String pid);
}
