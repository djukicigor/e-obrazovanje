package vp.spring.rcs.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import vp.spring.rcs.model.Passing_exams;
import vp.spring.rcs.model.Subject_lecture;

@Entity
public class Teacher extends SecurityUser {
	
	@GeneratedValue
	private int citizenID;
	
	private String role;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinTable(name = "securityUser_subjectLecture", joinColumns = @JoinColumn(name = "securityUser_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
	List<Subject_lecture> subjectLectures = new ArrayList<Subject_lecture>();
	
	@OneToMany(fetch=FetchType.EAGER)
	List<Passing_exams> passingExams = new ArrayList<Passing_exams>();
	
//	uraditi subject_lecture m2m , passing_exams o2m... namestiti getere setere za sve u svemu(add/remove)
	
	public Teacher(String username, String password, String firstName, String lastName, int citizenID, String role) {
		super(username, password, firstName, lastName);
		this.citizenID = citizenID;
		this.role = role;
		// TODO Auto-generated constructor stub
	}

	public Teacher() {
		// TODO Auto-generated constructor stub
	}

	public int getCitizenID() {
		return citizenID;
	}

	public void setCitizenID(int citizenID) {
		this.citizenID = citizenID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public List<Subject_lecture> getSubjectLectures() {
		return subjectLectures;
	}

	public void setSubjectLectures(List<Subject_lecture> subjectsLecture) {
		this.subjectLectures = subjectsLecture;
	}
	
	public void addSubjectLecture(Subject_lecture subjectLecture){
		this.subjectLectures.add(subjectLecture);
		
		if(!subjectLecture.getTeachers().contains(this)){
			subjectLecture.addTeacher(this);
		}
	}
	
	public void removeSubjectLecture(Subject_lecture subjectLecture){
		if(subjectLecture.getTeachers().contains(this)){
			subjectLecture.getTeachers().remove(this);
		}
		//passingExams.remove(subjectLecture);
	}
	
	
	public List<Passing_exams> getPassingExams() {
		return passingExams;
	}

	public void setPassingExams(List<Passing_exams> passingExams) {
		this.passingExams = passingExams;
	}

	public void addPassingExam(Passing_exams passingExam){
		this.passingExams.add(passingExam);
	}
	
	public void removePassingExam(Passing_exams passingExam){
		passingExams.remove(passingExam);
		
		
	}

}
