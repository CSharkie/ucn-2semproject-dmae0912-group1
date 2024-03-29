package model;

public class RoomBooking {

	private int roomBookingId;
	private double totalPrice;
	private LinkedList<RoomBookingLine> roomBookingLines;
	private Guest ownerGuest;
	private Employee employee;
	private TravelAgency agency;

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

	public RoomBooking(double totalPrice,
			LinkedList<RoomBookingLine> roomBookingLines, Guest ownerGuest,
			Employee employee, TravelAgency travelAgency) {
		this.totalPrice = totalPrice;
		this.roomBookingLines = roomBookingLines;
		this.ownerGuest = ownerGuest;
		this.employee = employee;
		this.agency = travelAgency;
	}

	public RoomBooking() {
	}

	public RoomBooking(int roomBookingId, double totalPrice, Guest ownerGuest,
			Employee employee) {
		this.roomBookingId = roomBookingId;
		this.totalPrice = totalPrice;
		this.ownerGuest = ownerGuest;
		this.employee = employee;
	}

	public RoomBooking(int roomBookingId, double totalPrice, Guest ownerGuest,
			Employee employee, TravelAgency travelAgency) {
		this.roomBookingId = roomBookingId;
		this.totalPrice = totalPrice;
		this.ownerGuest = ownerGuest;
		this.employee = employee;
		this.agency = travelAgency;
	}

	public RoomBooking(Guest ownerGuest, Employee employee) {
		this.ownerGuest = ownerGuest;
		this.employee = employee;
		this.totalPrice = 0.0;
	}

	public RoomBooking(Guest ownerGuest, Employee employee,
			TravelAgency travelAgency) {
		this.ownerGuest = ownerGuest;
		this.employee = employee;
		this.agency = travelAgency;
		this.totalPrice = 0.0;
	}

	public RoomBooking(int roomBookingId) {
		this.roomBookingId = roomBookingId;
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

	public TravelAgency getAgency() {
		return agency;
	}

	public void setAgency(TravelAgency agency) {
		this.agency = agency;
	}
}
