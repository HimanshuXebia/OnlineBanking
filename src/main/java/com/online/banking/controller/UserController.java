package com.online.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
=======
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
>>>>>>> 11ea1bb9acc59d536a0eef0cda6d5f27f63379d4
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.banking.entity.Users;
<<<<<<< HEAD
import com.online.banking.request.UserRegistrationRequestDto;
=======
import com.online.banking.exception.OnlineBankingException;
import com.online.banking.request.UserRegistrationRequestDto;
import com.online.banking.request.UserStatusRequestDto;
import com.online.banking.response.UserPaginationResponse;
>>>>>>> 11ea1bb9acc59d536a0eef0cda6d5f27f63379d4
import com.online.banking.service.UserService;

import jakarta.websocket.server.PathParam;

@RequestMapping("/api/v1/")
@RestController
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

<<<<<<< HEAD
	// API to get all the users in the db.
=======
	@GetMapping("users/search")
	public ResponseEntity<List<Users>> searchUsers(@RequestParam(required = false) String userName,
			@RequestParam(required = false) String email) {
		List<Users> userList = userService.searchUsers(userName, email);
		return ResponseEntity.ok(userList);
	}

>>>>>>> 11ea1bb9acc59d536a0eef0cda6d5f27f63379d4
	@GetMapping
	public ResponseEntity<UserPaginationResponse> getAllUser(
			@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		UserPaginationResponse response = userService.getAllUser(pageNumber, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable Long id) throws OnlineBankingException {
		Users user = userService.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable Long id) throws OnlineBankingException {
		String response = userService.deleteUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@PatchMapping("/users/{id}")
	public ResponseEntity<String> updateUserDeletedStatus(@PathVariable Long id,
			@org.springframework.web.bind.annotation.RequestBody UserStatusRequestDto userStatusRequestDto)
			throws OnlineBankingException {
		String response = userService.updateUserDeletedStatus(id, userStatusRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<Users> updateUserDetails(@PathVariable Long id,
			@org.springframework.web.bind.annotation.RequestBody UserRegistrationRequestDto updatedUser)
			throws OnlineBankingException {
		Users user = userService.updateUser(id, updatedUser);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	// API to get the user by the user id
	@GetMapping("/user/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable Long id) {
		Users user = userService.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	// API to soft delete the user.
	@DeleteMapping("/delete/{id}")
	public void softDeleteUser(@PathVariable Long id) {
		userService.softDeleteUser(id);
	}

	// API to update user details.
	@PutMapping("/update/{id}")
	public ResponseEntity<Users> updateUserDetails(@PathVariable Long id,
			@RequestBody UserRegistrationRequestDto updatedUser) {
		Users user = userService.updateUserDetails(id, updatedUser);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	// API to search user by username or email id
	@GetMapping("/search")
	public ResponseEntity<?> searchByUsernameOrEmail(@RequestParam(required = false) String userName,
			@RequestParam(required = false) String email) {

		if(userName == null && email == null) {
			return ResponseEntity.badRequest().body("Provide with username or email id");
		}else {
			List<Users> user = userService.searchByUserNameOrEmail(userName, email);
			if(user == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users present in database");
			}
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
	}

}
