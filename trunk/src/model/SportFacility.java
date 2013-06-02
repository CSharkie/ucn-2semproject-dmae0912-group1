package model;

public class SportFacility {

	int sportFacilityId;
	String name;
	int maxPersons;
	double cost;
	String type;
	int numberOfLocations;
	LinkedList<Instructor> instructors;

	public SportFacility() {

	}
	
	public SportFacility(int sportFacilityId, String name, int maxPersons,
			double cost, String type, int numberOfLocations) {
		this.sportFacilityId = sportFacilityId;
		this.name = name;
		this.maxPersons = maxPersons;
		this.cost = cost;
		this.type = type;
		this.numberOfLocations = numberOfLocations;
	}


	

	public SportFacility(String name, int maxPersons, double cost, String type,
			int numberOfLocations) {
		this.name = name;
		this.maxPersons = maxPersons;
		this.cost = cost;
		this.type = type;
		this.numberOfLocations = numberOfLocations;
	}



	public SportFacility(int sportFacilityId) {
		this.sportFacilityId = sportFacilityId;
	}

	public int getSportFacilityId() {
		return sportFacilityId;
	}

	public void setSportFacilityId(int sportFacilityId) {
		this.sportFacilityId = sportFacilityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxPersons() {
		return maxPersons;
	}

	public void setMaxPersons(int maxPersons) {
		this.maxPersons = maxPersons;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNumberOfLocations() {
		return numberOfLocations;
	}

	public void setNumberOfLocations(int numberOfLocations) {
		this.numberOfLocations = numberOfLocations;
	}

	public LinkedList<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(LinkedList<Instructor> instructors) {
		this.instructors = instructors;
	}
}
