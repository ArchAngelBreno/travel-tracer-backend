package com.traveltracer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traveltracer.controller.dto.CreateGroupRequest;
import com.traveltracer.controller.dto.GroupResponse;
import com.traveltracer.controller.dto.UpdateGroupRequest;
import com.traveltracer.model.Group;
import com.traveltracer.model.User;
import com.traveltracer.repository.GroupRepository;
import com.traveltracer.repository.UserRepository;
import com.traveltracer.service.exception.ObjectNotFoundException;
import com.traveltracer.service.exception.UnprocessableEntityException;

@Service
public class GroupService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupRepository groupRepository;

	public void createGroup(CreateGroupRequest createGroupRequest) {
		Optional<User> user = userRepository.findById(createGroupRequest.getUserId());
		
		if (!user.isEmpty()) {
			user.get().addGroup(createGroupRequest.getGroup(), createGroupRequest.isOwner());
			userRepository.save(user.get());
		} else {
			throw new UnprocessableEntityException("Não foi possivel adicionar o usuario no grupo");
		}
	}

	public void updateGroup(UpdateGroupRequest updateUserRequest, Long groupId) {
		Group group = groupRepository.findById(groupId).get();
		group.addUser(updateUserRequest.getUser(), updateUserRequest.isOwner());
		userRepository.save(updateUserRequest.getUser());
		groupRepository.save(group);
	}

	public GroupResponse findGroupById(Long id) {
		Optional<Group> group = groupRepository.findById(id);

		if (!group.isEmpty()) {
			return new GroupResponse(group);
		}

		throw new ObjectNotFoundException("O id: " + id + " não existe!");

	}

}
