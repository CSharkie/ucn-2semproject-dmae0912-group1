package database;

import model.LinkedList;
import model.Room;

public interface IFDBRoom {
	// getAllRooms
	public LinkedList<Room> getAllRooms(boolean retrieveAssociation);

	// get one Room by ID
	public Room searchRoomById(int roomId, boolean retrieveAssociation);

	// get rooms by type
	public LinkedList<Room> searchRoomsByType(String type,
			boolean retrieveAssociation);

	// insert a new Room
	public int insertRoom(Room room) throws Exception;

	// update information about a Room;
	public int updateRoom(Room room);

	// delete a Room;
	public int deleteRoom(int roomId);
}
