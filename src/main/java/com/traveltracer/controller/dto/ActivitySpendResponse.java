package com.traveltracer.controller.dto;

import java.io.Serializable;
import java.util.List;

import com.traveltracer.model.ActivitySpend;
import com.traveltracer.model.Payment;

public class ActivitySpendResponse implements Serializable{

	
	private static final long serialVersionUID = -4174439561571358853L;
	private double total;
	private String name;
	private List<Payment> paymentPaid;
	private List<Payment> paymentOwing;
	private List<Payment> paymentToReceive;
	
	public ActivitySpendResponse(ActivitySpend activitySpend, Long userId) {
		super();
		this.total = activitySpend.getPrice();
		this.name = activitySpend.getName();
		this.paymentPaid = activitySpend.getPaymentPaid(userId);
		this.paymentOwing = activitySpend.getPaymentOwing(userId);
		this.paymentToReceive = activitySpend.getPaymentToReceive(userId);
	}
	
	public ActivitySpendResponse(ActivitySpend activitySpend) {
		super();
		this.total = activitySpend.getPrice();
		this.name = activitySpend.getName();
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Payment> getPaymentPaid() {
		return paymentPaid;
	}

	public void setPaymentPaid(List<Payment> paymentPaid) {
		this.paymentPaid = paymentPaid;
	}

	public List<Payment> getPaymentOwing() {
		return paymentOwing;
	}

	public void setPaymentOwing(List<Payment> paymentOwing) {
		this.paymentOwing = paymentOwing;
	}

	public List<Payment> getPaymentToReceive() {
		return paymentToReceive;
	}

	public void setPaymentToReceive(List<Payment> paymentToReceive) {
		this.paymentToReceive = paymentToReceive;
	}
	
}
