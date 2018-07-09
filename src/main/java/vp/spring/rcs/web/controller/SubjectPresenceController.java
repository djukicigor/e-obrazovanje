package vp.spring.rcs.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vp.spring.rcs.model.Subject_presence;
import vp.spring.rcs.service.SubjectPresenceService;
import vp.spring.rcs.web.dto.CommonResponseDTO;

@RestController
@RequestMapping(value = "/api/subjectpresence")
public class SubjectPresenceController {

	@Autowired
	SubjectPresenceService subjectPresenceService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Subject_presence>> getAll() {
		List<Subject_presence> subjectPresences = subjectPresenceService.findAll();
		return new ResponseEntity<>(subjectPresences, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Subject_presence> getById(@PathVariable Long id) {
		Subject_presence subject_presence = subjectPresenceService.findOne(id);
		
		if (subject_presence != null) {
			return new ResponseEntity<>(subject_presence, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subject_presence> create(@RequestBody Subject_presence subject_presence) {
		Subject_presence retVal = subjectPresenceService.save(subject_presence);

		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subject_presence> update(@PathVariable Long id,
			@RequestBody Subject_presence subject_presence) {
		if (subjectPresenceService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		subject_presence.setId(id);
		Subject_presence retVal = subjectPresenceService.save(subject_presence);

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CommonResponseDTO> delete(@PathVariable Long id) {
		Subject_presence subject_presence = subjectPresenceService.findOne(id);
		if (subject_presence != null) {
			subjectPresenceService.remove(id);
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.NOT_FOUND);
		}
	}
}
