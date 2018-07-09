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

import vp.spring.rcs.model.user.Teacher;
import vp.spring.rcs.service.TeacherService;
import vp.spring.rcs.web.dto.CommonResponseDTO;

@RestController
@RequestMapping(value = "/api/teachers")
public class TeacherController {

	@Autowired
	TeacherService teacherService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Teacher>> getAll() {
		List<Teacher> teachers = teacherService.findAll();
		return new ResponseEntity<>(teachers, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Teacher> getById(@PathVariable Long id) {
		Teacher teacher = teacherService.findOne(id);
		
		if (teacher != null) {
			return new ResponseEntity<>(teacher, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Teacher> create(@RequestBody Teacher teacher) {
		Teacher retVal = teacherService.save(teacher);

		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Teacher> update(@PathVariable Long id,
			@RequestBody Teacher teacher) {
		if (teacherService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		teacher.setId(id);
		Teacher retVal = teacherService.save(teacher);

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CommonResponseDTO> delete(@PathVariable Long id) {
		Teacher teacher = teacherService.findOne(id);
		if (teacher != null) {
			teacherService.remove(id);
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.NOT_FOUND);
		}
	}
}
