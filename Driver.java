/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Barely Started
 * Last update: 04/04/19
 * Submitted:  04/04/19
 * Comment: test suite and sample run attached
 * @author: Matthew Tam and Chris Ancheta
 * @version: 04/04/19
 */

import java.io.*;

public class Driver {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        Theater theTheater = setup();
        boolean prompt = true;
        menu();
        while (true) {
            prompt = select(theTheater, prompt);
        }
    }

    private static Theater setup() {
        int rowsShazam;
        int colsShazam;
        int rowsDumbo;
        int colsDumbo;
        double price;
        System.out.print("Enter number of rows in Shazam! theater room: ");
        rowsShazam = Integer.parseInt(getInput());
        System.out.print("Enter number of seats per row: ");
        colsShazam = Integer.parseInt(getInput());
        System.out.print("Enter number of rows in Dumbo theater room: ");
        rowsDumbo = Integer.parseInt(getInput());
        System.out.print("Enter number of seats per row: ");
        colsDumbo = Integer.parseInt(getInput());
        System.out.print("Enter ticket price: ");
        price = Double.parseDouble(getInput());
        return new Theater(new Room(rowsShazam, colsShazam, "Shazam!"),
                           new Room(rowsDumbo, colsDumbo, "Dumbo"), price);
    }

    // Display a list of menu options
    private static void menu() {

        System.out.print("Select from the following menu:\n"
                         + "\t0. Exit the program. \n"
                         + "\t1. Customer(s) enter(s) Movie Theater. \n"
                         + "\t2. Customer buys ticket(s).\n"
                         + "\t3. Customer(s) leave(s) the theater.\n"
                         + "\t4. Display info about customers waiting for tickets.\n"
                         + "\t5. Display seating chart for Shazam! Movie Theater.\n"
                         + "\t6. Display seating chart for Dumbo Movie Theater.\n"
                         + "\t7. Display number of tickets sold and total earnings.\n");
    }

    // Process users menu selection and perform the required tasks
    // updating the user with results or errors
    private static boolean select(Theater theTheater, boolean prompt) {
        String selection = "";
        Group customer = null;
        int groupSize = 0;
        boolean kids = false;

        System.out.print("\nMake your menu selection now: ");
        System.out.println(selection = getInput());

        switch (selection) {
        // Customer enters -- Ask for name until unique name is entered
        // Ask user for group size
        // Ask user if there are kids < 11 years old
        // Place them in the line that will yield the shortest wait
        case "1":
            boolean done = false;
            String name = "";
            System.out.print("Welcome!\nPlease enter your name: ");
            while (!done) { // prompt user for name until unique name is entered
                try {
                    name = getInput();
                    theTheater.addName(name);
                    done = true;
                } catch (ListException e) {
                    System.out.print(e.getMessage() + "\nEnter a different name: ");
                }
            }
            System.out.print("Enter number of people in group: ");
            groupSize = Integer.parseInt(getInput());
            System.out.print("Is anyone in the group under 11 years old y/n? ");
            selection = getInput();
            kids = selection.equalsIgnoreCase("y") ? true : false;
            theTheater.enter(new Group(name, groupSize, kids));
            break;
        /*
         * Customer buys ticket On first use have user choose which line to serve first.
         * Prompt user to select a movie and continue if there's room otherwise ask user
         * if they'd like to watch the other movie. If yes continue if there's room, if
         * no room or answer is no the customer leaves.
         */
        case "2":
            String movie1 = "";
            String movie2 = "";
            if (prompt) {
                lineSetup(); // Initial setup of which line to serve first chosen by the user
                selection = getInput().toLowerCase();
                switch(selection) {
                case "0":
                case "express":
                    customer = theTheater.serveCustomer(0);
                    break;
                case "1":
                case "reg1":
                    customer = theTheater.serveCustomer(1);
                    break;
                case "2":
                case "reg2":
                    customer = theTheater.serveCustomer(2);
                    break;
                default:
                    customer = theTheater.serveCustomer(Integer.parseInt(selection));
                    break;
                }
                //customer = theTheater.serveCustomer(Integer.parseInt(selection));
                prompt = false;
            } else {
                customer = theTheater.serveCustomer();
            }

            if (customer == null) {
                System.out.println("All lines are empty.");
            } else {
                System.out.print("Which movie would you like to see " + customer.getName() + "?\n\t1. Shazam!\n"
                                 + "\t2. Dumbo\n" + "Make your selection now: ");
                selection = getInput();
                if (selection.equals("1") || selection.toLowerCase().equals("shazam")) {
                    movie1 = "Shazam!";
                    movie2 = "Dumbo";
                } else {
                    movie1 = "Dumbo";
                    movie2 = "Shazam!";
                }
                try {
                    theTheater.purchaseTicket(customer, movie1);
                    System.out.println("Enjoy the show!");
                } catch (ListException e1) {
                    System.out.print(e1.getMessage() + "\nWould you like to see " + movie2 + " instead Y/N? : ");
                    selection = getInput();
                    if (selection.equalsIgnoreCase("y")) {
                        try {
                            theTheater.purchaseTicket(customer, movie2);
                            System.out.println("Enjoy the show!");
                        } catch (ListException e2) {
                            System.out.print(e2.getMessage()
                                             + "\nNo other movies available. "
                                             + customer.getName() + " leaves.");
                            theTheater.removeName(customer.getName());
                        }
                    } else {
                        System.out.println(customer.getName() + " leaves.");
                        theTheater.removeName(customer.getName());
                    }
                }
            }
            break;
        // Customer leaves
        case "3":
	    if (theTheater.getNumCustomers() == 0) {
		    System.out.println("No customers are in the movie theater at this time.");
	    } else {
		    System.out.print("Enter a name: ");
		    selection = getInput();
		    try {
			System.out.println("Bye " + theTheater.leave(selection).getName());
		    } catch (ListException e) {
			System.out.println(e.getMessage());
		    }
	    }
            break;
        case "4":
            System.out.println(theTheater.displayLines());
            break;
        case "5":
            System.out.println(theTheater.getSeatingChart("shazam!"));
            break;
        case "6":
            System.out.println(theTheater.getSeatingChart("dumbo"));
            break;
        case "7":
            System.out.println("Tickets sold = " + theTheater.getTicketsSold()
                               + "\nTicket Earnings = " + theTheater.getEarnings());
            break;
        case "0":
            System.out.println("Exiting . . .");
            System.exit(0);
            break;
        default: // User input an invalid menu option
            System.out.println("Selection not valid");
            menu();
            break;
        }
        return prompt;
    }

    private static void lineSetup() {
        System.out.print("Select the first line to serve:\n" + "\t0. Express\n" + "\t1. Reg1\n" + "\t2. Reg2\n"
                         + "Enter your selection now: ");
    }

    // Get user input
    private static String getInput() {
        String s = "";
        try {
            s = br.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(s);
        return s;
    }// end getInput
}
