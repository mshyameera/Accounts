package com.assignment.accounts.dao;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDAO extends User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDAO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}

}
