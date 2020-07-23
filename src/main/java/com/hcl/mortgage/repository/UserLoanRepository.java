package com.hcl.mortgage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mortgage.entity.Loan;
import com.hcl.mortgage.entity.User;
import com.hcl.mortgage.entity.UserLoan;

@Repository
public interface UserLoanRepository extends JpaRepository<UserLoan, Integer> {
	
	public Optional<UserLoan> findByUser(User user);
	public Optional<UserLoan> findByUserAndLoan(User user,Loan loan);
//	public Optional<UserLoan> findByUserIdAndLoanId(int userId,int loanId);

}
