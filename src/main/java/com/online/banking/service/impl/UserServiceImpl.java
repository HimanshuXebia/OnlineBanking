package com.online.banking.service.impl;

import java.time.LocalDateTime;
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
import com.online.banking.exception.OnlineBankingException;
import com.online.banking.exception.UserNotFoundException;
import com.online.banking.request.UserRegistrationRequestDto;
import com.online.banking.service.UserService;

import jakarta.validation.Valid;

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
	@Override
	public List<Users> getAllActiveUsers(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Users> userPage = registerUserRepository.findByIsDeletedFalse(pageable);

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
	//Update user
	@Override
	public void updateUser(Long userId, @Valid UserRegistrationRequestDto updateUserRequestDto) throws UserNotFoundException,OnlineBankingException{
		if(userId == null || updateUserRequestDto == null) {
		    throw new OnlineBankingException(HttpStatus.BAD_REQUEST, "User ID cannot be null. Please provide a valid user ID.");
		}

		Users existingUser = registerUserRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with given userId: " + userId));

        existingUser.setFirstName(updateUserRequestDto.getFirstName());
        existingUser.setLastName(updateUserRequestDto.getLastName());
        existingUser.setEmail(updateUserRequestDto.getEmail());

        registerUserRepository.save(existingUser);
	}
	
	//Delete user [Soft-Delete]
	@Override
	public void deleteUser(Long userId) throws UserNotFoundException{
		Users user = registerUserRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found with given userId: "+userId));
		user.setDeleted(true);
		user.setDeletedDate(LocalDateTime.now());
		registerUserRepository.save(user);
	}
	
	//Find user using userName and email
	@Override
	public Users findUser(String userName,String email) throws UserNotFoundException,OnlineBankingException{
    	if(userName==null) {
    		throw new OnlineBankingException(HttpStatus.BAD_REQUEST,"Username is null,provide a valid username.");
    	}
    	if(email==null) {
    		throw new OnlineBankingException(HttpStatus.BAD_REQUEST,"email is null,provide a valid email.");
    	}
		Users user = registerUserRepository.findUserByUserNameAndEmail(userName,email);
		if(user==null) {
			throw new UserNotFoundException("User with username : ["+userName+"] and email : ["+email+"] doesn't exist");
		}

		return user;
	}
	


}
