package com.assignment.accounts.repos;

import java.util.List;
import java.util.Map;

import com.assignment.accounts.dao.StatementsDAO;

public interface StatementsRepo {

	List<Map<String, Object>> getAllStatements();

	List<StatementsDAO> getAllStatementsByAccountId(Long id);

	String getMaxTransactionDate(Long id);

	List<StatementsDAO> getThreeMonthStatements(Long id, String endDate, String endDate2);

	List<StatementsDAO> getStatementsByAccountNumberWithDate(Long id, String startDate, String endDate);

}
