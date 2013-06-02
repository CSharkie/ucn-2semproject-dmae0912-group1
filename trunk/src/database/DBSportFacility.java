package database;

import model.SportFacility;
import java.sql.*;
import model.LinkedList;

public class DBSportFacility implements IFDBSportFacility {
	private Connection con;

	/** Creates a new instance of DBRoom */
	public DBSportFacility() {
		con = DBConnection.getInstance().getDBcon();
	}

	// Implements the methods from the interface
	// get all Rooms
	public LinkedList<SportFacility> getAllSportFacilities(
			boolean retrieveAssociation) {
		return miscWhere("", retrieveAssociation);
	}

	// get one Room having the ID
	public SportFacility searchSportFacilityById(int sportFacilityId,
			boolean retrieveAssociation) {
		String wClause = " SportFacilityId = '" + sportFacilityId + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

	// insert a new SportFacility
	public int insertSportFacility(SportFacility sportFacility)
			throws Exception { // call to get
		// the next Id
		// number
		int nextId = getMax
				.getMaxId("Select max(SportFacilityId) from SportFacility");
		nextId = nextId + 1;
		System.out.println("next ID = " + nextId);
		sportFacility.setSportFacilityId(nextId);
		int rc = -1;
		String query = "INSERT INTO SportFacility(sportFacilityId, name, maxPersons, cost, numberOfLocations, type)  VALUES('"
				+ sportFacility.getSportFacilityId()
				+ "','"
				+ sportFacility.getName()
				+ "','"
				+ sportFacility.getMaxPersons()
				+ "','"
				+ sportFacility.getCost()
				+ "','"
				+ sportFacility.getNumberOfLocations()
				+ "','"
				+ sportFacility.getType() + "');";

		System.out.println("insert : " + query);
		try { // insert new Room
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("SportFacility is not inserted");
			throw new Exception("SportFacility is not inserted");
		}
		return (rc);
	}

	@Override
	public int updateSportFacility(SportFacility sportFacility) {
		int rc = -1;

		String query = "UPDATE SportFacility SET " + "name ='"
				+ sportFacility.getName() + "', " + "maxPersons ='"
				+ sportFacility.getMaxPersons() + "', " + "cost ='"
				+ sportFacility.getCost() + "', " + "numberOfLocations ='"
				+ sportFacility.getNumberOfLocations() + "', " + "type ='"
				+ sportFacility.getType() + "'" + " WHERE SportFacilityId = '"
				+ sportFacility.getSportFacilityId() + "'";
		System.out.println("Update query:" + query);
		try { // update SportFacility
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		}// end try
		catch (Exception ex) {
			System.out.println("Update exception in SportFacility db: " + ex);
		}
		return (rc);
	}

	public int deleteSportFacility(int sportFacilityId) {
		int rc = -1;

		String query = "DELETE FROM SportFacility WHERE SportFacilityId = '"
				+ sportFacilityId + "'";
		System.out.println(query);
		try { // delete from SportFacility
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (Exception ex) {
			System.out.println("Delete exception in SportFacility db: " + ex);
		}
		return (rc);
	}

	// private methods
	// michWere is used whenever we want to select more than one SportFacility

	private LinkedList<SportFacility> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		LinkedList<SportFacility> list = new LinkedList<SportFacility>();

		String query = buildQuery(wClause);

		try { // read the Room from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				SportFacility sportFacility = new SportFacility();
				sportFacility = buildSportFacility(results);

				list.add(sportFacility);
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
	private SportFacility singleWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		SportFacility sportFacility = new SportFacility();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { // read the Room from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				sportFacility = buildSportFacility(results);
				stmt.close();

			} else { // no SportFacility was found
				sportFacility = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return sportFacility;
	}

	// method to build the query
	private String buildQuery(String wClause) {
		String query = "SELECT sportFacilityId, name, maxPersons, cost, numberOfLocations, type FROM SportFacility";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	// method to build an Room object
	private SportFacility buildSportFacility(ResultSet results) {
		SportFacility sportFacility = new SportFacility();
		try { // the columns from the table Room are used
			sportFacility.setSportFacilityId(results.getInt("sportFacilityId"));
			sportFacility.setName(results.getString("name"));
			sportFacility.setMaxPersons(results.getInt("maxPersons"));
			sportFacility.setCost(results.getDouble("cost"));
			sportFacility.setNumberOfLocations(results
					.getInt("numberOfLocations"));
			sportFacility.setType(results.getString("type"));
		} catch (Exception e) {
			System.out.println("error in building the SportFacility object");
		}
		return sportFacility;
	}

}
