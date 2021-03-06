package com.assignment.accounts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.assignment.accounts.exceptionHandlers.CustomException;

@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.isEmpty() || (username.equals(null)))
				throw new CustomException("606", "Given username is null");
		try {
			return userService.getUserByName(username);
		} catch (Exception e) {
			throw new CustomException("607", "Something went wrong. Please contact your administrator "+e.getMessage());			
		}
	}

}
