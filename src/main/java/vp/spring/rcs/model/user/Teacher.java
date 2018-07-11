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

import vp.spring.rcs.model.Subject_lecture;
import vp.spring.rcs.model.Subject_presence;

@Entity
public class Teacher extends SecurityUser {
	
	@GeneratedValue
	private int citizenID;
	
	private String role;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinTable(name = "securityUser_subjectLecture", joinColumns = @JoinColumn(name = "securityUser_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
	List<Subject_lecture> subjectsLecture = new ArrayList<Subject_lecture>();
	
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
	
	public List<Subject_lecture> getSubjectPresences() {
		return subjectsLecture;
	}

	public void setSubjectPresences(List<Subject_lecture> subjectsLecture) {
		this.subjectsLecture = subjectsLecture;
	}

}
