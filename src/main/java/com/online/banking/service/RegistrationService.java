package com.online.banking.service;

import com.online.banking.request.UserRegistrationRequestDto;

public interface RegistrationService {

	String registerUser(UserRegistrationRequestDto userRegistrationRequestDto);

}
