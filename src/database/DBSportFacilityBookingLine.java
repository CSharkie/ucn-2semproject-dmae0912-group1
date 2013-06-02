package database;

import model.SportFacility;
import model.SportFacilityBooking;
import model.SportFacilityBookingLine;

import java.sql.*;
import model.LinkedList;

public class DBSportFacilityBookingLine extends DBBookingLine implements
		IFDBSportFacilityBookingLine {
	private Connection con;

	/** Creates a new instance of DBRoomBookingLine */
	public DBSportFacilityBookingLine() {
		con = DBConnection.getInstance().getDBcon();
	}

	// Implements the methods from the interface
	// get all SportFacilityBookingLines
	public LinkedList<SportFacilityBookingLine> getAllSportFacilityBookingLines(
			int sportFacilityBookingId, boolean retrieveAssociation) {
		String wClause = "s.SportFacilityBookingId = '" + sportFacilityBookingId + "'";
		return miscWhere(wClause, retrieveAssociation);
	}

	// get one SportFacilityBookingLine having the ID
	public SportFacilityBookingLine searchSportFacilityBookingLineById(int sportFacilityBookingLineId,
			boolean retriveAssociation) {
		String wClause = " s.SportFacilityBookingLineId = '" + sportFacilityBookingLineId + "'";
		return singleWhere(wClause, retriveAssociation);
	}

	// insert a new SportFacilityBookingLine
	public int insertSportFacilityBookingLine(SportFacilityBookingLine sportFacilityBookingLine)
			throws Exception { // call to get
		// the next Id
		// number
		int nextId = super.insertBookingLine(sportFacilityBookingLine);
		System.out.println("next ID = " + nextId);
		sportFacilityBookingLine.setBookingLineId(nextId);
		int rc = -1;
		String query = "INSERT INTO SportFacilityBookingLine(bookingLineId, sportFacilityBookingId, sportFacilityId) VALUES ('"
				+ nextId
				+ "','"
				+ sportFacilityBookingLine.getSportFacility().getSportFacilityId()
				+ "','"
				+ sportFacilityBookingLine.getSportFacilityBooking().getSportFacilityBookingId()
				+ "');";

		System.out.println("insert : " + query);
		try { // insert new RoomBookingLine
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("SportFacilityBookingLine is not inserted");
			throw new Exception("SportFacilityBookingLine is not inserted");
		}
		return (rc);
	}

	@Override
	public int updateSportFacilityBookingLine(SportFacilityBookingLine sportFacilityBookingLine) {
		int rc = -1;
		super.updateBookingLine(sportFacilityBookingLine);
		String query = "UPDATE SportFacilityBookingLine SET " + "sportFacilityId ='"
				+ sportFacilityBookingLine.getSportFacility().getSportFacilityId() + "', "
				+ "sportFacilityBookingId ='"
				+ sportFacilityBookingLine.getSportFacilityBooking().getSportFacilityBookingId()
				+ " WHERE bookingLineId = '"
				+ sportFacilityBookingLine.getBookingLineId() + "'";
		System.out.println("Update query:" + query);
		try { // update SportFacilityBookingLine
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		}// end try
		catch (Exception ex) {
			System.out.println("Update exception in SportFacilityBookingLine db: " + ex);
		}
		return (rc);
	}

	public int deleteSportFacilityBookingLine(int sportFacilityBookingLineId) {
		// just delete bookingLine, the DBMS will do the rest
		return super.deleteBookingLine(sportFacilityBookingLineId);
	}

	// private methods
	// michWere is used whenever we want to select more than one RoomBookingLine

	private LinkedList<SportFacilityBookingLine> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		LinkedList<SportFacilityBookingLine> list = new LinkedList<SportFacilityBookingLine>();

		String query = buildQuery(wClause);

		try { // read the SportFacilityBookingLine from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			System.out.println(query);
			results = stmt.executeQuery(query);

			while (results.next()) {
				SportFacilityBookingLine sportFacilityBookingLine = new SportFacilityBookingLine();
				sportFacilityBookingLine = buildSportFacilityBookingLine(results);
				list.add(sportFacilityBookingLine);
			}// end while
			stmt.close();

		}// end try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	// Single where is used when we only select one SportFacilityBookingLine
	private SportFacilityBookingLine singleWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		SportFacilityBookingLine sportFacilityBookingLine = new SportFacilityBookingLine();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { // read the SportFacilityBookingLine from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				sportFacilityBookingLine = buildSportFacilityBookingLine(results);
				stmt.close();
			} else { // no SportFacilityBookingLine was found
				sportFacilityBookingLine = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return sportFacilityBookingLine;
	}

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT b.bookingLineId, s.SportFacilityBookingId, b.startDateTime, b.endDateTime, b.subtotal, s.sportFacilityId FROM BookingLine b JOIN SportFacilityBookingLine s ON b.bookingLineId = s.bookingLineId";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an RoomBookingLine object
	private SportFacilityBookingLine buildSportFacilityBookingLine(ResultSet results) {
		SportFacilityBookingLine sportFacilityBookingLine = new SportFacilityBookingLine();
		try { // the columns from the table RoomBookingLine are used
			sportFacilityBookingLine.setBookingLineId(results.getInt("bookingLineId"));
			sportFacilityBookingLine.setStartDateTime(results
					.getTimestamp("startDateTime"));
			sportFacilityBookingLine.setEndDateTime(results.getTimestamp("endDateTime"));
			sportFacilityBookingLine.setSubtotal(results.getDouble("subtotal"));
			sportFacilityBookingLine.setSportFacility(new SportFacility(results.getInt("sportFacilityId")));
			sportFacilityBookingLine.setSportFacilityBooking(new SportFacilityBooking(results
					.getInt("sportFacilityBookingId")));
		} catch (Exception e) {
			System.out.println(e);
		}
		return sportFacilityBookingLine;
	}
}
