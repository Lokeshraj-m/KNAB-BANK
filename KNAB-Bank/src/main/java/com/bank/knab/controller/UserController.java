package com.bank.knab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bank.knab.entity.User;
import com.bank.knab.service.UserService;

@RestController
@RequestMapping("/Knab-Bank/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService registrationService;
	
	@PostMapping(value = {"/registration"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	String userRegistration(@RequestPart("user") User user, @RequestPart("image") MultipartFile image) {
		String msg =this.registrationService.userRegistration(user,image);
		return msg;
	}
	
	@GetMapping("/fetchAllUsers")
	List<User> fetchAllUsers() {
		List<User> users = this.registrationService.fetchAllUsers();
		return users;
	}
}
