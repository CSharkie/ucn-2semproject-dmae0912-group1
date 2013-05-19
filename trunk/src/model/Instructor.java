package model;

public class Instructor extends Employee {
	
	String skills;

	public Instructor(String skills) {
		super();
		this.skills = skills;
	}
	
	public Instructor(){
	
		
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	
	
	

}
