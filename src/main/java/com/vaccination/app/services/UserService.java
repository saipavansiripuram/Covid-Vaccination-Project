package com.vaccination.app.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.vaccination.app.models.User;
import com.vaccination.app.repositories.UserRepository;
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	
	public User registerUser(User user) {
		user.setStatus("WAITING");
		return userRepository.registerUser(user);
	}


	public User loginUser(User user) {
		
		return userRepository.loginUser(user);
	}


	public ArrayList<User> getAllUsers() {
		
		return userRepository.getAllUsers();
	}


	public User getUserByAadhaar(String aadhaar) {
		
		return userRepository.getUserByAadhaar(aadhaar);
	}

}
