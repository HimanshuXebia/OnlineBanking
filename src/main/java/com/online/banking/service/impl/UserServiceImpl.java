package com.online.banking.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import com.online.banking.exception.OnlineBankingException;
import com.online.banking.request.UserRegistrationRequestDto;
import com.online.banking.request.UserStatusRequestDto;
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

	@Override
	public List<Users> getAllUser(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Users> userPage = registerUserRepository.findByIsDeleted(false, pageable);
//		Optional<Users> registerOptional = registerUserRepository.findById(1L);
		return userPage.getContent();
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

}
