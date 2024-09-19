package ar.com.gbl.user.challenge.model.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	
	private String name;
	
	@NotNull
	private String email;

	@NotNull
	private String password;
	
	private List<PhoneRequest> phones;

}
