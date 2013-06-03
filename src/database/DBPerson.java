package database;

import model.Person;
import java.sql.*;


public abstract class DBPerson implements IFDBPerson {
	private Connection con;
	
	/** Creates a new instance of DBPerson */
	public DBPerson() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	// Implements the methods from the interface
			// insert a new RoomPerson
			public int insertPerson(Person person) throws Exception { // call to get
																		// the next Id
																		// number
				int nextId = getMax.getMaxId("Select max(PersonId) from Person");
				nextId = nextId + 1;
				System.out.println("next ID = " + nextId);
				person.setPersonId(nextId);
				int rc = -1;
				String query = "INSERT INTO Person(personId, firstname, surname, address, phoneNo, email) VALUES('"
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
				if(rc != -1) return nextId;
				return (rc);
			}

			@Override
			public int updatePerson(Person person) {
				int rc = -1;

				String query = "UPDATE Person SET " 
						+ "firstname = '" + person.getFirstName()
						+ "', "
						+ "surname = '" + person.getSurName()
						+ "', " 
						+ "address = '" + person.getAddress()
						+ "', "
						+ "phoneno = '" + person.getPhoneNo()
						+ "', "
						+ "email = '" + person.getEmail()
						+ "' WHERE personId = '" + person.getPersonId() + "'";
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

			public int deletePerson(int personId) {
				int rc = -1;

				String query = "DELETE FROM Person WHERE personId = '" + personId + "'";
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

}
