package vp.spring.rcs.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vp.spring.rcs.model.user.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}