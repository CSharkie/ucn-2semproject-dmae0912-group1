package database;

import model.Person;

public interface IFDBPerson {
	// insert a new Person
	public int insertPerson(Person person) throws Exception;

	// update information about a Person;
	public int updatePerson(Person person);

	// delete a Person;
	public int deletePerson(int personId);
}
