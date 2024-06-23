package com.online.banking.controller;

import java.util.List;
import java.util.Optional;

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
import com.online.banking.exception.OnlineBankingException;
import com.online.banking.exception.UserNotFoundException;
import com.online.banking.request.UserRegistrationRequestDto;
import com.online.banking.service.UserService;

import jakarta.validation.Valid;

@RequestMapping("/api/v1/")
@RestController
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<Users>> getAllUser(
			@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		List<Users> userList = userService.getAllUser(pageNumber, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}
	
	@GetMapping("/active-users")
	public ResponseEntity<List<Users>> getAllActiveUsers(
			@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		List<Users> userList = userService.getAllActiveUsers(pageNumber, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<Optional<Users>> getUserById(@PathVariable Long userId) {
	   Optional<Users> user = userService.getUserById(userId);
	   return ResponseEntity.status(HttpStatus.OK).body(user);    
	}
	
    @PutMapping("/users/{userId}")
    public ResponseEntity<String> updateUserDetails(
            @PathVariable Long userId,
            @Valid @RequestBody UserRegistrationRequestDto updateUserRequestDto) throws UserNotFoundException,OnlineBankingException{
        
            userService.updateUser(userId, updateUserRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body("User details updated successfully.");

        
    }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(
    		@PathVariable Long userId ) throws UserNotFoundException{
    	userService.deleteUser(userId);
    	return ResponseEntity.status(HttpStatus.OK).body("User Deleted successfully. [Soft-Delete]");
    	
    }
	



}
