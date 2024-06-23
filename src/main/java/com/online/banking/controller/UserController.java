package com.online.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.banking.entity.Users;
import com.online.banking.request.UserRegistrationRequestDto;
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

	// API to get all the users in the db.
	@GetMapping
	public ResponseEntity<List<Users>> getAllUser(
			@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		List<Users> userList = userService.getAllUser(pageNumber, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}
	
	
	// API to get the user by the user id
	@GetMapping("/user/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable Long id){
		Users user = userService.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	// API to soft delete the user.
	@DeleteMapping("/{id}")
	public void softDeleteUser(@PathVariable Long id){
		userService.softDeleteUser(id);
	}
	
	// API to update user details.
	@PutMapping("/{id}")
	public ResponseEntity<Users> updateUserDetails(@PathVariable Long id, @RequestBody UserRegistrationRequestDto updatedUser){
		Users user = userService.updateUserDetails(id, updatedUser);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

}
