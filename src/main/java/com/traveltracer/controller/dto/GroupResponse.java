package com.traveltracer.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.traveltracer.model.Group;

public class GroupResponse {

	private Long id;
	private String name;

	@JsonProperty("activitySpends")
	private List<ActivitySpendResponse> activitySpendsResponse;

	private double totalGroupSpend;
	private double totalUserSpend;

	public GroupResponse() {
		// TODO Auto-generated constructor stub
	}

	public GroupResponse(Group group) {
		super();
		id = group.getId();
		name = group.getName();
		activitySpendsResponse = group.getActivitySpends().parallelStream().map(activitySpend -> new ActivitySpendResponse(activitySpend)).collect(Collectors.toList());
	}

	public GroupResponse(Group group, Long userId) {
		super();
		this.id = group.getId();
		this.name = group.getName();
		this.activitySpendsResponse = group.getActivitySpends().parallelStream().map(activitySpend -> new ActivitySpendResponse(activitySpend, userId)).collect(Collectors.toList());
		this.totalGroupSpend = group.getActivitySpends().parallelStream().mapToDouble(activitySpend -> activitySpend.getPrice()).sum();
		this.totalUserSpend = group.totalUserSpendInTrip(userId);
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

	public List<ActivitySpendResponse> getActivitySpendsResponse() {
		return activitySpendsResponse;
	}

	public void setActivitySpendsResponse(List<ActivitySpendResponse> activitySpendsResponse) {
		this.activitySpendsResponse = activitySpendsResponse;
	}

	public double getTotalGroupSpend() {
		return totalGroupSpend;
	}

	public void setTotalGroupSpend(double totalGroupSpend) {
		this.totalGroupSpend = totalGroupSpend;
	}

	public double getTotalUserSpend() {
		return totalUserSpend;
	}

	public void setTotalUserSpend(double totalUserSpend) {
		this.totalUserSpend = totalUserSpend;
	}

}
