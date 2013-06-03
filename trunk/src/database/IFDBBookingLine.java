package database;

import model.BookingLine;

public interface IFDBBookingLine {
	// insert a new BookingLine
	public int insertBookingLine(BookingLine bookingLine) throws Exception;

	// update information about a BookingLine;
	public int updateBookingLine(BookingLine bookingLine);

	// delete a BookingLine;
	public int deleteBookingLine(int bookingLineId);
}
