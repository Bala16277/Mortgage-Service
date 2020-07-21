package com.hcl.mortgage.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class UserLoan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userLoanId;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "loanId")
	private Loan loan;

}
