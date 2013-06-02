package model;

public class Instructor extends Employee {
	
	String skills;

	public Instructor(int personId, String firstName, String surName,
			String address, String phoneNo, String email, double salary, String type, String skills) {
		super(personId, firstName, surName, address, phoneNo, email, salary, type);
		this.skills = skills;
	}
	
	public Instructor(String firstName, String surName,
			String address, String phoneNo, String email, double salary, String type, String skills) {
		super(firstName, surName, address, phoneNo, email, salary, type);
		this.skills = skills;
	}
	
	public Instructor(){
		super();
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
}
