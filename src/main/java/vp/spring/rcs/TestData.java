package vp.spring.rcs;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vp.spring.rcs.model.Author;
import vp.spring.rcs.model.Passed_exams;
import vp.spring.rcs.model.Passing_exams;
import vp.spring.rcs.model.Record;
import vp.spring.rcs.model.Student_documents;
import vp.spring.rcs.model.Style;
import vp.spring.rcs.model.Subject;
import vp.spring.rcs.model.Subject_lecture;
import vp.spring.rcs.model.Subject_presence;
import vp.spring.rcs.model.Transactions;
import vp.spring.rcs.model.user.SecurityUser;
import vp.spring.rcs.model.user.SecurityAuthority;
import vp.spring.rcs.model.user.SecurityUserAuthority;
import vp.spring.rcs.service.AuthorService;
import vp.spring.rcs.service.PassedExamsService;
import vp.spring.rcs.service.PassingExamsService;
import vp.spring.rcs.service.RecordService;
import vp.spring.rcs.service.StyleService;
import vp.spring.rcs.service.SubjectLectureService;
import vp.spring.rcs.service.SubjectPresenceService;
import vp.spring.rcs.service.SubjectService;
import vp.spring.rcs.service.TeacherService;
import vp.spring.rcs.service.TransactionsService;
import vp.spring.rcs.service.SecurityUserService;
import vp.spring.rcs.service.StudentDocumentsService;
import vp.spring.rcs.service.SecurityAuthorityService;
import vp.spring.rcs.service.SecurityUserAuthorityService;
import vp.spring.rcs.service.StudentService;
import vp.spring.rcs.model.user.Student;
import vp.spring.rcs.model.user.Teacher;

@Component
public class TestData {

	@Autowired
	AuthorService authorService;

	@Autowired
	StyleService styleService;
	
	@Autowired
	RecordService recordService;
	
	@Autowired
	SecurityUserService securityUserService;
	
	@Autowired
	SecurityAuthorityService securityAuthorityService;
	
	@Autowired
	SecurityUserAuthorityService securityUserAuthorityService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	PassedExamsService passedExamsService;
	
	@Autowired
	PassingExamsService passingExamsService;
	
	@Autowired
	StudentDocumentsService studentDocumentsService;
	
	@Autowired
	SubjectLectureService subjectLectureService;
	
	@Autowired
	SubjectPresenceService subjectPresenceService;
	
	@Autowired
	TransactionsService transactionsService;
	

	/*
	 * Inicijalizacija testnih podataka. 
	 */
	@PostConstruct
	public void init(){
		Author milesDavis = new Author("Miles Davis");
		authorService.save(milesDavis);
		
		Author johnColtrane = new Author("John Coltrane");
		authorService.save(johnColtrane);
		
		Author bobDylan = new Author("Aca Dylan");
		authorService.save(bobDylan);
		
		Style coolJazz = new Style("Cool jazz");
		styleService.save(coolJazz);

		Style jazz = new Style("Jazz");
		styleService.save(jazz);
		
		Style avantGardeJazz = new Style("Avant-garde jazz");
		styleService.save(avantGardeJazz);
		
		Style rockAndRoll = new Style("Rock and roll");
		styleService.save(rockAndRoll);
		
		Record highway61Revisited = new Record("Highway 61 Revisited", "https://upload.wikimedia.org/wikipedia/en/9/95/Bob_Dylan_-_Highway_61_Revisited.jpg",
				700, bobDylan);
		highway61Revisited.getStyles().add(rockAndRoll);
		recordService.save(highway61Revisited);
		
		Record birthOfTheCool = new Record("Birth of the Cool","https://upload.wikimedia.org/wikipedia/en/a/a8/Birth_of_the_Cool.jpg",
				950, milesDavis);
		birthOfTheCool.getStyles().add(jazz);
		birthOfTheCool.getStyles().add(coolJazz);
		recordService.save(birthOfTheCool);
		
		Record interstellarSpace = new Record("Interstellar Space", "https://upload.wikimedia.org/wikipedia/en/2/26/John_Coltrane_Interstellar_Space.jpg", 
				500, johnColtrane);
		interstellarSpace.getStyles().add(jazz);
		interstellarSpace.getStyles().add(avantGardeJazz);
		recordService.save(interstellarSpace);
		
		SecurityUser user1 = new SecurityUser("User", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Zoran", "Peric");
		securityUserService.save(user1);
		
		SecurityAuthority authority1 = new SecurityAuthority(1, "User");
		securityAuthorityService.save(authority1);
		
		SecurityUserAuthority userAuthority1 = new SecurityUserAuthority(1, user1, authority1);
		securityUserAuthorityService.save(userAuthority1);
		
		Student student1 = new Student("pera", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Petar", "Petrovic", 123456789, "SF100", 10000);
		studentService.save(student1);
		
		Teacher teacher1 = new Teacher("bojana", "bojana", "Bojana", "Kusljic", 4848552, "uloga");
		teacherService.save(teacher1);
		
		Subject subject1 = new Subject("srpski", "opis");
		subjectService.save(subject1);
		
		List<Student> students = new ArrayList<>();
		students.add(student1);
		
		Passing_exams passingExam1 = new Passing_exams(new Date(), teacher1, students);
		passingExamsService.save(passingExam1);
		
		Passed_exams passedExam1 = new Passed_exams(10, passingExam1, student1);
		passedExamsService.save(passedExam1);
		
		Student_documents studDoc1 = new Student_documents("slika", student1);
		studentDocumentsService.save(studDoc1);
		
		List<Teacher> teachers = new ArrayList<>();
		teachers.add(teacher1);
		
		Subject_lecture subLec1 = new Subject_lecture(subject1, teachers);
		subjectLectureService.save(subLec1);
		
		Subject_presence subPres1 = new Subject_presence(subject1, students);
		subjectPresenceService.save(subPres1);
		
		Transactions transaction1 = new Transactions(45678, student1);
		transactionsService.save(transaction1);
		
//		Printing out objects as JSON for testing
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user1));
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student1));
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(teacher1));
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(subject1));
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(passingExam1));
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(passedExam1));
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(studDoc1));
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(subLec1));
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(subPres1));
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(transaction1));
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
