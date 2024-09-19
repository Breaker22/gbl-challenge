package ar.com.gbl.user.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.gbl.user.challenge.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
	
	public User findByToken(String token);

}
