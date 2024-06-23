package com.online.banking.service;

import java.util.List;
import java.util.Optional;

import com.online.banking.entity.Users;
import com.online.banking.exception.OnlineBankingException;
import com.online.banking.exception.UserNotFoundException;
import com.online.banking.request.UserRegistrationRequestDto;



public interface UserService {

	List<Users> getAllUser(Integer pageNumber, Integer pageSize);
	List<Users> getAllActiveUsers(Integer pageNumber, Integer pageSize);
	
	Optional<Users> getUserById(Long userId);
	void updateUser(Long userId, UserRegistrationRequestDto updateUserRequestDto) throws UserNotFoundException,OnlineBankingException;
	void deleteUser(Long userId) throws UserNotFoundException;



}
