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


@Entity
public class Subject_presence {
	

	@Id
    @GeneratedValue
    private Long id;
		
	@ManyToOne(fetch=FetchType.EAGER)
	private Subject subject;	
	
	@JsonIgnore
	@ManyToMany(mappedBy="subjectPresences", fetch=FetchType.LAZY)
	List<Student> students = new ArrayList<Student>();
	
	public Subject_presence(Subject subject) {
		super();
		this.subject = subject;
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
	
	public void addStudent(Student student){
		this.students.add(student);
		
		if(!student.getSubjectPresences().contains(this)){
			student.addSubjectPresence(this);
		}
	}
	
	public void removeStudent(Student student){
		if(student.getSubjectPresences().contains(this)){
			student.getSubjectPresences().remove(this);
		}
		students.remove(student);
	}
}
