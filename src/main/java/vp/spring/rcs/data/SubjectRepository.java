package vp.spring.rcs.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vp.spring.rcs.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
