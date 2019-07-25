package gr.hua.dit.team9.IntranetSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "application")
public class Application {
	@Id
	@Column(name = "studentAmka")
	private String amka;
	
	@Column(name = "hasBrotherSameCity")
	private boolean hasBrotherSameCity;
	
	@Column(name = "noOfUnderageBrothers")
	private int noOfUnderageBrothers;
	
	@Column(name = "cityOfOrigin")
	private String cityOfOrigin;
	
	@Column(name = "familyIncome")
	private double familyIncome;
	
	@Column(name = "choice1")
	private String choice1;
	
	@Column(name = "choice2")
	private String choice2;
	
	@Column(name = "points")
	private int points;
	
	@Column(name = "oikogeneiakh_katastash")
	private byte[] oikogeneiakh_katastash;
	
	@Column(name = "ekkatharistiko")
	private byte[] ekkatharistiko;
	
	@Column(name = "bebaiwsh_spoudwn")
	private byte[] bebaiwsh_spoudwn;
	
	@Column(name = "monimh_katoikia")
	private byte[] monimh_katoikia;
	
	public String getAmka() {
		return amka;
	}
	public void setAmka(String amka) {
		this.amka = amka;
	}
	public boolean isHasBrotherSameCity() {
		return hasBrotherSameCity;
	}
	public void setHasBrotherSameCity(boolean hasBrotherSameCity) {
		this.hasBrotherSameCity = hasBrotherSameCity;
	}
	public int getNoOfUnderageBrothers() {
		return noOfUnderageBrothers;
	}
	public void setNoOfUnderageBrothers(int noOfUnderageBrothers) {
		this.noOfUnderageBrothers = noOfUnderageBrothers;
	}
	public String getCityOfOrigin() {
		return cityOfOrigin;
	}
	public void setCityOfOrigin(String cityOfOrigin) {
		this.cityOfOrigin = cityOfOrigin;
	}
	public double getFamilyIncome() {
		return familyIncome;
	}
	public void setFamilyIncome(double familyIncome) {
		this.familyIncome = familyIncome;
	}
	public String getChoice1() {
		return choice1;
	}
	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}
	public String getChoice2() {
		return choice2;
	}
	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public byte[] getOikogeneiakh_katastash() {
		return oikogeneiakh_katastash;
	}
	public void setOikogeneiakh_katastash(byte[] oikogeneiakh_katastash) {
		this.oikogeneiakh_katastash = oikogeneiakh_katastash;
	}
	public byte[] getEkkatharistiko() {
		return ekkatharistiko;
	}
	public void setEkkatharistiko(byte[] ekkatharistiko) {
		this.ekkatharistiko = ekkatharistiko;
	}
	public byte[] getBebaiwsh_spoudwn() {
		return bebaiwsh_spoudwn;
	}
	public void setBebaiwsh_spoudwn(byte[] bebaiwsh_spoudwn) {
		this.bebaiwsh_spoudwn = bebaiwsh_spoudwn;
	}
	public byte[] getMonimh_katoikia() {
		return monimh_katoikia;
	}
	public void setMonimh_katoikia(byte[] monimh_katoikia) {
		this.monimh_katoikia = monimh_katoikia;
	}
	public Application() {
		super();
	}
	
	public Application(String amka, boolean hasBrotherSameCity, int noOfUnderageBrothers, String cityOfOrigin,
			double familyIncome, String choice1, String choice2, int points, byte[] oikogeneiakh_katastash,
			byte[] ekkatharistiko, byte[] monimh_katoikia, byte[] bebaiwsh_spoudwn) {
		super();
		this.amka = amka;
		this.hasBrotherSameCity = hasBrotherSameCity;
		this.noOfUnderageBrothers = noOfUnderageBrothers;
		this.cityOfOrigin = cityOfOrigin;
		this.familyIncome = familyIncome;
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.points = points;
		this.oikogeneiakh_katastash = oikogeneiakh_katastash;
		this.ekkatharistiko = ekkatharistiko;
		this.bebaiwsh_spoudwn = bebaiwsh_spoudwn;
		this.monimh_katoikia = monimh_katoikia;
	}
	
	//Method used to calculate points based on user's choices. Called on ApplicationController.
	public int calculatePoints(boolean hasBrotherSameCity, int noOfUnderageBrothers, String cityOfOrigin, double familyIncome) {
		int points = 0;
		
		if (hasBrotherSameCity) {
			points+=100;
		}
		
		points+=(noOfUnderageBrothers*50);
		
		if (familyIncome < 8000) {
			points+=80;
		}
		
		if (familyIncome >= 8000 && familyIncome < 12000) {
			points+=40;
		}
		
		if (cityOfOrigin.equals("Αθήνα")) {
			points+=50;
		}
		
		return points;
	}
	
}
