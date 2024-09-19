package ar.com.gbl.user.challenge.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericErrorResponse {
	
	private String timestamp;
	
	private Integer code;
	
	private String detail;

}
