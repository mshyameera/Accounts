package com.assignment.accounts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.assignment.accounts.exceptionHandlers.CustomException;
import com.assignment.accounts.repos.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;

	public UserDetails getUserByName(String username) {
		// TODO Auto-generated method stub
		try {
			return userRepo.getUserByName(username);
		} catch (Exception e) {
			throw new CustomException("612", "Given user credentials cannot be found. Please try again "+e.getMessage());
		}
	}
	
	

}
