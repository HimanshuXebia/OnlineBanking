package com.online.banking.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users implements Serializable {

	private static final long serialVersionUID = 8500851767043648592L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(name = "email")
	private String email;

	@Column
	private String userName;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String phoneNumber;

	@Column
	private LocalDateTime dateOfBirth;

	@Column
	private String registrationOtp;

	@Column
	private LocalDateTime registrationOtpTime;

	@Column
	private boolean isBlocked;

	@Column
	private boolean isDeleted;

	@Column
	private Integer noOfAttempt;

	@Column
	private LocalDateTime userLockedTime;

	@Column
	private String forgotPasswordOtp;

	@Column
	private LocalDateTime forgotPasswordOtpTime;

	@Column
	private LocalDateTime createdDate = LocalDateTime.now();

	@Column
	private LocalDateTime deletedDate;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getRegistrationOtp() {
		return registrationOtp;
	}

	public void setRegistrationOtp(String registrationOtp) {
		this.registrationOtp = registrationOtp;
	}

	public LocalDateTime getRegistrationOtpTime() {
		return registrationOtpTime;
	}

	public void setRegistrationOtpTime(LocalDateTime registrationOtpTime) {
		this.registrationOtpTime = registrationOtpTime;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getNoOfAttempt() {
		return noOfAttempt;
	}

	public void setNoOfAttempt(Integer noOfAttempt) {
		this.noOfAttempt = noOfAttempt;
	}

	public LocalDateTime getUserLockedTime() {
		return userLockedTime;
	}

	public void setUserLockedTime(LocalDateTime userLockedTime) {
		this.userLockedTime = userLockedTime;
	}

	public String getForgotPasswordOtp() {
		return forgotPasswordOtp;
	}

	public void setForgotPasswordOtp(String forgotPasswordOtp) {
		this.forgotPasswordOtp = forgotPasswordOtp;
	}

	public LocalDateTime getForgotPasswordOtpTime() {
		return forgotPasswordOtpTime;
	}

	public void setForgotPasswordOtpTime(LocalDateTime forgotPasswordOtpTime) {
		this.forgotPasswordOtpTime = forgotPasswordOtpTime;
	}

	public LocalDateTime getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(LocalDateTime deletedDate) {
		this.deletedDate = deletedDate;
	}

}
