package com.bank.knab.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.knab.dao.LoanDetailsRepository;
import com.bank.knab.dao.PaymentScheduleRepository;
import com.bank.knab.entity.LoanDetails;
import com.bank.knab.entity.PaymentSchedule;
import com.bank.knab.exceptions.ResourceNotFoundException;
import com.bank.knab.service.LoanService;
import com.bank.knab.utill.PaymentStatus;


@Service
public class LoanServiceImp implements LoanService {

	@Autowired
	private LoanDetailsRepository loanDetailsRepo;

	@Autowired
	private PaymentScheduleRepository paymentScheduleRepo;
	
	@Autowired
	private MailSenderService mailSenderService;

	// To Create a New Loan
	@Override
	public String createLoan(LoanDetails loanDetails) throws MessagingException {
		String message = this.generatePaymentScheddule(loanDetails);
		return message;
	}

	// To Get the Created Loan list and Loan Schedule..
	@Override
	public List<LoanDetails> loanList() {
		return loanDetailsRepo.findAll();
	}

	// To get the Loan List and Loan Schedule by customer Id

	@Override
	public LoanDetails loanListById(int customerId) {
		LoanDetails loanList = loanDetailsRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Id Not Found with Id : " + customerId));
		return loanList;
	}

	// To update the Payment Status(PAID)..
	@Override
	public String paymentStatus(int paymentId) {
		PaymentSchedule scheduleList = paymentScheduleRepo.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment Id Not Found with Id : " + paymentId));
		scheduleList.setPaymentStatus(PaymentStatus.PAID.toString());
		paymentScheduleRepo.save(scheduleList);
		return "Payment Paid Successfully...";
	}
	
	
	private String generatePaymentScheddule(LoanDetails loanDetails) throws MessagingException {

		PaymentSchedule schedule = null;
		// To get current Date
		Calendar loanStartDate = Calendar.getInstance();
		loanStartDate.setTime(loanDetails.getLoanStartDate());

		Calendar maturityDate = Calendar.getInstance();
		maturityDate.setTime(loanDetails.getMaturityDate());

		int loanYears = maturityDate.get(Calendar.YEAR) - loanStartDate.get(Calendar.YEAR);

		int paymentFrequency = loanDetails.getPaymentFrequency();
		long loanAmount = loanDetails.getLoanAmount();
		double projectedInterest = ((loanDetails.getInterestRate() * loanAmount) / 100)*loanYears;
		int totalPaymentFrequency = (12 * loanYears) / paymentFrequency;
		double paymentAmount = projectedInterest;
		long loanAmountPerFrequency = loanAmount / totalPaymentFrequency;

		List<PaymentSchedule> paymentScheduleList = loanDetails.getPaymentSchedule();
		// To create PaymentSchedule Based on Payment Term
		while (loanDetails.getMaturityDate().compareTo(loanStartDate.getTime()) > 0) {
			if (loanDetails.getPaymentTerm().equalsIgnoreCase("Even Principal")) {
				projectedInterest = ((loanDetails.getInterestRate() * loanAmount) / 100)/(12/paymentFrequency);
				loanStartDate.add(Calendar.MONTH, paymentFrequency);
				paymentAmount = loanAmountPerFrequency + projectedInterest;
				schedule = new PaymentSchedule(loanStartDate.getTime(), loanAmount, projectedInterest,
						PaymentStatus.PROJECTED.toString(), paymentAmount);
				paymentScheduleList.add(schedule);
				loanAmount = loanAmount - loanAmountPerFrequency;
				

			} else if (loanDetails.getPaymentTerm().equalsIgnoreCase("Interest Only")) {
				paymentAmount = projectedInterest/totalPaymentFrequency;
				loanStartDate.add(Calendar.MONTH, paymentFrequency);
				if (loanDetails.getMaturityDate().compareTo(loanStartDate.getTime()) == 0) {
					loanAmount = loanDetails.getLoanAmount();
					paymentAmount = loanAmount + (projectedInterest / totalPaymentFrequency);
				}else {
					loanAmount=0;
				}
				schedule = new PaymentSchedule(loanStartDate.getTime(), loanAmount, projectedInterest/totalPaymentFrequency,
						PaymentStatus.PROJECTED.toString(), paymentAmount);
				paymentScheduleList.add(schedule);
			}
		}
		loanDetails.setPaymentSchedule(paymentScheduleList);
		loanDetailsRepo.save(loanDetails);
		mailSenderService.sendEmail(loanDetails.getEmailId(), "Loan Application","Dear "+loanDetails.getCustomerName()+", \nLoan is Created Successfully...");
		return "Loan is Successfully Created...";
	}
}
