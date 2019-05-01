public class Theater implements TheaterInterface // implements TheaterInterface
{
    private Room shazam;
    private Room dumbo;
    private AscendinglyOrderedStringList names;
    private QueueRA<Group> express;
    private QueueRA<Group> reg1;
    private QueueRA<Group> reg2;
    private int ticketsSold;
    private double ticketPrice;
    private int lineToServe;

    // Constructor
    public Theater(Room shazam, Room dumbo, double ticketPrice) {
        this.shazam = shazam;
        this.dumbo = dumbo;
        express = new QueueRA<Group>();
        reg1 = new QueueRA<Group>();
        reg2 = new QueueRA<Group>();
        names = new AscendinglyOrderedStringList();
        ticketsSold = 0;
        lineToServe = -1;
        this.ticketPrice = ticketPrice;
    }// end constructor

    // Remove a name from the master list of names
    public void removeName(String name) {
        try {
            names.remove(names.search(name));
            // unit testing
            //System.out.println(names);
        } catch (ListIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }// end removeName

    // Add a name to the master list of names
    public void addName(String customer) {
        names.add(customer);
    }// end addName

    // Customer enters the Theater and is placed into the shortest line possible
    public void enter(Group customer) {
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
    }// end enter

    /*
     * First time serving
     */
    public Group serveCustomer(int lineNum) {
        lineToServe = lineNum;
        return serveCustomer();

    }

    /*
     * All subsequent times serving a customer
     *
     */
    public Group serveCustomer() {
        // Queues processed in the order Express->Reg1->Reg2->Express->
        // if a queue is empty try the next one, if all are empty returns null
        Group customer = null;
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

    public boolean purchaseTicket(Group customer, String movie) throws ListException {
        boolean result = false;
        switch (movie.toLowerCase()) {
        case "shazam!":
            if (shazam.getAvailableSeats() >= customer.getSize()) {
                shazam.addGroup(customer);
                ticketsSold += customer.getSize();
                result = true;
            } else
                throw new ListException("Not enough seats available");
            break;
        case "dumbo":
            if (dumbo.getAvailableSeats() >= customer.getSize()) {
                dumbo.addGroup(customer);
                ticketsSold += customer.getSize();
                result = true;
            } else
                throw new ListException("Not enough seats available");
            break;
        }
        return result;
    }

    public Group leave(String searchKey) {
        Group result = null;
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

    public int getNumCustomers() {
	return names.size();
    }

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
