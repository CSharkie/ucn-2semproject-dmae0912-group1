package controller;

import model.LinkedList;
import model.SportFacility;
import database.DBConnection;
import database.DBSportFacility;
import database.IFDBSportFacility;

public class SportFacilityCtr {

	public SportFacilityCtr() {

	}

	public LinkedList<SportFacility> getAllSportFacilities() {
		IFDBSportFacility dbSportFacility = new DBSportFacility();
		LinkedList<SportFacility> list = new LinkedList<SportFacility>();
		list = dbSportFacility.getAllSportFacilities(false);
		return list;
	}

	public SportFacility searchSportFacilityById(int SportFacilityId) {
		IFDBSportFacility dbSportFacility = new DBSportFacility();
		return dbSportFacility.searchSportFacilityById(SportFacilityId, true);
	}

	public int updateSportFacility(int sportFacilityId, String name,
			int maxPersons, double cost, String type, int numberOfLocations) {
		IFDBSportFacility dbRoom = new DBSportFacility();
		SportFacility sportFacility = new SportFacility(sportFacilityId, name,
				maxPersons, cost, type, numberOfLocations);
		return dbRoom.updateSportFacility(sportFacility);
	}

	public int insertSportFacility(String name, int maxPersons, double cost,
			String type, int numberOfLocations) {
		SportFacility sportFacility = new SportFacility(name, maxPersons, cost,
				type, numberOfLocations);
		try {
			DBConnection.startTransaction();
			DBSportFacility dbSportFacility = new DBSportFacility();
			dbSportFacility.insertSportFacility(sportFacility);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
		return sportFacility.getSportFacilityId();
	}

	public void removeSportFacility(int SportFacilityId) {
		IFDBSportFacility dbSportFacility = new DBSportFacility();
		dbSportFacility.deleteSportFacility(SportFacilityId);
	}

}
