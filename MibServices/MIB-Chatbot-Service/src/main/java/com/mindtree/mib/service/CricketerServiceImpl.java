/**
 * 
 */
package com.mindtree.mib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.mib.CricketerStatistic;
import com.mindtree.mib.PlayerDetails;
import com.mindtree.mib.repository.CricketerRepository;

/**
 * @author M1038389
 *
 */

@Service
public class CricketerServiceImpl implements CricketerService {
	
	private final CricketerRepository cricketerRepository;
	
	@Autowired
	public CricketerServiceImpl(CricketerRepository cricketerRepository) {
		this.cricketerRepository = cricketerRepository;
	}
	
	public CricketerStatistic findByPid(final Integer pid) {
		return cricketerRepository.findByPid(pid);
	}
	
	public List<PlayerDetails> fetchPlayerIdAndName()  {
		List<PlayerDetails> playerDetails = null;
		try {
			
			playerDetails = cricketerRepository.findPidAndName();
			
		} catch (Exception ex) {
			
		}
		 
		return playerDetails;
	}

}
