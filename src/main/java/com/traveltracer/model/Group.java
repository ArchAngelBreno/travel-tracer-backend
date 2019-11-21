package com.traveltracer.model;

import java.util.ArrayList;
import java.util.List;

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

	public void addActivitySpend(CreateActivitySpendRequest createActivitySpendRequest, List<User> usersPayers, List<User> usersDebtors) {
		if (activitySpends == null) {
			activitySpends = new ArrayList<>();
		}

		ActivitySpend activitySpend = new ActivitySpend();
		activitySpend.setGroup(this);
		activitySpend.setName(createActivitySpendRequest.getName());
		activitySpend.setPrice(createActivitySpendRequest.getPrice());
		activitySpend.setPayments(new ArrayList<>());
		
		double totalValue = activitySpend.getPrice() / userGroups.size();
		
		
		usersPayers.forEach(userPayer -> {
			Payment paymentPayers = new Payment();
			paymentPayers.setUserPayer(userPayer);
			paymentPayers.setStatus(PaymentStatus.PAGO);
			paymentPayers.setTotalValue(activitySpend.getPrice() / usersPayers.size());
			paymentPayers.setActivitySpend(activitySpend);
			
			
			activitySpend.getPayments().add(paymentPayers);
			
			usersDebtors.forEach(userDebtor -> {
				Payment paymentDebtor = new Payment();
				paymentDebtor.setUserPayer(userDebtor);
				paymentDebtor.setUserReceiver(userPayer);
				paymentDebtor.setStatus(PaymentStatus.NAO_PAGO);
				paymentDebtor.setTotalValue(totalValue / usersPayers.size());
				paymentDebtor.setActivitySpend(activitySpend);
				
				activitySpend.getPayments().add(paymentDebtor);
			});
		});
		
		
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

	public double totalUserSpendInTrip(Long userId) {
		return activitySpends.parallelStream().mapToDouble(activitySpend -> activitySpend.getPaymentPaid(userId).parallelStream().mapToDouble(payment -> payment.getTotalValue()).sum()).sum();
		
	}

}
