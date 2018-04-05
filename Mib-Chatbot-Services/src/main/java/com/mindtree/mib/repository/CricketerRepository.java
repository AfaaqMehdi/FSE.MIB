/**
 * 
 */
package com.mindtree.mib.repository;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mindtree.mib.CricketerStatistic;
import com.mindtree.mib.PlayerDetails;

/**
 * @author M1038389
 *
 */
@Document(collection="cricketerStatistic")
public interface CricketerRepository extends MongoRepository<CricketerStatistic, String>{
	
	CricketerStatistic findByPid(final Integer pid);
	
	@Query(value="{}",fields="{ 'pid' : 1, 'name' : 1, 'imageURL':1, 'born':1,'playingRole':1, 'data':1}")
	 public List<PlayerDetails> findPidAndName();
}
