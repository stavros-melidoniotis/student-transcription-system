
package gr.hua.dit.team9.IntranetSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	
	@Column(name = "fullName")
	private String fullName;
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "semester")
	private int semester;
	
	@Column(name = "amka")
	private String amka;
	
	@Column(name = "currentDepartment")
	private String currDep;
	
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getAmka() {
		return amka;
	}
	public void setAmka(String amka) {
		this.amka = amka;
	}
	public String getCurrDep() {
		return currDep;
	}
	public void setCurrDep(String currDep) {
		this.currDep = currDep;
	}
	public Student(String fullName, String id, String email, String password, int semester, String amka, String currDep) {
		super();
		this.fullName = fullName;
		this.id = id;
		this.email = email;
		this.password = password;
		this.semester = semester;
		this.amka = amka;
		this.currDep = currDep;
	}
	
	public Student() {
		super();
	}
}
