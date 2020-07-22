package com.hcl.mortgage.dto;

public class LoanDto {

	private String loanType;
	
	private Double loanAmount;
	
	private Integer loanInterest;
	
	private Integer loanTenure;
	
	private Integer loanEmi;

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getLoanInterest() {
		return loanInterest;
	}

	public void setLoanInterest(Integer loanInterest) {
		this.loanInterest = loanInterest;
	}

	public Integer getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(Integer loanTenure) {
		this.loanTenure = loanTenure;
	}

	public Integer getLoanEmi() {
		return loanEmi;
	}

	public void setLoanEmi(Integer loanEmi) {
		this.loanEmi = loanEmi;
	}
}
