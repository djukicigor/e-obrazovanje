package vp.spring.rcs.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vp.spring.rcs.model.Student_documents;
import vp.spring.rcs.model.user.SecurityUser;
import vp.spring.rcs.model.user.Student;
import vp.spring.rcs.security.TokenUtils;
import vp.spring.rcs.service.SecurityUserService;
import vp.spring.rcs.service.TeacherService;
import vp.spring.rcs.web.dto.LoginDTO;
import vp.spring.rcs.web.dto.TokenDTO;

@RestController
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	SecurityUserService securityUserService;
	
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            String genToken = tokenUtils.generateToken(details);
            return new ResponseEntity<TokenDTO>(new TokenDTO(genToken), 
            		HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<TokenDTO>(new TokenDTO(""), HttpStatus.BAD_REQUEST);
        }
	}
	
	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public ResponseEntity<List<SecurityUser>> getAll() {
		List<SecurityUser> users = securityUserService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/api/users/username/{username}", method = RequestMethod.GET)
	public ResponseEntity<SecurityUser> getByUsername(@PathVariable String username) {
		SecurityUser student = securityUserService.findByUsername(username);
		
		if (student != null) {
			return new ResponseEntity<>(student, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/api/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<SecurityUser> getById(@PathVariable Long id) {
		SecurityUser student = securityUserService.findOne(id);
		
		if (student != null) {
			return new ResponseEntity<>(student, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
