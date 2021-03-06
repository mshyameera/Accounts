package com.assignment.accounts.repos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.assignment.accounts.dao.AccountsDAO;
import com.assignment.accounts.exceptionHandlers.CustomException;
import com.assignment.accounts.utils.MaskAccountNumber;

@Repository
public class AccountsRepoImpl implements AccountsRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired 
	private MaskAccountNumber mask;

	public List<AccountsDAO> getAllAccounts() {
		//return jdbcTemplate.queryForList("Select * from account");
		
		try {
			List<AccountsDAO> statements = this.jdbcTemplate.query(String.format(
					"Select * from account"
					), new RowMapper<AccountsDAO>() {
						public AccountsDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
							AccountsDAO account = new AccountsDAO();
							account.setId(rs.getLong("id"));
							account.setAccount_type(rs.getString("account_type"));
							account.setAccount_number(mask.maskNumber((rs.getString("account_number"))));
							return account;
						}
					});
			return statements;
		} catch (Exception e) {
			throw new CustomException("701", "Probelem with data retrieval "+e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	public AccountsDAO getAccountByAccountNumber(String account_number) {
		String sql = "SELECT * FROM account WHERE account_number = ?";
		try {
			return (AccountsDAO) jdbcTemplate.queryForObject(sql, new Object[] { account_number },
					(rs, rowNum) -> new AccountsDAO(rs.getLong("id"), rs.getString("account_type"),
							mask.maskNumber((rs.getString("account_number")))));
		} catch (Exception e) {
			throw new CustomException("702", "Probelem with data retrieval "+e.getMessage());
		}

	}
}