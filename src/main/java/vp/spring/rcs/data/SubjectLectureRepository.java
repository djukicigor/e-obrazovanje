package vp.spring.rcs.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vp.spring.rcs.model.Subject_lecture;



@Repository
public interface SubjectLectureRepository extends JpaRepository<Subject_lecture, Long>{

}
