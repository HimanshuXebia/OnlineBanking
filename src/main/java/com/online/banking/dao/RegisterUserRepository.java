package com.online.banking.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.online.banking.entity.Users;

@Repository
public interface RegisterUserRepository extends JpaRepository<Users, Long> {

	List<Users> findByUserName(String userName);

	List<Users> findByEmail(String email);

	Page<Users> findByIsDeleted(boolean isDeleted, Pageable pageable);


	List<Users> findByUserNameAndEmail(String userName, String email);

}
