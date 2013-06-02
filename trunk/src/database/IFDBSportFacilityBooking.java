package database;

import model.LinkedList;
import model.SportFacilityBooking;

public interface IFDBSportFacilityBooking {
	// getAllSportFacilityBookings
	public LinkedList<SportFacilityBooking> getAllSportFacilityBookings(
			boolean retrieveAssociation);

	// get one SportFacilityBooking by ID
	public SportFacilityBooking searchSportFacilityBookingById(int sportFacilityBookingId,
			boolean retrieveAssociation);

	// get some SportFacilityBookinsg by Customer Name
	public LinkedList<SportFacilityBooking> searchSportFacilityBookingByCustomerName(
			String customerName, boolean retrieveAssociation);

	// insert a new SportFacilityBookingLine
	public int insertSportFacilityBooking(SportFacilityBooking sportFacilityBooking) throws Exception;

	// update information about a SportFacilityBooking;
	public int updateSportFacilityBooking(SportFacilityBooking sportFacilityBooking);

	// delete a SportFacilityBooking;
	public int deleteSportFacilityBooking(int sportFacilityBookingId);
}