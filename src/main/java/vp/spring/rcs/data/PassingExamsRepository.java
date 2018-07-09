package vp.spring.rcs.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vp.spring.rcs.model.Passing_exams;


@Repository
public interface PassingExamsRepository extends JpaRepository<Passing_exams, Long>{

}
