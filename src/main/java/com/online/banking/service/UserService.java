package com.online.banking.service;

import java.util.List;
import java.util.Optional;

import com.online.banking.entity.Users;

public interface UserService {

	List<Users> getAllUser(Integer pageNumber, Integer pageSize);
	Optional<Users> getUserById(Long userId);



}
