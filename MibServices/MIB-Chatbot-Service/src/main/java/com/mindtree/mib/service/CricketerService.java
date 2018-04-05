/**
 * 
 */
package com.mindtree.mib.service;

import java.util.List;

import com.mindtree.mib.CricketerStatistic;
import com.mindtree.mib.PlayerDetails;

/**
 * @author M1038389
 *
 */
public interface CricketerService {
	
	CricketerStatistic findByPid(final Integer pid);
	public List<PlayerDetails> fetchPlayerIdAndName();
}
