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

	public Integer getUserLoanId() {
		return userLoanId;
	}

	public void setUserLoanId(Integer userLoanId) {
		this.userLoanId = userLoanId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

}
