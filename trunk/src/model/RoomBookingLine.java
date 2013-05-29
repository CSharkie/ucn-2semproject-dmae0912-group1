package model;

public class RoomBookingLine extends BookingLine {
	
	int checkInDateTime;
	String depositStatus;
	
	public RoomBookingLine(int bookingLineId, int startDateTime,
			int endDateTime, Double subtotal, int checkInDateTime, String depositStatus) {
		super(bookingLineId, startDateTime, endDateTime, subtotal);
		this.checkInDateTime = checkInDateTime;
		this.depositStatus = depositStatus;
	}

	public int getCheckInDateTime() {
		return checkInDateTime;
	}

	public void setCheckInDateTime(int checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}

	public String getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(String depositStatus) {
		this.depositStatus = depositStatus;
	}
	
	
}
