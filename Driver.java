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
import java.lang.*;

public class Driver {

    static BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));

    public static int stdinInt(String prompt) throws Exception
    {
        return stdinInt(prompt, "Invalid Input - Please enter a valid integer.");
    }

    public static int stdinInt(String prompt, String error) throws Exception {
        /* Takes input from the BufferedReader stdin and assumes that the string entered
         * is an integer.  If not, catches the error and asks the user for a proper integer.
         *
         * Converts the string into a number and passes that number.
         */
        int num = -1;

        try {
            System.out.print(prompt);
            num = Integer.parseInt(stdin.readLine().replaceAll("\\s+",""));//Remove whitespaces with RegEx0
            System.out.println(num);
            /*DEBUG
            	System.out.println("Non-negative mode: " + nonneg);
            	System.out.println(num);
            //*/
        } catch (Exception e) {
            //If the user enters a non integer, returns this message.
            //System.out.println(error);
        	throw new Exception(e);
        }

        return num;
    }

    public static String stdinString(String prompt) {
        /* Takes input from the BufferedReader stdin.
         *
         * If there's an error catches the error and asks the user for a proper input.
         *
         * Passes the entered string.
         */
        String string = "";

        try {
            System.out.print(prompt);
            string = stdin.readLine().trim();
            System.out.println(string);
            /*DEBUG
            	System.out.println(string);
            //*/
            return string;
        } catch (Exception e) {
            //If the user enters an invalid input, returns this message.
            System.out.println("Invalid Input - Please enter a valid input.");
        }
        return string;
    }

    public static <T> void openInterface(StackRA<Request> pending, QueueRA<Request> give, QueueRA<Request> get) throws Exception {
    	
    	System.out.println("Welcome to the Pokemon Exchange Center!");
    	
    	int customersThatLeft = 0;
    	
    	//Stock:
    	int ghost = stdinInt("Enter number of Ghost Pokemon in stock: ");
    	int fire = stdinInt("Enter number of Fire Pokemon in stock: ");
    	int ice = stdinInt("Enter number of Ice Pokemon in stock: ");
    	
        System.out.print(new StringBuilder()
                         .append("Select from the following menu:\n")
                         .append("\t 0. Exit the program.\n")
                         .append("\t 1. Customer enters.\n")
                         .append("\t 2. Customer giving Pokemon is served.\n")
                         .append("\t 3. Customer getting Pokemon is served.\n")
                         .append("\t 4. Employee Processes pending requests.\n")
                         .append("\t 5. Display customers waiting to give Pokemon and their requests.\n")
                         .append("\t 6. Display customers waiting to get Pokemon and their requests.\n")
                         .append("\t 7. Display pending requests.\n")
                         .append("\t 8. Display stock and number of customers that have left.\n")
                        );

        boolean stay = true;

        Request request = null;
        
        String name = "";
        boolean giveOrGetInput = true; //True = Give, False = Get
        String giveOrGetToString = "give";
        int customerGhost = 0;
        int customerFire = 0;
        int customerIce = 0;
        
        while(stay) {
            switch(stdinInt("Make your menu selection now: ")) {
            case 0:
            	System.out.println("The Pokemon Exchange Center is closing :Good Bye...");
            	stay = false;
            	break;
            case 1:
                try {
                    name = stdinString("Welcome, your name please: ");
                    if (stdinString("Are you here to give Pokemon(Y/N): ").toUpperCase().equals("Y")) {
                    	giveOrGetInput = true;
                    	giveOrGetToString = "give";
                    } else {
                    	giveOrGetInput = false;
                    	giveOrGetToString = "get";
                    }
                    customerGhost = stdinInt("How many Ghost Pokemon: ");
                    customerFire = stdinInt("How many Fire Pokemon: ");
                    customerIce = stdinInt("How many Ice Pokemon: ");
                    request = new Request(name, giveOrGetInput, customerGhost, customerFire, customerIce);
                    if (giveOrGetInput) {
                    	giveOrGetToString = "give";
                    	give.enqueue(request);
                    } else {
                    	giveOrGetToString = "get";
                    	get.enqueue(request);
                    }
                    System.out.println(
                    		new StringBuilder(name.toString())
                    		.append(" is now waiting to ")
                    		.append(giveOrGetToString.toString())
                    		.append(" Pokemon!")
                    		.toString());
                } catch (Exception e) {
                    System.out.println(e);
                }
                /*DEBUG
                System.out.print(request);
                //*/
                break;
            case 2:
            	if (!give.isEmpty()) {
            		request = give.dequeue();
            		System.out.println(new StringBuilder(request.getName().toString())
            				.append(" dropped off ")
           					.append(String.valueOf(request.getGhost()))
        					.append(" Ghost, ")
        					.append(String.valueOf(request.getFire()))
        					.append(" Fire, ")
        					.append(String.valueOf(request.getIce()))
        					.append(" Ice ")
        					.append("Pokemon.  Thanks!")
            				.toString());
            		ghost += request.getGhost();
            		fire += request.getFire();
            		ice += request.getIce();
            		customersThatLeft++;
            	} else {
            		System.out.println("No customers giving Pokemon are waiting to be served!");
            	}
                break;
            case 3:
            	if (!get.isEmpty()) {
            		request = get.peek();
            		if ((ghost - request.getGhost()) < 0 ||
            				fire - request.getFire() < 0 ||
            				ice - request.getIce() < 0) {
            			System.out.println(new StringBuilder(request.getName().toString())
            					.append("'s request: ")
            					.append(String.valueOf(request.getGhost()))
            					.append(" Ghost, ")
            					.append(String.valueOf(request.getFire()))
            					.append(" Fire, ")
            					.append(String.valueOf(request.getIce()))
            					.append(" Ice ")
            					.append("Pokemon now pending.")
            					.toString()
            					);
            			System.out.println("We will let you know when your order is processed, bye!");
            			pending.push(get.dequeue());
            		} else {
            			System.out.println(new StringBuilder(request.getName().toString())
            					.append(" leaving with ")
               					.append(String.valueOf(request.getGhost()))
            					.append(" Ghost, ")
            					.append(String.valueOf(request.getFire()))
            					.append(" Fire, ")
            					.append(String.valueOf(request.getIce()))
            					.append(" Ice ")
            					.append("Pokemon.")
            					.toString());
            			
                		ghost -= request.getGhost();
                		fire -= request.getFire();
                		ice -= request.getIce();
                		get.dequeue();
            		}
            		customersThatLeft++;
            	} else {
            		System.out.println("No customers getting Pokemon are waiting to be served!");
            	}
                break;
            case 4:
            	boolean loop = true;
            	while(loop) {
	            	if (!pending.isEmpty()) {
	            		request = pending.peek();
	        				if ((ghost - request.getGhost()) < 0 ||
	                				fire - request.getFire() < 0 ||
	                				ice - request.getIce() < 0) {
	            				System.out.println("No request could be processed!");
	            				loop = false;
	                		} else {
	                			System.out.println(new StringBuilder(request.getName().toString())
	                					.append("'s request: ")
	                					.append(String.valueOf(request.getGhost()))
	                					.append(" Ghost, ")
	                					.append(String.valueOf(request.getFire()))
	                					.append(" Fire, ")
	                					.append(String.valueOf(request.getIce()))
	                					.append(" Ice ")
	                					.append("Pokemon processed and customer notified.")
	                					.toString()
	                					);
	                    		ghost -= request.getGhost();
	                    		fire -= request.getFire();
	                    		ice -= request.getIce();
	                    		pending.pop();
	                		}
	            	} else {
	            		System.out.println("No request could be processed!");
	            		loop = false;
	            	}
            	}
                break;
            case 5:
            	if (!give.isEmpty()) {
	            	System.out.println("These customers giving Pokemon are waiting to be served: ");
	            	System.out.println(give.toString());
            	} else {
            		System.out.println("No customers giving Pokemon are waiting to be served! ");
            	}
                break;
            case 6:
            	if (!get.isEmpty()) {
	            	System.out.println("These customers getting Pokemon are waiting to be served: ");
	            	System.out.println(get.toString());
            	} else {
            		System.out.println("No customers getting Pokemon are waiting to be served! ");
            	}
                break;
            case 7:
            	if (!pending.isEmpty()) {
	            	System.out.println("These requests are pending: ");
	            	System.out.println(pending.toString());
            	} else {
            		System.out.println("No pending requests! ");
            	}
                break;
            case 8:
            	StringBuilder result = new StringBuilder("stock: ")
            			.append(String.valueOf(ghost))
            			.append(" Ghosts, ")
            			.append(String.valueOf(fire))
            			.append(" Fire, ")
            			.append(String.valueOf(ice))
            			.append(" Ice ")
            			.append("Pokemon\n");

            	if (customersThatLeft == 0) {
            		result.append("No customers have left");
            	} else {
            		result.append(String.valueOf(customersThatLeft));
            		result.append(" customers have been served.");
            	}
            	System.out.println(result.toString());
                break;
            default:
                System.out.println("Please choose a number from the menu above.\n");
                break;
            }
            System.out.println();
            System.out.print("You know the options.");
        }
    }	

    public static void main(String[] args) throws Exception {

    	QueueRA<Customer> lineOne = new QueueRA();
    	QueueRA<Customer> lineTwo = new QueueRA();
    	QueueRA<Customer> express = new QueueRA();
        
        openInterface(lineOne, lineTwo, express);
    }
}
