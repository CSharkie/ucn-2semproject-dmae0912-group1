package model;

import java.sql.Timestamp;

public abstract class BookingLine {
	private int bookingLineId;
	private Timestamp startDateTime;
	private Timestamp endDateTime;
	private Double subtotal;

	protected BookingLine(int bookingLineId, Timestamp startDateTime,
			Timestamp endDateTime, Double subtotal) {
		this.bookingLineId = bookingLineId;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.subtotal = subtotal;
	}

	protected BookingLine(Timestamp startDateTime, Timestamp endDateTime,
			Double subtotal) {
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.subtotal = subtotal;
	}

	public BookingLine() {
	}

	public BookingLine(int roomBookingLineId) {
		this.bookingLineId = roomBookingLineId;
	}

	public int getBookingLineId() {
		return bookingLineId;
	}

	public void setBookingLineId(int bookingLineId) {
		this.bookingLineId = bookingLineId;
	}

	public Timestamp getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Timestamp startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Timestamp getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Timestamp endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
}
