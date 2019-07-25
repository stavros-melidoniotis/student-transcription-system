package gr.hua.dit.team9.IntranetSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "checked")
public class Check {
	
	@Id
	@Column(name="studentAmka")
	private String studentAmka;
	
	@Column(name = "dateOfCheck")
	private String dateOfCheck;
	
	@Column(name = "isApproved")
	private boolean approved;
	
	@Column(name = "position")
	private int position;
	
	@Column(name = "acceptedDepartment")
	private String acceptedDep;
	
	public String getStudentAmka() {
		return studentAmka;
	}
	public void setStudentAmka(String amka) {
		this.studentAmka = amka;
	}
	public String getDateOfCheck() {
		return dateOfCheck;
	}
	public void setDateOfCheck(String dateOfCheck) {
		this.dateOfCheck = dateOfCheck;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public String getAcceptedDep() {
		return acceptedDep;
	}
	public void setAcceptedDep(String acceptedDep) {
		this.acceptedDep = acceptedDep;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	public Check(String studentAmka, String dateOfCheck, boolean approved, String acceptedDep, int position) {
		super();
		this.studentAmka = studentAmka;
		this.dateOfCheck = dateOfCheck;
		this.approved = approved;
		this.acceptedDep = acceptedDep;
		this.position = position;
	}
	public Check() {
		super();
	}
	
	
}
