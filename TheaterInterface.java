
public interface TheaterInterface {
	
	/*
	 * Remove a name from the master list of names
	 * 
	 * @param name The String name to be removed
	 */
	public void removeName(String name);
	/*
	 * Add a name to the master list of names
	 * 
	 * @param customer The String name to be added
	 */
	public void addName(String customer);
	/*
	 * Place a new customer into the appropriate line with the shortest wait time
	 * 
	 * @param Group The information needed pertaining to the new Group
	 */
	public void enter(Group customer);
	/*
	 * Serve the customer at the front of the line lineNum
	 * 
	 * @param lineNum The integer value representing which line to serve. 0,1 or 2 accepted
	 * 
	 * @return The Group to be served
	 */
	public Group serveCustomer(int lineNum);
	/*
	 * Serve the customer at the front of the next line in the order
	 * 
	 * @return The Group to be served
	 */
	public Group serveCustomer();
	/*
	 * Allow a customer to purchase a ticket for one of the available movies and add them to the appropriate Room.
	 * If the Room does not have enough available seats, throws a ListException
	 * 
	 * @param customer The Group trying to purchase a ticket
	 * @param movie The movie for which the ticket is being purchased
	 * 
	 * @return True or False if the add was a success
	 */
	public boolean purchaseTicket(Group customer, String movie) throws ListException;
	/*
	 * The Group matching the searchKey parameter is removed from the movie Room and the master list of names.
	 * searchKey must be in a move Room to be eligible.
	 * 
	 * @param searchKey The name of the Group to be removed
	 * 
	 * @return The Group that is removed
	 */
	public Group leave(String searchKey);
	/*
	 * Display information about the Groups waiting in line.
	 * 
	 * @return The String containing the information
	 */
	public String displayLines();
	/*
	 * Get the Theaters current earnings
	 * 
	 * @return The earnings
	 */
	public double getEarnings();
	/*
	 * Get the Theaters number of tickets sold
	 * 
	 * @return The number of tickets sold
	 */
	public int getTicketsSold();
	/*
	 * Get the seating chart for a movie Room
	 *
	 * @param movieName The name of the movie Room that you want displayed
	 * 
	 * @return The String containing a display of the seating chart
	 */
	public String getSeatingChart(String movieName);
}
