package controller;

import java.sql.Timestamp;
import java.util.Date;

import model.Guest;
import model.LinkedList;
import model.SportFacility;
import model.SportFacilityBooking;
import model.SportFacilityBookingLine;
import database.DBSportFacilityBooking;
import database.DBSportFacilityBookingLine;
import database.DBConnection;
import database.IFDBSportFacilityBooking;
import database.IFDBSportFacilityBookingLine;

public class SportFacilityBookingCtr {

	public SportFacilityCtr sportFacilityCtr;

	public SportFacilityBookingCtr() {
		sportFacilityCtr = new SportFacilityCtr();
	}

	public LinkedList<SportFacilityBooking> getAllSportFacilityBooking() {
		IFDBSportFacilityBooking dbSportFacilityBooking = new DBSportFacilityBooking();
		LinkedList<SportFacilityBooking> list = new LinkedList<SportFacilityBooking>();
		list = dbSportFacilityBooking.getAllSportFacilityBookings(true);
		return list;
	}

	public SportFacilityBooking searchSportFacilityBookingById(int sportFacilityBookingId) {
		IFDBSportFacilityBooking dbSportFacilityBooking = new DBSportFacilityBooking();
		return dbSportFacilityBooking.searchSportFacilityBookingById(sportFacilityBookingId, true);
	}

	public LinkedList<SportFacilityBooking> searchSportFacilityBookingByCustomerName(
			String customerName) {
		IFDBSportFacilityBooking dbSportFacilityBooking = new DBSportFacilityBooking();
		return dbSportFacilityBooking
				.searchSportFacilityBookingByCustomerName(customerName, true);
	}

	public int updateSportFacilityBooking(int sportFacilityBookingId, double totalPrice,
			int ownerGuestId) {
		IFDBSportFacilityBooking dbSportFacilityBooking = new DBSportFacilityBooking();
		SportFacilityBooking sportFacilityBooking = new SportFacilityBooking(sportFacilityBookingId, totalPrice,
				new Guest(ownerGuestId));
		return dbSportFacilityBooking.updateSportFacilityBooking(sportFacilityBooking);
	}

	public int insertSportFacilityBooking(int ownerGuestId) {
		SportFacilityBooking sportFacilityBooking = new SportFacilityBooking(new Guest(ownerGuestId));
		try {
			DBConnection.startTransaction();
			DBSportFacilityBooking dbSportFacilityBooking = new DBSportFacilityBooking();
			dbSportFacilityBooking.insertSportFacilityBooking(sportFacilityBooking);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
		return sportFacilityBooking.getSportFacilityBookingId();
	}

	public void removeSportFacilityBooking(int sportFacilityBookingId) {
		IFDBSportFacilityBooking dbSportFacilityBooking = new DBSportFacilityBooking();
		dbSportFacilityBooking.deleteSportFacilityBooking(sportFacilityBookingId);
	}

	public void addSportFacilityBookingLine(int sportFacilityBookingId, int sportFacilityId,
			Date startDate, Date endDate) {
		SportFacility sportFacility = sportFacilityCtr.searchSportFacilityById(sportFacilityId);
		long hour1 = startDate.getTime()/(60*60*1000);
		long hour2 = endDate.getTime()/(60*60*1000);
		long amount = hour2 - hour1;
		double subtotal = amount * sportFacility.getCost();
		if (sportFacility != null) {
			SportFacilityBookingLine sportFacilityBookingLine = new SportFacilityBookingLine(new SportFacilityBooking(sportFacilityBookingId), new Timestamp(startDate.getTime()),
					new Timestamp(endDate.getTime()), subtotal, sportFacility);
			try {
				DBConnection.startTransaction();
				IFDBSportFacilityBookingLine dbSportFacilityBookingLine = new DBSportFacilityBookingLine();
				dbSportFacilityBookingLine.insertSportFacilityBookingLine(sportFacilityBookingLine);
				DBConnection.commitTransaction();
			} catch (Exception e) {
				DBConnection.rollbackTransaction();
			}
		}
	}

	public LinkedList<SportFacilityBookingLine> getAllSportFacilityBookingLines(int sportFacilityBookingId) {
		IFDBSportFacilityBookingLine dbSportFacilityBookingLine = new DBSportFacilityBookingLine();
		LinkedList<SportFacilityBookingLine> list = new LinkedList<SportFacilityBookingLine>();
		list = dbSportFacilityBookingLine.getAllSportFacilityBookingLines(sportFacilityBookingId, false);
		return list;
	}

	public void removeSportFacilityBookingLine(int sportFacilityBookingLineId) {
		IFDBSportFacilityBookingLine dbSportFacilityBookingLine = new DBSportFacilityBookingLine();
		dbSportFacilityBookingLine.deleteSportFacilityBookingLine(sportFacilityBookingLineId);
	}
}
