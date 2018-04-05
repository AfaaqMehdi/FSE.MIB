package com.mindtree.mib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.mib.PersonalInfo;
import com.mindtree.mib.repository.PersonalInfoRepository;
@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {
	@Autowired
	private final PersonalInfoRepository personalInfoRepository;

	@Autowired
	public PersonalInfoServiceImpl(PersonalInfoRepository personalInfoRepo) {
		this.personalInfoRepository = personalInfoRepo;
	}
	@Override
	public PersonalInfo findByPid(Integer pid) {
		PersonalInfo findById = personalInfoRepository.findByPid(pid);
		return findById;
	}
}
