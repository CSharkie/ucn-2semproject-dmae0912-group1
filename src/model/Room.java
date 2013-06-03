package model;

public class Room {

	private int roomId;
	private String type;
	private double price;
	private int capacity;
	private String status;

	public Room() {

	}

	public Room(int roomId, String type, double price, int capacity,
			String status) {
		this.roomId = roomId;
		this.type = type;
		this.price = price;
		this.capacity = capacity;
		this.status = status;
	}

	public Room(String type, double price, int capacity, String status) {
		this.type = type;
		this.price = price;
		this.capacity = capacity;
		this.status = status;
	}

	public Room(int roomId) {
		this.roomId = roomId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
