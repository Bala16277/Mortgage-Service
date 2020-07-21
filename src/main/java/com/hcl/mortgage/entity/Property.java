package com.hcl.mortgage.entity;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Property {

	private Integer propertyId;

	private String propertyName;

	private String propertyDescription;

	private Double propertyArea;

	private String properyLocation;

	@OneToOne
	@JoinColumn(name = "propertyDetailId")
	private PropertyDetail propertyDetail;

	@OneToOne
	@JoinColumn(name = "userId")
	private User user;

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyDescription() {
		return propertyDescription;
	}

	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}

	public Double getPropertyArea() {
		return propertyArea;
	}

	public void setPropertyArea(Double propertyArea) {
		this.propertyArea = propertyArea;
	}

	public String getProperyLocation() {
		return properyLocation;
	}

	public void setProperyLocation(String properyLocation) {
		this.properyLocation = properyLocation;
	}

	public PropertyDetail getPropertyDetail() {
		return propertyDetail;
	}

	public void setPropertyDetail(PropertyDetail propertyDetail) {
		this.propertyDetail = propertyDetail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
