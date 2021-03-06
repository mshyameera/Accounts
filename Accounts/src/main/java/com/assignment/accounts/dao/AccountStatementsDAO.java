package com.assignment.accounts.dao;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatementsDAO {

	private AccountsDAO account;
	private List<StatementsDAO> statements;
}
