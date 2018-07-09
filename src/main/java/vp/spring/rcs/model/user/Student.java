package vp.spring.rcs.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import vp.spring.rcs.model.user.SecurityUser;

@Entity
public class Student extends SecurityUser {
	
	@GeneratedValue
	private int citizenID;
	
	private String indexNumber;


	public Student(int id, String username, String password, String firstName, String lastName, int citizenID, String indexNumber) {
		super(id, username, password, firstName, lastName);
		this.citizenID = citizenID;
		this.indexNumber = indexNumber;
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
	
	

}
