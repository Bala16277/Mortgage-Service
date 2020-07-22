package com.hcl.mortgage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mortgage.dto.LoanDto;
import com.hcl.mortgage.entity.Loan;
import com.hcl.mortgage.service.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {

	@Autowired LoanService loanService;
	
	@GetMapping("")
	public ResponseEntity<List<LoanDto>> getLoanList(@RequestParam int userId){
		List<LoanDto> availableLoans = loanService.getLoanList(userId);
		
		return new ResponseEntity<List<LoanDto>>(availableLoans, HttpStatus.OK);
	}
}
