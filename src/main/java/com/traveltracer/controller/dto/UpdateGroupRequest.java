package com.traveltracer.controller.dto;

import javax.validation.constraints.NotNull;

import com.traveltracer.model.User;

public class UpdateGroupRequest {
	
	@NotNull
	private User user;
	
	private boolean owner;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	
}
