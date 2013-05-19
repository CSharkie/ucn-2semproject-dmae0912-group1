package model;

public class Guest extends Person {
	
	String passportNo;
	String password;
	double discount;
	
	public Guest( String passportNo,String password, double discount) {
		super();
		this.passportNo = passportNo;
		this.password = password;
		this.discount = discount;
	}
	
	public Guest() {
	
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
