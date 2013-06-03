package model;

import java.sql.Timestamp;

public class RoomBookingLine extends BookingLine {
	
	Timestamp checkInDateTime;
	String depositStatus;
	LinkedList<Guest> guests;
	Room room;
	RoomBooking roomBooking;
	
	public RoomBookingLine(int bookingLineId, RoomBooking roomBooking, Timestamp startDateTime,
			Timestamp endDateTime, Double subtotal, Timestamp checkInDateTime, String depositStatus, Room room) {
		super(bookingLineId, startDateTime, endDateTime, subtotal);
		this.checkInDateTime = checkInDateTime;
		this.depositStatus = depositStatus;
		guests = new LinkedList<Guest>();
		this.room = room;
		this.roomBooking = roomBooking;
	}
	
	public RoomBookingLine(RoomBooking roomBooking, Timestamp startDateTime,
			Timestamp endDateTime, Double subtotal, Timestamp checkInDateTime, String depositStatus, Room room) {
		super(startDateTime, endDateTime, subtotal);
		this.checkInDateTime = checkInDateTime;
		this.depositStatus = depositStatus;
		guests = new LinkedList<Guest>();
		this.room = room;
		this.roomBooking = roomBooking;
	}

	public RoomBookingLine() {
		super();
	}

	public RoomBookingLine(int roomBookingLineId, String depositStatus,
			Timestamp checkInDateTime) {
		super(roomBookingLineId);
		this.checkInDateTime = checkInDateTime;
		this.depositStatus = depositStatus;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Timestamp getCheckInDateTime() {
		return checkInDateTime;
	}

	public void setCheckInDateTime(Timestamp checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}

	public String getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(String depositStatus) {
		this.depositStatus = depositStatus;
	}
	
	public void addGuest(Guest guest)
	{
		guests.add(guest);
	}

	public RoomBooking getRoomBooking() {
		return roomBooking;
	}

	public void setRoomBooking(RoomBooking roomBooking) {
		this.roomBooking = roomBooking;
	}	
	
	
}
