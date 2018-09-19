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

import vp.spring.rcs.model.Passed_exams;
import vp.spring.rcs.model.user.Student;
import vp.spring.rcs.service.PassedExamsService;
import vp.spring.rcs.service.StudentService;
import vp.spring.rcs.web.dto.CommonResponseDTO;

@RestController
@RequestMapping(value = "/api/passedexams")
public class PassedExamsController {

	@Autowired
	PassedExamsService passedExamsService;
	
	@Autowired
	StudentService studentsService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Passed_exams>> getAll() {
		List<Passed_exams> passedExams = passedExamsService.findAll();
		return new ResponseEntity<>(passedExams, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Passed_exams> getById(@PathVariable Long id) {
		Passed_exams passed_exams = passedExamsService.findOne(id);
		
		if (passed_exams != null) {
			return new ResponseEntity<>(passed_exams, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Passed_exams> create(@PathVariable Long id, @RequestBody Passed_exams passed_exams) {
		Passed_exams retVal = passedExamsService.save(passed_exams);
		
		Student student = studentsService.findOne(id);
		student.addPassed_exams(passed_exams);
		studentsService.save(student);

		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Passed_exams> update(@PathVariable Long id,
			@RequestBody Passed_exams passed_exams) {
		if (passedExamsService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		passed_exams.setId(id);
		Passed_exams retVal = passedExamsService.save(passed_exams);

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CommonResponseDTO> delete(@PathVariable Long id) {
		Passed_exams passed_exams = passedExamsService.findOne(id);
		if (passed_exams != null) {
			passedExamsService.remove(id);
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.NOT_FOUND);
		}
	}
}
