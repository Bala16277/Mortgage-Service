package com.hcl.mortgage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.mortgage.dto.LoanDto;
import com.hcl.mortgage.dto.MortgageRequestDto;
import com.hcl.mortgage.dto.MortgageResponseDto;
import com.hcl.mortgage.dto.ResponseDto;
import com.hcl.mortgage.entity.Loan;
import com.hcl.mortgage.entity.Property;
import com.hcl.mortgage.entity.PropertyDetail;
import com.hcl.mortgage.entity.User;
import com.hcl.mortgage.entity.UserLoan;
import com.hcl.mortgage.repository.LoanRepository;
import com.hcl.mortgage.repository.PropertyDetailRepository;
import com.hcl.mortgage.repository.PropertyRepository;
import com.hcl.mortgage.repository.UserLoanRepository;
import com.hcl.mortgage.repository.UserRepository;

@Service
public class MortgageServiceImpl implements MortgageService {

	private static Logger logger = Logger.getLogger(MortgageServiceImpl.class);

	@Autowired
	PropertyDetailRepository propertyDetailRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PropertyRepository propertyRepository;

	@Autowired
	LoanRepository loanRepository;

	@Autowired
	UserLoanRepository userLoanRepository;

	public MortgageResponseDto applyForLoan(MortgageRequestDto mortgageRequestDto) {
		MortgageResponseDto mortgageResponseDto = new MortgageResponseDto();
		logger.info("inside apply loan service::::: ");
		User user = new User();
		Property property = new Property();
		BeanUtils.copyProperties(mortgageRequestDto, user);
		BeanUtils.copyProperties(mortgageRequestDto, property);
		PropertyDetail propertyDetail = propertyDetailRepository
				.findByPropertyDetailId(mortgageRequestDto.getPropertyDetailId());
		property.setPropertyDetail(propertyDetail);
		property.setUser(user);
		logger.info("user:::: " + user);
		logger.info("property:" + property);
		userRepository.save(user);
		propertyRepository.save(property);
		double totalIncome = (user.getSalary() + user.getSecondIncome() + user.getOtherIncome());
		logger.info("total income:::: " + totalIncome);
		double emiAmount = (40 * totalIncome) / 100;
		logger.info("emi::: " + emiAmount);
		double loanAmount = ((80) * (property.getPropertyArea() * propertyDetail.getCost())) / 100;
		logger.info("PropertyLaonAmount::: " + loanAmount);
		List<Loan> loanList = loanRepository.findByLoanAmountLessThanEqualAndEmiAmountLessThanEqual(loanAmount,
				emiAmount);
		List<LoanDto> loanDtos = new ArrayList<>();
		for (Loan loan : loanList) {
			LoanDto loanDto = new LoanDto();
			logger.info("loan id: " + loan.getLoanId());
			BeanUtils.copyProperties(loan, loanDto);
			loanDtos.add(loanDto);
		}
		mortgageResponseDto.setStatusMessage("The list of applicable loans for the user is: ");
		mortgageResponseDto.setStatusCode(HttpStatus.OK.value());
		mortgageResponseDto.setLoanList(loanDtos);
		return mortgageResponseDto;
	}

	public ResponseDto selectLoan(Integer userId, Integer loanId) {
		logger.info("inside select a loan method:::: ");
		ResponseDto responseDto = new ResponseDto();
		Optional<User> users = userRepository.findByUserId(userId);
		UserLoan userLoan = new UserLoan();

		if (users.isPresent()) {
			User user = users.get();
			userLoan.setUser(user);
		} else {
			responseDto.setStatusMessage("User does not exist");
			responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
			return responseDto;
		}
		Optional<Loan> loans = loanRepository.findByLoanId(loanId);
		if (loans.isPresent()) {
			Loan loan = loans.get();
			userLoan.setLoan(loan);
		} else {
			responseDto.setStatusMessage("Not a valid loan");
			responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
			return responseDto;
		}
		userLoanRepository.save(userLoan);
		responseDto.setStatusMessage("User has selected a loan");
		responseDto.setStatusCode(HttpStatus.OK.value());
		return responseDto;
	}

}
