package com.hcl.mortgage.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.mortgage.dto.MortgageRequestDto;
import com.hcl.mortgage.repository.LoanRepository;
import com.hcl.mortgage.repository.PropertyDetailRepository;
import com.hcl.mortgage.repository.PropertyRepository;
import com.hcl.mortgage.repository.UserLoanRepository;
import com.hcl.mortgage.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MortgageServiceImplTest {

	@InjectMocks
	MortgageServiceImpl mortgageServiceImpl;

	@Mock
	PropertyDetailRepository propertyDetailRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	PropertyRepository propertyRepository;

	@Mock
	LoanRepository loanRepository;

	@Mock
	UserLoanRepository userLoanRepository;
	
	@Test
	public void testForAddUserInApplyForLoan() {
		
		MortgageRequestDto mortgageRequestDto = new MortgageRequestDto();
		mortgageRequestDto.setUserId(1);
		
		
	}
}
