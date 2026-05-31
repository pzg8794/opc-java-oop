/*
 * Jugs.java 
 * 
 * Version:
 * $Id: 
 *
 * Revisions:
 * $Log:
 *
 */

/**
 * This class provides the universal traits of a water jug. 
 * @author Piter Garcia
 */
public class Jugs{

    /*
     * contains the maximum number of hours to be reached.
     */
	private int goal;
	
    /*
     * contains the maximum number of hours to be reached.
     */
	protected int capacity;
	
    /*
     * contains the maximum number of hours to be reached.
     */
	protected int gallons;

	
	/**
     * Jugs constructor, initializes a jug and its capacity.
     * 
     * @param  capacity, a integer value of the maximum amount of water.
     */
	public Jugs (int capacity){
		
		this.capacity = capacity;
		this.gallons = 0;

	}


	/**
     * Jugs constructor, initializes the list of jugs and the goal.
     * @param jug, represents a jug.
     */
	public Jugs (Jugs jug){
		
		this.capacity = jug.capacity;
		this.gallons = jug.gallons;
	}
	
	
	
	/**
     * this method returns the capacity of a jug.
     * 
     * @return capacity, maximum amount of water.
     */
	public int getCapacity(){
		
		return capacity;
	}


	
	/**
     * this method returns the amount of water in a jug.
     * 
     * @return gallons, the amount of water in the jug.
     */
	public int getGallons(){
		return this.gallons;
	}
	
	
	
	/**
     * this method empties a jug.
     * 
     * @return gallons, the amount of water in the jug.
     */
	public int dump(){
		
		this.gallons = 0;
		return this.gallons;
	}

	

	/**
     * this method checks if a jug is empty.
     * 
     * return true, if the jug is empty.
     * return false, if the jug is not empty. 
     */
	public boolean isEmpty(){
		return this.gallons == 0;
	}


	
	/**
     * this method checks if a jug is full.
     * 
     * return true, if the jug not is empty.
     * return false, if the jug is empty. 
     */
	public boolean isFull(){
		return this.gallons == this.capacity;
	}


	/**
     * this method fills a jug till its capacity.
     * 
     * @return gallons, the amount of water in the jug.
     */
	public int fill()
	{
		this.gallons = this.capacity;
		return this.gallons;
	}


	
	/**
     * this method checks if a list of jugs are equal.
     * 
     * return true, if the list of jugs are equal.
     * return false, if the list of jugs are not equal.
     */
	public boolean equals(Object o){
		if (o instanceof Jugs){
			Jugs temp = (Jugs)o;
			if( this.getGallons() == temp.getGallons() && 
					this.getCapacity() == temp.getCapacity())
			  return true;
		}
		return false;
	}
	
	
	
	/**
     * this method pours water from one jug to another.
     * @param jug, represents a jug.
     * @return gallons, the amount of water in the jug.
     */
	public int transfer(Jugs second)
	{
		while (this.gallons > 0)
		{
			//Fill the second jug one gallon at a time.
			//Second jug must not be full and first jug must not be empty.
			if (second.gallons < second.capacity && this.gallons > 0)
			{
				//Remove one gallon from the first and put it in the second.
				this.gallons--;
				second.gallons++;
				//If either jug reaches two gallons, stop immediately, the goal is reached.
//				if (second.gallons == this.goal ||this.gallons == this.goal)
			}
			else break;
		}

		return this.gallons;
	}

	
	
	/**
     * this method returns the string value of a jug amount of water.
     * @return string, a string value of the amount of water in a jug.
     */
	public String toString(){
		return ""+this.gallons;

	}
}
