package database;

import model.LinkedList;
import model.SportFacilityBookingLine;

public interface IFDBSportFacilityBookingLine {
	// getAllSportFacilityBookingLines by SportFacilityBooking Id
	public LinkedList<SportFacilityBookingLine> getAllSportFacilityBookingLines(
			int sportFacilityBookingId, boolean retrieveAssociation);

	// get one SportFacilityBookingLine by ID
	public SportFacilityBookingLine searchSportFacilityBookingLineById(
			int sportFacilityBookingLineId, boolean retrieveAssociation);

	// insert a new SportFacilityBookingLine
	public int insertSportFacilityBookingLine(
			SportFacilityBookingLine sportFacilityBookingLine) throws Exception;

	// update information about a SportFacilityBookingLine;
	public int updateSportFacilityBookingLine(
			SportFacilityBookingLine sportFacilityBookingLine);

	// delete a SportFacilityBookingLine;
	public int deleteSportFacilityBookingLine(int sportFacilityBookingLineId);
}