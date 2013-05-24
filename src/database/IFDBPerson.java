package database;

import model.LinkedList;
import model.Person;

public interface IFDBPerson {
	//getAllPersons
	public LinkedList getAllPersons(boolean retrieveAssociation);
    //get one Person by ID
	public Person searchPersonById(int personId, boolean retrieveAssociation);
	//get Persons by type
	public LinkedList searchPersonByType(String type, boolean retrieveAssociation);
	//insert a new Person
	public int insertPerson(Person person) throws Exception;
	//update information about a Person;
	public int updatePerson(Person person);
	//delete a Person;
	public int deletePerson(int personId);
	//get one person by name
	public LinkedList searchPersonsByName(String name, boolean retrieveAssociation);
}
