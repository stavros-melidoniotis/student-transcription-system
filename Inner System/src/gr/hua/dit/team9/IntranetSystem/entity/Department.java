package gr.hua.dit.team9.IntranetSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	@Id
	@Column(name = "depCode")
	private int depCode;
	
	@Column(name = "name")
	private String depName;
	
	@Column(name = "belongingUniversity")
	private String belongingUni;
	
	@Column(name = "matchingDep")
	private String matchingDep;
	
	
	public int getDepCode() {
		return depCode;
	}
	public void setDepCode(int depCode) {
		this.depCode = depCode;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getBelongingUni() {
		return belongingUni;
	}
	public void setBelongingUni(String belongingUni) {
		this.belongingUni = belongingUni;
	}
	public String getMatchingDep() {
		return matchingDep;
	}
	public void setMatchingDep(String matchingDep) {
		this.matchingDep = matchingDep;
	}
	
	public Department(int depCode, String depName, String belongingUni, String matchingDep) {
		super();
		this.depCode = depCode;
		this.depName = depName;
		this.belongingUni = belongingUni;
		this.matchingDep = matchingDep;
	}
	public Department() {
		super();
	}
	
	
}
