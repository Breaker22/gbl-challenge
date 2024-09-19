package ar.com.gbl.user.challenge.response;

import ar.com.gbl.user.challenge.model.request.UserRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse extends UserRequest {
	
	private String id;
	
	private String created;
	
	private String lastLogin;
	
	private String token;
	
	private Boolean isActive;

}
