/*
 * Flight.java
 *
 * Version:
 *  $Id: Flight.java,v 1.1 2013/06/19 02:43:15 pzg8794 Exp $
 *
 * Revisions:
 *  $Log: Flight.java,v $
 *  Revision 1.1  2013/06/19 02:43:15  pzg8794
 *  Lab3!
 *
 *
 */

import java.util.Scanner;

/**
 * Simmulates a flight.
 * @author piter garcia
 *
 */
public class Flight{

	/*
	 * variable to access and input from the terminal
	 */
	private Scanner in;

	/*
	 * contains the most recent passenger removed
	 */
	private Passenger rmvPassenger;

	/*
	 * list of passengers
	 */
	private LinkedListQueue<Passenger> list;

	/*
	 * list of waiting passengers
	 */
	private LinkedListQueue<Passenger> list2;



	/**
	 * default constructor
	 */
	public Flight(){

		in = new Scanner(System.in);
	}



	/**
	 * gets input access to the terminal 
	 * @return in, scanner
	 */
	public Scanner getIn() {
		return in;
	}



	/**
	 * main method where all methods are tested.
	 * @param args, ignored
	 */
	public static void main( String[] args){

		Flight flight = new Flight();
		flight.list = new LinkedListQueue<Passenger>();
		flight.list2 = new LinkedListQueue<Passenger>();

		int choice = 0;

		do{

			System.out.println("Enter an option: ");
			choice = flight.menu();

		}while( choice != 3);


		//	System.out.println(flight);
	}



	/**
	 * contains a menu for user to determine to either add, remove a passnger
	 * or quit the program. 
	 * @return choice, integer value of the user choice.
	 */
	public int menu(){
		int choice = 0;
		System.out.println("1 to Add a passenger to the queue" +
				"\n2 to Remove and print the first passenger" +
				"\n3 to quit");

		System.out.println("Your choice: " + (choice = getIn().nextInt()));

		switch(choice){

		case 1:
			addPassenger();		
			break;

		case 2:
			removePassenger();
			break;

		case 3:
			return choice;

		default:
			menu();
		}

		return choice;
	}



	/**
	 * removes the highest priority passenger and add those in the waiting
	 * list according to there priority.
	 */
	private void removePassenger() {

		rmvPassenger = list.dequeue();
		if( rmvPassenger == null)
			System.out.println("Queue is empty");
		else
			System.out.println("Removing : " + rmvPassenger.toString());	

		//Automatically adding Passengers from the waiting list.
		if(!list2.isEmpty()){
			list.enqueue(list2.pop());
		}
	}



	/**
	 * adds a passenger to the list of passenger.
	 */
	private void addPassenger() {

		String name; 
		char board = 0;
		int sequence = 0;

		System.out.print("Passenger name: ");
		name = getIn().next(); 



		//making sure that the boarding group value is valid.
		do{
			System.out.print("Boarding Group: ");
			board = getIn().next().toUpperCase().charAt(0); 

			if( board != 'A' && board !='B' &&  board != 'C'){
				System.out.println("Please Enter a Valid Boarding Group");
			}

		}while(( board != 'A' && board !='B' &&  board != 'C'));


		//making sure that the sequence value is valid.
		do{
			System.out.print("Sequence : ");
			sequence = getIn().nextInt();

			if( sequence > 60)
				System.out.println("Please Enter a Valid Sequence");

		}while( sequence > 60);

		// If the flight is full passengers will have to wait till a seat is 
		//available.
		if(list.size() < 60)
			list.enqueue(new Passenger(name, board, sequence));
		else{
			System.out.println("Sorry the AirPlain is Full,\nAdding Passenger" +
					"to a Waiting List");
			list.enqueue(new Passenger(name, board, sequence));
		}
	}



	/**
	 * gets the list of passenger
	 * @return list, list of passenger.
	 */
	public LinkedListQueue<Passenger> getList() {
		return list;
	}



	/**
	 * return the most recent removed passenger.
	 * @return rmvPassenger, most recent removed passenger.
	 */
	public Passenger getRmvPassenger() {
		return rmvPassenger;
	}

	/**
	 * Prints all passengers in the flight
	 * @return temp, all passengers in the flight.
	 */
	public String toString(){

		return list.toString();
	}

}
