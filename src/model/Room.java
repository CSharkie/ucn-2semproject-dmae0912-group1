package model;

public class Room {

	int roomId;
	String type;
	int price;
	int capacity;
	Boolean status;
	
	public Room(int roomId, String type, int price, int capacity, Boolean status) {
		super();
		this.roomId = roomId;
		this.type = type;
		this.price = price;
		this.capacity = capacity;
		this.status = status;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
