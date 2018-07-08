package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.AuthorityRepository;
import vp.spring.rcs.model.user.SecurityAuthority;

@Component
public class SecurityAuthorityService {
	
	@Autowired
	AuthorityRepository authorityRepository;

	public SecurityAuthority findOne(Long id) {
		return authorityRepository.findOne(id);
	}

	public List<SecurityAuthority> findAll() {
		return authorityRepository.findAll();
	}

	public SecurityAuthority save(SecurityAuthority securityAuthority) {
		return authorityRepository.save(securityAuthority);
	}

	public void remove(Long id) {
		authorityRepository.delete(id);
	}

	


}

