package com.traveltracer.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.traveltracer.model.enumerator.PaymentStatus;

@Entity
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private double totalValue;
	
	private Integer status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_payer_id")
	private User userPayer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_receiver_id")
	private User userReceiver;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="activity_id")
	@JsonIgnore
	private ActivitySpend activitySpend;
	
	public Payment() {
		// TODO Auto-generated constructor stub
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}
	
	public PaymentStatus getStatus() {
		return PaymentStatus.toEnum(status);
	}

	public void setStatus(PaymentStatus status) {
		this.status = status.getCode();
	}

	public User getUserPayer() {
		return userPayer;
	}

	public void setUserPayer(User userPayer) {
		this.userPayer = userPayer;
	}

	public User getUserReceiver() {
		return userReceiver;
	}

	public void setUserReceiver(User userReceiver) {
		this.userReceiver = userReceiver;
	}

	public ActivitySpend getActivitySpend() {
		return activitySpend;
	}

	public void setActivitySpend(ActivitySpend activitySpend) {
		this.activitySpend = activitySpend;
	}
}
