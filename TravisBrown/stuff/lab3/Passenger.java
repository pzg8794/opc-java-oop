/*
 * Passenger.java
 *
 * Version:
 *  $Id: Passenger.java,v 1.1 2013/06/19 02:43:15 pzg8794 Exp $
 *
 * Revisions:
 *  $Log: Passenger.java,v $
 *  Revision 1.1  2013/06/19 02:43:15  pzg8794
 *  Lab3!
 *
 *
 */

/**
 * Simmulates a passsenger
 * @author piter garcia
 *
 */
public class Passenger implements Comparable<Passenger> {

	/*
	 * name of passenger
	 */
	private String name;
	
	/*
	 * section of passenger seat
	 */
	private char board;
	
	/*
	 * passenger seat's number
	 */
	private int sequence;
	
	/*
	 * sections integer value
	 */
	private int boardV;

	
	
	/**
	 * Constructor, initializes all passenger's member variables.
	 * @param name, passenger's name
	 * @param board, passenger's seat section
	 * @param sequence, passenger's seat number
	 */
	public Passenger(String name, char board, int sequence) {
		this.name = name;
		this.board = board;
		this.sequence = sequence;

		if( board == 'A') this.boardV = 1;
		if( board == 'B') this.boardV = 2;
		if( board == 'C') this.boardV = 3;
	}

	
	
	/**
	 * main method
	 * @param args, ignored
	 */
	public static void main(String[] args){

	}


	
	/**
	 * compares two passengers according to their boarding 
	 * and sequence number.
	 * 
	 * @return 1, if first passenger is greater, -1 if not
	 */
	@Override
	public int compareTo(Passenger  o) {

		if(this.board == o.board){
			if(this.hashCode() > o.hashCode())
				return 1;
			if(this.hashCode() < o.hashCode())
				return -1;
		}
		if( this.board != o.board){
			if(this.board == 'A')
				return -1;
			if(this.board == 'B' && o.board == 'C')
				return -1;
			else
				return 1;
		}
		return 0;
	}

	
	
	/**
	 * checks if two passengers are equal
	 * @return true, if passengers are equal. false, if not.
	 */
	public boolean equals(Object o){

		if( o instanceof Passenger){
			Passenger temp = (Passenger)o;
			if((this.name.equals(temp.name)) && (this.board == temp.board) 
					&& (this.sequence == temp.sequence))
				return true;
		}
		return false;

	}

	
	
	/**
	 * Used to identify passengers priority number
	 * returns x*100, a unique number that distinguish all passengers.
	 */
	public int hashCode(){
		return (this.boardV*this.sequence)*100;

	}

	
	
	/**
	 * gest the name of the passenger.
	 * @return name, passenger's name.
	 */
	public String getName() {
		return name;
	}

	
	
	/**
	 * gets the section of the passenger.
	 * @return board, passenger's section.
	 */
	public char getBoard() {
		return board;
	}

	
	
	/**
	 * gets the sequence of the passenger.
	 * @return sequence, passeger's sequence number.
	 */
	public int getSequence() {
		return sequence;
	}

	
	
	/**
	 * prints the passenger according to its seat and section number.
	 * @return String, seat and section number of a passenger.
	 */
	public String toString(){
		return getName() + " in seat "+ getBoard() + getSequence();
	}
}
