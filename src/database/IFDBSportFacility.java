package database;

import model.LinkedList;
import model.SportFacility;

public interface IFDBSportFacility {
	// getAllRooms
	public LinkedList<SportFacility> getAllSportFacilities(
			boolean retrieveAssociation);

	// get one Room by ID
	public SportFacility searchSportFacilityById(int sportFacilityId,
			boolean retrieveAssociation);

	// insert a new Room
	public int insertSportFacility(SportFacility sportFacility)
			throws Exception;

	// update information about a Room;
	public int updateSportFacility(SportFacility sportFacility);

	// delete a Room;
	public int deleteSportFacility(int sportFacilityId);
}
