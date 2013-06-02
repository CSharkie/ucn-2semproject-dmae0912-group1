package controller;

import model.Employee;
import model.Guest;
import model.Instructor;
import model.LinkedList;
import database.*;

public class PersonCtr {

	public PersonCtr() {

	}

	public LinkedList<Guest> getAllGuests() {
		IFDBGuest dbGuest = new DBGuest();
		LinkedList<Guest> linkedList = new LinkedList<Guest>();
		linkedList = dbGuest.getAllGuests(false);
		return linkedList;
	}

	public Guest searchGuestById(int personId) {
		IFDBGuest dbGuest = new DBGuest();
		return dbGuest.searchGuestById(personId, false);
	}

	public LinkedList<Guest> searchGuestByName(String guestName) {
		IFDBGuest dbGuest = new DBGuest();
		return dbGuest.searchGuestByName(guestName, false);
	}

	public int updateGuest(int personId, String firstname, String surname,
			String address, String phoneNo, String email, int passportNo,
			String password, double discount) {
		IFDBGuest dbGuest = new DBGuest();
		Guest guest = new Guest(personId, firstname, surname, address, phoneNo,
				email, passportNo, password, discount);
		return dbGuest.updateGuest(guest);
	}

	public int insertGuest(String firstname, String surname, String address,
			String phoneNo, String email, int passportNo, String password,
			double discount) {
		Guest guest = new Guest(firstname, surname, address, phoneNo, email,
				passportNo, password, discount);
		try {
			DBConnection.startTransaction();
			DBGuest dbGuest = new DBGuest();
			dbGuest.insertGuest(guest);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
		return guest.getPersonId();
	}

	public void removeGuest(int PersonId) {
		IFDBGuest dbGuest = new DBGuest();
		dbGuest.deleteGuest(PersonId);
	}

	public LinkedList<Employee> getAllEmployees() {
		IFDBEmployee dbEmployee = new DBEmployee();
		LinkedList<Employee> linkedList = new LinkedList<Employee>();
		linkedList = dbEmployee.getAllEmployees(false);
		return linkedList;
	}

	public Employee searchEmployeeById(int personId) {
		IFDBEmployee dbEmployee = new DBEmployee();
		return dbEmployee.searchEmployeeById(personId, false);
	}

	public LinkedList<Employee> searchEmployeeByName(String name) {
		IFDBEmployee dbEmployee = new DBEmployee();
		return dbEmployee.searchEmployeeByName(name, false);
	}

	public int updateEmployee(int personId, String firstname, String surname,
			String address, String phoneNo, String email, double salary,
			String type) {
		IFDBEmployee dbEmployee = new DBEmployee();
		Employee employee = new Employee(personId, firstname, surname, address,
				phoneNo, email, salary, type);
		return dbEmployee.updateEmployee(employee);
	}

	public int insertEmployee(String firstname, String surname, String address,
			String phoneNo, String email, double salary, String type) {
		Employee employee = new Employee(firstname, surname, address, phoneNo,
				email, salary, type);
		try {
			DBConnection.startTransaction();
			DBEmployee dbEmployee = new DBEmployee();
			dbEmployee.insertEmployee(employee);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
		return employee.getPersonId();
	}

	public void removeEmployee(int PersonId) {
		IFDBEmployee dbEmployee = new DBEmployee();
		dbEmployee.deleteEmployee(PersonId);
	}

	public LinkedList<Instructor> getAllInstructors() {
		IFDBInstructor dbInstructor = new DBInstructor();
		LinkedList<Instructor> linkedList = new LinkedList<Instructor>();
		linkedList = dbInstructor.getAllInstructors(false);
		return linkedList;
	}

	public Instructor searchInstructorById(int personId) {
		IFDBInstructor dbInstructor = new DBInstructor();
		return dbInstructor.searchInstructorById(personId, false);
	}

	public LinkedList<Instructor> searchInstructorByName(String instructorName) {
		IFDBInstructor dbInstructor = new DBInstructor();
		return dbInstructor.searchInstructorByName(instructorName, false);
	}

	public int updateInstructor(int personId, String firstname, String surname,
			String address, String phoneNo, String email, double salary,
			String type, String skills) {
		IFDBInstructor dbInstructor = new DBInstructor();
		Instructor instructor = new Instructor(personId, firstname, surname,
				address, phoneNo, email, salary, type, skills);
		return dbInstructor.updateInstructor(instructor);
	}

	public int insertInstructor(String firstname, String surname,
			String address, String phoneNo, String email, double salary,
			String type, String skills) {
		Instructor instructor = new Instructor(firstname, surname, address,
				phoneNo, email, salary, type, skills);
		try {
			DBConnection.startTransaction();
			DBInstructor dbInstructor = new DBInstructor();
			dbInstructor.insertInstructor(instructor);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
		return instructor.getPersonId();
	}

	public void removeInstructor(int PersonId) {
		IFDBInstructor dbInstructor = new DBInstructor();
		dbInstructor.deleteInstructor(PersonId);
	}

}
