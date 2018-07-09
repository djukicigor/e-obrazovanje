package vp.spring.rcs.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vp.spring.rcs.model.Subject_presence;

@Repository
public interface SubjectPresenceRepository extends JpaRepository<Subject_presence, Long> {

}
