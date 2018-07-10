package vp.spring.rcs.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import vp.spring.rcs.model.user.SecurityUser;

@Entity
public class Student extends SecurityUser {
	
	@GeneratedValue
	private int citizenID;

	private String indexNumber;
	
	private float balance;


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
	
	

}
