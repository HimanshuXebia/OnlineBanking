package com.online.banking.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.banking.entity.Users;

@Repository
public interface RegisterUserRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUserName(String userName);

	Optional<Users> findByEmail(String email);

	Page<Users> findByIsDeletedFalse(Pageable pageable);

	Users findUserByUserNameAndEmail(String userName, String email);

}
