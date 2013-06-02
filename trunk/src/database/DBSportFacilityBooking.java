package database;

import model.Guest;
import model.SportFacilityBooking;

import java.sql.*;
import model.LinkedList;

public class DBSportFacilityBooking implements IFDBSportFacilityBooking {
	private Connection con;

	/** Creates a new instance of DBRoomBooking */
	public DBSportFacilityBooking() {
		con = DBConnection.getInstance().getDBcon();
	}

	// Implements the methods from the interface
	// get all RoomBookings
	public LinkedList<SportFacilityBooking> getAllSportFacilityBookings(
			boolean retrieveAssociation) {
		String extraFields = ", p.FirstName, p.SurName ";
		String joinClause = "Guest g ON s.OwnerGuestId = g.PersonId JOIN Person p ON g.PersonId = p.PersonId";
		return miscWhere(extraFields, joinClause, "", retrieveAssociation);
	}

	// get one RoomBooking having the ID
	public SportFacilityBooking searchSportFacilityBookingById(
			int SportFacilityId, boolean retrieveAssociation) {
		String wClause = "s.SportFacilityBookingId = '" + SportFacilityId + "'";
		return singleWhere("", "", wClause, retrieveAssociation);
	}

	// get some RoomBookings having the Customer Name
	public LinkedList<SportFacilityBooking> searchSportFacilityBookingByCustomerName(
			String customerName, boolean retrieveAssociation) {
		String wClause = " g.FirstName LIKE '%" + customerName
				+ "%' OR g.SurName LIKE '%" + customerName + "%'";
		String extraFields = ", p.FirstName, p.SurName ";
		String joinClause = "Guest g ON s.OwnerGuestId = g.PersonId JOIN Person p ON g.PersonId = p.PersonId";
		return miscWhere(extraFields, joinClause, wClause, retrieveAssociation);
	}

	// insert a new SportFacilityBooking
	public int insertSportFacilityBooking(
			SportFacilityBooking sportFacilityBooking) throws Exception { // call
		// to
		// get
		// the next Id
		// number
		int nextId = getMax
				.getMaxId("Select max(sportFacilityBookingId) from SportFacilityBooking");
		nextId = nextId + 1;
		System.out.println("next ID = " + nextId);
		sportFacilityBooking.setSportFacilityBookingId(nextId);
		int rc = -1;

		String query = "INSERT INTO SportFacilityBooking(sportFacilityBookingId, totalPrice, OwnerGuestId) VALUES('"
				+ sportFacilityBooking.getSportFacilityBookingId()
				+ "','"
				+ sportFacilityBooking.getTotalPrice()
				+ "','"
				+ sportFacilityBooking.getOwnerGuest().getPersonId() + "');";

		System.out.println("insert : " + query);
		try { // insert new SportFacilityBooking
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("SportFacilityBooking is not inserted");
			throw new Exception("SportFacilityBooking is not inserted");
		}
		return (rc);
	}

	@Override
	public int updateSportFacilityBooking(
			SportFacilityBooking sportFacilityBooking) {
		int rc = -1;

		String query = "UPDATE SportFacilityBooking SET " + "totalPrice ='"
				+ sportFacilityBooking.getTotalPrice() + "', "
				+ "ownerGuestId ='"
				+ sportFacilityBooking.getOwnerGuest().getPersonId()
				+ "' WHERE SportFacilityBookingId = '"
				+ sportFacilityBooking.getSportFacilityBookingId() + "'";
		System.out.println("Update query:" + query);
		try { // update RoomBooking
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		}// end try
		catch (Exception ex) {
			System.out.println("Update exception in SportFacilityBooking db: "
					+ ex);
		}
		return (rc);
	}

	public int deleteSportFacilityBooking(int SportFacilityBookingId) {
		int rc = -1;

		String query = "DELETE FROM SportFacilityBooking WHERE SportFacilityBookingId = '"
				+ SportFacilityBookingId + "'";
		System.out.println(query);
		try { // delete from SportFacilityBooking
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (Exception ex) {
			System.out.println("Delete exception in SportFacilityBooking db: "
					+ ex);
		}
		return (rc);
	}

	// private methods
	// michWere is used whenever we want to select more than one RoomBooking

	private LinkedList<SportFacilityBooking> miscWhere(String extraFields,
			String joinClause, String wClause, boolean retrieveAssociation) {
		ResultSet results;
		LinkedList<SportFacilityBooking> list = new LinkedList<SportFacilityBooking>();

		String query = buildQuery(extraFields, joinClause, wClause);

		try { // read the RoomBooking from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			System.out.println(query);
			results = stmt.executeQuery(query);

			while (results.next()) {
				SportFacilityBooking sportFacilityBooking = new SportFacilityBooking();
				sportFacilityBooking = buildSportFacilityBooking(results);

				list.add(sportFacilityBooking);
			}// end while
			stmt.close();
			if (retrieveAssociation) {
				for (SportFacilityBooking sportFacilityBooking : list) {
					// OwnerGuest
					DBGuest dbOwnerGuest = new DBGuest();
					Guest ownerGuest = dbOwnerGuest.searchGuestById(
							sportFacilityBooking.getOwnerGuest().getPersonId(),
							false);
					System.out.println("Guest is selected ");
					sportFacilityBooking.setOwnerGuest(ownerGuest);
				}
			}

		}// end try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	// Single where is used when we only select one sportFacilityBooking
	private SportFacilityBooking singleWhere(String extraFields,
			String joinClause, String wClause, boolean retrieveAssociation) {
		ResultSet results;
		SportFacilityBooking sportFacilityBooking = new SportFacilityBooking();

		String query = buildQuery(extraFields, joinClause, wClause);
		System.out.println(query);
		try { // read the sportFacilityBooking from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				sportFacilityBooking = buildSportFacilityBooking(results);
				stmt.close();

			} else { // no sportFacilityBooking was found
				sportFacilityBooking = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return sportFacilityBooking;
	}

	// method to build the query
	private String buildQuery(String extraFields, String joinClause,
			String wClause) {
		String query = "SELECT s.sportFacilityBookingId, s.totalPrice, s.OwnerGuestId";

		if (extraFields.length() > 0) {
			query = query + extraFields;
		}

		query = query + " FROM SportFacilityBooking s ";

		if (joinClause.length() > 0) {
			query = query + "JOIN " + joinClause;
		}

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an SportFacilityBooking object
	private SportFacilityBooking buildSportFacilityBooking(ResultSet results) {
		SportFacilityBooking sportFacilityBooking = new SportFacilityBooking();
		try { // the columns from the table SportFacilityBooking are used
			sportFacilityBooking.setSportFacilityBookingId(results
					.getInt("sportFacilityBookingId"));
			sportFacilityBooking.setTotalPrice(results.getDouble("totalPrice"));
			sportFacilityBooking.setOwnerGuest(new Guest(results
					.getInt("OwnerGuestId")));
		} catch (Exception e) {
			System.out.println("error in building the RoomBooking object");
		}
		return sportFacilityBooking;
	}

}
