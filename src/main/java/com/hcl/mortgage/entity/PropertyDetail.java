package com.hcl.mortgage.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class PropertyDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer propertyDetailId;

	private Integer propertyPincode;

	private Double cost;

	public Integer getPropertyDetailId() {
		return propertyDetailId;
	}

	public void setPropertyDetailId(Integer propertyDetailId) {
		this.propertyDetailId = propertyDetailId;
	}

	public Integer getPropertyPincode() {
		return propertyPincode;
	}

	public void setPropertyPincode(Integer propertyPincode) {
		this.propertyPincode = propertyPincode;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

}
