package database;

import model.LinkedList;
import model.RoomBooking;

public interface IFDBRoomBooking {
	//getAllRoomBookings
	public LinkedList<RoomBooking> getAllRoomBookings(boolean retrieveAssociation);
    //get one RoomBooking by ID
	public RoomBooking searchRoomBookingById(int roomBookingId, boolean retrieveAssociation);
	//insert a new RoomBookingLine
	public int insertRoomBooking(RoomBooking roomBooking) throws Exception;
	//update information about a RoomBooking;
	public int updateRoomBooking(RoomBooking roomBooking);
	//delete a RoomBooking;
	public int deleteRoomBooking(int roomBookingId);
}