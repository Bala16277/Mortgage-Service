package com.hcl.mortgage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.mortgage.entity.PropertyDetail;

@Repository
public interface PropertyDetailRepository extends JpaRepository<PropertyDetail, Integer>{

	//PropertyDetail getPropertyDetailByPropertyDetailId(int userId);

	PropertyDetail getByPropertyDetailId(int userId);

}
