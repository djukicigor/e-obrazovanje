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

import vp.spring.rcs.model.Subject_lecture;
import vp.spring.rcs.service.SubjectLectureService;
import vp.spring.rcs.web.dto.CommonResponseDTO;

@RestController
@RequestMapping(value = "/api/subjectlectures")
public class SubjectLectureController {

	@Autowired
	SubjectLectureService subjectLectureService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Subject_lecture>> getAll() {
		List<Subject_lecture> subjectLectures = subjectLectureService.findAll();
		return new ResponseEntity<>(subjectLectures, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Subject_lecture> getById(@PathVariable Long id) {
		Subject_lecture subject_lecture = subjectLectureService.findOne(id);
		
		if (subject_lecture != null) {
			return new ResponseEntity<>(subject_lecture, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subject_lecture> create(@RequestBody Subject_lecture subject_lecture) {
		Subject_lecture retVal = subjectLectureService.save(subject_lecture);

		return new ResponseEntity<>(retVal, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subject_lecture> update(@PathVariable Long id,
			@RequestBody Subject_lecture subject_lecture) {
		if (subjectLectureService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		subject_lecture.setId(id);
		Subject_lecture retVal = subjectLectureService.save(subject_lecture);

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CommonResponseDTO> delete(@PathVariable Long id) {
		Subject_lecture subject_lecture = subjectLectureService.findOne(id);
		if (subject_lecture != null) {
			subjectLectureService.remove(id);
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CommonResponseDTO(),HttpStatus.NOT_FOUND);
		}
	}
}
