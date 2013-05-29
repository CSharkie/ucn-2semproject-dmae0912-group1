package model;

public abstract class BookingLine {
	int bookingLineId;
	int startDateTime;
	int endDateTime;
	Double subtotal;
	
	protected BookingLine(int bookingLineId, int startDateTime, int endDateTime,
			Double subtotal) {
		super();
		this.bookingLineId = bookingLineId;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.subtotal = subtotal;
	}

	public int getBookingLineId() {
		return bookingLineId;
	}

	public void setBookingLineId(int bookingLineId) {
		this.bookingLineId = bookingLineId;
	}

	public int getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(int startDateTime) {
		this.startDateTime = startDateTime;
	}

	public int getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(int endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}	
}
