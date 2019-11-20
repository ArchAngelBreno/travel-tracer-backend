package com.traveltracer.controller.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class CreateActivitySpendRequest {

	@NotNull
	private String name;
	
	@NotNull
	private double price;
	
	private List<Long> usersPayers;

	@NotNull
	private List<Long> usersReceivers;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Long> getUsersPayers() {
		return usersPayers;
	}

	public void setUsersPayers(List<Long> usersPayers) {
		this.usersPayers = usersPayers;
	}

	public List<Long> getUsersReceivers() {
		return usersReceivers;
	}

	public void setUsersReceivers(List<Long> usersReceivers) {
		this.usersReceivers = usersReceivers;
	}
	
	

}
