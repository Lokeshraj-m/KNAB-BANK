package com.bank.knab.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(String toEmail,String subject,String body) throws MessagingException{
		System.out.println("Mail Initiateed");
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		helper.setFrom("rajramlokesh007@gmail.com");
		helper.setTo(toEmail);
		helper.setSubject(subject);
		helper.setText(body);
		mailSender.send(message);
		System.out.println("Mail Sent Successfully");

	}
}
