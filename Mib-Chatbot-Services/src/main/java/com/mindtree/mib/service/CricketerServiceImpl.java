/**
 * 
 */
package com.mindtree.mib.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mindtree.mib.CricketerStatistic;
import com.mindtree.mib.PlayerDetails;
import com.mindtree.mib.exception.ChatbotFetchException;
import com.mindtree.mib.exception.ChatbotNoDataFoundException;
import com.mindtree.mib.repository.CricketerRepository;

/**
 * @author M1038389
 *
 */

@Service
public class CricketerServiceImpl implements CricketerService {
	
	private static final Logger LOGGER = Logger.getLogger(CricketerServiceImpl.class);
	
	private final CricketerRepository cricketerRepository;
	
	@Autowired
	public CricketerServiceImpl(CricketerRepository cricketerRepository) {
		this.cricketerRepository = cricketerRepository;
	}
	
	public CricketerStatistic findByPid(final Integer pid) throws Exception {
		LOGGER.info("Entering method findByPid ---> ");
		CricketerStatistic cricketerStatistic = null;
		try {
			cricketerStatistic = cricketerRepository.findByPid(pid);
			if(cricketerStatistic == null) {
				throw new ChatbotNoDataFoundException(HttpStatus.NO_CONTENT);
			}
		} catch (ChatbotFetchException ex) {
			LOGGER.error("Error while accessing database");
			throw new ChatbotFetchException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Exiting method findByPid ---> ");
		return cricketerStatistic;
	}
	
	public List<PlayerDetails> fetchPlayerIdAndName() throws Exception {
		LOGGER.info("Entering method fetchPlayerIdAndName ---> ");
		List<PlayerDetails> playerDetails = null;
		try {
			playerDetails = cricketerRepository.findPidAndName();
			if(playerDetails == null) {
				throw new ChatbotNoDataFoundException(HttpStatus.NO_CONTENT);
			}
			
		} catch (ChatbotFetchException ex) {
			LOGGER.error("Error while accessing database");
			throw new ChatbotFetchException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Exiting method fetchPlayerIdAndName ---> ");
		return playerDetails;
	}

}
