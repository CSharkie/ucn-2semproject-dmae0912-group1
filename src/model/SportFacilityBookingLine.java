package model;

import java.sql.Timestamp;

public class SportFacilityBookingLine extends BookingLine {

	private SportFacility sportFacility;
	private SportFacilityBooking sportFacilityBooking;

	public SportFacilityBookingLine(int bookingLineId,
			SportFacilityBooking sportFacilityBooking, Timestamp startDateTime,
			Timestamp endDateTime, Double subtotal, SportFacility sportFacility) {
		super(bookingLineId, startDateTime, endDateTime, subtotal);
		this.sportFacility = sportFacility;
		this.sportFacilityBooking = sportFacilityBooking;
	}

	public SportFacilityBookingLine(SportFacilityBooking sportFacilityBooking,
			Timestamp startDateTime, Timestamp endDateTime, Double subtotal,
			SportFacility sportFacility) {
		super(startDateTime, endDateTime, subtotal);
		this.sportFacility = sportFacility;
		this.sportFacilityBooking = sportFacilityBooking;
	}

	public SportFacilityBookingLine() {
		super();
	}

	public SportFacility getSportFacility() {
		return sportFacility;
	}

	public void setSportFacility(SportFacility sportFacility) {
		this.sportFacility = sportFacility;
	}

	public SportFacilityBooking getSportFacilityBooking() {
		return sportFacilityBooking;
	}

	public void setSportFacilityBooking(
			SportFacilityBooking sportFacilityBooking) {
		this.sportFacilityBooking = sportFacilityBooking;
	}
}
