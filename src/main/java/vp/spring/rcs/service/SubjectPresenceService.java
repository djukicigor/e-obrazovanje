package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.SubjectPresenceRepository;
import vp.spring.rcs.model.Subject_presence;

@Component
public class SubjectPresenceService {
	
	@Autowired
	SubjectPresenceRepository subjectPresenceRepository;
	
	public Subject_presence findOne(Long id) {
		return subjectPresenceRepository.findOne(id);
	}

	public List<Subject_presence> findAll() {
		return subjectPresenceRepository.findAll();
	}

	public Subject_presence save(Subject_presence subject_presence) {
		return subjectPresenceRepository.save(subject_presence);
	}

	public void remove(Long id) {
		subjectPresenceRepository.delete(id);
	}

}
