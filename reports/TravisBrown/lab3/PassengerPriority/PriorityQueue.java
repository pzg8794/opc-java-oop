/*
 * PriorityQueue.java
 *
 * Version:
 *  $Id: PriorityQueue.java,v 1.1 2013/06/19 02:43:14 pzg8794 Exp $
 *
 * Revisions:
 *  $Log: PriorityQueue.java,v $
 *  Revision 1.1  2013/06/19 02:43:14  pzg8794
 *  Lab3!
 *
 *
 */

/**
 * Interface for a generic priority queue.  Does not specify whether
 * small or large priority values are "high" priority.
 *
 * @author zjb
 * @author Trudy Howles    tmh@cs.rit.edu
 */
public interface PriorityQueue<T extends Comparable<T> > {

	/**
	 * Is there anything in the queue?
	 * @return true if the queue is empty; otherwise return false
	 */
	public boolean isEmpty ();

	/**
	 * Add an item to the queue (at the appropriate location)
	 * @param toInsert Item to be added
	 */
	public void enqueue (T toInsert);

	/**
	 * Removes and returns the item at the front of the queue.
	 * @return the removed element
	 */
	public T dequeue ();

}
