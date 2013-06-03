package database;

import model.Employee;
import model.LinkedList;

public interface IFDBEmployee {
	// getAllEmployees
	public LinkedList<Employee> getAllEmployees(boolean retrieveAssociation);

	// get one Employee by ID
	public Employee searchEmployeeById(int personId, boolean retrieveAssociation);

	// get one Employee by Name
	public LinkedList<Employee> searchEmployeeByName(String employeeName,
			boolean retrieveAssociation);

	// insert a new Employee
	public int insertEmployee(Employee employee) throws Exception;

	// update information about a Employee;
	public int updateEmployee(Employee employee);

	// delete a Employee;
	public int deleteEmployee(int personId);
}
