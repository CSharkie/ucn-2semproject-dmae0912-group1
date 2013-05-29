package model;

public class Employee extends Person {
	
	int salary;
	String type;
	
	public Employee(int salary, String type) {
		this.salary = salary;
		this.type = type;
	}
	
	public Employee(int personId, String firstName, String surName,
			String address, String phoneNo, String email, int salary, String type) {
		super(personId, firstName, surName, address, phoneNo, email);
		this.salary = salary;
		this.type = type;
	}
	
	public Employee() {
		
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	

}
