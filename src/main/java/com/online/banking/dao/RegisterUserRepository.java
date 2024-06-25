package com.online.banking.dao;

import java.util.List;

<<<<<<< HEAD
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> 11ea1bb9acc59d536a0eef0cda6d5f27f63379d4
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.online.banking.entity.Users;

@Repository
public interface RegisterUserRepository extends JpaRepository<Users, Long> {
<<<<<<< HEAD
	List<Users> findByUserNameOrEmail(String userName, String email);
=======

	List<Users> findByUserName(String userName);

	List<Users> findByEmail(String email);

	Page<Users> findByIsDeleted(boolean isDeleted, Pageable pageable);


	List<Users> findByUserNameAndEmail(String userName, String email);

>>>>>>> 11ea1bb9acc59d536a0eef0cda6d5f27f63379d4
}
