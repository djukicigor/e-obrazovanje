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

import vp.spring.rcs.model.Passing_exams;
import vp.spring.rcs.model.user.Student;
import vp.spring.rcs.service.PassingExamsService;
import vp.spring.rcs.service.StudentService;
import vp.spring.rcs.web.dto.CommonResponseDTO;

@RestController
@RequestMapping(value = "/api/passingexams")
public class PassingExamsController {

	@Autowired
	PassingExamsService passingExamsService;
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Passing_exams>> getAll() {
		List<Passing_exams> passingExams = passingExamsService.findAll();
		return new ResponseEntity<>(passingExams, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Passing_exams> getById(@PathVariable Long id) {
		Passing_exams passing_exams = passingExamsService.findOne(id);
		
		if (passing_exams != null) {
			return new ResponseEntity<>(passing_exams, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Passing_exams> create(@RequestBody Passing_exams passing_exams) {
		Passing_exams retVal = passingExamsService.save(passing_exams);

		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Passing_exams> update(@PathVariable Long id,
			@RequestBody Passing_exams passing_exams) {
		if (passingExamsService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		passing_exams.setId(id);
		Passing_exams retVal = passingExamsService.save(passing_exams);
		
		Student student = studentService.findOne(retVal.getStudents().get(retVal.getStudents().size() - 1).getId());
		System.out.println(student);
		student.addPassingExam(retVal);
		studentService.save(student);

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CommonResponseDTO> delete(@PathVariable Long id) {
		Passing_exams passing_exams = passingExamsService.findOne(id);
		if (passing_exams != null) {
			passingExamsService.remove(id);
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.NOT_FOUND);
		}
	}
}
