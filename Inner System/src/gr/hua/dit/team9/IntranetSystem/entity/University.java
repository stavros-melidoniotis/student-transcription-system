package gr.hua.dit.team9.IntranetSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "university")
public class University {
	@Id
	@Column(name = "code")
	private int code;
	
	@Column(name = "name")
	private String uniName;
	
	@Column(name = "location")
	private String uniLocation;
	
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getUniName() {
		return uniName;
	}
	public void setUniName(String uniName) {
		this.uniName = uniName;
	}
	public String getUniLocation() {
		return uniLocation;
	}
	public void setUniLocation(String uniLocation) {
		this.uniLocation = uniLocation;
	}
	
	public University(int code, String uniName, String uniLocation) {
		super();
		this.code = code;
		this.uniName = uniName;
		this.uniLocation = uniLocation;
	}
	public University() {
		super();
	}
	
	
}
