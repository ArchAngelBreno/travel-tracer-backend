package com.traveltracer.controller.dto;

import javax.validation.constraints.NotNull;

import com.traveltracer.model.Group;

public class CreateGroupRequest {

	@NotNull
	private long userId;
	
	private Group group;
	
	private boolean owner;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public boolean isOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}
	
	
	
}
