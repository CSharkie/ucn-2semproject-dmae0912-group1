package model;

public class RoomBooking {

	int roomBookingId;
	double totalPrice;
	LinkedList<RoomBookingLine> roomBookingLines;
	Guest ownerGuest;
	Employee employee;

	public RoomBooking(int roomBookingId, double totalPrice,
			LinkedList<RoomBookingLine> roomBookingLines, Guest ownerGuest,
			Employee employee) {
		this.roomBookingId = roomBookingId;
		this.totalPrice = totalPrice;
		this.roomBookingLines = roomBookingLines;
		this.ownerGuest = ownerGuest;
		this.employee = employee;
	}

	public RoomBooking(double totalPrice,
			LinkedList<RoomBookingLine> roomBookingLines, Guest ownerGuest,
			Employee employee) {
		this.totalPrice = totalPrice;
		this.roomBookingLines = roomBookingLines;
		this.ownerGuest = ownerGuest;
		this.employee = employee;
	}

	public RoomBooking() {
	}

	public int getRoomBookingId() {
		return roomBookingId;
	}

	public void setRoomBookingId(int roomBookingId) {
		this.roomBookingId = roomBookingId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LinkedList<RoomBookingLine> getRoomBookingLines() {
		return roomBookingLines;
	}

	public void setRoomBookingLines(LinkedList<RoomBookingLine> roomBookingLines) {
		this.roomBookingLines = roomBookingLines;
	}

	public Guest getOwnerGuest() {
		return ownerGuest;
	}

	public void setOwnerGuest(Guest ownerGuest) {
		this.ownerGuest = ownerGuest;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void addRoomBookingLine(RoomBookingLine roomBookingLine) {
		roomBookingLines.add(roomBookingLine);
	}
}
