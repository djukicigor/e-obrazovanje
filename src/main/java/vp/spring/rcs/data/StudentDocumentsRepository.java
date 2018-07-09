package vp.spring.rcs.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vp.spring.rcs.model.Student_documents;


@Repository
public interface StudentDocumentsRepository extends JpaRepository<Student_documents, Long>{

}
