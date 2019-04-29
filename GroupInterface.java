/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete
 * Last update: 04/29/19
 * Submitted:  04/29/19
 * Comment: test suite and sample run attached
 * @author: Matthew Tam
 * @version: 04/29/19
 */
public interface GroupInterface {

	/**
	 * Gets the Group's unique name
	 * @author Matthew Tam
	 * @return this Group's unique name
	 */
	String getName();

	/**
	 * Gets the Group's size
	 * @author Matthew Tam
	 * @return this Group's size
	 */
	int getSize();

	/**
	 * Returns true if this group has kids under 11
	 * Returns false if this group doesn't have kids under 11
	 * @author Matthew Tam
	 * @return whether or not this group has kids.
	 */
	boolean getKids();

	/**
	 * Packages all information about the group into a String and returns it
	 * @author Matthew Tam
	 * @return information about theGroup like the size, unique name, and whether or not this group has kids under 11
	 */
	String toString();

}