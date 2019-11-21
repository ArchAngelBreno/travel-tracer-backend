package com.traveltracer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.traveltracer.model.enumerator.PaymentStatus;

@Entity
public class ActivitySpend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="group_id")
	private Group group;
	
	@OneToMany(mappedBy="activitySpend",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Payment> payments;
	
	public ActivitySpend() {
		// TODO Auto-generated constructor stub
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	public List<Payment> getPaymentToReceive(Long userId) {
		List<Payment> payments = new ArrayList<>();
		payments.addAll(getPayments().parallelStream()
				.filter(payment -> payment.getActivitySpend().getId().equals(getId()) && payment.getUserReceiver() != null && payment.getUserReceiver().getId().equals(userId)
						&& payment.getStatus().equals(PaymentStatus.NAO_PAGO))
				.collect(Collectors.toList()));

		return payments;
	}
	
	public List<Payment> getPaymentPaid(Long userId) {
		List<Payment> payments = new ArrayList<>();
		payments.addAll(getPayments().parallelStream().filter(payment -> payment.getActivitySpend().getId().equals(getId()) && payment.getUserPayer().getId().equals(userId)
				&& payment.getStatus().equals(PaymentStatus.PAGO)).collect(Collectors.toList()));
		
		return payments;
	}

	public List<Payment> getPaymentOwing(Long userId) {
		List<Payment> payments = new ArrayList<>();
		payments.addAll(getPayments().parallelStream().filter(payment -> payment.getActivitySpend().getId().equals(getId()) && payment.getUserPayer().getId().equals(userId)
				&& payment.getStatus().equals(PaymentStatus.NAO_PAGO)).collect(Collectors.toList()));

		return payments;
	}
	
	
}
