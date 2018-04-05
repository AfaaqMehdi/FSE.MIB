package com.mindtree.mib.playerstats.detailinfo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mindtree.mib.playerstats.dto.CricketerStatistic;
import com.mindtree.mib.playerstats.dto.PlayerDetails;

public interface CricketerStatisticRepository extends MongoRepository<CricketerStatistic, String> {

    public CricketerStatistic findByPid(Integer pid);

    @Query(value="{}",fields="{ 'pid' : 1, 'name' : 1, 'imageURL':1, 'born':1,'playingRole':1, 'currentAge':1}")
  	 public List<PlayerDetails> fetchAllPlayers();

}