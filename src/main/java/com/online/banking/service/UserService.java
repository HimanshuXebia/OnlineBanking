package com.online.banking.service;

import java.util.List;

import com.online.banking.entity.Users;

public interface UserService {

	List<Users> getAllUser(Integer pageNumber, Integer pageSize);

	Object getUserById(Long id);

	List<Users> findByUserName(String userName);

	List<Users> findByEmail(String email);

	void deleteUserById(Long id);
	void softDeleteUserById(Long id);

}
