package com.bank.knab.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bank.knab.dao.LoanDetailsRepository;
import com.bank.knab.entity.LoanDetails;
import com.bank.knab.entity.PaymentSchedule;
import com.bank.knab.utill.PaymentStatus;



@Component
public class Scheduler {

	@Autowired
	LoanDetailsRepository loanDetailsRepo;

	@Autowired
	MailSenderService mailSenderService;

	@Scheduled(cron = "0/1 * * * * *")
	public void changeAwaitingStatus() throws MessagingException {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = dateFormat.format(date);
		List<LoanDetails> loanList = this.loanDetailsRepo.findAll();
		for (LoanDetails loanDetails : loanList) {
			List<PaymentSchedule> paymentScheduleList = loanDetails.getPaymentSchedule();
			for (PaymentSchedule paymentSchedule : paymentScheduleList) {
				String paymentScheduleDate = dateFormat.format(paymentSchedule.getPaymentDate());
				if (paymentScheduleDate.equals(currentDate) && paymentSchedule.getPaymentStatus().equals("PROJECTED")) {
					paymentSchedule.setPaymentStatus(PaymentStatus.AWAITINGPAYMENT.toString());
					loanDetailsRepo.save(loanDetails);
					mailSenderService.sendEmail(loanDetails.getEmailId(), "Loan Application",
							"Dear " + loanDetails.getCustomerName() + ", \nPay ₹ " + paymentSchedule.getPaymentAmount()
									+ "/-" + "\nToday is Your Due Date\nKindly Pay your Dues\n\n\nThank You");
				}
			}
		}
	}
}
