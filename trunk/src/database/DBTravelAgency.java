package database;

import model.TravelAgency;
import java.sql.*;
import model.LinkedList;

public class DBTravelAgency {

	private Connection con;

		/** Creates a new instance of DBTravelAgency */
		public DBTravelAgency() {
			con = DBConnection.getInstance().getDBcon();
		}

		// Implements the methods from the interface
		// get all TravelAgencys
		public LinkedList getAllTravelAgencys(boolean retrieveAssociation) {
			return miscWhere("", retrieveAssociation);
		}
		
		// get one TravelAgency having the ID
		public TravelAgency searchTravelAgencyById(int agencyId,
				boolean retrieveAssociation) {
			String wClause = " agencyId = '" + agencyId + "'";
			return singleWhere(wClause, retrieveAssociation);
		}

		// find one TravelAgency having the name
		public LinkedList searchTravelAgencysByName(String name, boolean retrieveAssociation) {
			String wClause = "Name LIKE '%" + name + "%'";
			System.out.println("TravelAgency " + wClause);
			return miscWhere(wClause, retrieveAssociation);
		}

		// insert a new TravelAgency
		public int insertTravelAgency(TravelAgency TravelAgency) throws Exception { // call to get
																	// the next Id
																	// number
			int nextId = getMax.getMaxId("Select max(agencyId) from TravelAgency");
			nextId = nextId + 1;
			System.out.println("next ID = " + nextId);
			TravelAgency.setAgencyId(nextId);
			int rc = -1;
			String query = "INSERT INTO TravelAgency(agencyId, type, price, capacity, status)  VALUES('"
					+ TravelAgency.getAgencyId()
					+ "','"
					+ TravelAgency.getName()
					+ "','"
					+ TravelAgency.getDiscount()
					+ "','"
					+ TravelAgency.getAddress()
					+ "','"
					+ TravelAgency.getPhoneNo()
					+ "','"
					+ TravelAgency.getEmail()
					+ "');";

			System.out.println("insert : " + query);
			try { // insert new TravelAgency
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				rc = stmt.executeUpdate(query);
				stmt.close();
			}// end try
			catch (SQLException ex) {
				System.out.println("TravelAgency is not inserted");
				throw new Exception("TravelAgency is not inserted");
			}
			return (rc);
		}

		public int updateTravelAgency(TravelAgency TravelAgency) {
			TravelAgency TravelAgencyObj = TravelAgency;
			int rc = -1;

			String query = "UPDATE TravelAgency SET " + "name ='" + TravelAgencyObj.getName()
					+ "', " + "discount ='" + TravelAgencyObj.getDiscount() + "', "
					+ "address ='" + TravelAgencyObj.getAddress() + "', " + "phoneNo ='"
					+ TravelAgencyObj.getPhoneNo() + "', " + TravelAgencyObj.getEmail()
					+ "' " + " WHERE agencyId = '" + TravelAgencyObj.getAgencyId()
					+ "'";
			System.out.println("Update query:" + query);
			try { // update TravelAgency
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				rc = stmt.executeUpdate(query);

				stmt.close();
			}// end try
			catch (Exception ex) {
				System.out.println("Update exception in TravelAgency db: " + ex);
			}
			return (rc);
		}

		public int deleteTravelAgency(int agencyId) {
			int rc = -1;

			String query = "DELETE FROM TravelAgency WHERE agencyId = '" + agencyId + "'";
			System.out.println(query);
			try { // delete from TravelAgency
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				rc = stmt.executeUpdate(query);
				stmt.close();
			}// end try
			catch (Exception ex) {
				System.out.println("Delete exception in TravelAgency db: " + ex);
			}
			return (rc);
		}

		// private methods
		// michWere is used whenever we want to select more than one TravelAgency

		private LinkedList miscWhere(String wClause,
				boolean retrieveAssociation) {
			ResultSet results;
			LinkedList list = new LinkedList();

			String query = buildQuery(wClause);

			try { // read the TravelAgency from the database
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				results = stmt.executeQuery(query);

				while (results.next()) {
					TravelAgency TravelAgencyObj = new TravelAgency();
					TravelAgencyObj = buildTravelAgency(results);
					list.add(TravelAgencyObj);
				}// end while
				stmt.close();

			}// end try
			catch (Exception e) {
				System.out.println("Query exception - select: " + e);
				e.printStackTrace();
			}
			return list;
		}

		// Single where is used when we only select one TravelAgency
		private TravelAgency singleWhere(String wClause, boolean retrieveAssociation) {
			ResultSet results;
			TravelAgency TravelAgencyObj = new TravelAgency();

			String query = buildQuery(wClause);
			System.out.println(query);
			try { // read the TravelAgency from the database
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				results = stmt.executeQuery(query);

				if (results.next()) {
					TravelAgencyObj = buildTravelAgency(results);
					stmt.close();
				} else { // no TravelAgency was found
					TravelAgencyObj = null;
				}
			}// end try
			catch (Exception e) {
				System.out.println("Query exception: " + e);
			}
			return TravelAgencyObj;
		}

		// method to build the query
		private String buildQuery(String wClause) {
			String query = "SELECT agencyId, name, disocunt, address, phoneNo, email  FROM TravelAgency";

			if (wClause.length() > 0)
				query = query + " WHERE " + wClause;

			return query;
		}

		// method to build an TravelAgency object
		private TravelAgency buildTravelAgency(ResultSet results) {
			TravelAgency TravelAgencyObj = new TravelAgency();
			try { // the columns from the table TravelAgency are used
				TravelAgencyObj.setAgencyId(results.getInt("AgencyId"));
				TravelAgencyObj.setName(results.getString("Name"));
				TravelAgencyObj.setDiscount(results.getInt("Discount"));
				TravelAgencyObj.setAddress(results.getString("Address"));
				TravelAgencyObj.setPhoneNo(results.getString("PhoneNo"));
				TravelAgencyObj.setEmail(results.getString("Email"));
			} catch (Exception e) {
				System.out.println("error in building the TravelAgency object");
			}
			return TravelAgencyObj;
		}


	}
