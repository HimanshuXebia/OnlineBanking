package com.online.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.banking.entity.Users;
import com.online.banking.exception.OnlineBankingException;
import com.online.banking.request.UserRegistrationRequestDto;
import com.online.banking.request.UserStatusRequestDto;
import com.online.banking.response.UserPaginationResponse;
import com.online.banking.service.UserService;

@RequestMapping("/api/v1/")
@RestController
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("users/search")
	public ResponseEntity<List<Users>> searchUsers(@RequestParam(required = false) String userName,
			@RequestParam(required = false) String email) {
		List<Users> userList = userService.searchUsers(userName, email);
		return ResponseEntity.ok(userList);
	}

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

}
