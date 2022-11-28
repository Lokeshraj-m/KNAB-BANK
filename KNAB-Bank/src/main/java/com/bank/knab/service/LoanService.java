package com.bank.knab.service;

import java.util.List;

import javax.mail.MessagingException;

import com.bank.knab.entity.LoanDetails;


                  
public interface LoanService {
	
	public String createLoan(LoanDetails loanDetails) throws MessagingException;

	public List<LoanDetails> loanList();

	public LoanDetails loanListById(int customerId);

	public String paymentStatus(int paymentId);

}
