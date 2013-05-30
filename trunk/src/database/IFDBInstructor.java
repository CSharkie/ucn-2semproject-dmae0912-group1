package database;

import model.Instructor;
import model.LinkedList;

public interface IFDBInstructor {
	//getAllInstructors
	public LinkedList<Instructor> getAllInstructors(boolean retrieveAssociation);
    //get one Instructor by ID
	public Instructor searchInstructorById(int personId, boolean retrieveAssociation);
	//insert a new Instructor
	public int insertInstructor(Instructor instructor) throws Exception;
	//update information about a Instructor;
	public int updateInstructor(Instructor instructor);
	//delete a Instructor;
	public int deleteInstructor(int personId);
}
