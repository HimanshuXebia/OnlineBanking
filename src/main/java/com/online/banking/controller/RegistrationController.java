package com.online.banking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.banking.exception.OnlineBankingException;
import com.online.banking.request.UserRegistrationRequestDto;
import com.online.banking.service.RegistrationService;

@RequestMapping("/api/v1/")
@RestController
public class RegistrationController {

	private final RegistrationService registrationService;

	@Autowired
	public RegistrationController(RegistrationService registrationService) {
		super();
		this.registrationService = registrationService;
	}

	@PostMapping("register")
	public ResponseEntity<String> registerUser(
			@Valid @RequestBody UserRegistrationRequestDto userRegistrationRequestDto) throws OnlineBankingException {
		String response = registrationService.registerUser(userRegistrationRequestDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
