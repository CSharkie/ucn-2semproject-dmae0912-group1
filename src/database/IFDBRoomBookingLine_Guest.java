package database;

import model.Guest;
import model.LinkedList;

public interface IFDBRoomBookingLine_Guest {
	// getAllRooms
	public LinkedList<Guest> getAllGuests(int RoomBookingLineId,
			boolean retrieveAssociation);

	// insert a Guest
	public int insertGuest(int RoomBookingLineId, int GuestId) throws Exception;

	// delete a Guest;
	public int deleteGuest(int RoomBookingLineId, int GuestId);
}
