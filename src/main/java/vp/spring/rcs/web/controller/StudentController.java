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

import vp.spring.rcs.model.user.Student;
import vp.spring.rcs.service.StudentService;

@RestController
@RequestMapping(value = "/api/students")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Student>> getAll() {
		List<Student> students = studentService.findAll();
		return new ResponseEntity<>(students, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Student> getById(@PathVariable Long id) {
		Student student = studentService.findOne(id);
		
		if (student != null) {
			return new ResponseEntity<>(student, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> create(@RequestBody Student student) {
		Student retVal = studentService.save(student);

		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> update(@PathVariable Long id,
			@RequestBody Student student) {
		if (studentService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		student.setId(id);
		Student retVal = studentService.save(student);

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

}
