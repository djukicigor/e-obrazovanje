package vp.spring.rcs.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import vp.spring.rcs.model.user.Student;

@Entity
public class Transactions {
	
	@Id
    @GeneratedValue
    private Long id;
	
	private float amount;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Student student;
	
	
	public Transactions(Long id, float amount, Student student) {
		super();
		this.id = id;
		this.amount = amount;
		this.student = student;
	}
	
	public Transactions() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
