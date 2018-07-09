package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.TeacherRepository;
import vp.spring.rcs.model.user.Teacher;

@Component
public class TeacherService {

	@Autowired
	TeacherRepository teacherRepository;
	
	public Teacher findOne(Long id) {
		return teacherRepository.findOne(id);
	}

	public List<Teacher> findAll() {
		return teacherRepository.findAll();
	}

	public Teacher save(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	public void remove(Long id) {
		teacherRepository.delete(id);
	}
}
