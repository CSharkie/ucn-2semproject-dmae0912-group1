package model;

public class TravelAgency {
	int agencyId;
	String name;
	int discount;
	String address;
	String phoneNo;
	String email;
	
	public TravelAgency(){
		
	}
	
	public TravelAgency(int agencyId, String name, int discount,
			String address, String phoneNo, String email) {
		super();
		this.agencyId = agencyId;
		this.name = name;
		this.discount = discount;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
	}

	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
