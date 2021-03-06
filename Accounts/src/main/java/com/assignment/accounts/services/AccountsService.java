package com.assignment.accounts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.accounts.dao.AccountsDAO;
import com.assignment.accounts.dao.RequestDAO;
import com.assignment.accounts.exceptionHandlers.CustomException;
import com.assignment.accounts.repos.AccountsRepo;

@Service
public class AccountsService {

	@Autowired
	private AccountsRepo accountsRepo;

	public List<AccountsDAO> getAllAccounts() {
		try {
			return accountsRepo.getAllAccounts();
		} 
		catch (Exception e) {
			throw new CustomException("604", "Problem with retreiving data. Please contact your administrator."+e.getMessage());
		}
	}

	public AccountsDAO getAccountByAccountNumber(String account_number) {
		if (account_number.isEmpty() || (account_number.length() != 13)) {
			throw new CustomException("601", "Given account number is either null or invalid. Please try again");
		}
		try {
			return accountsRepo.getAccountByAccountNumber(account_number);
		}
		catch (Exception e) {
			throw new CustomException("602", "Something went wrong. Please contact your administrator ");
		}
	}
	
	public AccountsDAO getAccountByAccountNumber(RequestDAO requestDAO) {
		String account_number = requestDAO.getAccountnumber();
		String startdate = requestDAO.getStartdate();
		String enddate = requestDAO.getEnddate();
		
		if (account_number.isEmpty() || (account_number.length() != 13) || startdate.isBlank() || startdate.isEmpty() || enddate.isEmpty() || enddate.isBlank()) {
			throw new CustomException("603", "Problem with entered parameters. Please check and try again");
		}
		try {
			return accountsRepo.getAccountByAccountNumber(account_number);
		}
		catch (Exception e) {
			throw new CustomException("604", "Something went wrong. Please contact your administrator ");
		}
	}

}
