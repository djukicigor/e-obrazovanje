package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.PassedExamsRepository;
import vp.spring.rcs.model.Passed_exams;

@Component
public class PassedExamsService {

	@Autowired
	PassedExamsRepository passedExamsRepository;
	
	public Passed_exams findOne(Long id) {
		return passedExamsRepository.findOne(id);
	}

	public List<Passed_exams> findAll() {
		return passedExamsRepository.findAll();
	}

	public Passed_exams save(Passed_exams passed_exams) {
		return passedExamsRepository.save(passed_exams);
	}

	public void remove(Long id) {
		passedExamsRepository.delete(id);
	}
}
