package com.hcl.mortgage.dto;

import java.util.List;

public class LoanResponseDto {

	private String message;

	private Integer statusCode;
	
	private List<LoanDto> loanDto;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public List<LoanDto> getLoanDto() {
		return loanDto;
	}

	public void setLoanDto(List<LoanDto> loanDto) {
		this.loanDto = loanDto;
	}
}
