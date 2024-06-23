package com.online.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.banking.entity.Users;
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
	public ResponseEntity<?> searchUsers(@RequestParam(required = false) String userName,
			@RequestParam(required = false) String email) {
		if (userName == null && email == null) {
			return ResponseEntity.badRequest().body("Either userName or email must be provided.");
		}
		List<Users> users;
		if (userName != null) {
			users = userService.findByUserName(userName);
		} else {
			users = userService.findByEmail(email);
		}
		if (users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found.");
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping
	public ResponseEntity<List<Users>> getAllUser(
			@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		List<Users> userList = userService.getAllUser(pageNumber, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}

	@GetMapping("/user/")
	public ResponseEntity<Object> getUserById(Long id) {
//		List<Users> userList = userService.getAllUser(pageNumber, pageSize);
		Object user = userService.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@DeleteMapping("/users/delete")
	public void deleteUserById(Long id) {
		userService.deleteUserById(id);
	}

}
