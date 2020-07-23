package com.hcl.mortgage.controller;


/**
 * @author Bala Suresh Krishna. K
 * @description This is a mortgage application
 * @since 21-07-2020
 * 
 */
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
import com.hcl.mortgage.exception.LoanNotValidException;
import com.hcl.mortgage.exception.UserNotFoundException;
import com.hcl.mortgage.service.MortgageService;

/**
 * This is a mortgage application controller
 *
 */

@RestController
@RequestMapping("/mortgages")
public class MortgageController {
	
	private static Logger logger = Logger.getLogger(MortgageController.class);
	
	@Autowired
	MortgageService mortgageService;
	
	/**
	 * @author Bala
	 * @param mortgageRequestDto to get the user and property details of the user
	 * @return MortgageResponseDto to display the response
	 */
	
	@PostMapping("/apply")
	public ResponseEntity<MortgageResponseDto> applyForLoan(@RequestBody MortgageRequestDto mortgageRequestDto) {
		logger.info("inside apply method of mortgage controller");
		MortgageResponseDto mortgageResponseDto;
		mortgageResponseDto = mortgageService.applyForLoan(mortgageRequestDto);
		return new ResponseEntity<>(mortgageResponseDto,HttpStatus.OK);
	}
	
	/**
	 * @param userId we need userid to get the particular user
	 * @param loanId we need loanId to get the loan details
	 * @return ResponseDto returning the responseDto containing the applied loan
	 * @throws LoanNotValidException if loanId is not a valid loan for that user
	 * @throws UserNotFoundException if userId is not present in the database
	 */
	@PostMapping("/select")
	public ResponseEntity<ResponseDto> selectLoan(@RequestParam Integer userId, @RequestParam Integer loanId) throws LoanNotValidException,UserNotFoundException{
		logger.info("inside select loan method of mortgage controller");
		ResponseDto responseDto;
		responseDto = mortgageService.selectLoan(userId, loanId);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}

}
