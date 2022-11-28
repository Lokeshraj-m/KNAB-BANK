package com.bank.knab.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bank.knab.entity.Login;
import com.bank.knab.entity.User;

public interface UserService {

	public List<User> fetchAllUsers();

	public String userRegistration(User user, MultipartFile image);
	
	public User fetchById(long employeeId);

	public HashMap<String, Object> userLogin(Login login);

}
