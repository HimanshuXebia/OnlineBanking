package com.online.banking.service;

import com.online.banking.exception.OnlineBankingException;
import com.online.banking.request.UserRegistrationRequestDto;

public interface RegistrationService {

	String registerUser(UserRegistrationRequestDto userRegistrationRequestDto) throws OnlineBankingException;
	boolean isUsernameUnique(String username);
	boolean isEmailUnique(String email);

}
