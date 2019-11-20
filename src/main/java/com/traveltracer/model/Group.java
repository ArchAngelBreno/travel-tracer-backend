package com.traveltracer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.traveltracer.controller.dto.CreateActivitySpendRequest;
import com.traveltracer.model.enumerator.PaymentStatus;

@Entity
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<UserGroup> userGroups;

	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ActivitySpend> activitySpends;

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

	public List<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}


	public List<ActivitySpend> getActivitySpends() {
		return activitySpends;
	}

	public void setActivitySpends(List<ActivitySpend> activitySpends) {
		this.activitySpends = activitySpends;
	}

	public void addActivitySpend(CreateActivitySpendRequest createActivitySpendRequest, List<User> usersPayers, List<User> usersReceivers) {
		if (activitySpends == null) {
			activitySpends = new ArrayList<>();
		}

		ActivitySpend activitySpend = new ActivitySpend();
		activitySpend.setGroup(this);
		activitySpend.setNome(createActivitySpendRequest.getName());
		activitySpend.setPrice(createActivitySpendRequest.getPrice());
		
		Payment payment = new Payment();
		
		usersPayers.forEach(userPayer -> {
			payment.setUserPayer(userPayer);
			payment.setStatus(PaymentStatus.PAGO);
		});
		
		usersReceivers.forEach(userReceiver -> {
			payment.setUserReceiver(userReceiver);
			payment.setStatus(PaymentStatus.NAO_PAGO);
		});
		
		
		List<User> spendUsers = Stream.concat(usersPayers.stream(), usersReceivers.stream()).collect(Collectors.toList());
		
		double totalValue = activitySpend.getPrice() / spendUsers.size();
		
		payment.setTotalValue(totalValue);
		payment.setActivitySpend(activitySpend);
		
		if (activitySpend.getPayments() == null) {
			activitySpend.setPayments(new ArrayList<>());
		}
		
		activitySpend.getPayments().add(payment);

		activitySpends.add(activitySpend);
	}

	public void addUser(User userFromService, boolean owner) {
		if (userGroups == null) {
			userGroups = new ArrayList<UserGroup>();
		}
		
		
		UserGroup userGroup = new UserGroup();
		userGroup.setUser(userFromService);
		userGroup.setGroup(this);
		userGroup.setOwner(owner);
		
		userGroups.add(userGroup);
	}


}
