package com.traveltracer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traveltracer.controller.dto.CreateActivitySpendRequest;
import com.traveltracer.model.Group;
import com.traveltracer.model.User;
import com.traveltracer.repository.GroupRepository;
import com.traveltracer.repository.UserRepository;

@Service
public class ActivitySpendService {

	@Autowired
	private GroupRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void createSpend(CreateActivitySpendRequest createActivitySpendRequest, long groupId) {
		Group group = repository.findById(groupId).get();
		List<User> usersPayers = userRepository.findAllById(createActivitySpendRequest.getUsersPayers());
		List<User> usersReceivers = userRepository.findAllById(createActivitySpendRequest.getUsersReceivers());
		
		
		group.addActivitySpend(createActivitySpendRequest, usersPayers, usersReceivers);
		
		repository.save(group);
	}
}
