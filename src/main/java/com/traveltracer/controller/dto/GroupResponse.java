package com.traveltracer.controller.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.traveltracer.model.ActivitySpend;
import com.traveltracer.model.Group;
import com.traveltracer.model.User;

public class GroupResponse {

	private Long id;
	private String name;
	private List<ActivitySpend> activitySpends;
	private List<User> users;

	public GroupResponse() {
		// TODO Auto-generated constructor stub
	}

	public GroupResponse(Optional<Group> group) {
		super();
		this.id = group.get().getId();
		this.name = group.get().getName();
		this.activitySpends = group.get().getActivitySpends();
		this.users = group.get().getUserGroups().stream().map(userGroup -> userGroup.getUser()).collect(Collectors.toList());
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<ActivitySpend> getActivitySpends() {
		return activitySpends;
	}

	public void setActivitySpends(List<ActivitySpend> activitySpends) {
		this.activitySpends = activitySpends;
	}

	

}
