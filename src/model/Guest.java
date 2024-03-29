package model;

public class Guest extends Person {

	private int passportNo;
	private String password;
	private double discount;

	public Guest(int personId, String firstName, String surName,
			String address, String phoneNo, String email, int passportNo,
			String password, double discount) {
		super(personId, firstName, surName, address, phoneNo, email);
		this.passportNo = passportNo;
		this.password = password;
		this.discount = discount;
	}

	public Guest(String firstName, String surName, String address,
			String phoneNo, String email, int passportNo, String password,
			double discount) {
		super(firstName, surName, address, phoneNo, email);
		this.passportNo = passportNo;
		this.password = password;
		this.discount = discount;
	}

	public Guest(int personId) {
		super(personId);
	}

	public Guest() {
		super();
	}

	public int getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(int passportNo) {
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
