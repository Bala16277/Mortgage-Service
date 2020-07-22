package com.hcl.mortgage.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mortgage.entity.Loan;

@Repository

public interface LoanRepository extends JpaRepository<Loan, Integer> {
	
	public List<Loan> findByLoanAmountLessThanEqualAndEmiAmountLessThanEqual(Double loanAmount, Double emiAmount);
	
	public Optional<Loan> findByLoanId(Integer loanId);
	

}
