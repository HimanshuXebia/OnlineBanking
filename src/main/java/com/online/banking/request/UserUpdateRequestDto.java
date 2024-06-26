package com.online.banking.request;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserUpdateRequestDto {
	@NotEmpty(message = "Please enter a valid user name")
	private String userName;

	@NotEmpty(message = "Please enter a valid email")
	@Email(message = "Email should be valid")
	private String email;

	@NotEmpty(message = "Please enter a valid first name")
	private String firstName;

	private String lastName;
	private String phoneNumber;
	private LocalDateTime dateOfBirth;
	private UserAddressRequestDto userAddressRequestDto;

	// Getters and setters

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public UserAddressRequestDto getUserAddressRequestDto() {
		return userAddressRequestDto;
	}

	public void setUserAddressRequestDto(UserAddressRequestDto userAddressRequestDto) {
		this.userAddressRequestDto = userAddressRequestDto;
	}
}
