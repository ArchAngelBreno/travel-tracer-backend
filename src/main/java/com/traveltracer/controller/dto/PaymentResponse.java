package com.traveltracer.controller.dto;

import com.traveltracer.model.Payment;
import com.traveltracer.model.User;

public class PaymentResponse {

	private User userPayer;
	private User userReceiver;
	private String activitySpend;
	private double value;

	public PaymentResponse(Payment payment) {
		this.userPayer = payment.getUserPayer();
		this.userPayer = payment.getUserReceiver();
		this.value = payment.getTotalValue();
		this.activitySpend = payment.getActivitySpend().getName(); 
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getActivitySpend() {
		return activitySpend;
	}

	public void setActivitySpend(String activitySpend) {
		this.activitySpend = activitySpend;
	}

	
}
