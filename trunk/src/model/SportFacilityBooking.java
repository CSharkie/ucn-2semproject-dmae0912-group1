package model;

public class SportFacilityBooking {

	int sportFacilityBookingId;
	double totalPrice;
	LinkedList<SportFacilityBookingLine> sportFacilityBookingLines;
	Guest ownerGuest;

	public SportFacilityBooking(int sportFacilityBookingId, double totalPrice,
			LinkedList<SportFacilityBookingLine> sportFacilityBookingLines, Guest ownerGuest) {
		this.sportFacilityBookingId = sportFacilityBookingId;
		this.totalPrice = totalPrice;
		this.sportFacilityBookingLines = sportFacilityBookingLines;
		this.ownerGuest = ownerGuest;
	}

	public SportFacilityBooking(double totalPrice,
			LinkedList<SportFacilityBookingLine> sportFacilityBookingLines, Guest ownerGuest) {
		this.totalPrice = totalPrice;
		this.sportFacilityBookingLines = sportFacilityBookingLines;
		this.ownerGuest = ownerGuest;
	}


	public SportFacilityBooking() {
	}

	public SportFacilityBooking(int sportFacilityBookingId, double totalPrice, Guest ownerGuest) {
		this.sportFacilityBookingId = sportFacilityBookingId;
		this.totalPrice = totalPrice;
		this.ownerGuest = ownerGuest;
	}

	public SportFacilityBooking(Guest ownerGuest) {
		this.ownerGuest = ownerGuest;
		this.totalPrice = 0.0;
	}

	public SportFacilityBooking(int sportFacilityBookingId) {
		this.sportFacilityBookingId = sportFacilityBookingId;
	}

	public int getSportFacilityBookingId() {
		return sportFacilityBookingId;
	}

	public void setSportFacilityBookingId(int sportFacilityBookingId) {
		this.sportFacilityBookingId = sportFacilityBookingId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LinkedList<SportFacilityBookingLine> getSportFacilityBookingLines() {
		return sportFacilityBookingLines;
	}

	public void setSportFacilityBookingLines(LinkedList<SportFacilityBookingLine> sportFacilityBookingLines) {
		this.sportFacilityBookingLines = sportFacilityBookingLines;
	}

	public Guest getOwnerGuest() {
		return ownerGuest;
	}

	public void setOwnerGuest(Guest ownerGuest) {
		this.ownerGuest = ownerGuest;
	}

	public void addSportFacilityBookingLine(SportFacilityBookingLine sportFacilityBookingLine) {
		sportFacilityBookingLines.add(sportFacilityBookingLine);
	}
}
