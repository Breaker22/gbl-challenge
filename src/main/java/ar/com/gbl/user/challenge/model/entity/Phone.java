package ar.com.gbl.user.challenge.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Phone implements Serializable {
	
	private static final long serialVersionUID = 5250775142532239938L;
	
	@Id
	private String id;
	
	private Long number;
	
	private Integer cityCode;
	
	private String countryCode;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

}
