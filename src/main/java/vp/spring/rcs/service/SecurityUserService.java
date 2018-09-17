package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.UserRepository;
import vp.spring.rcs.model.user.SecurityUser;

@Component
public class SecurityUserService {

	@Autowired
	UserRepository userRepository;

	public SecurityUser findOne(Long id) {
		return userRepository.findOne(id);
	}
	
	public SecurityUser findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<SecurityUser> findAll() {
		return userRepository.findAll();
	}

	public SecurityUser save(SecurityUser securityUser) {
		return userRepository.save(securityUser);
	}

	public void remove(Long id) {
		userRepository.delete(id);
	}

	


}
