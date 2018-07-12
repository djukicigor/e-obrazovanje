package vp.spring.rcs;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

		
		
		
		
		SecurityUser user1 = new SecurityUser("admin", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Zoran", "Peric");
		SecurityAuthority authority1 = new SecurityAuthority("ROLE_ADMIN");
		SecurityUserAuthority userAuthority1 = new SecurityUserAuthority(user1, authority1);
		SecurityUser user2 = new SecurityUser("teacher", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Jovan", "Jovanovic");
		SecurityAuthority authority2 = new SecurityAuthority("ROLE_TEACHER");
		SecurityUserAuthority userAuthority2 = new SecurityUserAuthority(user2, authority2);
		SecurityUser user3 = new SecurityUser("student", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Ivan", "Ivanovic");
		SecurityAuthority authority3 = new SecurityAuthority("ROLE_STUDENT");
		SecurityUserAuthority userAuthority3 = new SecurityUserAuthority(user3, authority3);
		
		Student student1 = new Student("pera", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Petar", "Petrovic", 123456789, "SF100", 10000);
		SecurityUserAuthority studentAuthority1 = new SecurityUserAuthority(student1, authority3);
		Student student2 = new Student("zoran", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Zoran", "Petrovic", 123456789, "SF120", 10000);
		SecurityUserAuthority studentAuthority2 = new SecurityUserAuthority(student2, authority3);
		Student student3 = new Student("goran", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Goran", "Petrovic", 123456789, "SF130", 10000);
		SecurityUserAuthority studentAuthority3 = new SecurityUserAuthority(student3, authority3);
		Student student4 = new Student("boban", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Boban", "Petrovic", 123456789, "SF130", 10000);
		SecurityUserAuthority studentAuthority4 = new SecurityUserAuthority(student4, authority3);
		
		Teacher teacher1 = new Teacher("bojana", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Bojana", "Kusljic", 4848552, "uloga");
		SecurityUserAuthority teacherAuthority1 = new SecurityUserAuthority(teacher1, authority2);
		Teacher teacher2 = new Teacher("dajana", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Dajana", "Dajanic", 4848552, "uloga");
		SecurityUserAuthority teacherAuthority2 = new SecurityUserAuthority(teacher2, authority2);
		Teacher teacher3 = new Teacher("jovana", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Jovana", "Jovanic", 4848552, "uloga");
		SecurityUserAuthority teacherAuthority3 = new SecurityUserAuthority(teacher3, authority2);
		Teacher teacher4 = new Teacher("ivana", "$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi", "Ivana", "Ivanic", 4848552, "uloga");
		SecurityUserAuthority teacherAuthority4 = new SecurityUserAuthority(teacher4, authority2);
		
		Subject subject1 = new Subject("srpski", "opis");
		Subject subject2 = new Subject("matematika", "opis");
		Subject subject3 = new Subject("engleski", "opis");
		
		Passing_exams passingExam1 = new Passing_exams(new Date(), subject3, teacher1);
		Passing_exams passingExam2 = new Passing_exams(new Date(), subject2, teacher2);
		Passing_exams passingExam3 = new Passing_exams(new Date(), subject3, teacher3);
		Passing_exams passingExam4 = new Passing_exams(new Date(), subject1, teacher1);
		Passing_exams passingExam5 = new Passing_exams(new Date(), subject2, teacher2);
		
		Passed_exams passedExam1 = new Passed_exams(10, passingExam1, student1);
		Passed_exams passedExam2 = new Passed_exams(9, passingExam2, student2);
		Passed_exams passedExam3 = new Passed_exams(8, passingExam3, student3);
		Passed_exams passedExam4 = new Passed_exams(7, passingExam4, student4);
		
		Transactions transaction1 = new Transactions(45678, student1);
		Transactions transaction2 = new Transactions(12000, student2);
		Transactions transaction3 = new Transactions(15000, student3);
		Transactions transaction4 = new Transactions(20000, student4);
		
		Subject_presence subPres1 = new Subject_presence(subject1);
		Subject_presence subPres2 = new Subject_presence(subject2);
		Subject_presence subPres3 = new Subject_presence(subject3);
		Subject_presence subPres4 = new Subject_presence(subject1);
		Subject_presence subPres5 = new Subject_presence(subject2);
		
		Student_documents studDoc1 = new Student_documents("slika", student1);
		Student_documents studDoc2 = new Student_documents("slika", student2);
		Student_documents studDoc3 = new Student_documents("slika", student3);
		Student_documents studDoc4 = new Student_documents("slika", student4);
		
		Subject_lecture subLec1 = new Subject_lecture(subject1);
		Subject_lecture subLec2 = new Subject_lecture(subject2);
		Subject_lecture subLec3 = new Subject_lecture(subject3);
		Subject_lecture subLec4 = new Subject_lecture(subject1);
		Subject_lecture subLec5 = new Subject_lecture(subject2);
		
		securityUserService.save(user1);
		securityUserService.save(user2);
		securityUserService.save(user3);
		securityAuthorityService.save(authority1);
		securityAuthorityService.save(authority2);
		securityAuthorityService.save(authority3);
		securityUserAuthorityService.save(userAuthority1);
		securityUserAuthorityService.save(userAuthority2);
		securityUserAuthorityService.save(userAuthority3);
		
		subjectService.save(subject1);
		subjectService.save(subject2);
		subjectService.save(subject3);
		
		teacherService.save(teacher1);
		teacherService.save(teacher2);
		teacherService.save(teacher3);
		teacherService.save(teacher4);
		securityUserAuthorityService.save(teacherAuthority1);
		securityUserAuthorityService.save(teacherAuthority2);
		securityUserAuthorityService.save(teacherAuthority3);
		securityUserAuthorityService.save(teacherAuthority4);
		
		studentService.save(student1);
		studentService.save(student2);
		studentService.save(student3);
		studentService.save(student4);
		securityUserAuthorityService.save(studentAuthority1);
		securityUserAuthorityService.save(studentAuthority2);
		securityUserAuthorityService.save(studentAuthority3);
		securityUserAuthorityService.save(studentAuthority4);

		passingExamsService.save(passingExam1);
		passingExamsService.save(passingExam2);
		passingExamsService.save(passingExam3);
		passingExamsService.save(passingExam4);
		passingExamsService.save(passingExam5);
		
		passedExamsService.save(passedExam1);
		passedExamsService.save(passedExam2);
		passedExamsService.save(passedExam3);
		passedExamsService.save(passedExam4);
		
		transactionsService.save(transaction1);
		transactionsService.save(transaction2);
		transactionsService.save(transaction3);
		transactionsService.save(transaction4);
		
		subjectPresenceService.save(subPres1);
		subjectPresenceService.save(subPres2);
		subjectPresenceService.save(subPres3);
		subjectPresenceService.save(subPres4);
		subjectPresenceService.save(subPres5);
		
		studentDocumentsService.save(studDoc1);
		studentDocumentsService.save(studDoc2);
		studentDocumentsService.save(studDoc3);
		studentDocumentsService.save(studDoc4);
		
		subjectLectureService.save(subLec1);
		subjectLectureService.save(subLec2);
		subjectLectureService.save(subLec3);
		subjectLectureService.save(subLec4);
		subjectLectureService.save(subLec5);

		
		student1.addPassingExam(passingExam1);
		student1.addPassingExam(passingExam2);
		student1.addTransaction(transaction1);
		student1.addPassed_exams(passedExam1);
		student1.addSubjectPresence(subPres1);
		student1.addSubjectPresence(subPres2);
		student1.addSubjectPresence(subPres3);
		student1.addStudent_documents(studDoc1);
		
		student2.addPassingExam(passingExam2);
		student2.addPassingExam(passingExam3);
		student2.addTransaction(transaction2);
		student2.addPassed_exams(passedExam2);
		student2.addSubjectPresence(subPres2);
		student2.addStudent_documents(studDoc2);
		
		student3.addPassingExam(passingExam3);
		student3.addPassingExam(passingExam2);
		student3.addTransaction(transaction3);
		student3.addPassed_exams(passedExam3);
		student3.addSubjectPresence(subPres3);
		student3.addStudent_documents(studDoc3);
		
		student4.addPassingExam(passingExam1);
		student4.addPassingExam(passingExam2);
		student4.addTransaction(transaction4);
		student4.addPassed_exams(passedExam4);
		student4.addSubjectPresence(subPres4);
		student4.addStudent_documents(studDoc4);
		
//		Printing out objects as JSON for testing
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(passingExam1));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		studentService.save(student1);
		studentService.save(student2);
		studentService.save(student3);
		studentService.save(student4);
		
		teacher1.addSubjectLecture(subLec1);
		teacher1.addPassingExam(passingExam3);
		teacherService.save(teacher1);
		

	}

}
