package com.assignment.accounts.dao;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatementsDAO {
	
	private Long id;
	
	private Long account_id;	
	
	@JsonFormat(pattern = "dd.MM.yyyy")
	private String datefield;
	
	private BigDecimal amount;

}

