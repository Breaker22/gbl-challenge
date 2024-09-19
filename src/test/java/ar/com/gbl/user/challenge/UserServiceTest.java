package ar.com.gbl.user.challenge;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.gbl.user.challenge.exception.UserServiceException;
import ar.com.gbl.user.challenge.model.entity.User;
import ar.com.gbl.user.challenge.model.request.PhoneRequest;
import ar.com.gbl.user.challenge.model.request.UserRequest;
import ar.com.gbl.user.challenge.repository.UserRepository;
import ar.com.gbl.user.challenge.service.UserService;
import ar.com.gbl.user.challenge.utils.BuildPhoneUtils;

@SpringBootTest
class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepo;

	@Test
	void testOkCreateUser() {
		UserRequest request = new UserRequest();
		PhoneRequest phoneReq = new PhoneRequest();

		request.setEmail("example@mail.com");
		request.setName("abc");
		request.setPassword("Ab3234fko");

		phoneReq.setCityCode(22);
		phoneReq.setCountryCode("arg");
		phoneReq.setNumber(Long.valueOf(10));

		request.setPhones(Collections.singletonList(phoneReq));

		Assertions.assertNotNull(userService.createUser(request));
		Assertions.assertDoesNotThrow(() -> UserServiceException.class);
	}

	@Test
	void testBadEmail() {
		UserRequest request = new UserRequest();

		request.setEmail("example");
		request.setName("abc");
		request.setPassword("Ab3234fko");

		Assertions.assertNotNull(
				Assertions.assertThrows(UserServiceException.class, () -> userService.createUser(request)));
	}

	@Test
	void testBadPassword() {
		UserRequest request = new UserRequest();

		request.setEmail("example@mail.com");
		request.setName("abc");
		request.setPassword("a");

		Assertions.assertNotNull(
				Assertions.assertThrows(UserServiceException.class, () -> userService.createUser(request)));
	}

	@Test
	void testOkLoginUser() {
		UserRequest request = new UserRequest();
		PhoneRequest phoneReq = new PhoneRequest();

		request.setEmail("example@mail.com");
		request.setName("abc");
		request.setPassword("Ab3234fko");

		phoneReq.setCityCode(22);
		phoneReq.setCountryCode("arg");
		phoneReq.setNumber(Long.valueOf(10));

		request.setPhones(Collections.singletonList(phoneReq));

		Mockito.when(userRepo.findByToken(Mockito.anyString())).thenReturn(buildMockUser(request.getPhones()));

		Assertions.assertNotNull(userService.loginUser("xyz"));
		Assertions.assertDoesNotThrow(() -> UserServiceException.class);
	}

	@Test
	void testUserLoginNotFound() {
		UserRequest request = new UserRequest();

		request.setEmail("example");
		request.setName("abc");
		request.setPassword("Ab3234fko");

		Assertions
				.assertNotNull(Assertions.assertThrows(UserServiceException.class, () -> userService.loginUser("xyz")));
	}

	private User buildMockUser(List<PhoneRequest> listPhones) {
		User user = new User();

		user.setId("acdc");
		user.setEmail("acdb@mail.com");
		user.setCreated(LocalDateTime.now());
		user.setIsActive(Boolean.TRUE);
		user.setLastLogin(LocalDateTime.now());
		user.setName("aa");
		user.setPassword("Example11");
		user.setPhones(BuildPhoneUtils.buildPhoneEntity(listPhones, user));

		return user;
	}
}