package com.hcl.mortgage.dto;

import java.util.Date;

public class MortgageRequestDto {

	private Integer userId;

	private String userName;

	private String password;

	private Date dob;

	private String gender;

	private String email;

	private String phone;

	private String pan;

	private Double salary;

	private Double secondIncome;

	private Double otherIncome;

	private Integer propertyId;

	private String propertyName;

	private String propertyDescription;

	private Double propertyArea;

	private String properyLocation;

	private Integer propertyDetailId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getSecondIncome() {
		return secondIncome;
	}

	public void setSecondIncome(Double secondIncome) {
		this.secondIncome = secondIncome;
	}

	public Double getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}

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

	public Integer getPropertyDetailId() {
		return propertyDetailId;
	}

	public void setPropertyDetailId(Integer propertyDetailId) {
		this.propertyDetailId = propertyDetailId;
	}

}
