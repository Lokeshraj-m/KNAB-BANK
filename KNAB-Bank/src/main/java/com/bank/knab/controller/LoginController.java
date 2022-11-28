package com.bank.knab.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.knab.entity.Login;
import com.bank.knab.service.UserService;

@RestController
@RequestMapping("/Knab-Bank")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/portal/login")
	ResponseEntity<HashMap<String,Object>> userLogin(@RequestBody Login login) {
		HashMap<String, Object> response = this.userService.userLogin(login);
		return new ResponseEntity<HashMap<String,Object>>(response,HttpStatus.OK);
	}
}
