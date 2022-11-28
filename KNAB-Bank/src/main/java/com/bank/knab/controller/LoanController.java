package com.bank.knab.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.knab.entity.LoanDetails;
import com.bank.knab.service.LoanService;



@RestController
@RequestMapping("/Knab-Bank/loan")
@CrossOrigin(origins = "http://localhost:4200")
public class LoanController {

	@Autowired
	private LoanService service;

	// To create a new Loan...
	@PostMapping("/create")
	ResponseEntity<String> createLoan(@Valid @RequestBody LoanDetails loanDetails) throws MessagingException {
		String msg = this.service.createLoan(loanDetails);
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}

	// To Get all Loan list...
	@GetMapping("/lists")
	List<LoanDetails> loanList() {
		List<LoanDetails> loanList = this.service.loanList();
		return loanList;
	}

	@GetMapping("/list/{id}")
	LoanDetails loanListById(@PathVariable int id) {
		LoanDetails loanList = this.service.loanListById(id);
		return loanList;
	}

	// To change the Paid Status...
	@PutMapping("/PaymentStatus/{paymentId}")
	ResponseEntity<String> paymentStatus(@PathVariable int paymentId) {
		String msg = this.service.paymentStatus(paymentId);
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}
}
