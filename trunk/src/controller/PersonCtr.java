package controller;

import model.Person;
import model.LinkedList;
import database.*;

public class PersonCtr {

	public PersonCtr() {
		
	}
	
	public LinkedList getAllPersons(){
		IFDBPerson dbCust=new DBPerson();
		LinkedList linkedList = new LinkedList();
		linkedList = dbCust.getAllPersons(false);
		return linkedList;
	}
	public LinkedList searchPersonsByName(String name){
		IFDBPerson dbCust=new DBPerson();
		return dbCust.searchPersonsByName(name, true);
	}
	public Person searchPersonById(int PersonId){
		IFDBPerson dbCust=new DBPerson();
		return dbCust.searchPersonById(PersonId, true);
	}
	public int updatePerson(int personId, String firstname, String surname, String address, String phoneNo, String email){
		IFDBPerson dbCust=new DBPerson();
		Person cust=new Person(personId, firstname, surname, address, phoneNo, email);
		return dbCust.updatePerson(cust);
	}
	
	public int insertPerson(String firstname, String surname, String address, String phoneNo, String email){
		Person custObj=new Person(firstname, surname, address, phoneNo, email);	
		try{
			DBConnection.startTransaction();
			DBPerson dbCust=new DBPerson();
			dbCust.insertPerson(custObj);
			DBConnection.commitTransaction();
		}
		catch(Exception e)
		{
			DBConnection.rollbackTransaction();
		}
		return custObj.getPersonId();
	}
	public void removePerson(int PersonId) {
		IFDBPerson dbCust=new DBPerson();
		dbCust.deletePerson(PersonId);
	}
	
}
