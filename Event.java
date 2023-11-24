/* 
This is the edited Pair class in accordance to the rubric. There is an added public variable (arrivalTime), and "first" and "second" have been changed to "id" and "hangUp".
id is an integer representing the ID number of the caller. hangUp is a boolean. If hangUp is true, the Event is a hang-up event. If it is false, the Event is a dial-in event.

Programmer: Chris Pichler
Course:     COSC 311, F' 23
Project:    3
Due date:   10-26-23
 */

package pichlerProject3;

/**
 * A generic class with a bounded type parameter representing an event
 *  
 * @author  COSC 311, Fall '23
 * @version (9-12-23)
*/


public class Event<T extends Comparable<T>> implements Comparable<Event<T>>{
	//id is an integer representing the caller's ID number
	private int id;
	
	//hangUp is a boolean. If it is true for an Event object, it's a hang-up event. If it is false, that Event is a dial-in event
	private boolean hangUp;
	
	//arrivalTime is an int representing the time unit number that the Event was created in. It is public so it can be accessed by PriorityQueue.java
	public int arrivalTime;
	
	// constructors
	/**
     * Construct a pair and set elements to null
     * @param none
     */
	public Event() {
		id = 0;
		arrivalTime = 0;
		hangUp = false;
		
	}
	
	/**
     * Construct a pair with the given data values
     * @param one - The data value for first
     * @param two - The data value for second
     */
	public Event (int one, int two, boolean three) {
		id = one;
		arrivalTime = two;
		hangUp = three;
	}
	
	// getters and setters
	//Set the event's ID number
	public void setID (int other) {
		id = other;
	}
	
	//Set the event's arrival time
	public void setArrivalTime (int other) {
		arrivalTime = other;
	}
	
	//Set the event's status to true (hang up event) or false (dial-in event)
	public void setStatus(boolean other) {
		hangUp = other;
	}
	
	
	//Get the event's ID number
	public int getID () {
		return id;
	}
	
	//Get the event's arrival time
	public int getArrivalTime () {
		return arrivalTime;
	}
	

	//Get the event's status (true = hang up, false = dial-in)
	public boolean getStatus () {
		return hangUp;
	}
	
	
	public String toString() {
		return "Customer ID: " + id + ", Arrival time: " + arrivalTime + ", Hang up event? " + hangUp;
	}
	
	/**
     * Compares two pair objects based on the value of their first element
     * @param other a pair object
     * @returns 0 if first elements are the same, -1 if first < other.first, and
     *          1 if first > other.first
     */
	public int compareTo(Event<T> other) {
		if (arrivalTime - other.arrivalTime < 0)
			return -1;
		else if (arrivalTime - other.arrivalTime > 0)
			return 1;
		else
			return 0;
	}
}

