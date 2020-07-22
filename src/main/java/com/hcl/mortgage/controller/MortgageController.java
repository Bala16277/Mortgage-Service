package com.hcl.mortgage.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mortgage.dto.MortgageRequestDto;
import com.hcl.mortgage.dto.MortgageResponseDto;
import com.hcl.mortgage.dto.ResponseDto;
import com.hcl.mortgage.service.MortgageService;

@RestController
@RequestMapping("/mortgages")
public class MortgageController {
	
	private static Logger logger = Logger.getLogger(MortgageController.class);
	
	@Autowired
	MortgageService mortgageService;
	
	@PostMapping("/apply")
	public ResponseEntity<MortgageResponseDto> applyForLoan(@RequestBody MortgageRequestDto mortgageRequestDto) {
		logger.info("Inside apply method: ");
		MortgageResponseDto mortgageResponseDto;
		mortgageResponseDto = mortgageService.applyForLoan(mortgageRequestDto);
		return new ResponseEntity<>(mortgageResponseDto,HttpStatus.OK);
	}
	
	@PostMapping("/select")
	public ResponseEntity<ResponseDto> selectLoan(@RequestParam Integer userId, @RequestParam Integer loanId) {
		logger.info("inside select loan method: ");
		ResponseDto responseDto;
		responseDto = mortgageService.selectLoan(userId, loanId);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}

}
