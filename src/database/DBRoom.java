package database;

import model.Room;
import java.sql.*;
import model.LinkedList;

public class DBRoom implements IFDBRoom {
	private Connection con;

	/** Creates a new instance of DBRoom */
	public DBRoom() {
		con = DBConnection.getInstance().getDBcon();
	}

	// Implements the methods from the interface
	// get all Rooms
	public LinkedList<Room> getAllRooms(boolean retrieveAssociation) {
		return miscWhere("", retrieveAssociation);
	}
	
	// get one Room having the ID
	public Room searchRoomById(int RoomId,
			boolean retrieveAssociation) {
		String wClause = " RoomId = '" + RoomId + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

	public LinkedList<Room> searchRoomsByType(String type, boolean retrieveAssociation) {
		String wClause = "Type LIKE '%" + type + "%'";
		System.out.println("Room " + wClause);
		return miscWhere(wClause, retrieveAssociation);
	}
	
	// insert a new Room
	public int insertRoom(Room room) throws Exception { // call to get
																// the next Id
																// number
		int nextId = getMax.getMaxId("Select max(RoomId) from Room");
		nextId = nextId + 1;
		System.out.println("next ID = " + nextId);
		room.setRoomId(nextId);
		int rc = -1;
		String query = "INSERT INTO Room(roomId, type, price, capacity, status)  VALUES('"
				+ room.getRoomId()
				+ "','"
				+ room.getType()
				+ "','"
				+ room.getPrice()
				+ "','"
				+ room.getCapacity()
				+ "','"
				+ room.getStatus()
				+ "');";

		System.out.println("insert : " + query);
		try { // insert new Room
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("Room is not inserted");
			throw new Exception("Room is not inserted");
		}
		return (rc);
	}

	@Override
	public int updateRoom(Room room) {
		Room roomObj = room;
		int rc = -1;

		String query = "UPDATE Room SET " + "type ='" + roomObj.getType()
				+ "', " + "price ='" + roomObj.getPrice() + "', "
				+ "capacity ='" + roomObj.getCapacity() + "', " + "status ='"
				+ roomObj.getStatus()
				+ "' " + " WHERE RoomId = '" + roomObj.getRoomId()
				+ "'";
		System.out.println("Update query:" + query);
		try { // update Room
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		}// end try
		catch (Exception ex) {
			System.out.println("Update exception in Room db: " + ex);
		}
		return (rc);
	}

	public int deleteRoom(int RoomId) {
		int rc = -1;

		String query = "DELETE FROM Room WHERE RoomId = '" + RoomId + "'";
		System.out.println(query);
		try { // delete from Room
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (Exception ex) {
			System.out.println("Delete exception in Room db: " + ex);
		}
		return (rc);
	}

	// private methods
	// michWere is used whenever we want to select more than one Room

	private LinkedList<Room> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		LinkedList<Room> list = new LinkedList<Room>();

		String query = buildQuery(wClause);

		try { // read the Room from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Room roomObj = new Room();
				roomObj = buildRoom(results);
				
				list.add(roomObj);
			}// end while
			stmt.close();

		}// end try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	// Single where is used when we only select one Room
	private Room singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Room roomObj = new Room();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { // read the Room from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				roomObj = buildRoom(results);
				stmt.close();
				
			} else { // no Room was found
				roomObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return roomObj;
	}

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT roomId, type, price, capacity, status  FROM Room";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an Room object
	private Room buildRoom(ResultSet results) {
		Room roomObj = new Room();
		try { // the columns from the table Room are used
			roomObj.setRoomId(results.getInt("RoomId"));
			roomObj.setType(results.getString("Type"));
			roomObj.setPrice(results.getInt("Price"));
			roomObj.setCapacity(results.getInt("Capacity"));
			roomObj.setStatus(results.getBoolean("Status"));
		} catch (Exception e) {
			System.out.println("error in building the Room object");
		}
		return roomObj;
	}


}
