package com.hcl.mortgage.service;

import com.hcl.mortgage.dto.MortgageRequestDto;
import com.hcl.mortgage.dto.MortgageResponseDto;
import com.hcl.mortgage.dto.ResponseDto;

public interface MortgageService {
	
	public MortgageResponseDto applyForLoan(MortgageRequestDto mortgageRequestDto);
	
	public ResponseDto selectLoan(Integer userId, Integer loanId);

}
