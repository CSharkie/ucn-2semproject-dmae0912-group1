package model;

public class Employee extends Person {

	private double salary;
	private String type;

	public Employee(int personId, String firstName, String surName,
			String address, String phoneNo, String email, double salary,
			String type) {
		super(personId, firstName, surName, address, phoneNo, email);
		this.salary = salary;
		this.type = type;
	}

	public Employee(String firstName, String surName, String address,
			String phoneNo, String email, double salary, String type) {
		super(firstName, surName, address, phoneNo, email);
		this.salary = salary;
		this.type = type;
	}

	public Employee() {
		super();
	}

	public Employee(int personId) {
		super(personId);
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
