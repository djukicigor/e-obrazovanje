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

import vp.spring.rcs.model.Student_documents;
import vp.spring.rcs.service.StudentDocumentsService;
import vp.spring.rcs.web.dto.CommonResponseDTO;

@RestController
@RequestMapping(value = "/api/studentdocuments")
public class StudentDocumentsController {

	@Autowired
	StudentDocumentsService studentDocumentsService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Student_documents>> getAll() {
		List<Student_documents> studentDocuments = studentDocumentsService.findAll();
		return new ResponseEntity<>(studentDocuments, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Student_documents> getById(@PathVariable Long id) {
		Student_documents student_documents = studentDocumentsService.findOne(id);
		
		if (student_documents != null) {
			return new ResponseEntity<>(student_documents, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student_documents> create(@RequestBody Student_documents student_documents) {
		Student_documents retVal = studentDocumentsService.save(student_documents);

		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student_documents> update(@PathVariable Long id,
			@RequestBody Student_documents student_documents) {
		if (studentDocumentsService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		student_documents.setId(id);
		Student_documents retVal = studentDocumentsService.save(student_documents);

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CommonResponseDTO> delete(@PathVariable Long id) {
		Student_documents student_documents = studentDocumentsService.findOne(id);
		if (student_documents != null) {
			studentDocumentsService.remove(id);
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.NOT_FOUND);
		}
	}
}
