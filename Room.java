/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Barely Started
 * Last update: 04/04/19
 * Submitted:  04/04/19
 * Comment: test suite and sample run attached
 * @author: Matthew Tam and Chris Ancheta
 * @version: 04/04/19
 */
public class Room {

	private ListRA<Group> seats;
	private int cols;
	private int rows;
	private String movieName;
	private int occupancy;
	
	
	
	public Room(ListRA<Group> seats, int cols, int rows, String movieName, int occupancy) {
		super();
		this.seats = seats;
		this.cols = cols;
		this.rows = rows;
		this.movieName = movieName;
		this.occupancy = occupancy;
	}
	
	public Room(ListRA<Group> seats, int cols, int rows, String movieName) {
		super();
		this.seats = seats;
		this.cols = cols;
		this.rows = rows;
		this.movieName = movieName;
		this.occupancy = 0;
	}

    public static int sequentialSearch(String sk, ListRA<Group> list) {
    	int listSize = list.size();
    	int result = -1; //Unsuccessful by default
    	boolean done = false;
    	
    	for (int i = 0; i < listSize && !done; i++) {
    		/*DEBUG
    		System.out.println(sk + " == " + list.get(i));
    		//*/
    		if ( sk.equals(list.get(i).getName())) {
    			result = i;
    			done = true;  //If a match is found, stop the loop and return the value.
    		}
    	}
    	
    	//returns position, not value.
    	return result;
    }
	
	public void addGroup (Group customer) {
		seats.add(0, customer);
		occupancy +=  customer.getSize();
	}
	
	public Group removeGroup (Group customer) throws ListException {
		Group result = null;
		int index = sequentialSearch(customer.getName(), seats);
		
		switch (index) {
		case -1:
			throw new ListException("Group not found");
		default:
			result = seats.remove(index);
			break;
		}
		
		return result;
	}
    
	public int getAvailableSeats() {
		return occupancy;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		int seatsTaken = occupancy;
		
		for (int i = 1; i <= cols; i++) {
			for (int j = 1; j <= rows; j++) {
				char seat = ' ';
				
				if (seatsTaken > 0) {
					seat = 'X';
					seatsTaken--;
				}
				
				result.append("[")
					.append(seat)
					.append("] ");
			}
			result.append("\n");
		}
		
		return result.toString();
	}
	
	public static void main(String[] args) {
		
		Group test = new Group("test", 5, true);
		
		ListRA<Group> groups = new ListRA<Group>();
		groups.add(0, test);
		
		Room movie = new Room(groups, 3, 3, "Spiderman", 4);
		
		System.out.println(movie);
	}
}
