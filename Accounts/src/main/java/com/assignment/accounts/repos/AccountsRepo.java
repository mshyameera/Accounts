package com.assignment.accounts.repos;

import java.util.List;

import com.assignment.accounts.dao.AccountsDAO;


public interface AccountsRepo {

	List<AccountsDAO> getAllAccounts();

	AccountsDAO getAccountByAccountNumber(String account_number);
}
