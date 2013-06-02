package database;

import model.Guest;
import java.sql.*;
import model.LinkedList;

public class DBRoomBookingLine_Guest implements IFDBRoomBookingLine_Guest {
	private Connection con;

	/** Creates a new instance of DBRoom */
	public DBRoomBookingLine_Guest() {
		con = DBConnection.getInstance().getDBcon();
	}

	// Implements the methods from the interface
	// get all Rooms
	public LinkedList<Guest> getAllGuests(int RoomBookingLineId, boolean retrieveAssociation) {
		String whereClause = "RoomBookingLineId = '" + RoomBookingLineId + "'";
		return miscWhere(whereClause, retrieveAssociation);
	}

	// insert a new Room
	public int insertGuest(int RoomBookingLineId, int GuestId) throws Exception { // call to get
																// the next Id
																// number
		int nextId = getMax.getMaxId("Select max(RoomBookingLine_GuestId) from RoomBookingLine_Guest");
		nextId = nextId + 1;
		System.out.println("next ID = " + nextId);
		int rc = -1;
		String query = "INSERT INTO RoomBookingLine_Guest(RoomBookingLine_GuestId, RoomBookingLineId, GuestId)  VALUES('"
				+ nextId
				+ "','"
				+ RoomBookingLineId
				+ "','"
				+ GuestId
				+ "');";

		System.out.println("insert : " + query);
		try { // insert new Room
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("RoomBookingLine_Guest is not inserted");
			throw new Exception("RoomBookingLine_Guest is not inserted");
		}
		return (rc);
	}

	public int deleteGuest(int RoomBookingLineId, int GuestId) {
		int rc = -1;

		String query = "DELETE FROM RoomBookingLine_Guest WHERE RoomBookingLineId = '" + RoomBookingLineId + "' AND GuestId = '" + GuestId + "'";
		System.out.println(query);
		try { // delete from RoomBookingLine_Guest
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (Exception ex) {
			System.out.println("Delete exception in RoomBookingLine_Guest db: " + ex);
		}
		return (rc);
	}

	// private methods
	// michWere is used whenever we want to select more than one RoomBookingLine_Guest

	private LinkedList<Guest> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		LinkedList<Guest> list = new LinkedList<Guest>();

		String query = buildQuery(wClause);

		try { // read the Room from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			System.out.println(query);
			results = stmt.executeQuery(query);

			while (results.next()) {
				IFDBGuest dbGuest = new DBGuest();
				Guest guest = dbGuest.searchGuestById(results.getInt("GuestId"), false);
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

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT GuestId FROM RoomBookingLine_Guest";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}
}
