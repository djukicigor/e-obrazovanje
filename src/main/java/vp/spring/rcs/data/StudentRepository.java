package vp.spring.rcs.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vp.spring.rcs.model.user.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
}

