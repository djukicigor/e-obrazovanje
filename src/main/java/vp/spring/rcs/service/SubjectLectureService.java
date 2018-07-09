package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.SubjectLectureRepository;
import vp.spring.rcs.model.Subject_lecture;

@Component
public class SubjectLectureService {
	
	@Autowired
	SubjectLectureRepository subjectLectureRepository;
	
	public Subject_lecture findOne(Long id) {
		return subjectLectureRepository.findOne(id);
	}

	public List<Subject_lecture> findAll() {
		return subjectLectureRepository.findAll();
	}

	public Subject_lecture save(Subject_lecture subject_lecture) {
		return subjectLectureRepository.save(subject_lecture);
	}

	public void remove(Long id) {
		subjectLectureRepository.delete(id);
	}

}
