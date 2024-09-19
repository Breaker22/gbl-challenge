package ar.com.gbl.user.challenge.interfaces;

import ar.com.gbl.user.challenge.model.request.UserRequest;
import ar.com.gbl.user.challenge.response.UserResponse;

public interface UserInterface {
	
	public UserResponse createUser(UserRequest request);
	
	public UserResponse loginUser(String token);

}
