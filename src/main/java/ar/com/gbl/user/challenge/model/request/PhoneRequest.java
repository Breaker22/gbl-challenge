package ar.com.gbl.user.challenge.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneRequest {
	
	private Long number;
	
	private Integer cityCode;
	
	private String countryCode;

}
