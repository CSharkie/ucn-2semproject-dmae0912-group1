package model;

public class Guest extends Person {
	
	String passportNo;
	String password;
	double discount;
	
	public Guest(String passportNo, String password, double discount) {
		this.passportNo = passportNo;
		this.password = password;
		this.discount = discount;
	}
	
	public Guest(int personId, String firstName, String surName,
			String address, String phoneNo, String email, String passportNo, String password, double discount) {
		super(personId, firstName, surName, address, phoneNo, email);
		this.passportNo = passportNo;
		this.password = password;
		this.discount = discount;
	}
	
	public Guest() {
		super();
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
