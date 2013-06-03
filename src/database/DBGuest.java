package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Guest;
import model.LinkedList;

public class DBGuest extends DBPerson implements IFDBGuest {
	private Connection con;

	/** Creates a new instance of DBPerson */
	public DBGuest() {
		con = DBConnection.getInstance().getDBcon();
	}

	// Implements the methods from the interface
	// get all Guests
	public LinkedList<Guest> getAllGuests(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	// get one Guest having the ID
	public Guest searchGuestById(int personId, boolean retriveAssociation) {
		String wClause = " g.personId = '" + personId + "'";
		return singleWhere(wClause, retriveAssociation);
	}

	// get some Guest having the Name
	public LinkedList<Guest> searchGuestByName(String guestName,
			boolean retriveAssociation) {
		String wClause = " p.firstname LIKE '%" + guestName
				+ "%' OR p.surname LIKE '%" + guestName + "%'";
		return miscWhere(wClause, retriveAssociation);
	}

	// insert a new Guest
	public int insertGuest(Guest guest) throws Exception { // call to get
															// the next Id
															// number
		int nextId = super.insertPerson(guest);
		System.out.println("next ID = " + nextId);
		guest.setPersonId(nextId);
		int rc = -1;
		String query = "INSERT INTO Guest(personId, passportNo, password, discount) VALUES ('"
				+ nextId
				+ "','"
				+ guest.getPassportNo()
				+ "','"
				+ guest.getPassword() + "'," + guest.getDiscount() + ");";

		System.out.println("insert : " + query);
		try { // insert new Guest
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("Guest is not inserted");
			throw new Exception("Guest is not inserted");
		}
		return (rc);
	}

	@Override
	public int updateGuest(Guest guest) {
		int rc = -1;
		super.updatePerson(guest);
		String query = "UPDATE Guest SET " + "personId ='"
				+ guest.getPersonId() + "', " + "passportNo ='"
				+ guest.getPassportNo() + "', " + "password = '"
				+ guest.getPassword() + "', " + "discount = '"
				+ guest.getDiscount() + "' WHERE personId = '"
				+ guest.getPersonId() + "'";
		System.out.println("Update query:" + query);
		try { // update Guest
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		}// end try
		catch (Exception ex) {
			System.out.println("Update exception in Guest db: " + ex);
		}
		return (rc);
	}

	public int deleteGuest(int personId) {
		// just delete person, the DBMS will do the rest
		return super.deletePerson(personId);
	}

	// private methods
	// michWere is used whenever we want to select more than one Guest

	private LinkedList<Guest> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		LinkedList<Guest> list = new LinkedList<Guest>();

		String query = buildQuery(wClause);

		try { // read the Guest from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Guest guest = new Guest();
				guest = buildGuest(results);
				// TODO retrieveAssociation
				/*
				 * if (retrieveAssociation) { IFDBSalesOrder salesOrders = new
				 * DBSalesOrder(); LinkedList<SalesOrder> orders =
				 * salesOrders.getAllSalesOrdersByPersonId
				 * (PersonObj.getPersonId(), false);
				 * PersonObj.setSalesOrders(orders);
				 * System.out.println("Orders are selected"); }
				 */
				list.add(guest);
			}// end while
			stmt.close();

		}// end try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	// Single where is used when we only select one Guest
	private Guest singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Guest guest = new Guest();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { // read the Guest from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				guest = buildGuest(results);
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
			} else { // no Guest was found
				guest = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return guest;
	}

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT p.personId, p.firstName, p.surName, p.address, p.phoneNo, p.email, g.passportNo, g.password, g.discount FROM Person p JOIN Guest g ON p.personId = g.personId";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an Guest object
	private Guest buildGuest(ResultSet results) {
		Guest guest = new Guest();
		try { // the columns from the table Guest are used
			guest.setPersonId(results.getInt("personId"));
			guest.setFirstName(results.getString("firstName"));
			guest.setSurName(results.getString("surName"));
			guest.setAddress(results.getString("address"));
			guest.setPhoneNo(results.getString("phoneNo"));
			guest.setEmail(results.getString("email"));
			guest.setPassportNo(results.getInt("passportNo"));
			guest.setPassword(results.getString("password"));
			guest.setDiscount(results.getDouble("discount"));
		} catch (Exception e) {
			System.out.println(e);
		}
		return guest;
	}
}
