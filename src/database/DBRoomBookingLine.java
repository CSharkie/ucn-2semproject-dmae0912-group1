package database;

import model.Room;
import model.RoomBooking;
import model.RoomBookingLine;

import java.sql.*;
import java.util.Date;

import model.LinkedList;

public class DBRoomBookingLine extends DBBookingLine implements
		IFDBRoomBookingLine {
	private Connection con;

	/** Creates a new instance of DBRoomBookingLine */
	public DBRoomBookingLine() {
		con = DBConnection.getInstance().getDBcon();
	}

	// Implements the methods from the interface
	// get all RoomBookingLines
	public LinkedList<RoomBookingLine> getAllRoomBookingLines(
			int roomBookingId, boolean retriveAssociation) {
		String wClause = "r.RoomBookingId = '" + roomBookingId + "'";
		return miscWhere(wClause, retriveAssociation);
	}

	// get one RoomBookingLine having the ID
	public RoomBookingLine searchRoomBookingLineById(int roomBookingLineId,
			boolean retriveAssociation) {
		String wClause = " r.bookingLineId = '" + roomBookingLineId + "'";
		return singleWhere(wClause, retriveAssociation);
	}

	// insert a new RoomBookingLine
	public int insertRoomBookingLine(RoomBookingLine roomBookingLine)
			throws Exception { // call to get
		// the next Id
		// number
		int nextId = super.insertBookingLine(roomBookingLine);
		System.out.println("next ID = " + nextId);
		roomBookingLine.setBookingLineId(nextId);
		int rc = -1;
		String query = "INSERT INTO RoomBookingLine(bookingLineId, roomBookingId, roomId, depositStatus, checkInDateTime) VALUES ('"
				+ nextId
				+ "','"
				+ roomBookingLine.getRoom().getRoomId()
				+ "','"
				+ roomBookingLine.getRoomBooking().getRoomBookingId()
				+ "','"
				+ roomBookingLine.getDepositStatus()
				+ "',"
				+ roomBookingLine.getCheckInDateTime() + ");";

		System.out.println("insert : " + query);
		try { // insert new RoomBookingLine
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("RoomBookingLine is not inserted");
			throw new Exception("RoomBookingLine is not inserted");
		}
		return (rc);
	}

	@Override
	public int updateRoomBookingLine(RoomBookingLine roomBookingLine) {
		int rc = -1;
		super.updateBookingLine(roomBookingLine);
		String query = "UPDATE RoomBookingLine SET " + "roomId ='"
				+ roomBookingLine.getRoom().getRoomId() + "', "
				+ "depositStatus ='" + roomBookingLine.getDepositStatus()
				+ "', " + "roomBookingId ='"
				+ roomBookingLine.getRoomBooking().getRoomBookingId() + "', "
				+ "checkInDateTime = " + roomBookingLine.getCheckInDateTime()
				+ " WHERE bookingLineId = '"
				+ roomBookingLine.getBookingLineId() + "'";
		System.out.println("Update query:" + query);
		try { // update RoomBookingLine
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		}// end try
		catch (Exception ex) {
			System.out.println("Update exception in RoomBookingLine db: " + ex);
		}
		return (rc);
	}

	@Override
	public int updateRoomBookingLineDepAndCheck(RoomBookingLine roomBookingLine) {
		int rc = -1;

		String checkInDate = null;

		if (roomBookingLine.getCheckInDateTime() != null) {
			checkInDate = "'" + String.valueOf(roomBookingLine.getCheckInDateTime()) + "'";
		} else
			checkInDate = "NULL";

		String query = "UPDATE RoomBookingLine SET " + "depositStatus ='"
				+ roomBookingLine.getDepositStatus() + "', "
				+ "checkInDateTime = " + checkInDate
				+ " WHERE bookingLineId = '"
				+ roomBookingLine.getBookingLineId() + "'";
		System.out.println("Update query:" + query);
		try { // update RoomBookingLine
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		}// end try
		catch (Exception ex) {
			System.out.println("Update exception in RoomBookingLine db: " + ex);
		}
		return (rc);
	}

	public int deleteRoomBookingLine(int roomBookingLineId) {
		// just delete bookingLine, the DBMS will do the rest
		return super.deleteBookingLine(roomBookingLineId);
	}

	// private methods
	// michWere is used whenever we want to select more than one RoomBookingLine

	private LinkedList<RoomBookingLine> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		LinkedList<RoomBookingLine> list = new LinkedList<RoomBookingLine>();

		String query = buildQuery(wClause);

		try { // read the RoomBookingLine from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			System.out.println(query);
			results = stmt.executeQuery(query);

			while (results.next()) {
				RoomBookingLine roomBookingLine = new RoomBookingLine();
				roomBookingLine = buildRoomBookingLine(results);
				list.add(roomBookingLine);
			}// end while
			stmt.close();

		}// end try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	// Single where is used when we only select one RoomBookingLine
	private RoomBookingLine singleWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		RoomBookingLine roomBookingLine = new RoomBookingLine();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { // read the RoomBookingLine from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				roomBookingLine = buildRoomBookingLine(results);
				stmt.close();
			} else { // no RoomBookingLine was found
				roomBookingLine = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return roomBookingLine;
	}

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT b.bookingLineId, r.RoomBookingId, b.startDateTime, b.endDateTime, b.subtotal, r.roomId, r.depositStatus, r.checkInDateTime FROM BookingLine b JOIN RoomBookingLine r ON b.bookingLineId = r.bookingLineId";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an RoomBookingLine object
	private RoomBookingLine buildRoomBookingLine(ResultSet results) {
		RoomBookingLine roomBookingLine = new RoomBookingLine();
		try { // the columns from the table RoomBookingLine are used
			roomBookingLine.setBookingLineId(results.getInt("bookingLineId"));
			roomBookingLine.setStartDateTime(results
					.getTimestamp("startDateTime"));
			roomBookingLine.setEndDateTime(results.getTimestamp("endDateTime"));
			roomBookingLine.setSubtotal(results.getDouble("subtotal"));
			roomBookingLine.setRoom(new Room(results.getInt("roomId")));
			roomBookingLine.setRoomBooking(new RoomBooking(results
					.getInt("roomBookingId")));
			roomBookingLine
					.setDepositStatus(results.getString("depositStatus"));
			roomBookingLine.setCheckInDateTime(results
					.getTimestamp("checkInDateTime"));
		} catch (Exception e) {
			System.out.println(e);
		}
		return roomBookingLine;
	}

	public boolean checkRoomAvailability(int roomId, Date startDate,
			Date endDate) {
		String wClause = " r.roomId = " + roomId + " AND ( '"
				+ new Timestamp(startDate.getTime()) + "' >= b.startDateTime "
				+ " AND '" + new Timestamp(startDate.getTime())
				+ "' <= b.endDateTime ) OR ( '"
				+ new Timestamp(endDate.getTime())
				+ "' >= b.startDateTime AND '"
				+ new Timestamp(endDate.getTime())
				+ "' <= b.endDateTime ) OR ( b.startDateTime >= '"
				+ new Timestamp(startDate.getTime())
				+ "' AND b.endDateTime <= '" + new Timestamp(endDate.getTime())
				+ "' )";
		RoomBookingLine roomBookingLine = singleWhere(wClause, false);
		if (roomBookingLine != null) {
			System.out.println("false");
			return false;
		}
		System.out.println("true");
		return true;
	}
}
