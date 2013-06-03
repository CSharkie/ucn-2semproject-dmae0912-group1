package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.LinkedList;
import model.Employee;

public class DBEmployee extends DBPerson implements IFDBEmployee {

	private Connection con;

	/** Creates a new instance of DBEmployee */
	public DBEmployee() {
		con = DBConnection.getInstance().getDBcon();
	}

	// Implements the methods from the interface
	// get all Employees
	public LinkedList<Employee> getAllEmployees(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	// get one Employee having the ID
	public Employee searchEmployeeById(int personId, boolean retriveAssociation) {
		String wClause = " e.personId = '" + personId + "'";
		return singleWhere(wClause, retriveAssociation);
	}

	// get some Employees having the Name
	public LinkedList<Employee> searchEmployeeByName(String employeeName,
			boolean retriveAssociation) {
		String wClause = " p.firstname LIKE '%" + employeeName
				+ "%' OR p.surname LIKE '%" + employeeName + "%'";
		return miscWhere(wClause, retriveAssociation);
	}

	// insert a new Employee
	public int insertEmployee(Employee employee) throws Exception { // call to
																	// get
		// the next Id
		// number
		int nextId = super.insertPerson(employee);
		System.out.println("next ID = " + nextId);
		employee.setPersonId(nextId);
		int rc = -1;
		String query = "INSERT INTO Employee(personId, salary, type) VALUES ('"
				+ nextId + "','" + employee.getSalary() + "','"
				+ employee.getType() + "');";

		System.out.println("insert : " + query);
		try { // insert new Employee
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("Employee is not inserted");
			throw new Exception("Employee is not inserted");
		}
		if (rc != -1)
			return nextId;
		return (rc);
	}

	@Override
	public int updateEmployee(Employee employee) {
		int rc = -1;
		super.updatePerson(employee);
		String query = "UPDATE Employee SET salary ='" + employee.getSalary()
				+ "', " + "type = '" + employee.getType()
				+ "' WHERE personId = '" + employee.getPersonId() + "'";
		System.out.println("Update query:" + query);
		try { // update Employee
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		}// end try
		catch (Exception ex) {
			System.out.println("Update exception in Employee db: " + ex);
		}
		return (rc);
	}

	public int deleteEmployee(int personId) {
		// just delete person, the DBMS will do the rest
		return super.deletePerson(personId);
	}

	// private methods
	// michWere is used whenever we want to select more than one Employee

	private LinkedList<Employee> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		LinkedList<Employee> list = new LinkedList<Employee>();

		String query = buildQuery(wClause);

		try { // read the Employee from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Employee employee = new Employee();
				employee = buildEmployee(results);
				// TODO retrieveAssociation
				/*
				 * if (retrieveAssociation) { IFDBSalesOrder salesOrders = new
				 * DBSalesOrder(); LinkedList<SalesOrder> orders =
				 * salesOrders.getAllSalesOrdersByPersonId
				 * (PersonObj.getPersonId(), false);
				 * PersonObj.setSalesOrders(orders);
				 * System.out.println("Orders are selected"); }
				 */
				list.add(employee);
			}// end while
			stmt.close();

		}// end try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	// Single where is used when we only select one Employee
	private Employee singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Employee employee = new Employee();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { // read the Employee from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				employee = buildEmployee(results);
				stmt.close();
				// TODO retrieveAssociation
				/*
				 * if (retrieveAssociation) { IFDBSalesOrder salesOrders = new
				 * DBSalesOrder(); LinkedList<SalesOrder> orders =
				 * salesOrders.getAllSalesOrdersByPersonId
				 * (PersonObj.getPersonId(), false);
				 * PersonObj.setSalesOrders(orders);
				 * System.out.println("Orders are selected"); }
				 */
			} else { // no Employee was found
				employee = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return employee;
	}

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT p.personId, p.firstName, p.surName, p.address, p.phoneNo, p.email, e.salary, e.type FROM Person p JOIN Employee e ON p.personId = e.personId";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an Employee object
	private Employee buildEmployee(ResultSet results) {
		Employee employee = new Employee();
		try { // the columns from the table Employee are used
			employee.setPersonId(results.getInt("personId"));
			employee.setFirstName(results.getString("firstName"));
			employee.setSurName(results.getString("surName"));
			employee.setAddress(results.getString("address"));
			employee.setPhoneNo(results.getString("phoneNo"));
			employee.setEmail(results.getString("email"));
			employee.setSalary(results.getDouble("salary"));
			employee.setType(results.getString("type"));
		} catch (Exception e) {
			System.out.println(e);
		}
		return employee;
	}
}
