package com.hcl.mortgage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.mortgage.config.ApplicationConstants;
import com.hcl.mortgage.dto.LoanDto;
import com.hcl.mortgage.dto.MortgageRequestDto;
import com.hcl.mortgage.dto.MortgageResponseDto;
import com.hcl.mortgage.dto.ResponseDto;
import com.hcl.mortgage.entity.Loan;
import com.hcl.mortgage.entity.Property;
import com.hcl.mortgage.entity.PropertyDetail;
import com.hcl.mortgage.entity.User;
import com.hcl.mortgage.entity.UserLoan;
import com.hcl.mortgage.exception.LoanNotValidException;
import com.hcl.mortgage.exception.UserNotFoundException;
import com.hcl.mortgage.repository.LoanRepository;
import com.hcl.mortgage.repository.PropertyDetailRepository;
import com.hcl.mortgage.repository.PropertyRepository;
import com.hcl.mortgage.repository.UserLoanRepository;
import com.hcl.mortgage.repository.UserRepository;

/**
 * @author Bala
 * @since 21-07-2020
 * @implNote This is a implementation of MortgageService
 * @implSpec java8 and spring jpa
 */

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
	
	/**
	 * @param mortgageRequestDto the details required from the user
	 * @return MortgageResponseDto the response whether user applied for a loan or not
	 * @since 21-07-2020
	 */

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
		logger.info("User:::: " + user);
		logger.info("Property:" + property);
		logger.info("Info is saved into the user");
		userRepository.save(user);
		logger.info("Info is saved into the property");
		propertyRepository.save(property);
		List<LoanDto> loanDtos = getApplicableLoanList(user.getUserId());
		mortgageResponseDto.setStatusMessage(ApplicationConstants.APPLICABLE_LOAN);
		mortgageResponseDto.setStatusCode(HttpStatus.OK.value());
		mortgageResponseDto.setLoanList(loanDtos);
		return mortgageResponseDto;
	}

	/**
	 * This is a method for getting applicable loans for which user can apply
	 * @param userId Need userId to get a list of applicable loans
	 * @return list of applicable laons
	 */
	public List<LoanDto> getApplicableLoanList(Integer userId) {

		User user = userRepository.getByUserId(userId);
		Property property = propertyRepository.getByUser(user);
		PropertyDetail propertyDetail = property.getPropertyDetail();
		double totalIncome = (user.getSalary() + user.getSecondIncome() + user.getOtherIncome());
		logger.info("Total Income:::: " + totalIncome);
		double emiAmount = ApplicationConstants.EMI_PERCENT * totalIncome;
		logger.info("EMI::: " + emiAmount);
		logger.info("property object: " + property);
		logger.info("area " + property.getPropertyArea());
		logger.info("area cost: " + propertyDetail.getCost());
		double totalPropertyValue = (property.getPropertyArea() * propertyDetail.getCost());
		logger.info("Total Property Value:::: " + totalPropertyValue);
		double loanAmount = ApplicationConstants.PROPERTY_PERCENT
				* (property.getPropertyArea() * propertyDetail.getCost());
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
		return loanDtos;
	}

	/**
	 * @param userId Need a user and we need to check user is present in db or not	
	 * @param loanId To get the valid loan
	 * @return ResponseDto to return the response
	 * @throws LoanNotValidException if loanId is not a valid loan for that user
	 * @throws UserNotFoundException if userId is not present in the database
	 */
	
	public ResponseDto selectLoan(Integer userId, Integer loanId) throws LoanNotValidException, UserNotFoundException {
		logger.info("inside select a loan method:::: ");

		ResponseDto responseDto = new ResponseDto();
		Optional<User> users = userRepository.findByUserId(userId);
		logger.info("user object: " + users);
		
		UserLoan userLoan = new UserLoan();
		if (users.isPresent()) {
			User user = users.get();
			List<Loan> loanList = new ArrayList<>();
			List<LoanDto> listDtos = getApplicableLoanList(user.getUserId());
			Loan loan1 = loanRepository.getByLoanId(loanId);
			for (LoanDto loanDto : listDtos) {
				Loan loan = new Loan();
				BeanUtils.copyProperties(loanDto, loan);
				loanList.add(loan);
			}
			if (userLoanRepository.findByUserAndLoan(user, loan1).isPresent()) {
				responseDto.setStatusMessage(ApplicationConstants.USER_ALREADY_APPLIED);
				responseDto.setStatusCode(HttpStatus.OK.value());
				return responseDto;
			} else if (loanList.contains(loan1)) {
				userLoan.setUser(user);
				userLoan.setLoan(loan1);
				logger.info("userLoan: " + userLoan);
				userLoanRepository.save(userLoan);
				responseDto.setStatusMessage(ApplicationConstants.LOAN_APPLY_SUCCESS);
				responseDto.setStatusCode(HttpStatus.OK.value());
				return responseDto;
			} else {
				throw new LoanNotValidException(ApplicationConstants.LOAN_NOT_APPLICABLE);
			}
		} else {

			throw new UserNotFoundException(ApplicationConstants.USER_NOT_FOUND);
		}

	}
}
