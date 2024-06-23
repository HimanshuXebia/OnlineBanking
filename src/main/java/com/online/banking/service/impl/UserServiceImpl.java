package com.online.banking.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	// Implementing service to get all the users
	@Override
	public List<Users> getAllUser(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Users> userPage = registerUserRepository.findAll(pageable);
		Optional<Users> registerOptional = registerUserRepository.findById(1L);
		return userPage.getContent();
	}

	// Implementing service to get user by id
	@Override
	public Users getUserById(Long id) {
		Optional<Users> user = registerUserRepository.findById(id);
		return user.orElse(null);
	}
	
	// Implementing service for soft delete
	public void softDeleteUser(Long userId) {
        Users user = registerUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setDeleted(true);
        user.setDeletedDate(LocalDateTime.now());
        registerUserRepository.save(user);
    }

}
