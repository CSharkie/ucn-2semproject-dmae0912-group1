package database;

import model.Person;
import java.sql.*;
import model.LinkedList;


public class DBPerson implements IFDBPerson {
	private Connection con;
	
	/** Creates a new instance of DBPerson */
	public DBPerson() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	// Implements the methods from the interface
		// get all Persons
		public LinkedList<Person> getAllPersons(boolean retriveAssociation) {
			return miscWhere("", retriveAssociation);
		}
		
		// get one Person having the ID
		public Person searchPersonById(int personId,
				boolean retriveAssociation) {
			String wClause = " PersonId = '" + personId + "'";
			return singleWhere(wClause, retriveAssociation);
		}

		// find one Person having the name
		public LinkedList<Person> searchPersonsByName(String name, boolean retriveAssociation) {
			String wClause = "Name LIKE '%" + name + "%'";
			System.out.println("Person " + wClause);
			return miscWhere(wClause, retriveAssociation);
		}

		// insert a new Person
		public int insertPerson(Person person) throws Exception { // call to get
																	// the next Id
																	// number
			int nextId = getMax.getMaxId("Select max(personId) from Person");
			nextId = nextId + 1;
			System.out.println("next ID = " + nextId);
			person.setPersonId(nextId);
			int rc = -1;
			String query = "INSERT INTO Person(personId, firstname, surname, address, phoneNo, email)  VALUES('"
					+ person.getPersonId()
					+ "','"
					+ person.getFirstName()
					+ "','"
					+ person.getSurName()
					+ "','"
					+ person.getAddress()
					+ "','"
					+ person.getPhoneNo()
					+ "','"
					+ person.getEmail()
					+ "');";

			System.out.println("insert : " + query);
			try { // insert new Person
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				rc = stmt.executeUpdate(query);
				stmt.close();
			}// end try
			catch (SQLException ex) {
				System.out.println("Person is not inserted");
				throw new Exception("Person is not inserted");
			}
			return (rc);
		}

		@Override
		public int updatePerson(Person Person) {
			Person PersonObj = Person;
			int rc = -1;

			String query = "UPDATE Person SET " 
					+ "address ='" + PersonObj.getAddress()
					+ "', " 
					+ "email ='" + PersonObj.getEmail()
					+ "', "
					+ "firstName ='" + PersonObj.getFirstName()
					+ "', " 
					+ "surName ='" + PersonObj.getSurName()
					+ "', " 
					+ "phoneNo ='" + PersonObj.getPhoneNo()
					+ "' " 
					+ " WHERE PersonId = '" + PersonObj.getPersonId()
					+ "'";
			System.out.println("Update query:" + query);
			try { // update Person
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				rc = stmt.executeUpdate(query);

				stmt.close();
			}// end try
			catch (Exception ex) {
				System.out.println("Update exception in Person db: " + ex);
			}
			return (rc);
		}

		public int deletePerson(int PersonId) {
			int rc = -1;

			String query = "DELETE FROM Person WHERE PersonId = '" + PersonId + "'";
			System.out.println(query);
			try { // delete from Person
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				rc = stmt.executeUpdate(query);
				stmt.close();
			}// end try
			catch (Exception ex) {
				System.out.println("Delete exception in Person db: " + ex);
			}
			return (rc);
		}

		// private methods
		// michWere is used whenever we want to select more than one Person

		private LinkedList<Person> miscWhere(String wClause,
				boolean retrieveAssociation) {
			ResultSet results;
			LinkedList<Person> list = new LinkedList<Person>();

			String query = buildQuery(wClause);

			try { // read the Person from the database
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				results = stmt.executeQuery(query);

				while (results.next()) {
					Person PersonObj = new Person();
					PersonObj = buildPerson(results);
					/*
					if (retrieveAssociation) {
						IFDBSalesOrder salesOrders = new DBSalesOrder();
						LinkedList<SalesOrder> orders = salesOrders.getAllSalesOrdersByPersonId(PersonObj.getPersonId(), false);
						PersonObj.setSalesOrders(orders);
						System.out.println("Orders are selected");
					}
					*/
					list.add(PersonObj);
				}// end while
				stmt.close();

			}// end try
			catch (Exception e) {
				System.out.println("Query exception - select: " + e);
				e.printStackTrace();
			}
			return list;
		}

		// Single where is used when we only select one Person
		private Person singleWhere(String wClause, boolean retrieveAssociation) {
			ResultSet results;
			Person PersonObj = new Person();

			String query = buildQuery(wClause);
			System.out.println(query);
			try { // read the Person from the database
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				results = stmt.executeQuery(query);

				if (results.next()) {
					PersonObj = buildPerson(results);
					stmt.close();
					/*
					if (retrieveAssociation) {
						IFDBSalesOrder salesOrders = new DBSalesOrder();
						LinkedList<SalesOrder> orders = salesOrders.getAllSalesOrdersByPersonId(PersonObj.getPersonId(), false);
						PersonObj.setSalesOrders(orders);
						System.out.println("Orders are selected");
					}
					*/
				} else { // no Person was found
					PersonObj = null;
				}
			}// end try
			catch (Exception e) {
				System.out.println("Query exception: " + e);
			}
			return PersonObj;
		}

		// method to build the query
		private String buildQuery(String wClause) {
			String query = "SELECT PersonId, type, price, capacity, status  FROM Person";

			if (wClause.length() > 0)
				query = query + " WHERE " + wClause;

			return query;
		}

		// method to build an Person object
		private Person buildPerson(ResultSet results) {
			Person PersonObj = new Person();
			try { // the columns from the table Person are used
				PersonObj.setPersonId(results.getInt("personId"));
				PersonObj.setAddress(results.getString("address"));
				PersonObj.setEmail(results.getString("email"));
				PersonObj.setFirstName(results.getString("firstName"));
				PersonObj.setSurName(results.getString("surName"));
				PersonObj.setPhoneNo(results.getString("phoneNo"));
			} catch (Exception e) {
				System.out.println("error in building the Person object");
			}
			return PersonObj;
		}

		@Override
		public LinkedList<Person> searchPersonByType(String type, boolean retrieveAssociation) {
			// TODO Auto-generated method stub
			return null;
		}

}
