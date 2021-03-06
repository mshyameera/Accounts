package com.assignment.accounts.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.accounts.dao.AccountStatementsDAO;
import com.assignment.accounts.dao.AccountsDAO;
import com.assignment.accounts.dao.RequestDAO;
import com.assignment.accounts.dao.StatementsDAO;
import com.assignment.accounts.services.AccountsService;
import com.assignment.accounts.services.StatementsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
	
	@Autowired
	private AccountsService accountService;
	
	@Autowired
	private StatementsService statementsService;
	
	@GetMapping("/")
	public ResponseEntity<List<AccountsDAO>> getAllAccounts(){
		return new ResponseEntity<List<AccountsDAO>>(accountService.getAllAccounts(),HttpStatus.OK);
	}
	
	@GetMapping("/statements")
	public ResponseEntity<List<Map<String, Object>>> getAllStatements(){
		return new ResponseEntity<List<Map<String,Object>>>(statementsService.getAllStatements(), HttpStatus.OK);
	}	
	
	@GetMapping("/{account_number}")
	public ResponseEntity<AccountsDAO> getAccountByAccountNumber(@PathVariable("account_number") String account_number){
		return new ResponseEntity<AccountsDAO>(accountService.getAccountByAccountNumber(account_number), HttpStatus.OK);
	}
	
	@GetMapping("/statements/{account_number}")
	public ResponseEntity<AccountStatementsDAO> getStatementsByAccountNumber(@PathVariable("account_number") String account_number){
		AccountsDAO accountsDAO = accountService.getAccountByAccountNumber(account_number); 
		List<StatementsDAO> statementsList = statementsService.getAllStatementsByAccountId(accountsDAO.getId());
		AccountStatementsDAO accountStatementsDAO = new AccountStatementsDAO(accountsDAO, statementsList);
		return new ResponseEntity<AccountStatementsDAO>(accountStatementsDAO, HttpStatus.OK);
	}
	
	@PostMapping("/statements/date")
	public ResponseEntity<AccountStatementsDAO> getStatementsByAccountNumberWithDate(@Valid @ModelAttribute RequestDAO requestDAO){
		//requestDAO = new RequestDAO("0012250016003", "15.11.2010", "15.11.2020");
		log.info("inside getStatementsByAccountNumberWithDate");
        System.out.println(requestDAO);
		AccountsDAO accountsDAO = accountService.getAccountByAccountNumber(requestDAO.getAccountnumber()); 
		List<StatementsDAO> statementsList = statementsService.getStatementsByAccountNumberWithDate(requestDAO, accountsDAO.getId());
		AccountStatementsDAO accountStatementsDAO = new AccountStatementsDAO(accountsDAO, statementsList);
		return new ResponseEntity<AccountStatementsDAO>(accountStatementsDAO, HttpStatus.OK);
	}

}
