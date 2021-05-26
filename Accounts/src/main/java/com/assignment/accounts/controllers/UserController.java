package com.assignment.accounts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.accounts.dao.AccountStatementsDAO;
import com.assignment.accounts.dao.AccountsDAO;
import com.assignment.accounts.dao.StatementsDAO;
import com.assignment.accounts.services.AccountsService;
import com.assignment.accounts.services.StatementsService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private AccountsService accountService;
	
	@Autowired
	private StatementsService statementsService;
	
	@GetMapping("/{account_number}")
	public ResponseEntity<AccountStatementsDAO> getThreeMonthStatements(@PathVariable("account_number") String account_number){
		AccountsDAO accountsDAO = accountService.getAccountByAccountNumber(account_number); 
		List<StatementsDAO> statementsList = statementsService.getThreeMonthStatements(accountsDAO.getId());
		AccountStatementsDAO accountStatementsDAO = new AccountStatementsDAO(accountsDAO, statementsList);
		return new ResponseEntity<AccountStatementsDAO>(accountStatementsDAO, HttpStatus.OK);
	}

}
