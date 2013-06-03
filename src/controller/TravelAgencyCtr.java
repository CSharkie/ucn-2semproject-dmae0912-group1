package controller;

import model.TravelAgency;
import model.LinkedList;
import database.DBTravelAgency;
import database.IFDBTravelAgency;
import database.DBConnection;

public class TravelAgencyCtr {

	public TravelAgencyCtr() {

	}

	public LinkedList<TravelAgency> getAllTravelAgencies() {
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		LinkedList<TravelAgency> list = new LinkedList<TravelAgency>();
		list = dbTravelAgency.getAllTravelAgencies(false);
		return list;
	}

	public TravelAgency searchTravelAgencyById(int TravelAgencyId) {
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		return dbTravelAgency.searchTravelAgencyById(TravelAgencyId, true);
	}

	public LinkedList<TravelAgency> searchTravelAgencyByName(String name) {
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		LinkedList<TravelAgency> list = new LinkedList<TravelAgency>();
		list = dbTravelAgency.searchTravelAgencyByName(name, false);
		return list;
	}

	public int updateTravelAgency(int travelAgencyId, String name,
			double discount, String address, String phoneNo, String email) {
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		TravelAgency travelAgency = new TravelAgency(travelAgencyId, name,
				discount, address, phoneNo, email);
		return dbTravelAgency.updateTravelAgency(travelAgency);
	}

	public int insertTravelAgency(String name, double discount, String address,
			String phoneNo, String email) {
		TravelAgency travelAgency = new TravelAgency(name, discount, address,
				phoneNo, email);
		try {
			DBConnection.startTransaction();
			DBTravelAgency dbTravelAgency = new DBTravelAgency();
			dbTravelAgency.insertTravelAgency(travelAgency);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
		return travelAgency.getAgencyId();
	}

	public void removeTravelAgency(int TravelAgencyId) {
		IFDBTravelAgency dbTravelAgency = new DBTravelAgency();
		dbTravelAgency.deleteTravelAgency(TravelAgencyId);
	}

}
