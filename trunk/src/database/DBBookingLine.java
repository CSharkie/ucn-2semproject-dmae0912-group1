package database;

import model.BookingLine;

import java.sql.*;


public abstract class DBBookingLine implements IFDBBookingLine {
	private Connection con;
	
	/** Creates a new instance of DBRoomBookingLine */
	public DBBookingLine() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	// Implements the methods from the interface
		// insert a new RoomBookingLine
		public int insertBookingLine(BookingLine bookingLine) throws Exception { // call to get
																	// the next Id
																	// number
			int nextId = getMax.getMaxId("Select max(bookingLineId) from BookingLine");
			nextId = nextId + 1;
			System.out.println("next ID = " + nextId);
			bookingLine.setBookingLineId(nextId);
			int rc = -1;
			String query = "INSERT INTO BookingLine(bookingLineId, startDateTime, endDateTime, subtotal) VALUES('"
					+ bookingLine.getBookingLineId()
					+ "','"
					+ bookingLine.getStartDateTime()
					+ "','"
					+ bookingLine.getEndDateTime()
					+ "','"
					+ bookingLine.getSubtotal()
					+ "');";

			System.out.println("insert : " + query);
			try { // insert new BookingLine
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				rc = stmt.executeUpdate(query);
				stmt.close();
			}// end try
			catch (SQLException ex) {
				System.out.println("BookingLine is not inserted");
				throw new Exception("BookingLine is not inserted");
			}
			if(rc != -1) return nextId;
			return (rc);
		}

		@Override
		public int updateBookingLine(BookingLine bookingLine) {
			int rc = -1;

			String query = "UPDATE BookingLine SET " 
					+ "bookingLineId = '" + bookingLine.getBookingLineId()
					+ "', " 
					+ "startDateTime = '" + bookingLine.getStartDateTime()
					+ "', "
					+ "endDateTime = '" + bookingLine.getEndDateTime()
					+ "', " 
					+ "subtotal = '" + bookingLine.getSubtotal()
					+ "'";
			System.out.println("Update query:" + query);
			try { // update BookingLine
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

		public int deleteBookingLine(int bookingLineId) {
			int rc = -1;

			String query = "DELETE FROM BookingLine WHERE BookingLineId = '" + bookingLineId + "'";
			System.out.println(query);
			try { // delete from BookingLine
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				rc = stmt.executeUpdate(query);
				stmt.close();
			}// end try
			catch (Exception ex) {
				System.out.println("Delete exception in BookingLine db: " + ex);
			}
			return (rc);
		}
}
