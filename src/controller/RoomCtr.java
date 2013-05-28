package controller;

import model.Room;
import model.LinkedList;
import database.DBRoom;
import database.IFDBRoom;
import database.DBConnection;

public class RoomCtr {

	public RoomCtr() {
		
	}
	
	public LinkedList getAllRooms(){
		IFDBRoom dbRoom=new DBRoom();
		LinkedList list = new LinkedList();
		list = dbRoom.getAllRooms(false);
		return list;
	}
	public Room searchRoomById(int RoomId){
		IFDBRoom dbRoom=new DBRoom();
		return dbRoom.searchRoomById(RoomId, true);
	}
	public LinkedList searchRoomsByType(String type){
		IFDBRoom dbRoom=new DBRoom();
		LinkedList list=new LinkedList();
		list=dbRoom.searchRoomsByType(type, false);
		return list;
	}
	
	public int updateRoom(int roomId, String type, int price, int capacity, Boolean status){
		IFDBRoom dbRoom=new DBRoom();
		Room Room=new Room(roomId, type, price, capacity, status);
		return dbRoom.updateRoom(Room);
	}
	
	public int insertRoom(int roomId, String type, int price, int capacity, Boolean status){
		Room RoomObj=new Room(roomId, type, price, capacity, status);	
		try{
			DBConnection.startTransaction();
			DBRoom dbRoom=new DBRoom();
			dbRoom.insertRoom(RoomObj);
			DBConnection.commitTransaction();
		}
		catch(Exception e)
		{
			DBConnection.rollbackTransaction();
		}
		return RoomObj.getRoomId();
	}
	public void removeRoom(int RoomId) {
		IFDBRoom dbRoom=new DBRoom();
		dbRoom.deleteRoom(RoomId);
	}
	
}
