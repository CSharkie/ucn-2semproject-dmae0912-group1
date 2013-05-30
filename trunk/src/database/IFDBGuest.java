package database;

import model.LinkedList;
import model.Guest;

public interface IFDBGuest {
	//getAllGuests
		public LinkedList<Guest> getAllGuests(boolean retrieveAssociation);
	    //get one Guest by ID
		public Guest searchGuestById(int personId, boolean retrieveAssociation);
		//insert a new Guest
		public int insertGuest(Guest guest) throws Exception;
		//update information about a Guest;
		public int updateGuest(Guest guest);
		//delete a Guest;
		public int deleteGuest(int personId);
	}
