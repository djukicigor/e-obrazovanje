package vp.spring.rcs.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vp.spring.rcs.model.user.Student;
import vp.spring.rcs.model.user.Teacher;

@Entity
public class Passing_exams {
	
	@Id
    @GeneratedValue
    private Long id;
	
	private Date date;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Teacher teacher;
	
	@JsonIgnore
	@ManyToMany(mappedBy="passingExams", fetch=FetchType.LAZY)
	List<Student> students = new ArrayList<Student>();
	
	public Passing_exams(Date date, Teacher teacher) {
		super();
		this.date = date;
		this.teacher = teacher;
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}