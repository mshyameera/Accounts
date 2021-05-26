package com.assignment.accounts.dao;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountsDAO{
	
	private Long id;
	
	private String account_type;
	
	@NotNull
	@Size(min=13, message="Account no. should have 13 characters")
	private String account_number;

}
