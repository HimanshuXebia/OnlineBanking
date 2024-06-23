package com.online.banking.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.online.banking.dao.RegisterUserRepository;
import com.online.banking.entity.Users;
import com.online.banking.request.UserRegistrationRequestDto;
import com.online.banking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final RegisterUserRepository registerUserRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public UserServiceImpl(RegisterUserRepository registerUserRepository, ModelMapper modelMapper) {
		super();
		this.registerUserRepository = registerUserRepository;
		this.modelMapper = modelMapper;
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

	// Implementing service to update user details
	@Override
	public Users updateUserDetails(Long id, UserRegistrationRequestDto updatedUserDto) {
		Optional<Users> optionalUser = registerUserRepository.findById(id);
		if (optionalUser.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		Users user = optionalUser.get();
		modelMapper.map(updatedUserDto, user);

		return registerUserRepository.save(user);

	}

}
