package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.StudentRepository;
import vp.spring.rcs.model.user.Student;

@Component
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public Student findOne(Long id) {
		return studentRepository.findOne(id);
	}

	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	public Student save(Student student) {
		return studentRepository.save(student);
	}

	public void remove(Long id) {
		studentRepository.delete(id);
	}

}
