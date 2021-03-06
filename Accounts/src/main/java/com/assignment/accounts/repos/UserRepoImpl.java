package com.assignment.accounts.repos;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.assignment.accounts.dao.UserDAO;
import com.assignment.accounts.exceptionHandlers.CustomException;

@Repository
public class UserRepoImpl implements UserRepo {

	@Override
	public UserDetails getUserByName(String username) {
		if(username.isEmpty()||username.equals(null))
			throw new CustomException("707", "Username is empty. Please try again");
		if (username.equals("admin"))
			return new UserDAO("admin", "admin", new ArrayList<>());
		else if (username.equals("user"))
			return new UserDAO("user", "user", new ArrayList<>());
		else
			return null;
	}

}
