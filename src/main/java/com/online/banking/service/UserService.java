package com.online.banking.service;

import java.util.List;

import com.online.banking.entity.Users;
import com.online.banking.request.UserRegistrationRequestDto;

public interface UserService {

	List<Users> getAllUser(Integer pageNumber, Integer pageSize);
	Users getUserById(Long id);
	void softDeleteUser(Long id);
	Users updateUserDetails(Long id, UserRegistrationRequestDto updatedUserDto);
	List<Users> searchByUserNameOrEmail(String userName, String email);
}
