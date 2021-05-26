package com.assignment.accounts.repos;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepo {

	UserDetails getUserByName(String username);

}
