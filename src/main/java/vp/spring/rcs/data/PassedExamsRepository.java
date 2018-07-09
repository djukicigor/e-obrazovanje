package vp.spring.rcs.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vp.spring.rcs.model.Passed_exams;


@Repository
public interface PassedExamsRepository extends JpaRepository<Passed_exams, Long>{

}
