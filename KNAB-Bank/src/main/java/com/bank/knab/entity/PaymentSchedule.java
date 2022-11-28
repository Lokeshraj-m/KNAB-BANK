package com.bank.knab.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PaymentSchedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int paymentId;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date paymentDate;
	private long principal;
	private double projectedInterest;
	private String paymentStatus;
	private double paymentAmount;
	
	
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public long getPrincipal() {
		return principal;
	}
	public void setPrincipal(long principal) {
		this.principal = principal;
	}
	public double getProjectedInterest() {
		return projectedInterest;
	}
	public void setProjectedInterest(double projectedInterest) {
		this.projectedInterest = projectedInterest;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	
	public PaymentSchedule( Date paymentDate, long principal, double projectedInterest,
			String paymentStatus, double paymentAmount) {
		super();
		this.paymentDate = paymentDate;
		this.principal = principal;
		this.projectedInterest = projectedInterest;
		this.paymentStatus = paymentStatus;
		this.paymentAmount = paymentAmount;
	}
	public PaymentSchedule() {
		super();
	}
	@Override
	public String toString() {
		return "PaymentSchedule [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", principal=" + principal
				+ ", projectedInterest=" + projectedInterest + ", paymentStatus=" + paymentStatus + ", paymentAmount="
				+ paymentAmount + "]";
	}
	
	
}
