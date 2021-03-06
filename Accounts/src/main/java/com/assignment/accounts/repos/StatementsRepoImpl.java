package com.assignment.accounts.repos;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import com.assignment.accounts.dao.StatementsDAO;
import com.assignment.accounts.exceptionHandlers.CustomException;


@Repository
public class StatementsRepoImpl implements StatementsRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Map<String, Object>> getAllStatements() {
		try {
			// TODO Auto-generated method stub
			return jdbcTemplate.queryForList("Select * from statement");
		} catch (Exception e) {
			throw new CustomException("703", "Probelem with statements retrieval "+e.getMessage());
		}
	}

	@Override
	public List<StatementsDAO> getAllStatementsByAccountId(Long id) {
		// TODO Auto-generated method stub
		try {
			List<StatementsDAO> statements = this.jdbcTemplate.query(
					String.format("SELECT * FROM statement WHERE account_id = (%s)", id),
					new RowMapper<StatementsDAO>() {
						public StatementsDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
							StatementsDAO statement = new StatementsDAO();
							statement.setId(rs.getLong("id"));
							statement.setAccount_id(rs.getLong("account_id"));
							statement.setDatefield(rs.getString("datefield"));
							statement.setAmount(rs.getBigDecimal("amount"));
							return statement;
						}
					});
			return statements;
		} catch (Exception e) {
			throw new CustomException("704", "Probelem with statements retrieval "+e.getMessage());
		}
	}

	@Override
	public List<StatementsDAO> getThreeMonthStatements(Long id, String startDate, String endDate) {
		String sql = String.format(
				"SELECT * FROM statement WHERE account_id = (%s) and datefield between  \"%s\" and \"%s\"",
				id, startDate, endDate);
		System.out.println(sql);
	
		try {
			List<StatementsDAO> statements = this.jdbcTemplate.query(sql, new RowMapper<StatementsDAO>() {
						public StatementsDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
							StatementsDAO statement = new StatementsDAO();
							statement.setId(rs.getLong("id"));
							statement.setAccount_id(rs.getLong("account_id"));
							statement.setDatefield(rs.getString("datefield"));
							statement.setAmount(rs.getBigDecimal("amount"));
							return statement;
						}
					});
			return statements;
		} catch (Exception e) {
			throw new CustomException("705", "Probelem with statements retrieval "+e.getMessage());
		}
	}

	@Override
	public String getMaxTransactionDate(Long id) {
		try {
			return jdbcTemplate.queryForObject(
					String.format("Select max(datefield) from statement WHERE account_id = (%s)", id),
					String.class);
		} catch (Exception e) {
			throw new CustomException("706", "Probelem with date value "+e.getMessage());
		}
	}

	@Override
	public List<StatementsDAO> getStatementsByAccountNumberWithDate(Long id, String startDate, String endDate) {
		String sql = String.format(
				"SELECT * FROM statement WHERE account_id = (%s) and datefield between  \"%s\" and \"%s\"",
				id, startDate, endDate);
		System.out.println(sql);
		try {
			List<StatementsDAO> statements = this.jdbcTemplate.query(sql, new RowMapper<StatementsDAO>() {
						public StatementsDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
							StatementsDAO statement = new StatementsDAO();
							statement.setId(rs.getLong("id"));
							statement.setAccount_id(rs.getLong("account_id"));
							statement.setDatefield(rs.getString("datefield"));
							statement.setAmount(rs.getBigDecimal("amount"));
							return statement;
						}
					});
			return statements;
		} catch (Exception e) {
			throw new CustomException("707", "Probelem with statements retrieval "+e.getMessage());
		}
	}

}
