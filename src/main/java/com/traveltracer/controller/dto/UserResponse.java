package com.traveltracer.controller.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.traveltracer.model.Group;
import com.traveltracer.model.User;

public class UserResponse {
	
	private Long id;
	private String name;
	
	private List<Group> groups;
	
	
	public UserResponse(Optional<User> user) {
		super();
		this.id = user.get().getId();
		this.name = user.get().getName();
		this.groups = user.get().getUserGroups().stream().map(userGroup -> userGroup.getGroup()).collect(Collectors.toList());
	}
	
	public UserResponse(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.groups = user.getUserGroups().stream().map(userGroup -> userGroup.getGroup()).collect(Collectors.toList());
	}

	public UserResponse() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Group> getGroups() {
		return groups;
	}


	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	
	
	
}
