package ar.com.gbl.user.challenge.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ar.com.gbl.user.challenge.exception.UserServiceException;
import ar.com.gbl.user.challenge.interfaces.UserInterface;
import ar.com.gbl.user.challenge.model.entity.User;
import ar.com.gbl.user.challenge.model.request.UserRequest;
import ar.com.gbl.user.challenge.repository.UserRepository;
import ar.com.gbl.user.challenge.response.UserResponse;
import ar.com.gbl.user.challenge.utils.BuildPhoneUtils;
import ar.com.gbl.user.challenge.validations.ValidationField;

@Service
public class UserService implements UserInterface {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserResponse createUser(UserRequest request) {

		if (!ValidationField.isValidEmail(request.getEmail())) {
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "El email no tiene un formato valido!");
		}

		if (!ValidationField.isValidPassword(request.getPassword())) {
			throw new UserServiceException(HttpStatus.BAD_REQUEST,
					"La password debe tenes una letra mayuscula, 2 numeros, minimo 8 caracteres y maximo 12!");
		}

		if (userRepo.findByEmail(request.getEmail()) != null) {
			throw new UserServiceException(HttpStatus.CONFLICT, "El usuario ya existe!");
		}

		User user = new User();

		user.setId(UUID.randomUUID().toString());
		user.setEmail(request.getEmail());
		user.setName(request.getName());
		user.setPassword(Base64.getEncoder().encodeToString(request.getPassword().getBytes()));
		user.setCreated(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		user.setToken(Base64.getEncoder().encodeToString(user.getId().getBytes()));
		user.setIsActive(Boolean.TRUE);

		user.setPhones(BuildPhoneUtils.buildPhoneEntity(request.getPhones(), user));

		userRepo.save(user);

		UserResponse response = new UserResponse();

		response.setId(user.getId());
		response.setName(user.getName());
		response.setPassword(user.getPassword());
		response.setEmail(user.getEmail());
		response.setCreated(
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM).format(user.getCreated()));
		response.setIsActive(user.getIsActive());
		response.setLastLogin(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM)
				.format(user.getLastLogin()));
		response.setToken(user.getToken());
		response.setPhones(request.getPhones());

		return response;
	}

	@Override
	public UserResponse loginUser(String token) {
		UserResponse response = new UserResponse();
		User user = userRepo.findByToken(token);

		if (user == null) {
			throw new UserServiceException(HttpStatus.NOT_FOUND, "El usuario no existe!");
		}

		response.setId(user.getId());
		response.setName(user.getName());
		response.setPassword(user.getPassword());
		response.setEmail(user.getEmail());
		response.setCreated(
				DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM).format(user.getCreated()));
		response.setIsActive(user.getIsActive());
		response.setLastLogin(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM)
				.format(LocalDateTime.now()));
		response.setToken(user.getToken());

		response.setPhones(BuildPhoneUtils.buildPhoneRequest(user));

		return response;
	}
}