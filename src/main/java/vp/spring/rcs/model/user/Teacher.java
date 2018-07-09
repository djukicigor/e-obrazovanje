package vp.spring.rcs.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Teacher extends SecurityUser {
	
	@GeneratedValue
	private int citizenID;
	
	private String role;
	
	public Teacher(int id, String username, String password, String firstName, String lastName, int citizenID, String role) {
		super(id, username, password, firstName, lastName);
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

}
