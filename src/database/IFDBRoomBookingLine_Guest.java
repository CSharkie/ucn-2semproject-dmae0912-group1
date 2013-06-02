package database;

import model.Guest;
import model.LinkedList;
 
public interface IFDBRoomBookingLine_Guest {
			//getAllRooms
			public LinkedList<Guest> getAllGuests(int RoomBookingLineId, boolean retrieveAssociation);
			public int insertGuest(int RoomBookingLineId, int GuestId) throws Exception;
			//delete a Room;
			public int deleteGuest(int RoomBookingLineId, int GuestId);
}
