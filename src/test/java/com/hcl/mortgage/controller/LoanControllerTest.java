package com.hcl.mortgage.controller;

import java.util.ArrayList;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.mortgage.dto.LoanDto;
import com.hcl.mortgage.entity.Loan;
import com.hcl.mortgage.service.LoanService;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class LoanControllerTest {
	
	@InjectMocks LoanController loanController;

	@Mock LoanService loanService;
	
	@Test
	public void getLoanListTest(){
		List<LoanDto> eligibleLoans = new ArrayList<>();
		
		LoanDto loanDto = new LoanDto();
		loanDto.setLoanAmount(50000.00);
		loanDto.setLoanEmi(60000);
		loanDto.setLoanInterest(8);
		loanDto.setLoanTenure(15);
		loanDto.setLoanType("home loan");
		eligibleLoans.add(loanDto);
		
		ResponseEntity<List<LoanDto>> controllerRes = new ResponseEntity<List<LoanDto>>(eligibleLoans, HttpStatus.OK);
		
		Mockito.when(loanService.getLoanList(Mockito.anyInt())).thenReturn(eligibleLoans);
		ResponseEntity<List<LoanDto>> loanDtos = loanController.getLoanList(1);
		//System.out.println(loanDtos);
		Assert.assertNotNull(loanDtos);
	}
}
