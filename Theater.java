/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete
 * Last update: 04/29/19
 * Submitted:  04/29/19
 * Comment: test suite and sample run attached
 * @author: Chris Ancheta
 * @version: 04/29/19
 */
import java.util.StringJoiner;
public class Theater // implements TheaterInterface
{
	private RoomInterface shazam;
	private RoomInterface dumbo;
	private AscendinglyOrderedStringList names;
	private QueueRA<Group> express;
	private QueueRA<Group> reg1;
	private QueueRA<Group> reg2;
	private int ticketsSold;
	private double ticketPrice;
	private int lineToServe;

	public Theater(int rows, int cols, double ticketPrice) {
		shazam = new Room(rows, cols, "Shazam!");
		dumbo = new Room(rows, cols, "Dumbo");
		express = new QueueRA<Group>();
		reg1 = new QueueRA<Group>();
		reg2 = new QueueRA<Group>();
		names = new AscendinglyOrderedStringList();
		ticketsSold = 0;
		lineToServe = -1;
		this.ticketPrice = ticketPrice;
	}

	public void removeName(String name) {
		try {
			names.remove(names.search(name));
			// unit testing
			//System.out.println(names);
		} catch (ListIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
	}

	public void addName(String customer) {
		names.add(customer);
	}

	public void enter(GroupInterface customer) throws ListException {
		int reg1Size = reg1.size();
		int reg2Size = reg2.size();
		int expSize = express.size();
		if (customer.getKids()) { // Customer is eligible for express
			if (reg1Size < reg2Size) { // place them in the line that will have
				if (expSize > (2 * reg1Size)) { // the shortest wait time of the three
					reg1.enqueue(customer);
				} else {
					express.enqueue(customer);
				}
			} else {
				if (expSize > (2 * reg2Size)) {
					reg2.enqueue(customer);
				} else {
					express.enqueue(customer);
				}
			}
		} else { // Customer has no kids under 11 y/o
			if (reg1Size < reg2Size) { // Place them in the shortest of the two
				reg1.enqueue(customer); // available lines
			} else {
				reg2.enqueue(customer);
			}
		}

		// Unit testing
		//System.out.println("express " + express.toString());
		//System.out.println("reg1 " + reg1.toString());
		//System.out.println("reg2 " + reg2.toString());

	}

	/*
	 * First time serving
	 */
	public GroupInterface serveCustomer(int lineNum) {
		lineToServe = lineNum;
		return serveCustomer();

	}

	/*
	 * All subsequent times serving
	 * 
	 */
	public GroupInterface serveCustomer() {
		// Queues processed in the order Express->Reg1->Reg2->Express->
		// if a queue is empty try the next one, if all are empty returns null
		GroupInterface customer = null;
		int attempts = 0;
		while(attempts < 4)
		try {
			switch (lineToServe) {
			case 0:
				customer = express.dequeue();
				break;
			case 1:
				customer = reg1.dequeue();
				break;
			case 2:
				customer = reg2.dequeue();
				break;
			case 3:
				customer = express.dequeue();
				break;
			}
			attempts = 5;
		} catch (QueueException e) {
			lineToServe = (++lineToServe) % 4; 
			attempts++;
		}
		return customer;
	}

	public boolean purchaseTicket(GroupInterface customer, String movie) {
		boolean result = false;
		switch (movie.toLowerCase()) {
		case "shazam!":
			if (shazam.getAvailableSeats() >= customer.getSize()) {
				shazam.addGroup(customer);
				result = true;
			} else
				throw new ListException("Not enough seats available");
			break;
		case "dumbo":
			if (dumbo.getAvailableSeats() >= customer.getSize()) {
				dumbo.addGroup(customer);
				result = true;
			} else
				throw new ListException("Not enough seats available");
			break;
		}
		return result;
	}

	public GroupInterface leave(String searchKey) {
		GroupInterface result = null;
		int index = names.search(searchKey);
		if (index >= 0) {
			try {
				try {
					result = shazam.removeGroup(searchKey);
					names.remove(index);
				} catch (ListException e1) {
					try {
						result = dumbo.removeGroup(searchKey);
						names.remove(index);
					} catch (ListException e2) {
						throw e2;
					}
				}
			} catch (ListIndexOutOfBoundsException e3) {
				System.out.println("Something went wrong...");
				e3.printStackTrace();
			}
		} else {
			throw new ListException("Name doesn't exist.");
		}
		return result;
	}
	
	public String displayLines() {
		return String.format("Express line:\n%s\nReg1 Line:\n%s\nReg2 Line\n%s\n",
							express.toString(), reg1.toString(), reg2.toString());
	}
	

	//Experimental line display

	/*public String displayLines() {
		StringJoiner sj = new StringJoiner("\n");
		String[][] lines = {express.toString().split("\n"),
							reg1.toString().split("\n"),
							reg2.toString().split("\n")};
		int index = 0;
		int exausted = 0
		while(){
			String first = lines[0][index];
			String second = lines[1][index];
			String third = lines[2][index];
			sj.add((first == null ? "\t" : first + "\t")
					+ (second == null ? "\t" : second + "\t")
					+ (third == null ? "\t" : third + "\t"));
		}
		return String.format("Express Line \t Reg1 Line \t Reg2 Line\n%s \t %s \t %s",
				express.toString(), reg1.toString(), reg2.toString());
	}*/

	public double getEarnings() {
		return ticketPrice * ticketsSold;
	}
	
	public int getTicketsSold() {
		return ticketsSold;
	}
	
	public String getSeatingChart(String movieName) {
		String result = "";
		switch(movieName.toLowerCase()) {
		case "shazam!":
			result = "Shazam!'s Seating Chart: \n" + shazam.toString();
			break;
		case "dumbo":
			result = "Dumbo's Seating Chart: \n" + dumbo.toString();
			break;
			default:
				result = "You should never see this";
		}
		return result;
	}
}
