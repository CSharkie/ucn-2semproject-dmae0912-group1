package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Instructor;
import model.LinkedList;

public class DBInstructor extends DBEmployee implements IFDBInstructor {
	
private Connection con;
	
	/** Creates a new instance of DBInstructor */
	public DBInstructor() {
		con = DBConnection.getInstance().getDBcon();
	}

	// Implements the methods from the interface
			// get all Instructors
			public LinkedList<Instructor> getAllInstructors(boolean retriveAssociation) {
				return miscWhere("", retriveAssociation);
			}
			
			// get one Instructor having the ID
			public Instructor searchInstructorById(int personId,
					boolean retriveAssociation) {
				String wClause = " i.personId = '" + personId + "'";
				return singleWhere(wClause, retriveAssociation);
			}

			// insert a new Instructor
			public int insertInstructor(Instructor instructor) throws Exception { // call to get
																		// the next Id
																		// number
				int nextId = super.insertPerson(instructor);
				System.out.println("next ID = " + nextId);
				instructor.setPersonId(nextId);
				int rc = -1;
				String query = "INSERT INTO Instructor(personId, skills) VALUES ('"
						+ nextId
						+ "','"
						+ instructor.getSkills()
						+ "');";

				System.out.println("insert : " + query);
				try { // insert new Instructor
					Statement stmt = con.createStatement();
					stmt.setQueryTimeout(5);
					rc = stmt.executeUpdate(query);
					stmt.close();
				}// end try
				catch (SQLException ex) {
					System.out.println("Instructor is not inserted");
					throw new Exception("Instructor is not inserted");
				}
				return (rc);
			}

			@Override
			public int updateInstructor(Instructor instructor) {
				int rc = -1;
				super.updatePerson(instructor);
				String query = "UPDATE Instructor SET " 
						+ "personId ='" + instructor.getPersonId()
						+ "', " 
						+ "skills ='" + instructor.getSalary()
						+ "' WHERE personId = '" + instructor.getPersonId()
						+ "'";
				System.out.println("Update query:" + query);
				try { // update Instructor
					Statement stmt = con.createStatement();
					stmt.setQueryTimeout(5);
					rc = stmt.executeUpdate(query);

					stmt.close();
				}// end try
				catch (Exception ex) {
					System.out.println("Update exception in Instructor db: " + ex);
				}
				return (rc);
			}

			public int deleteInstructor(int personId) {
				// just delete person, the DBMS will do the rest
				return super.deletePerson(personId);
			}

			// private methods
			// michWere is used whenever we want to select more than one Instructor

			private LinkedList<Instructor> miscWhere(String wClause,
					boolean retrieveAssociation) {
				ResultSet results;
				LinkedList<Instructor> list = new LinkedList<Instructor>();

				String query = buildQuery(wClause);

				try { // read the Instructor from the database
					Statement stmt = con.createStatement();
					stmt.setQueryTimeout(5);
					results = stmt.executeQuery(query);

					while (results.next()) {
						Instructor instructor = new Instructor();
						instructor = buildInstructor(results);
						//TODO retrieveAssociation
						/*
						if (retrieveAssociation) {
							IFDBSalesOrder salesOrders = new DBSalesOrder();
							LinkedList<SalesOrder> orders = salesOrders.getAllSalesOrdersByPersonId(PersonObj.getPersonId(), false);
							PersonObj.setSalesOrders(orders);
							System.out.println("Orders are selected");
						}
						*/
						list.add(instructor);
					}// end while
					stmt.close();

				}// end try
				catch (Exception e) {
					System.out.println("Query exception - select: " + e);
					e.printStackTrace();
				}
				return list;
			}

			// Single where is used when we only select one Instructor
			private Instructor singleWhere(String wClause, boolean retrieveAssociation) {
				ResultSet results;
				Instructor instructor = new Instructor();

				String query = buildQuery(wClause);
				System.out.println(query);
				try { // read the Instructor from the database
					Statement stmt = con.createStatement();
					stmt.setQueryTimeout(5);
					results = stmt.executeQuery(query);

					if (results.next()) {
						instructor = buildInstructor(results);
						stmt.close();
						//TODO retrieveAssociation
						/*
						if (retrieveAssociation) {
							IFDBSalesOrder salesOrders = new DBSalesOrder();
							LinkedList<SalesOrder> orders = salesOrders.getAllSalesOrdersByPersonId(PersonObj.getPersonId(), false);
							PersonObj.setSalesOrders(orders);
							System.out.println("Orders are selected");
						}
						*/
					} else { // no Instructor was found
						instructor = null;
					}
				}// end try
				catch (Exception e) {
					System.out.println("Query exception: " + e);
				}
				return instructor;
			}

			// method to build the query
			private String buildQuery(String wClause) {
				
				// TODO: Write the proper query
				String query = "SELECT p.personId, p.firstName, p.surName, p.address, p.phoneNo, p.email, e.salary, e.type FROM Person p JOIN Instructor e ON p.personId = e.personId";

				if (wClause.length() > 0)
					query = query + " WHERE " + wClause;

				return query;
			}

			// method to build an Instructor object
			private Instructor buildInstructor(ResultSet results) {
				Instructor instructor = new Instructor();
				try { // the columns from the table Instructor are used
					instructor.setPersonId(results.getInt("personId"));
					instructor.setFirstName(results.getString("firstName"));
					instructor.setSurName(results.getString("surName"));
					instructor.setAddress(results.getString("address"));
					instructor.setPhoneNo(results.getString("phoneNo"));
					instructor.setEmail(results.getString("email"));
					instructor.setSalary(results.getInt("salary"));
					instructor.setType(results.getString("type"));
					instructor.setSkills(results.getString("skills"));
				} catch (Exception e) {
					System.out.println(e);
				}
				return instructor;
			}

}
