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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import vp.spring.rcs.model.Passed_exams;
import vp.spring.rcs.model.Passing_exams;
import vp.spring.rcs.model.Student_documents;
import vp.spring.rcs.model.Subject_presence;
import vp.spring.rcs.model.Transactions;
import vp.spring.rcs.model.user.SecurityUser;

@Entity
public class Student extends SecurityUser {
	
	@GeneratedValue
	private int citizenID;

	private String indexNumber;
	
	private float balance;
	
	@JsonIgnoreProperties("students")
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinTable(name = "securityUser_passingExams", joinColumns = @JoinColumn(name = "securityUser_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "passingExam_id", referencedColumnName = "id"))
	List<Passing_exams> passingExams = new ArrayList<Passing_exams>();
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinTable(name = "securityUser_subjectPresence", joinColumns = @JoinColumn(name = "securityUser_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
	List<Subject_presence> subjectPresences = new ArrayList<Subject_presence>();

	@OneToMany(fetch=FetchType.LAZY)
	List<Student_documents> studentDocuments = new ArrayList<Student_documents>();
	
	@OneToMany(fetch=FetchType.LAZY)
	List<Transactions> transactions = new ArrayList<Transactions>();
	
	@OneToMany(fetch=FetchType.LAZY)
	List<Passed_exams> passedExams = new ArrayList<Passed_exams>();
	
//	uraditi m2m subject_presence, student_document o2m, passed_exams o2m namestiti getere setere za sve u svemu(add/remove)
	
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
	
	public List<Subject_presence> getSubjectPresences() {
		return subjectPresences;
	}

	public void setSubjectPresences(List<Subject_presence> subjectPresences) {
		this.subjectPresences = subjectPresences;
	}
	
	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}
	
	public void addTransaction(Transactions transaction){
		this.transactions.add(transaction);
	}
	
	public void removeTransaction(Transactions transaction){
		transactions.remove(transaction);
	}
	
	public List<Passed_exams> getPassed_exams() {
		return passedExams;
	}

	public void setPassed_exams(List<Passed_exams> passedExams) {
		this.passedExams = passedExams;
	}
	
	public void addPassed_exams(Passed_exams passedExam){
		this.passedExams.add(passedExam);
	}
	
	public void removePassed_exams(Passed_exams passedExam){
		passedExams.remove(passedExam);
	}
	
	public List<Student_documents> getStudent_documents() {
		return studentDocuments;
	}

	public void setStudent_documents(List<Student_documents> studentDocuments) {
		this.studentDocuments = studentDocuments;
	}
	
	public void addStudent_documents(Student_documents studentDocument){
		this.studentDocuments.add(studentDocument);
	}
	
	public void removeStudent_documents(Student_documents studentDocument){
		studentDocuments.remove(studentDocument);
	}
	
	public void addPassingExam(Passing_exams passingExam){
		this.passingExams.add(passingExam);
		
		if(!passingExam.getStudents().contains(this)){
			passingExam.addStudent(this);
		}
	}
	
	public void removePassingExam(Passing_exams passingExam){
		if(passingExam.getStudents().contains(this)){
			passingExam.getStudents().remove(this);
		}
		passingExams.remove(passingExam);
	}
	
	public void addSubjectPresence(Subject_presence subjectPresence){
		this.subjectPresences.add(subjectPresence);
		
		if(!subjectPresence.getStudents().contains(this)){
			subjectPresence.addStudent(this);
		}
	}
	
	public void removeSubjectPresence(Subject_presence subjectPresence){
		if(subjectPresence.getStudents().contains(this)){
			subjectPresence.getStudents().remove(this);
		}
		passingExams.remove(subjectPresence);
	}
	
}
