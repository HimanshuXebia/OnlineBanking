package com.online.banking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.banking.entity.Users;

@Repository
public interface RegisterUserRepository extends JpaRepository<Users, Long> {
	List<Users> findByUserNameOrEmail(String userName, String email);
}
