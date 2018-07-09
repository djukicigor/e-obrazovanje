package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.StudentDocumentsRepository;
import vp.spring.rcs.model.Student_documents;

@Component
public class StudentDocumentsService {

	@Autowired
	StudentDocumentsRepository studentDocumentsRepository;
	
	public Student_documents findOne(Long id) {
		return studentDocumentsRepository.findOne(id);
	}

	public List<Student_documents> findAll() {
		return studentDocumentsRepository.findAll();
	}

	public Student_documents save(Student_documents student_documents) {
		return studentDocumentsRepository.save(student_documents);
	}

	public void remove(Long id) {
		studentDocumentsRepository.delete(id);
	}
}
