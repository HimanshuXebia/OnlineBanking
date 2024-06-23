package com.online.banking.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.online.banking.dao.RegisterUserRepository;
import com.online.banking.entity.Users;
import com.online.banking.exception.OnlineBankingException;
import com.online.banking.request.UserRegistrationRequestDto;
import com.online.banking.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private final RegisterUserRepository registerUserRepository;
	private final ModelMapper modelMapper;
	

	@Autowired
	public RegistrationServiceImpl(RegisterUserRepository registerUserRepository, ModelMapper modelMapper) {
		super();
		this.registerUserRepository = registerUserRepository;
		this.modelMapper = modelMapper;
	}
	
	//isUserNameUnique and IsEmailUnique -> To validate Duplicate entry
    @Override
    public boolean isUsernameUnique(String username) {
        Optional<Users> user = registerUserRepository.findByUserName(username);
//        System.out.println("USER : "+user +" Present : "+ !user.isPresent());
        return !user.isPresent(); // Returns true if user is not present 
    }

    @Override
    public boolean isEmailUnique(String email) {
        Optional<Users> user = registerUserRepository.findByEmail(email);
        return !user.isPresent(); // Returns true if user is not present 
    }

	@Override
	public String registerUser(UserRegistrationRequestDto userRegistrationRequestDto) throws OnlineBankingException {
		Users user = modelMapper.map(userRegistrationRequestDto, Users.class);
		if(!isUsernameUnique(user.getUserName())) {
			throw new OnlineBankingException(HttpStatus.BAD_REQUEST, "Username already exists. Please choose a different username.");
		}
		if(!isEmailUnique(user.getEmail())) {
			 throw new OnlineBankingException(HttpStatus.BAD_REQUEST, "Email address is already registered. Please use a different email.");
		}
		registerUserRepository.save(user);
		return "User has been created.";
	}

}
