package com.online.banking.service;

import java.util.List;

import com.online.banking.entity.Users;

public interface UserService {

	List<Users> getAllUser(Integer pageNumber, Integer pageSize);

}
