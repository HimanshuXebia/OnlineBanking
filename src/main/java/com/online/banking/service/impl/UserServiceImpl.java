package com.online.banking.service.impl;

import java.time.LocalDateTime;
<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> 11ea1bb9acc59d536a0eef0cda6d5f27f63379d4
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.online.banking.dao.RegisterUserRepository;
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
import com.online.banking.util.ConstantUtil;

@Service
public class UserServiceImpl implements UserService {

	private final RegisterUserRepository registerUserRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public UserServiceImpl(RegisterUserRepository registerUserRepository, ModelMapper modelMapper) {
		super();
		this.registerUserRepository = registerUserRepository;
		this.modelMapper = modelMapper;
	}

	// Implementing service to get all the users
	@Override
	public UserPaginationResponse getAllUser(Integer pageNumber, Integer pageSize) {
		UserPaginationResponse response = new UserPaginationResponse();
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Users> userPage = registerUserRepository.findByIsDeleted(false, pageable);
		response.setPageNo(pageNumber);
		response.setPageSize(pageSize);
		response.setTotalCounts(userPage.getTotalElements());
		response.setUserList(userPage.getContent());
//		Optional<Users> registerOptional = registerUserRepository.findById(1L);
		return response;
	}

	@Override
	public Users getUserById(Long id) throws OnlineBankingException {
		Users users = isUserPersists(id);
		return users;
	}

	private Users isUserPersists(Long id) throws OnlineBankingException {
		Optional<Users> userOptional = registerUserRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new OnlineBankingException(HttpStatus.NOT_FOUND, ConstantUtil.USER_NOT_AVAILABLE + id);
		}
		return userOptional.get();
	}

	@Override
	public String deleteUserById(Long id) throws OnlineBankingException {
		Users users = isUserPersists(id);
		registerUserRepository.delete(users);
		return ConstantUtil.USER_DELETE_MESSAGE;
	}

	@Override
	public List<Users> findByUserName(String userName) {
		return registerUserRepository.findByUserName(userName);
	}

	@Override
	public List<Users> findByEmail(String email) {
		return registerUserRepository.findByEmail(email);
	}

	@Override
	public String updateUserDeletedStatus(Long id, UserStatusRequestDto userStatusRequestDto)
			throws OnlineBankingException {
		Users user = isUserPersists(id);
		user.setDeleted(userStatusRequestDto.isDeleted());
		user.setDeletedDate(LocalDateTime.now());
		registerUserRepository.save(user);
		return ConstantUtil.USER_STATUS_MESSAGE;

	}

	@Override
	public Users updateUser(Long id, UserRegistrationRequestDto updatedUser) throws OnlineBankingException {
		Users user = isUserPersists(id);
		if (user.isDeleted()) {
			throw new OnlineBankingException(HttpStatus.BAD_REQUEST, ConstantUtil.USER_UPDATE_ERROR_MESSAGE);
		}
		user.setEmail(updatedUser.getEmail());
		user.setFirstName(updatedUser.getFirstName());
		user.setLastName(updatedUser.getLastName());
		user.setDateOfBirth(updatedUser.getDateOfBirth());
		user.setPhoneNumber(updatedUser.getPhoneNumber());
		return registerUserRepository.save(user);

	}

	@Override
	public List<Users> searchUsers(String userName, String email) {
		List<Users> userList = registerUserRepository.findByUserNameAndEmail(userName, email);
		if (userList.isEmpty()) {
			return new ArrayList<Users>();
		}
		return null;
	}

	// Implementing service to get user by id
	@Override
	public Users getUserById(Long id) {
		Optional<Users> user = registerUserRepository.findById(id);
		return user.orElse(null);
	}

	// Implementing service for soft delete
	public void softDeleteUser(Long userId) {
		Users user = registerUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		user.setDeleted(true);
		user.setDeletedDate(LocalDateTime.now());
		registerUserRepository.save(user);
	}

	// Implementing service to update user details
	@Override
	public Users updateUserDetails(Long id, UserRegistrationRequestDto updatedUserDto) {
		Optional<Users> optionalUser = registerUserRepository.findById(id);
		if (optionalUser.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		Users user = optionalUser.get();
		modelMapper.map(updatedUserDto, user);

		return registerUserRepository.save(user);

	}

	
	// Implementing service to search user using username or email id
	@Override
	public List<Users> searchByUserNameOrEmail(String userName, String email) {
		return registerUserRepository.findByUserNameOrEmail(userName, email);
	}

}
