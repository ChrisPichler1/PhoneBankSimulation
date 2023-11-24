/*
Programmer: Chris Pichler
Course:     COSC 311, F'23
Project:    3
Due date:   10-26-23


This is the main method of this project that will ultimately setup the simulation, create the report.txt file, run the simulation until the user is done, and complete the
report.txt file with correct information. The program takes in 5 inputs from the user, catching any exceptions for incorrect user input, and uses those inputs to run the
simulation in accordance to the rubric. It import java.util.* to use the Scanner to take user input, and PrintWriter to write to the report.txt file. 
*/

package pichlerProject3;

//import java.util.* to use Scanner to take user input, and PrintWriter to write to the report.txt file using text I/O
import java.util.*;

//import java.io.* to write to the report.txt file
import java.io.*;

//The class implements the Comparable interface in accordance to the rubric
public class Simulation <T extends Comparable<T>> implements Comparable<Simulation<T>>  {

	//The main method throws an IOException in order to successfully write to the report.txt file
	public static void main(String[] args) throws IOException{
		//Print the heading in accordance to the rubric
		heading();
		
		//scnr is the Scanner object used to read user input
		Scanner scnr;
		
		//length is an int input variable used to store the amount of time units the user would like to have in the simulation
		int length;
		
		//averageTime is a double input variable used to store the average time between dial-in attempts used by the Poisson class to simulate the number of people calling in during the simulation
		double averageTime;
		
		//connectionTime is an int input varialbe used to calculate the average time a user is using a modem used in the simulation with the Poisson class
		int connectionTime;
		
		//numOfModems is a double input variable used to show how many modems are available in any given simulation
		double numOfModems;
		
		//sizeOfQueue is an int input variable used to show the capacity of the WaitingQueue used in the simulation, if it's "-1", the WaitingQueue is infinite
		int sizeOfQueue;
		
		//input is a String input variable that is used to allow the user to decide whether or not they would like to repeat the simulation with new inputs
		String input;
		
		//skipLine is a boolean that is used to match the sample run by figuring out whether the "Average wait time: " line at the end of the simulation start with a new line
		boolean skipLine;
		
		//output is the PrintWriter object used to create and write to the report.txt file
		PrintWriter output = new PrintWriter("report.txt");
		
		//Write the opening menu of the report.txt file in accordance to the rubric
		output.println("1- Length of simulation");
		output.println("2- Average time between dial-in attempts");
		output.println("3- Average connection time");
		output.println("4- Number of modems in the bank");
		output.println("5- Size of the waiting queue, -1 for an infinite queue");
		output.println("6- Average wait time");
		output.println("7- Percentage of time modems were busy");
		output.println("8- Number of customers left in the waiting queue\n\n");
		output.println("\t1\t|\t2\t|\t3\t|\t4\t|\t5\t|\t6\t|\t7\t|\t8");
		output.println("---------------------------------------------------------------------------------------------------------------------------");
		
		//Run simulations until the user decides to be done by typing anything but "yes" to the final question at the bottom of the program
		do {
			//Don't skip a line with the "Average wait time: " print statement
			skipLine = false;
			
			//Take in the inputs from the user to start the simulation
			//Take in the length input from the user
			do {
				scnr = new Scanner(System.in);
				System.out.print("Enter the length of simulation: ");
			
				try {
					length = scnr.nextInt();
					//The user entered a negative number or 0, let them know it needs to be a positive integer and re-ask them for an input
					if(length < 1) {
						System.out.println("Please enter a positive integer\n");
						continue;
					}
					//The user entered a positive integer. Accept the input and move on
					break;
					
				//The user entered a float/string. Inform the user it needs to be a positive integer and give them another chance to type a correct input
				}catch(InputMismatchException ex) {
					System.out.println("Please enter a positive integer\n");
					continue;
				}
			//Continue asking for an input until a successful input is entered
			}while(true);
			
			//Take in the averageTime input from the user
			do {
				scnr = new Scanner(System.in);
				System.out.print("Enter the average time between dial-in attempts: ");
				
				try {
					averageTime = scnr.nextDouble();
					//The user entered an incorrect input, inform them and re-do asking them
					if(averageTime < 0 || averageTime > 1) {
						System.out.println("Please enter a number between 0 and 1\n");
						continue;
					}
					//The user entered a correct input, break out of this loop
					break;
				
				//The user entered a string. Inform the user and allow them to re-do the input
				}catch(InputMismatchException ex) {
					System.out.println("Please enter a number between 0 and 1\n");
					continue;
				}
			
			//Keep asking until the user enters a successful input
			}while(true);
	
			//Take in the connectionTime input variable
			do {
				scnr = new Scanner(System.in);
				System.out.print("Enter the average connection time: ");
				
				try {
					connectionTime = scnr.nextInt();
					//The user entered an incorrect number. Inform them and allow them to re-do it
					if(connectionTime < 1 || connectionTime > 10) {
						System.out.println("Please enter a positive integer less than 10\n");
						continue;
					}
					//The user entered a correct input, break out of the loop and ask for the next input
					break;	
					
				//The user entered a float or a string. Inform them and allow them to re-enter the input
				}catch(InputMismatchException ex) {
					System.out.println("Please enter a positive integer less than 10\n");
					continue;
				}
			
			//Continue asking for an input variable until it is successfully entered
			}while(true);
			
			//Take in the numOfModems input variable
			do {
				scnr = new Scanner(System.in);
				System.out.print("Enter the numbers of modems: ");
				
				try {
					numOfModems = scnr.nextInt();
					//The user entered a negative number or 0. Inform them and ask them to re-enter the input
					if(numOfModems < 1) {
						System.out.println("Please enter a positive integer\n");
						continue;
					}
					//The user entered the correct input, break out of the loop and ask for the next input
					break;	
				//The user entered a float or a string. Inform the user and ask them to re-do the input
				}catch(InputMismatchException ex) {
					System.out.println("Please enter a positive integer\n");
					continue;
				}
			//Continue asking for inputs until the user successfully enters a correct input
			}while(true);
			
			//Take in the sizeOfQueue input variable from the user
			do {
				scnr = new Scanner(System.in);
				System.out.print("Enter the size of the waiting queue, -1 for an infinite queue: ");
				
				try {
					sizeOfQueue = scnr.nextInt();
					//The user entered a number less than -1. Ask for another input
					if(sizeOfQueue < -1) {
						System.out.println("Please enter -1 or a positive integer\n");
						continue;
					}
					//The user entered a correct input. Break out of the loop and continue with the program
					break;
				//The user entered a float or string. Inform the user and allow them to enter a correct input
				}catch(InputMismatchException ex) {
					System.out.println("Please enter -1 or a positive integer\n");
					continue;
				}
				
			//Continue to ask for an input until a successful input is entered
			}while(true);
			
			//Skip a line in accordance to the sample run
			System.out.println();
			
			//Set constant variables for all inputs requiring a FINAL variable
			final int LENGTH = length;
			final int CONNECTIONTIME = connectionTime;
			final double NUMOFMODEMS = numOfModems;
			final int SIZEOFQUEUE;
			
			//If the user wants an infinite queue, set SIZEOFQUEUE to a very large number
			if(sizeOfQueue == -1) {
				SIZEOFQUEUE = 999999999;
			}
			else {
				SIZEOFQUEUE = sizeOfQueue;
			}
			
			//Output the 5 input variables to report.txt
			output.print("\t" + LENGTH + "\t");
			output.print("|\t" + averageTime + "\t");
			output.print("|\t" + CONNECTIONTIME + "\t");
			output.print("|\t" + (int)NUMOFMODEMS + "\t");
			
			if(SIZEOFQUEUE < 100) {
				output.print("|\t" + SIZEOFQUEUE + "\t|\t");
			}
			else {
				output.print("|\t-1\t|\t");
			}
			
			//Create the event queue and the waiting queue
			PriorityQueue<Event> eventQueue = new PriorityQueue<>();
			WaitingQueue<Event> waitingQueue = new WaitingQueue<>(sizeOfQueue);
			
			//id starts at 1000 and each Event object will be given a new ID one higher than the last
			int id = 1000;
			
			//timeUnit
			int timeUnit = 0;
			
			//Pre-fill the event queue with customers before the simulation begins
			while(length > 0) {
			//Determine how many customers will dial in at a given time unit
				Poisson dialIn = new Poisson (1 / averageTime);
				int numOfCustomers = dialIn.nextInt();
				while(numOfCustomers > 0) {
					eventQueue.offer(new Event(id, timeUnit, false));
					numOfCustomers--;
					id++;
				}
				timeUnit++;
				length--;
			}
			
			//Reset length to the constant LENGTH variable
			length = LENGTH;
			
			//Set the Poisson object based on connectionTime to determine how long a given person will be using a modem
			Poisson connection = new Poisson(connectionTime);
			
			//next represents the next Event object in the event queue
			Event next;
			
			//nextAvailable represents an Event object leaving the waiting queue
			Event nextAvailable;
			
			//availableModems = NUMOFMODEMS to start the simulation
			double availableModems = NUMOFMODEMS;
			
			//timeUnit = 0 to start the simulation
			timeUnit = 0;
			
			//waitingTime is used to calculate a single Event and how long they waited
			int waitingTime = 0;
			
			//totalWaitTime and totalCustomers is used to calculate average wait time which is printed to the console and sent to report.txt
			double totalWaitTime = 0;
			double totalCustomers = 0;
			
			//totalTimeOnPhone uses a Poisson distribution to determine how long a person will be using a modem
			int totalTimeOnPhone;
			
			//timeModemInUse used to calculate the % of time the modems were in use in a simulation
			int timeModemInUse = 0;
			
			
			//Start the simulation
			while(length > 0) {
				
				//While there are events in the event queue...
				while(eventQueue.size() > 0) {
					next = eventQueue.peek();
					
					//If the front of the event queue has an arrivalTime after the current timeUnit, break out of the loop
					if(next.getArrivalTime() > timeUnit) {
						break;
					}
					//next = the front Event in the event queue
					else {
						next = eventQueue.poll();
					}
					//If next is a hang-up event, availableModems++;
					if(next.getStatus() == true) {
						availableModems++;
					}
					
					//next is a dial-in, send it to the waitingQueue if there's room, or deny it and print to the console the information
					else {
						if(waitingQueue.size() < SIZEOFQUEUE) {
							waitingQueue.offer(next);
						}
						else {
							System.out.println("customer " + next.getID() + " was denied service at time unit " + timeUnit);
							skipLine = true;
						}
					}
					
					//While there are available modems and there are Events in the waiting queue...
					while(availableModems > 0 && waitingQueue.size() != 0) {
						
						//nextAvailable represents a customer already with an ID waiting to get a modem
						nextAvailable = waitingQueue.peek();
						
						if(nextAvailable.getArrivalTime() > timeUnit) {
							break;
						}
						else {
							//Take the next Event from the waiting queue and assign it a modem
							nextAvailable = waitingQueue.poll();
							
							//waitingTime is how long the Event was in the waiting queue
							waitingTime = timeUnit - (nextAvailable.getArrivalTime());
							
							//Add waitingTime to totalWaitingTime
							totalWaitTime += waitingTime;
							
							//Another customer given a modem
							totalCustomers++;
							
							//Determine how long the user will be using a modem
							totalTimeOnPhone = connection.nextInt();
							
							//Add a hang-up Event to the priority queue
							eventQueue.offer(new Event<>(nextAvailable.getID(), timeUnit + totalTimeOnPhone, true));
		
							//One less modem is available
							availableModems--;
						}
					}
				}
				
				//Determine how many modems are in use
				timeModemInUse += (NUMOFMODEMS - availableModems);
				
				//Add 1 to the time unit
				timeUnit++;
				
				//Subtract 1 to the length
				length --;
			}
			
			//The simulation has ended
			
			//The percentage of time the modems were busy is calculated, print to the console, and sent to report.txt
			double percentageModemsAreBusy = ((timeModemInUse / (NUMOFMODEMS * LENGTH))) * 100;
			
			//Average wait time is calculated, print to the console, and sent to report.txt
			double averageWaitTime = totalWaitTime / totalCustomers;
			
			//Print the appropriate information to the console in accordance to the rubric
			if(skipLine == true) {
				System.out.printf("\nAverage wait time = %.2f", averageWaitTime);
			}
			else {
				System.out.printf("Average wait time = %.2f", averageWaitTime);
			}
			System.out.printf("\nPercentage of time modems were busy = %.2f", percentageModemsAreBusy);
			System.out.printf("\nNumber of customers left in the waiting queue =%d", waitingQueue.size());
			
			//Does the user want to run the simulation again with new inputs?
			System.out.print("\n\nRun simulation again, yes (or no)? ");
			
			//input = A string whether or not the user wants to do it again. If they type "yes" ignoring case, send information to report.txt, re-do the simulation and ask for new inputs. Otherwise, print to report.txt and finish.
			input = scnr.next();
			
			System.out.println();
			
			//Send the appropriate information to report.txt in accordance to the rubric
			output.printf("%.2f", averageWaitTime);
			output.printf("\t|\t%.2f", percentageModemsAreBusy);
			output.printf("\t|\t%d", waitingQueue.size());
			output.println();
			
		}while(input.equalsIgnoreCase("yes"));
		
		//Close report.txt to ensure all information is sent to the file
		output.close();
	}
	
	//This is the heading that is printed at the start of the program in accordance to the rubric
	public static void heading() {
		System.out.println("Programmer:\tChris Pichler");
		System.out.println("Course:\t\tCOSC 311, F '23");
		System.out.println("Project:\t3");
		System.out.println("Due date:\t10-26-23\n");
	}

	//compareTo method is written in accordance to the rubric. It was not used.
	@Override
	public int compareTo(Simulation<T> o) {
		return 0;
	}
}

//Output provided in a separate text file in the zip folder in accordance to the rubric