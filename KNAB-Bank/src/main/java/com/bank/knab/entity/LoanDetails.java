package com.bank.knab.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoanDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	@Pattern(regexp = "^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,}$", message = "Invalid Customer Name.. "
			+ "Enter only Letters and Spaces..."
			+ "Example: Lokesh Raj M")
	@NotEmpty(message="Must not be Empty")
	private String customerName;
	private String emailId;
	@Min(value = 1000, message = "Inavlid Amount...Enter above 1000...")
	private long loanAmount;
	@CreationTimestamp
	private Date tradeDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date loanStartDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date maturityDate;
	private int paymentFrequency;
	@Range(min = (long) 0.0, max = (long) 100.0,message="Invalid Interest Rate... Interest Rate Should be Between 0 to 100... ")
	private double interestRate;
	private String paymentTerm;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "paymentIdFk",referencedColumnName = "customerId")
	private List<PaymentSchedule> paymentSchedule = new ArrayList<>();
	
	
}