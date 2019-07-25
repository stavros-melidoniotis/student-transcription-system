package gr.hua.dit.team9.IntranetSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "huaDepartments")
public class HuaDepartment {
	
	@Id
	@Column(name = "code")
	private int code;
	
	@Column(name = "department")
	private String dep_name;
	
	@Column(name = "noOfPositions")
	private int noOfPositions;

	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public int getNoOfPositions() {
		return noOfPositions;
	}

	public void setNoOfPositions(int noOfPositions) {
		this.noOfPositions = noOfPositions;
	}

	public HuaDepartment(int code, String dep_name, int noOfPositions) {
		super();
		this.code = code;
		this.dep_name = dep_name;
		this.noOfPositions = noOfPositions;
	}

	public HuaDepartment() {
		super();
	}
}
