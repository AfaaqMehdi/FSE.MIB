package com.mindtree.mib.config;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

import com.mindtree.mib.handler.ChatHistoryHandler;
import com.mindtree.mib.repository.ChatHistoryRepository;
import com.mindtree.mib.service.CricketerService;
import com.mindtree.mib.service.PersonalInfoService;

/**
 * @author Petri Kainulainen
 */
@Configuration
public class TestContext {

    private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

    @Bean
    public CricketerService todoService() {
        return Mockito.mock(CricketerService.class);
    }
	@Bean
    public PersonalInfoService todoPersonalService() {
        return Mockito.mock(PersonalInfoService.class);
    }
	
	@Bean
    public ChatHistoryRepository todoChatHistoryRepository() {
        return Mockito.mock(ChatHistoryRepository.class);
    }
	
	@Bean
	public ChatHistoryHandler todoChatHistoryHandler() {
		return Mockito.mock(ChatHistoryHandler.class);
	}
}
