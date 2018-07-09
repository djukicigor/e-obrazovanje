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

import vp.spring.rcs.model.user.Student;


@Entity
public class Subject_presence {
	

	@Id
    @GeneratedValue
    private Long id;
		
	@ManyToOne(fetch=FetchType.EAGER)
	private Subject subject;	
	
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	private List<Student> students = new ArrayList<Student>();
	
	public Subject_presence(Long id, Subject subject, List<Student> students) {
		super();
		this.id = id;
		this.subject = subject;
		this.students = students;
	}
	
	public Subject_presence() {
		
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
