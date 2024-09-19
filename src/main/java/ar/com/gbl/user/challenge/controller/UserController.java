package ar.com.gbl.user.challenge.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gbl.user.challenge.exception.UserServiceException;
import ar.com.gbl.user.challenge.interfaces.UserInterface;
import ar.com.gbl.user.challenge.model.request.UserRequest;
import ar.com.gbl.user.challenge.response.GenericErrorResponse;
import ar.com.gbl.user.challenge.response.UserResponse;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserInterface userInterface;

	@PostMapping("/sign-up")
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userInterface.createUser(request));
	}

	@GetMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestHeader("Authorization") String token) {
		return ResponseEntity.ok().body(userInterface.loginUser(token));
	}

	@ExceptionHandler(UserServiceException.class)
	private ResponseEntity<GenericErrorResponse> handleExUser(UserServiceException ex) {
		GenericErrorResponse errorResponse = new GenericErrorResponse();

		errorResponse.setCode(ex.getHttpStatus().value());
		errorResponse.setDetail(ex.getMessage());
		errorResponse.setTimestamp(LocalDateTime.now().toString());

		return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
	}

}
