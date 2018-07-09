package vp.spring.rcs.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import vp.spring.rcs.model.user.Teacher;


@Entity
public class Subject_lecture {
	

	@Id
    @GeneratedValue
    private Long id;
		
	@ManyToOne(fetch=FetchType.EAGER)
	private Subject subject;	
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	private List<Teacher> teachers = new ArrayList<Teacher>();
	
	public Subject_lecture(Long id, Subject subject, List<Teacher> teachers) {
		super();
		this.id = id;
		this.subject = subject;
		this.teachers = teachers;
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
