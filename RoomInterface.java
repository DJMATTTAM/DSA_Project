/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete
 * Last update: 04/29/19
 * Submitted:  04/29/19
 * Comment: test suite and sample run attached
 * @author: Matthew Tam
 * @version: 04/29/19
 */
public interface RoomInterface {

	
	/**
	 * Conducts a sequential search for a String key
	 * 
	 * @author Matthew Tam
	 * Assumes correct input (String)
	 * @param sk
	 * @return the position of the key if found.  Returns -1 if not found.
	 */
	int sequentialSearch(String sk);

	/**
	 * Adds a Group object to a the ListRA data structure contained inside of Room
	 * 
	 * @author Matthew Tam
	 * Assumes correct input
	 * @param customer
	 */
	void addGroup(Group customer);

	/**
	 * Removes a group from the ListRA data structure by conducting a sequential search
	 * for the unique string name of the group
	 * 
	 * @author Matthew Tam
	 * Assumes correct input
	 * @param customer
	 * @throws ListException
	 */
	Group removeGroup(String customer) throws ListException;

	/**
	 * @author Matthew Tam
	 * Calculates how many seats are still available in the Room
	 * @return the available seats by subtracting the size by the occupancy
	 */
	int getAvailableSeats();

	/**
	 * Generates a display of the Room that shows the current size of the Room and marks whether or not
	 * seats are occupied with an 'X', then packages the entire output into a String and returns it.
	 * @author Matthew Tam
	 * @return Display of the available seats in the Room
	 */
	String toString();

}