package database;

import model.Employee;
import model.Guest;
import model.RoomBooking;
import model.TravelAgency;

import java.sql.*;
import model.LinkedList;

public class DBRoomBooking implements IFDBRoomBooking {
	private Connection con;

	/** Creates a new instance of DBRoomBooking */
	public DBRoomBooking() {
		con = DBConnection.getInstance().getDBcon();
	}

	// Implements the methods from the interface
	// get all RoomBookings
	public LinkedList<RoomBooking> getAllRoomBookings(
			boolean retrieveAssociation) {
		String extraFields = ", p.FirstName, p.SurName ";
		String joinClause = "Guest g ON r.OwnerGuestId = g.PersonId JOIN Person p ON g.PersonId = p.PersonId";
		return miscWhere(extraFields, joinClause, "", retrieveAssociation);
	}

	// get one RoomBooking having the ID
	public RoomBooking searchRoomBookingById(int RoomBookingId,
			boolean retrieveAssociation) {
		String wClause = "r.RoomBookingId = '" + RoomBookingId + "'";
		return singleWhere("", "", wClause, retrieveAssociation);
	}

	// get some RoomBookings having the Customer Name
	public LinkedList<RoomBooking> searchRoomBookingByCustomerName(
			String customerName, boolean retrieveAssociation) {
		String wClause = " g.FirstName LIKE '%" + customerName
				+ "%' OR g.SurName LIKE '%" + customerName + "%'";
		String extraFields = ", p.FirstName, p.SurName ";
		String joinClause = "Guest g ON r.OwnerGuestId = g.PersonId JOIN Person p ON g.PersonId = p.PersonId";
		return miscWhere(extraFields, joinClause, wClause, retrieveAssociation);
	}

	// insert a new RoomBooking
	public int insertRoomBooking(RoomBooking roomBooking) throws Exception { // call
																				// to
																				// get
		// the next Id
		// number
		int nextId = getMax
				.getMaxId("Select max(roomBookingId) from RoomBooking");
		nextId = nextId + 1;
		System.out.println("next ID = " + nextId);
		roomBooking.setRoomBookingId(nextId);
		int rc = -1;
		
		String agency = null;
		
		if(roomBooking.getAgency().getAgencyId() != 0)
		{
			agency = String.valueOf(roomBooking.getAgency().getAgencyId());
		}
		else agency = "NULL";
		
		String query = "INSERT INTO RoomBooking(roomBookingId, AgencyId, totalPrice, OwnerGuestId, EmployeeId) VALUES('"
				+ roomBooking.getRoomBookingId()
				+ "',"
				+ agency
				+ ",'"
				+ roomBooking.getTotalPrice()
				+ "','"
				+ roomBooking.getOwnerGuest().getPersonId()
				+ "','"
				+ roomBooking.getEmployee().getPersonId() + "');";

		System.out.println("insert : " + query);
		try { // insert new Room
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("RoomBooking is not inserted");
			throw new Exception("RoomBooking is not inserted");
		}
		return (rc);
	}

	@Override
	public int updateRoomBooking(RoomBooking roomBooking) {
		int rc = -1;
		
		String agency = null;
		
		if(roomBooking.getAgency().getAgencyId() != 0)
		{
			agency = String.valueOf(roomBooking.getAgency().getAgencyId());
		}
		else agency = "NULL";

		String query = "UPDATE RoomBooking SET " + "totalPrice ='"
				+ roomBooking.getTotalPrice() + "', " + "ownerGuestId ='"
				+ roomBooking.getOwnerGuest().getPersonId() + "', AgencyId = "
				+ agency + ", "
				+ "employeeId ='" + roomBooking.getEmployee().getPersonId()
				+ "' " + " WHERE RoomBookingId = '"
				+ roomBooking.getRoomBookingId() + "'";
		System.out.println("Update query:" + query);
		try { // update RoomBooking
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		}// end try
		catch (Exception ex) {
			System.out.println("Update exception in RoomBooking db: " + ex);
		}
		return (rc);
	}

	public int deleteRoomBooking(int RoomBookingId) {
		int rc = -1;

		String query = "DELETE FROM RoomBooking WHERE RoomBookingId = '"
				+ RoomBookingId + "'";
		System.out.println(query);
		try { // delete from RoomBooking
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (Exception ex) {
			System.out.println("Delete exception in RoomBooking db: " + ex);
		}
		return (rc);
	}

	// private methods
	// michWere is used whenever we want to select more than one RoomBooking

	private LinkedList<RoomBooking> miscWhere(String extraFields,
			String joinClause, String wClause, boolean retrieveAssociation) {
		ResultSet results;
		LinkedList<RoomBooking> list = new LinkedList<RoomBooking>();

		String query = buildQuery(extraFields, joinClause, wClause);

		try { // read the RoomBooking from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			System.out.println(query);
			results = stmt.executeQuery(query);

			while (results.next()) {
				RoomBooking roomBookingObj = new RoomBooking();
				roomBookingObj = buildRoomBooking(results);

				list.add(roomBookingObj);
			}// end while
			stmt.close();
			if (retrieveAssociation) {
				for (RoomBooking roomBooking : list) {
					// OwnerGuest
					DBGuest dbOwnerGuest = new DBGuest();
					Guest ownerGuest = dbOwnerGuest.searchGuestById(roomBooking.getOwnerGuest().getPersonId(), false);
					System.out.println("Guest is selected ");
					roomBooking.setOwnerGuest(ownerGuest);
				}
			}

		}// end try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	// Single where is used when we only select one RoomBooking
	private RoomBooking singleWhere(String extraFields, String joinClause,
			String wClause, boolean retrieveAssociation) {
		ResultSet results;
		RoomBooking roomBookingObj = new RoomBooking();

		String query = buildQuery(extraFields, joinClause, wClause);
		System.out.println(query);
		try { // read the RoomBooking from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				roomBookingObj = buildRoomBooking(results);
				stmt.close();

			} else { // no RoomBooking was found
				roomBookingObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return roomBookingObj;
	}

	// method to build the query
	private String buildQuery(String extraFields, String joinClause,
			String wClause) {
		String query = "SELECT r.roomBookingId, r.totalPrice, r.OwnerGuestId, r.EmployeeId, r.AgencyId";

		if (extraFields.length() > 0) {
			query = query + extraFields;
		}

		query = query + " FROM RoomBooking r ";
		
		if (joinClause.length() > 0) {
			query = query + "JOIN " + joinClause;
		}

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an RoomBooking object
	private RoomBooking buildRoomBooking(ResultSet results) {
		RoomBooking roomBookingObj = new RoomBooking();
		try { // the columns from the table RoomBooking are used
			roomBookingObj.setRoomBookingId(results.getInt("RoomBookingId"));
			roomBookingObj.setTotalPrice(results.getDouble("totalPrice"));
			roomBookingObj.setOwnerGuest(new Guest(results
					.getInt("OwnerGuestId")));
			roomBookingObj.setEmployee(new Employee(results
					.getInt("EmployeeId")));			
			roomBookingObj.setAgency(new TravelAgency(results.getInt("AgencyId")));
		} catch (Exception e) {
			System.out.println("error in building the RoomBooking object");
		}
		return roomBookingObj;
	}

}
