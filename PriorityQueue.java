/* 
This is the Priority Queue class, also known as the "event queue" in the main method, that is a singly linked list queue used to sort callers by the time they arrived. The
only edited portion of this class is the offer() method in accordance to the rubric. It uses java.lang.reflect.Field to place the incoming Event into the queue in the correct
location. More on that below.

Programmer: Chris Pichler
Course:     COSC 311, F '23
Project:    3
Due date:   10-26-23
 */

package pichlerProject3;

import java.util.*;

//import java.lang.reflect.Field to correctly place the incoming Node in the correct location to the linked list queue based on the arrivalTime variable associated with that Event
import java.lang.reflect.Field;


/**
 * A class that represents a queue using a singly linked list
 * 
 * @author  COSC 311, F '23
 * @version (9-21-23)
 */


public class PriorityQueue<E>{
	
	// Class Node is defined as an inner class
    /** A Node is the building block for the SinglyLinkedList */
    private static class Node<E> {

        /** Data members */
        private E data;
        /** The link */
        private Node<E> next;

        /**
         * Construct a node with the given data value
         * @param data - The data value 
         */
        public Node(E data) {
            this(data, null);
        }
        
        /**
         * Construct a node with the given data value and link
         * @param data - The data value 
         * @param next - The link
         */
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
 
	//data members
	private Node<E> front = null;
	private Node<E> rear = null;
	private int size = 0;
	
	
	/** 
	 * Insert an item at the rear of the queue
	 * @param  item  The element to add
	 * @return true 
	 */
	/* 
	This is the only edited portion of this class from what was posted on Canvas, in accordance to the rubric. It uses a Field object to access the incoming Event's 
	arrivalTime variable to correctly sort the incoming Node into the correct place in the queue. In order to avoid any plagerism issues, I will explain I got the code:
	
	1. I referenced this article: https://stackoverflow.com/questions/32145175/in-java-how-do-i-sort-a-generic-class-t-on-a-field-variable-sent-to-me, where I used the
	first answer which was a link to the get feature in the Field class on the Java Oracle website, that can be found here: 
	https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Field.html#get-java.lang.Object-
	
	2. I used the information on the Oracle website TO COME UP WITH MY OWN CODE THROUGH TRIAL AND ERROR in order to successfully access the arrivalTime variable. To be clear,
	I used the information on the Oracle website to come up with code ON MY OWN. I did not copy/paste from anywhere, it is my code.
	 */
	public boolean offer (E item) {
		//Create a Field object called time
		Field time;
		
		//arrivalTime is an int that will represent the incoming Event's arrival time variable
		int arrivalTime;
		
		//Attempt to access the incoming object's "arrivalTime" variable. I had to put it in a try/catch because Eclipse notified me I needed to catch a NoSuchFieldException
		try {
			time = Event.class.getField("arrivalTime");
		}catch(NoSuchFieldException ex) {
			return false;
		}

		//Attempt to make arrivalTime equal to the incoming Event's arrvialTime variable. It is put in a try/catch because Eclipse told me I needed to catch an IllegalAccessException
		try {
			arrivalTime = time.getInt(item);
		}catch(IllegalAccessException ex) {
			return false;
		}
		
		//This is actually finding the correct place to put the Node in the event queue based on the arrivalTime variable, while also catching an IllegalAccessException because Eclipse told me to
		try {
			//The queue is empty, put the incoming Event at the start/rear
			if (size == 0) {
				front = rear = new Node<> (item);
			}
			//The incoming Event needs to go at the front of the queue
			else if(arrivalTime < time.getInt(front.data)) {
				Node<E> current = new Node<>(item);
				current.next = front;
				front = current;
			}
			//Put the incoming Event in the correct location in the queue based on the arrivalTime variable
			else {
				Node<E> current = front;
				//Move the current Node to the correct place in the queue based on the incoming Event's arrivalTime variable
				while(current.next != null && time.getInt(current.next.data) < time.getInt(item)) {
					current = current.next;
				}
				
				//Create new Node that will actually represent the incoming Event
				Node<E> incoming = new Node<>(item);
				
				//The incoming Event goes at the end of the linked list, set it to rear
				if(current.next == null) {
					rear = incoming;
				}
				//The incoming Event will have a Node after it because it is not in the rear
				else {
					incoming.next = current.next;
				}
				current.next = incoming;
			}
		}catch(IllegalAccessException ex) {
			return false;
		}
		
		//Add 1 to the size of the queue
		size ++;
		return true;
	}
	
	/** 
	 * Remove the entry at the front of the queue
	 * @param  None  
	 * @return the item removed or null if queue is empty
	 */
	public E poll() {
		E result = peek();
		if (result != null) {
			if (front == rear)   // has 1 element
				front = rear = null;
			else                 // has more than 1 element
				front = front.next;
			size --;
		}
		return result;
	}
	
	/** 
	 * Remove the entry at the front of the queue
	 * @param  None  
	 * @return the item removed or NoSuchElementException if queue is empty
	 */
	public E remove() {
		if (size == 0) 
			throw new NoSuchElementException("Queue is empty");
		E result = front.data;
		front = front.next;
		if (front == null)
			rear = null;
		size --;
		return result;
	}
	
	/** 
	 * Return the entry at the front of the queue
	 * @param  None  
	 * @return the item at the front of the queue or null if queue is empty
	 */
	public E peek() {
		if (size == 0) 
			return null;
		else
			return front.data;
	}
	
	/** 
	 * Return the entry at the front of the queue
	 * @param  None  
	 * @return the item at the front of the queue or throw NoSuchElementException if queue is empty
	 */
	public E element() {
		if (size == 0) 
			throw new NoSuchElementException("Queue is empty");
		else
			return front.data;
	}
	
	/** 
	 * Return a string representing the queue
	 * @param  None  
	 * @return a string representing the queue  	
	 */
	public String toString() {
		String result = "[";
		Node <E> current = front;
		while (current.next != null) {
			result = result + current.data + " ";
			current = current.next;
		}
		return result + current.data + "]";
	}
	
	
	/** 
	 * Determine whether or not queue is empty
	 * @param  None  
	 * @return true if the queue is empty, and false otherwise 	
	 */
	public boolean empty(){
		/*
		if (size == 0)
			return true;
		else
			return false;
		*/
		return (size ==0) ? true : false;
	}
	
	/** 
	 * Determine the number of elements stored in the queue
	 * @param  None  
	 * @return the number of elements in the queue
	 */
	public int size() {
		return size;
	}

}
