package com.assignment.accounts.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.accounts.dao.RequestDAO;
import com.assignment.accounts.dao.StatementsDAO;
import com.assignment.accounts.exceptionHandlers.CustomException;
import com.assignment.accounts.repos.StatementsRepo;

@Service
public class StatementsService {

	@Autowired
	private StatementsRepo statementsRepo;

	public List<Map<String, Object>> getAllStatements() {
		try {
			return statementsRepo.getAllStatements();
		} catch (Exception e) {
			throw new CustomException("609", "Something went wrong. Please contact your administrator "+e.getMessage());
		}
	}

	public List<StatementsDAO> getAllStatementsByAccountId(Long id) {
		try {
			List<StatementsDAO> statementsDAO = statementsRepo.getAllStatementsByAccountId(id);
			return statementsDAO;
		} catch (Exception e) {
			throw new CustomException("610", "Something went wrong. Please contact your administrator "+e.getMessage());
		}
	}

	public List<StatementsDAO> getThreeMonthStatements(Long id) {
		try {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		List<StatementsDAO> statementsDAO = statementsRepo.getAllStatementsByAccountId(id);
		List<StatementsDAO> filteredStatements = new ArrayList<>();
				
		LocalDate maxDate = statementsDAO.stream().map(u -> LocalDate.parse(u.getDatefield(),formatter)).max(LocalDate::compareTo).get();
		
		filteredStatements = statementsDAO.stream().filter(y -> LocalDate.parse(y.getDatefield(),formatter).isAfter(maxDate.minusMonths(3)))
				.collect(Collectors.toList());
			return filteredStatements;
						
		} catch (Exception e) {
			throw new CustomException("612", "Problem with statements retreival. Please contact your administrator "+e.getMessage());
		}
	}
	
	public List<StatementsDAO> getStatementsByAccountNumberWithDate(RequestDAO requestDAO, Long id) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate endDate = LocalDate.parse(requestDAO.getEnddate(),formatter);
		LocalDate startDate = LocalDate.parse(requestDAO.getStartdate(),formatter);
		System.out.println("inside");
				
		try {
			
			List<StatementsDAO> statementsDAO = statementsRepo.getAllStatementsByAccountId(id);
			List<StatementsDAO> filteredStatements = new ArrayList<>();
			
			filteredStatements = statementsDAO.stream().filter(y -> LocalDate.parse(y.getDatefield(),formatter).isAfter(startDate) && LocalDate.parse(y.getDatefield(),formatter).isBefore(endDate)) 
					.collect(Collectors.toList());
			
			return filteredStatements;
			
			
			
		} catch (Exception e) {
			throw new CustomException("613", "Problem with statements retreival. Please contact your administrator "+e.getMessage());
		}
	}

}
