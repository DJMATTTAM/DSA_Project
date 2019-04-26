/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Barely Started
 * Last update: 04/04/19
 * Submitted:  04/04/19
 * Comment: test suite and sample run attached
 * @author: Matthew Tam and Chris Ancheta
 * @version: 04/04/19
 */
public class Group {
	
	private String name;
	private int size;
	private boolean underEleven;
	
	public Group(String name, int size, boolean underEleven) {
		this.name = name;
		this.size = size;
		this.underEleven = underEleven;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public boolean getKids() {
		return underEleven;
	}

	@Override
	public String toString() {
		//return "Group [name=" + name + ", size=" + size + ", underEleven=" + underEleven + "]";
		return "Name: " + name + " Group Size: " + size;
	}
	
}
