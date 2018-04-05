package com.mindtree.mib.repository;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mindtree.mib.PersonalInfo;
@Document(collection="personalInfo")
public interface PersonalInfoRepository extends MongoRepository<PersonalInfo, String>{
	public String findByFather(String father);
	public PersonalInfo findByPid(Integer pid);
}
