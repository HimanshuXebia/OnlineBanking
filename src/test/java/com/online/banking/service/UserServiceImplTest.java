package com.online.banking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.online.banking.dao.RegisterUserRepository;
import com.online.banking.entity.Users;
import com.online.banking.exception.OnlineBankingException;
import com.online.banking.response.UserPaginationResponse;
import com.online.banking.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	private RegisterUserRepository registerUserRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Test
	void testGetAllUserReturnEmptyList() {
		Page<Users> userPage = Page.empty();
		Pageable pageable = PageRequest.of(0, 10);
		Mockito.when(registerUserRepository.findByIsDeleted(false, pageable)).thenReturn(userPage);
		UserPaginationResponse userPaginationResponse = userServiceImpl.getAllUser(0, 10);
		assertEquals(0, userPaginationResponse.getTotalCounts());
	}

	@Test
	void testGetUserById_Shoudl_Throw_OnlineBankingException() {

		Mockito.when(registerUserRepository.findById(1L)).thenReturn(Optional.empty());
		Exception exception = assertThrows(OnlineBankingException.class, () -> {
			userServiceImpl.getUserById(1L);
		});
		assertEquals("User is not avaialble in db with id 1", exception.getMessage());

	}

	@Test
	void testGetUserById_Shoudl_Return_User() throws OnlineBankingException {
		Users users = new Users();
		users.setFirstName("Test");
		Mockito.when(registerUserRepository.findById(1L)).thenReturn(Optional.of(users));

		Users user = userServiceImpl.getUserById(1L);
		assertEquals(users.getFirstName(), user.getFirstName());

	}

}
