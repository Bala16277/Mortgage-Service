package com.hcl.mortgage.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.mortgage.dto.LoanDto;
//import com.hcl.mortgage.dto.LoanResponseDto;
import com.hcl.mortgage.entity.Loan;
import com.hcl.mortgage.entity.Property;
import com.hcl.mortgage.entity.PropertyDetail;
import com.hcl.mortgage.entity.User;
import com.hcl.mortgage.repository.LoanRepository;
import com.hcl.mortgage.repository.PropertyDetailRepository;
import com.hcl.mortgage.repository.PropertyRepository;
import com.hcl.mortgage.repository.UserRepository;


@Service
public class LoanService {

	private static Logger logger = Logger.getLogger(LoanService.class);
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	PropertyRepository propertyRepository;

	@Autowired
	PropertyDetailRepository propertyDetailRepository;

	@Autowired
	LoanRepository loanRepository;

	public List<LoanDto> getLoanList(int userId) {

		User user = userRepository.getByUserId(userId);

		double income = user.getSalary() + user.getSecondIncome() + user.getOtherIncome();

		double emi = ((income) * 40) / 100;
		logger.info("Eligible EMI amount is:::" + emi);
		Property property = propertyRepository.findByUser(user);
		double propertyArea = property.getPropertyArea();
		
		PropertyDetail propertyDetail = (property.getPropertyDetail());
		double propertyValuePerUnit = propertyDetail.getCost();
		logger.info("Area per squere feet is:::" + propertyValuePerUnit);
		double propertyValue = propertyArea * propertyValuePerUnit;
		double loanValue = (propertyValue * 60) / 100;
		logger.info("Eligible Loan ammount is :::" + loanValue);
		
		List<LoanDto> eligibleLoans = new ArrayList<>();
		
		List<Loan> loans = loanRepository.findAll();

		for (Loan loan : loans) {
			if (loan.getEmiAmount() <= emi && loan.getLoanAmount() <= loanValue) {
				LoanDto loanDto = new LoanDto();
				try {
					BeanUtils.copyProperties(loanDto, loan);
					eligibleLoans.add(loanDto);
				} catch (Exception exception) {
					logger.info(exception.getMessage());
				}
				
			}

		}

		return eligibleLoans;

	}
}
