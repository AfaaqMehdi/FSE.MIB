package com.mindtree.mib;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CricketerStatisticRepository extends MongoRepository<CricketerStatistic, String> {

    public CricketerStatistic findByPid(Integer pid);
    //public List<CricketerStatistic> findByLastName(String lastName);

}