package com.hcl.mortgage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hcl.mortgage.entity.Property;
import com.hcl.mortgage.entity.PropertyDetail;
import com.hcl.mortgage.entity.User;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

	public Property findByUser(User user);

	public PropertyDetail findByPropertyDetail(PropertyDetail propertyDetail);

	public Property getByUser(User user);

}
