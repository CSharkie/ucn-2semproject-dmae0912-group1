package database;

import model.LinkedList;
import model.RoomBookingLine;

public interface IFDBRoomBookingLine {
	//getAllRoomBookingLines
	public LinkedList<RoomBookingLine> getAllRoomBookingLines(boolean retrieveAssociation);
    //get one RoomBookingLine by ID
	public RoomBookingLine searchRoomBookingLineById(int roomBookingLineId, boolean retrieveAssociation);
	//insert a new RoomBookingLine
	public int insertRoomBookingLine(RoomBookingLine roomBookingLine) throws Exception;
	//update information about a RoomBookingLine;
	public int updateRoomBookingLine(RoomBookingLine roomBookingLine);
	//delete a RoomBookingLine;
	public int deletePerson(int personId);
}
