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

import vp.spring.rcs.model.Passing_exams;
import vp.spring.rcs.model.user.SecurityUser;

@Entity
public class Student extends SecurityUser {
	
	@GeneratedValue
	private int citizenID;

	private String indexNumber;
	
	private float balance;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinTable(name = "securityUser_passingExams", joinColumns = @JoinColumn(name = "securityUser_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "passingExam_id", referencedColumnName = "id"))
	List<Passing_exams> passingExams = new ArrayList<Passing_exams>();

	public Student(String username, String password, String firstName, String lastName, int citizenID, String indexNumber, float balance) {
		super(username, password, firstName, lastName);
		this.citizenID = citizenID;
		this.indexNumber = indexNumber;
		this.balance = balance;
		// TODO Auto-generated constructor stub
	}
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCitizenID() {
		return citizenID;
	}

	public void setCitizenID(int citizenID) {
		this.citizenID = citizenID;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public List<Passing_exams> getPassingExams() {
		return passingExams;
	}

	public void setPassingExams(List<Passing_exams> passingExams) {
		this.passingExams = passingExams;
	}
	
}
