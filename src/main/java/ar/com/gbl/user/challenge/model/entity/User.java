package ar.com.gbl.user.challenge.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User implements Serializable {

	private static final long serialVersionUID = -3828735895338493130L;

	@Id
	private String id;

	private String name;

	private String email;

	private String password;

	private LocalDateTime created;

	private LocalDateTime lastLogin;

	private String token;

	private Boolean isActive;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Phone> phones;

}
