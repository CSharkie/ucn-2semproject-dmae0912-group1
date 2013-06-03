package controller;

import java.sql.Timestamp;
import java.util.Date;

import model.Employee;
import model.Guest;
import model.Room;
import model.RoomBooking;
import model.LinkedList;
import model.RoomBookingLine;
import model.TravelAgency;
import database.DBRoomBooking;
import database.DBRoomBookingLine;
import database.DBRoomBookingLine_Guest;
import database.IFDBRoomBooking;
import database.DBConnection;
import database.IFDBRoomBookingLine;
import database.IFDBRoomBookingLine_Guest;

public class RoomBookingCtr {

	public RoomCtr roomCtr;

	public RoomBookingCtr() {
		roomCtr = new RoomCtr();
	}

	public LinkedList<RoomBooking> getAllRoomBookings() {
		IFDBRoomBooking dbRoomBooking = new DBRoomBooking();
		LinkedList<RoomBooking> list = new LinkedList<RoomBooking>();
		list = dbRoomBooking.getAllRoomBookings(true);
		return list;
	}

	public RoomBooking searchRoomBookingById(int RoomBookingId) {
		IFDBRoomBooking dbRoomBooking = new DBRoomBooking();
		return dbRoomBooking.searchRoomBookingById(RoomBookingId, true);
	}

	public LinkedList<RoomBooking> searchRoomBookingByCustomerName(
			String customerName) {
		IFDBRoomBooking dbRoomBooking = new DBRoomBooking();
		return dbRoomBooking
				.searchRoomBookingByCustomerName(customerName, true);
	}

	public int updateRoomBooking(int roomBookingId, double totalPrice,
			int ownerGuestId, int employeeId, int agencyId) {
		IFDBRoomBooking dbRoomBooking = new DBRoomBooking();
		RoomBooking roomBooking = new RoomBooking(roomBookingId, totalPrice,
				new Guest(ownerGuestId), new Employee(employeeId),
				new TravelAgency(agencyId));
		return dbRoomBooking.updateRoomBooking(roomBooking);
	}

	public int insertRoomBooking(int ownerGuestId, int employeeId, int agencyId) {
		RoomBooking roomBooking = new RoomBooking(new Guest(ownerGuestId),
				new Employee(employeeId), new TravelAgency(agencyId));
		try {
			DBConnection.startTransaction();
			DBRoomBooking dbRoomBooking = new DBRoomBooking();
			dbRoomBooking.insertRoomBooking(roomBooking);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
		return roomBooking.getRoomBookingId();
	}

	public void removeRoomBooking(int RoomBookingId) {
		IFDBRoomBooking dbRoomBooking = new DBRoomBooking();
		dbRoomBooking.deleteRoomBooking(RoomBookingId);
	}

	public void addRoomBookingLine(int roomBookingId, int roomId,
			Date startDate, Date endDate) {
		Room room = roomCtr.searchRoomById(roomId);
		long days1 = startDate.getTime() / (60 * 60 * 24 * 1000);
		long days2 = endDate.getTime() / (60 * 60 * 24 * 1000);
		long amount = days2 - days1;
		double subtotal = amount * room.getPrice();
		if (room != null) {
			RoomBookingLine roomBookingLine = new RoomBookingLine(
					new RoomBooking(roomBookingId), new Timestamp(
							startDate.getTime()), new Timestamp(
							endDate.getTime()), subtotal, null, "Unpaid", room);
			try {
				DBConnection.startTransaction();
				DBRoomBookingLine dbRoomBookingLine = new DBRoomBookingLine();
				dbRoomBookingLine.insertRoomBookingLine(roomBookingLine);
				DBConnection.commitTransaction();
			} catch (Exception e) {
				DBConnection.rollbackTransaction();
			}
		}
	}

	public LinkedList<RoomBookingLine> getAllRoomBookingLines(int roomBookingId) {
		IFDBRoomBookingLine dbRoomBookingLine = new DBRoomBookingLine();
		LinkedList<RoomBookingLine> list = new LinkedList<RoomBookingLine>();
		list = dbRoomBookingLine.getAllRoomBookingLines(roomBookingId, false);
		return list;
	}

	public int updateRoomBookingLineDepAndCheck(int roomBookingLineId,
			String depositStatus, Date checkInDateTime) {
		Timestamp checkIn = null;
		if (checkInDateTime != null)
			checkIn = new Timestamp(checkInDateTime.getTime());
		IFDBRoomBookingLine dbRoomBookingLine = new DBRoomBookingLine();
		RoomBookingLine roomBookingline = new RoomBookingLine(
				roomBookingLineId, depositStatus, checkIn);
		return dbRoomBookingLine
				.updateRoomBookingLineDepAndCheck(roomBookingline);
	}

	public void removeRoomBoolingLine(int roomBookingLineId) {
		IFDBRoomBookingLine dbRoomBookingLine = new DBRoomBookingLine();
		dbRoomBookingLine.deleteRoomBookingLine(roomBookingLineId);
	}

	public void addGuest(int roomBookingLineId, int guestId) {
		try {
			DBConnection.startTransaction();
			IFDBRoomBookingLine_Guest dbRoomBookingLine_Guest = new DBRoomBookingLine_Guest();
			dbRoomBookingLine_Guest.insertGuest(roomBookingLineId, guestId);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
	}

	public void deleteGuest(int RoomBookingLineId, int GuestId) {
		IFDBRoomBookingLine_Guest dbRoomBookingLine_Guest = new DBRoomBookingLine_Guest();
		dbRoomBookingLine_Guest.deleteGuest(RoomBookingLineId, GuestId);
	}

	public LinkedList<Guest> getGuests(int roomBookingLineId) {
		IFDBRoomBookingLine_Guest dbRoomBookingLine_Guest = new DBRoomBookingLine_Guest();
		return dbRoomBookingLine_Guest.getAllGuests(roomBookingLineId, false);
	}

	public boolean checkRoomAvailability(int roomId, Date startDate,
			Date endDate) {
		IFDBRoomBookingLine dbRoomBookingLine = new DBRoomBookingLine();
		return dbRoomBookingLine.checkRoomAvailability(roomId, startDate,
				endDate);
	}
}
