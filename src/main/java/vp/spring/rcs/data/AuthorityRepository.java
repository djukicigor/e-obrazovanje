package vp.spring.rcs.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vp.spring.rcs.model.user.SecurityAuthority;

@Repository
public interface AuthorityRepository extends JpaRepository<SecurityAuthority, Long> {
	List<SecurityAuthority> findByNameContains(String name);
}
