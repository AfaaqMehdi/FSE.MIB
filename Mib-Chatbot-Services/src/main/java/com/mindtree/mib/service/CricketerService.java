/**
 * 
 */
package com.mindtree.mib.service;

import java.util.List;

import com.mindtree.mib.CricketerStatistic;
import com.mindtree.mib.PlayerDetails;
import com.mindtree.mib.exception.ChatbotFetchException;

/**
 * @author M1038389
 *
 */
public interface CricketerService {
	
	CricketerStatistic findByPid(final Integer pid) throws Exception;
	public List<PlayerDetails> fetchPlayerIdAndName() throws Exception;
}
