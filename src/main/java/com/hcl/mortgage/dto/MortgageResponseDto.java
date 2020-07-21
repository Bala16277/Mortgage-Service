package com.hcl.mortgage.dto;

import java.util.List;

import com.hcl.mortgage.entity.Loan;

public class MortgageResponseDto {

	private String statusMessage;

	private Integer statusCode;
	
	private List<LoanDto> loanList;

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public List<LoanDto> getLoanList() {
		return loanList;
	}

	public void setLoanList(List<LoanDto> loanList) {
		this.loanList = loanList;
	}


}
