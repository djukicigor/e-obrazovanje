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

import vp.spring.rcs.model.Subject;
import vp.spring.rcs.service.SubjectService;
import vp.spring.rcs.web.dto.CommonResponseDTO;

@RestController
@RequestMapping(value = "/api/subjects")
public class SubjectController {

	@Autowired
	SubjectService subjectService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Subject>> getAll() {
		List<Subject> subjects = subjectService.findAll();
		return new ResponseEntity<>(subjects, HttpStatus.OK); 
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Subject> getById(@PathVariable Long id) {
		Subject subject = subjectService.findOne(id);
		
		if (subject != null) {
			return new ResponseEntity<>(subject, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subject> create(@RequestBody Subject subject) {
		Subject retVal = subjectService.save(subject);

		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subject> update(@PathVariable Long id,
			@RequestBody Subject subject) {
		if (subjectService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		subject.setId(id);
		Subject retVal = subjectService.save(subject);

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CommonResponseDTO> delete(@PathVariable Long id) {
		Subject subject = subjectService.findOne(id);
		if (subject != null) {
			subjectService.remove(id);
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.NOT_FOUND);
		}
	}
}
