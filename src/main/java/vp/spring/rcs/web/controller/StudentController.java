package vp.spring.rcs.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transaction;

import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vp.spring.rcs.model.Passed_exams;
import vp.spring.rcs.model.Passing_exams;
import vp.spring.rcs.model.Subject_presence;
import vp.spring.rcs.model.user.Student;
import vp.spring.rcs.service.PassingExamsService;
import vp.spring.rcs.service.StudentService;
import vp.spring.rcs.service.TransactionsService;
import vp.spring.rcs.web.dto.CommonResponseDTO;

@RestController
@RequestMapping(value = "/api/students")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	PassingExamsService passingExamsService;
	
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
			@RequestBody Student student, @RequestBody Transaction transaction) {
		if (studentService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		student.setId(id);
		
		Student retVal = studentService.save(student);

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CommonResponseDTO> delete(@PathVariable Long id) {
		Student student = studentService.findOne(id);
		if (student != null) {
			studentService.remove(id);
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/exams/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Passing_exams>> getExams(@PathVariable Long id) {
		Student student = studentService.findOne(id);
		List<Subject_presence> subjects = student.getSubjectPresences();
		List<Passing_exams> alreadyPassingExams = student.getPassingExams();
		List<Passed_exams> alreadyPassedExams = student.getPassed_exams();
		List<Passing_exams> passingExams = passingExamsService.findAll();
		List<Passing_exams> fittingExams = new ArrayList<Passing_exams>();
		
		for (Passing_exams exam : passingExams ) {
		    boolean fits = false;
		    for (Subject_presence subject : subjects) {
		    	if (exam.getSubject().getId() == subject.getSubject().getId() && !alreadyPassingExams.contains(exam)) {
		    		fits = true;
//		    		for (Passing_exams passingExam : alreadyPassingExams) {
//		    			if(passingExam.getId() == exam.getSubject().getId()) {
//		    				fits = false;
//		    			}
//		    		}
		    		for (Passed_exams passedExam : alreadyPassedExams) {
		    			if(passedExam.getPassing_exams().getId() == exam.getId()) {
		    				fits = false;
		    			}
		    		}
		    	}
		    }
		    if (fits) {
		    	fittingExams.add(exam);
		    }
		}
		return new ResponseEntity<>(fittingExams, HttpStatus.OK); 
	}
}
