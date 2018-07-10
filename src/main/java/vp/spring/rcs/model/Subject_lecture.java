package vp.spring.rcs.model;

import java.util.ArrayList;
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
public class Subject_lecture {
	

	@Id
    @GeneratedValue
    private Long id;
		
	@ManyToOne(fetch=FetchType.EAGER)
	private Subject subject;	
	
	@JsonIgnore
	@ManyToMany(mappedBy="subjectsLecture", fetch=FetchType.LAZY)
	List<Teacher> teachers= new ArrayList<Teacher>();
	
	public Subject_lecture(Subject subject) {
		super();
		this.subject = subject;
	}
	
	public Subject_lecture() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

}
