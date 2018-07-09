package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.SubjectRepository;
import vp.spring.rcs.model.Subject;

@Component
public class SubjectService {

	@Autowired
	SubjectRepository subjectRepository;
	
	public Subject findOne(Long id) {
		return subjectRepository.findOne(id);
	}

	public List<Subject> findAll() {
		return subjectRepository.findAll();
	}

	public Subject save(Subject subject) {
		return subjectRepository.save(subject);
	}

	public void remove(Long id) {
		subjectRepository.delete(id);
	}
}
