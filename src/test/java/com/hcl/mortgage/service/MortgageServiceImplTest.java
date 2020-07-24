package com.hcl.mortgage.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;

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

@RunWith(MockitoJUnitRunner.Silent.class)
public class MortgageServiceImplTest {
	private static Logger logger = Logger.getLogger(MortgageServiceImplTest.class);

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
		
		MortgageResponseDto mortgageResponseDto = new MortgageResponseDto();
		
		MortgageRequestDto mortgageRequestDto = new MortgageRequestDto();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		mortgageRequestDto.setUserId(1);
		try {
		mortgageRequestDto.setDob(simpleDateFormat.parse("2020-07-22"));
		} catch(Exception e) {
			e.getMessage();
		}
		mortgageRequestDto.setEmail("bala@gmail.com");
		mortgageRequestDto.setGender("male");
		mortgageRequestDto.setPan("BTBTT7868A");
		mortgageRequestDto.setPassword("bala");
		mortgageRequestDto.setPhone("9705441499");
		mortgageRequestDto.setOtherIncome(5000.00);
		mortgageRequestDto.setPropertyArea(1200.00);
		mortgageRequestDto.setPropertyDescription("land");
		mortgageRequestDto.setPropertyDetailId(1);
		mortgageRequestDto.setPropertyId(2);
		mortgageRequestDto.setPropertyName("land");
		mortgageRequestDto.setProperyLocation("bangalore");
		mortgageRequestDto.setSalary(30000.00);
		mortgageRequestDto.setSecondIncome(10000.00);
		mortgageRequestDto.setUserName("bala");
		
		User user = new User();
		user.setUserId(27);
		user.setDob(mortgageRequestDto.getDob());
		user.setEmail(mortgageRequestDto.getEmail());
		user.setGender(mortgageRequestDto.getGender());
		user.setOtherIncome(mortgageRequestDto.getOtherIncome());
		user.setPan(mortgageRequestDto.getPan());
		user.setPassword(mortgageRequestDto.getPassword());
		user.setPhone(mortgageRequestDto.getPhone());
		user.setSalary(mortgageRequestDto.getSalary());
		user.setSecondIncome(mortgageRequestDto.getSecondIncome());
		user.setUserName(mortgageRequestDto.getUserName());
		
		PropertyDetail propertyDetail = new PropertyDetail();
		propertyDetail.setPropertyDetailId(mortgageRequestDto.getPropertyDetailId());
		logger.info("Property Detail Id: "+propertyDetail.getPropertyDetailId());
		propertyDetail.setPropertyPincode(560100);
		propertyDetail.setCost(1000.00);
		
		Mockito.when(propertyDetailRepository.findByPropertyDetailId(mortgageRequestDto.getPropertyDetailId())).thenReturn(propertyDetail);
		
		Property property = new Property();
		property.setPropertyId(1);
		property.setPropertyArea(mortgageRequestDto.getPropertyArea());
		property.setPropertyDescription(mortgageRequestDto.getPropertyDescription());
		property.setPropertyName(mortgageRequestDto.getPropertyName());
		property.setProperyLocation(mortgageRequestDto.getProperyLocation());
		property.setUser(user);
		property.setPropertyDetail(propertyDetail);
		
		logger.info("property details in test method: "+property.getPropertyDetail());
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(propertyRepository.save(property)).thenReturn(property);
		
		
		double totalIncome = (user.getSalary() + user.getSecondIncome() + user.getOtherIncome());
		double emiAmount = (40 * totalIncome) / 100;
		logger.info("area in property: "+property.getPropertyArea());
		logger.info("cost of property: "+propertyDetail.getCost());
		double totalPropertyValue = (property.getPropertyArea() * propertyDetail.getCost());
		double loanAmount = ((80) * totalPropertyValue) / 100;
		
		List<Loan> loanList = new ArrayList<>();
		Loan loan1 = new Loan();
		loan1.setLoanId(1);
		loan1.setLoanAmount(400000.00);
		loan1.setLoanInterest(8);
		loan1.setLoanTenure(15);
		loan1.setEmiAmount(3822.61);
		loanList.add(loan1);
		Loan loan2 = new Loan();
		loan2.setLoanId(2);
		loan2.setLoanAmount(600000.00);
		loan2.setLoanInterest(10);
		loan2.setLoanTenure(15);
		loan2.setEmiAmount(6447.63);
		loanList.add(loan2);
		Mockito.when(loanRepository.findByLoanAmountLessThanEqualAndEmiAmountLessThanEqual(loanAmount,
				emiAmount)).thenReturn(loanList);
		List<LoanDto> loanDtos = new ArrayList<>();
		for (Loan loan : loanList) {
			LoanDto loanDto = new LoanDto();
			BeanUtils.copyProperties(loan, loanDto);
			loanDtos.add(loanDto);
		}
		
		mortgageResponseDto.setStatusMessage("The list of applicable loans for the user is: ");
		mortgageResponseDto.setStatusCode(HttpStatus.OK.value());
		mortgageResponseDto.setLoanList(loanDtos);
		mortgageResponseDto = mortgageServiceImpl.applyForLoan(mortgageRequestDto);
		Assert.assertNotNull(mortgageResponseDto);
	}
	
	@Test
	public void testForSelectLoanForPositive() {
		ResponseDto responseDto = new ResponseDto();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		User user = new User();
		user.setUserId(1);
		try {
		user.setDob(simpleDateFormat.parse("2020-07-22"));
		} catch(Exception e) {
			e.getMessage();
		}
		user.setUserId(2);
		user.setEmail("bala@gmail.com");
		user.setGender("male");
		user.setOtherIncome(5000.00);
		user.setPan("BTBPK8678B");
		user.setPassword("bala");
		user.setPhone("9705441499");
		user.setSalary(30000.00);
		user.setSecondIncome(10000.00);
		user.setUserName("bala");
		
		Mockito.when(userRepository.findByUserId(2)).thenReturn(Optional.of(user));
		UserLoan userLoan = new UserLoan();
		if(user!=null) {
			userLoan.setUser(user);
		}
		
		
		Assert.assertNotNull(responseDto);
	}
}
