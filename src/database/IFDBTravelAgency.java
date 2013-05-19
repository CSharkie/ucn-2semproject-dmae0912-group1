package database;

import model.LinkedList;
import model.TravelAgency;

public interface IFDBTravelAgency {
		//getAllTravelAgencies
		public LinkedList getAllTravelAgencies(boolean retrieveAssociation);
	    //get one TravelAgency by ID
		public TravelAgency searchTravelAgencyById(int agencyId, boolean retrieveAssociation);
		//get one TravelAgency by name
		public LinkedList searchTravelAgencyByName(String name, boolean retrieveAssociation);
		
		//insert a new Travel Agency
		public int insertTravelAgency(TravelAgency travelAgency) throws Exception;
		//update information about a customer;
		public int updateTravelAgency(TravelAgency travelAgency);
		//delete a TravelAgency;
		public int deleteTravelAgency(int agencyId);
}
