package com.online.banking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.banking.entity.Users;

@Repository
public interface RegisterUserRepository extends JpaRepository<Users, Long> {

	List<Users> findByUserName(String userName);

	List<Users> findByEmail(String email);
}
