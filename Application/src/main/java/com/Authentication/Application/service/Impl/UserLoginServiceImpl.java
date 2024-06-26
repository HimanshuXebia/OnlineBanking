package com.Authentication.Application.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Authentication.Application.dao.UserRegisterRepository;
import com.Authentication.Application.entity.LoginDetails;
import com.Authentication.Application.entity.Users;
import com.Authentication.Application.exception.AuthException;
import com.Authentication.Application.request.UserLoginRequestDTO;
import com.Authentication.Application.service.UserLoginService;


@Service
public class UserLoginServiceImpl implements UserLoginService {
	
	private final UserRegisterRepository userRegisterRepository ;
	private final ModelMapper modelMapper;
	
	@Autowired
	public UserLoginServiceImpl(UserRegisterRepository userRegisterRepository,ModelMapper modelMapper) {
		this.userRegisterRepository = userRegisterRepository;	
		this.modelMapper = modelMapper;
		
	}
	
	@Override
    public String loginUser(UserLoginRequestDTO userLoginRequestDTO) throws AuthException {
		LoginDetails loginDetails = modelMapper.map(userLoginRequestDTO, LoginDetails.class);
		Users user = userRegisterRepository.findByUsernameAndPassword(loginDetails.getUsername(),loginDetails.getPassword());
        
		System.out.println("Password : "+loginDetails.getPassword());
		if (user != null && user.getpassword().equals(userLoginRequestDTO.getPassword())) {
            return "Login successful!";
        } else {
            return "Invalid credentials. Please try again.";
        }
       
    }
}
