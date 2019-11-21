package com.traveltracer.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.traveltracer.model.User;

public class UserResponse {
	
	private Long id;
	private String name;
	
	private List<GroupResponse> groups;
	
	
	public UserResponse(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.groups = user.getUserGroups().parallelStream().map(userGroup-> new GroupResponse(userGroup.getGroup(),id)).collect(Collectors.toList());
		
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

	public List<GroupResponse> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupResponse> groups) {
		this.groups = groups;
	}
	
}
