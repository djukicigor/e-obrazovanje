package vp.spring.rcs.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vp.spring.rcs.model.user.SecurityUserAuthority;

@Repository
public interface UserAuthorityRepository extends JpaRepository<SecurityUserAuthority, Long> {
	
	}

	
	

