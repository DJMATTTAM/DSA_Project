/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete
 * Last update: 04/29/19
 * Submitted:  04/29/19
 * Comment: test suite and sample run attached
 * @author: Matthew Tam
 * @version: 04/29/19
 */
public class Group implements GroupInterface {

    private String name;
    private int size;
    private boolean underEleven;
    private String movie;

    public Group(String name, int size, boolean underEleven, String movie) {
        this.name = name;
        this.size = size;
        this.underEleven = underEleven;
	this.movie = movie;
    }

    /* (non-Javadoc)
     * @see GroupInterface#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /* (non-Javadoc)
     * @see GroupInterface#getSize()
     */
    @Override
    public int getSize() {
        return size;
    }

    /* (non-Javadoc)
     * @see GroupInterface#getKids()
     */
    @Override
    public boolean getKids() {
        return underEleven;
    }

    public String getMovie() {
	return movie;
    }


    /* (non-Javadoc)
     * @see GroupInterface#toString()
     */
    @Override
    public String toString() {
        //return "Group [name=" + name + ", size=" + size + ", underEleven=" + underEleven + "]";
        return "Name: " + name + " Group Size: " + size;
    }

}
