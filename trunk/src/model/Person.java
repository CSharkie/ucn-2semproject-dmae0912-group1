package model;

public abstract class Person {
	int personId;
	String firstName;
	String surName;
	String address;
	String phoneNo;
	String email;
	
	public Person(int personId, String firstName, String surName,
			String address, String phoneNo, String email) {
		super();
		this.personId = personId;
		this.firstName = firstName;
		this.surName = surName;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
	}
	
	public Person(String firstName, String surName, String address, String phoneNo, String email) {
		this.firstName = firstName;
		this.surName = surName;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
	}
	
	public Person() {
	
	}
	
	public int getPersonId() {
		return personId;
	}
	
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getSurName() {
		return surName;
	}
	
	public void setSurName(String surName) {
		this.surName = surName;
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
