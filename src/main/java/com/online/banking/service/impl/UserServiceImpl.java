package com.online.banking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.online.banking.dao.RegisterUserRepository;
import com.online.banking.entity.Users;
import com.online.banking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final RegisterUserRepository registerUserRepository;

	@Autowired
	public UserServiceImpl(RegisterUserRepository registerUserRepository) {
		super();
		this.registerUserRepository = registerUserRepository;
	}

	@Override
	public List<Users> getAllUser(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Users> userPage = registerUserRepository.findAll(pageable);

		return userPage.getContent();
	}
	//Get user by userId
	@Override
	public Optional<Users> getUserById(Long userId) {
		if(userId==null) {
			return null;
		}
		return registerUserRepository.findById(userId);
	}


}
