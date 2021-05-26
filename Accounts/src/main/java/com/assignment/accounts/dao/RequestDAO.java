package com.assignment.accounts.dao;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Component
public class RequestDAO {
	
	private String accountnumber;
	private String startdate;
	private String enddate;
}
