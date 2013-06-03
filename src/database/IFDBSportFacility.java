package database;

import model.LinkedList;
import model.SportFacility;

public interface IFDBSportFacility {
	// getAllSportFacilities
	public LinkedList<SportFacility> getAllSportFacilities(
			boolean retrieveAssociation);

	// get one SportFacility by ID
	public SportFacility searchSportFacilityById(int sportFacilityId,
			boolean retrieveAssociation);

	// insert a new SportFacility
	public int insertSportFacility(SportFacility sportFacility)
			throws Exception;

	// update information about a SportFacility;
	public int updateSportFacility(SportFacility sportFacility);

	// delete a SportFacility;
	public int deleteSportFacility(int sportFacilityId);
}
