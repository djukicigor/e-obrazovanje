package vp.spring.rcs.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import vp.spring.rcs.model.Passing_exams;
import vp.spring.rcs.model.user.Student;

@Entity
public class Passed_exams {
	
	@Id
    @GeneratedValue
    private Long id;
	
	private int grade;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Passing_exams passing_exams;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Student student;
	
	
	public Passed_exams(Long id, int grade, Passing_exams passing_exams, Student student) {
		super();
		this.id = id;
		this.grade = grade;
		this.passing_exams = passing_exams;
		this.student = student;
	}
	
	public Passed_exams() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Passing_exams getPassing_exams() {
		return passing_exams;
	}

	public void setPassing_exams(Passing_exams passing_exams) {
		this.passing_exams = passing_exams;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}