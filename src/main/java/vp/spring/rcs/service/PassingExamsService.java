package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.PassingExamsRepository;
import vp.spring.rcs.model.Passing_exams;

@Component
public class PassingExamsService {

	@Autowired
	PassingExamsRepository passingExamsRepository;
	
	public Passing_exams findOne(Long id) {
		return passingExamsRepository.findOne(id);
	}

	public List<Passing_exams> findAll() {
		return passingExamsRepository.findAll();
	}

	public Passing_exams save(Passing_exams passing_exams) {
		return passingExamsRepository.save(passing_exams);
	}

	public void remove(Long id) {
		passingExamsRepository.delete(id);
	}
}
