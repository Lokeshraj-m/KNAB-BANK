package com.bank.knab.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bank.knab.dao.UserRepository;
import com.bank.knab.entity.ImageModel;
import com.bank.knab.entity.Login;
import com.bank.knab.entity.User;
import com.bank.knab.service.FileService;
import com.bank.knab.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FileService fileService;

	@Override
	public List<User> fetchAllUsers() {
		List<User> users = this.userRepository.findAll();
		return users;
	}

	@Override
	public String userRegistration(User user, MultipartFile file){
		try {
			ImageModel image =fileService.uploadImage(file);
			user.setProfilePicture(image);
			this.userRepository.save(user);
			return "Registered Successfully with employee Id :"+user.getEmployeeId();
		} catch (IOException e) {
			e.printStackTrace();
			return "message : "+e.getMessage();
		}
		
	}

	@Override
	public User fetchById(long employeeId) {
		User user = this.userRepository.findById(employeeId).get();
		return user;
	}

	@Override
	public HashMap<String,Object> userLogin(Login login) {
		HashMap<String,Object> map = new HashMap<>();
		User user = this.userRepository.findByEmail(login.getEmail());
		if(user == null) {
			map.put("message", "Incorrect mailId");
			return map;
		}
		else if(user.getPassword().equals(login.getPassword())){
			map.put("message","correct");
			map.put("user", user);
			return map;
		}
		else {
			map.put("message", "wrong password");
			return map;
		}
	}

}
