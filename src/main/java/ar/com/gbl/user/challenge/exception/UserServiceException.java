package ar.com.gbl.user.challenge.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = -8142495178168943487L;
	
	private HttpStatus httpStatus;

	public UserServiceException(HttpStatus httpStatus, String message) {
		super(message);

		this.httpStatus = httpStatus;
	}

}
