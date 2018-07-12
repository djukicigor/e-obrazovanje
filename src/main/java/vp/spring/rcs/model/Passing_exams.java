package vp.spring.rcs.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import vp.spring.rcs.model.user.Student;
import vp.spring.rcs.model.user.Teacher;

@Entity
public class Passing_exams {
	
	@Id
    @GeneratedValue
    private Long id;
	
	private Date date;

	@ManyToOne(fetch=FetchType.EAGER)
	private Subject subject;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Teacher teacher;

	@ManyToMany(mappedBy="passingExams", fetch=FetchType.EAGER)
	List<Student> students = new ArrayList<Student>();
	
	public Passing_exams(Date date, Subject subject) {
		super();
		this.date = date;
		this.subject = subject;
	}
	
	public Passing_exams() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject= subject;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student student){
		this.students.add(student);
		
		if(!student.getPassingExams().contains(this)){
			student.addPassingExam(this);
		}
	}
	
	public void removeStudent(Student student){
		if(student.getPassingExams().contains(this)){
			student.getPassingExams().remove(this);
		}
		students.remove(student);
	}
}