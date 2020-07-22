package com.hcl.mortgage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mortgage.entity.UserLoan;

@Repository
public interface UserLoanRepository extends JpaRepository<UserLoan, Integer> {

}
