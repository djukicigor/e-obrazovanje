package vp.spring.rcs.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	private List<Student> students = new ArrayList<Student>();
	
	public Passing_exams(Long id, Date date, Teacher teacher, List<Student> students) {
		super();
		this.id = id;
		this.date = date;
		this.teacher = teacher;
		this.students = students;
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