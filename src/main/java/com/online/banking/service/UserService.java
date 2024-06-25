package com.online.banking.service;

import java.util.List;

import com.online.banking.entity.Users;
<<<<<<< HEAD
import com.online.banking.request.UserRegistrationRequestDto;

public interface UserService {

	List<Users> getAllUser(Integer pageNumber, Integer pageSize);
	Users getUserById(Long id);
	void softDeleteUser(Long id);
	Users updateUserDetails(Long id, UserRegistrationRequestDto updatedUserDto);
	List<Users> searchByUserNameOrEmail(String userName, String email);
=======
import com.online.banking.exception.OnlineBankingException;
import com.online.banking.request.UserRegistrationRequestDto;
import com.online.banking.request.UserStatusRequestDto;
import com.online.banking.response.UserPaginationResponse;

public interface UserService {

	UserPaginationResponse getAllUser(Integer pageNumber, Integer pageSize);

	Users getUserById(Long id) throws OnlineBankingException;

	List<Users> findByUserName(String userName);

	List<Users> findByEmail(String email);

	String deleteUserById(Long id) throws OnlineBankingException;

	String updateUserDeletedStatus(Long id, UserStatusRequestDto userStatusRequestDto) throws OnlineBankingException;

	Users updateUser(Long id, UserRegistrationRequestDto updatedUser) throws OnlineBankingException;

	List<Users> searchUsers(String userName, String email);

>>>>>>> 11ea1bb9acc59d536a0eef0cda6d5f27f63379d4
}
