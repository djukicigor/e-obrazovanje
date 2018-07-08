package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.UserAuthorityRepository;

import vp.spring.rcs.model.user.SecurityUserAuthority;



@Component
public class SecurityUserAuthorityService {

	@Autowired
	UserAuthorityRepository userAuthorityRepository;

	public SecurityUserAuthority findOne(Long id) {
		return userAuthorityRepository.findOne(id);
	}

	public List<SecurityUserAuthority> findAll() {
		return userAuthorityRepository.findAll();
	}

	public SecurityUserAuthority save(SecurityUserAuthority securityUserAuthority) {
		return userAuthorityRepository.save(securityUserAuthority);
	}

	public void remove(Long id) {
		userAuthorityRepository.delete(id);
	}

	


}

