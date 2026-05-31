/*
 * HeapQueue.java
 *
 * Version:
 *  $Id: HeapQueue.java,v 1.1 2013/06/19 02:43:14 pzg8794 Exp $
 *
 *
 * Revisions:
 *  $Log: HeapQueue.java,v $
 *  Revision 1.1  2013/06/19 02:43:14  pzg8794
 *  Lab3!
 *
 *
 */

import java.util.ArrayList;

/**
 * A priority queue using a heap.  Assumes that larger numbers are higher
 * priority and therefore this is a max-heap (largest value on top).
 * 
 * @author zjb
 * @author Trudy Howles  tmh@cs.rit.edu 
 *
 */
public class HeapQueue<T extends Comparable<T> > implements PriorityQueue<T> {

        /** underlying heap storage */
	private ArrayList<T> theHeap;

        /**
	 * The constructor for the HeapQueue
	 *
	 */
	public HeapQueue() {
		theHeap = new ArrayList<T>();
	}

	/**
	 *
	 * Removes and returns the item at the front of the queue
	 *
	 * @return T the item at the front of the queue otherwise 
	 * returns a null
	 */
	public T dequeue() {
		T toReturn = null;
		if (! theHeap.isEmpty()) {
		    // keep the top of the heap to return later
		    toReturn = theHeap.get(0);
		    // if that wasn't the last one, take end of the heap to
		    // the root and bubble it down.
		    if (theHeap.size() > 1) {
			theHeap.set(0,theHeap.remove(theHeap.size()-1));
			int maxc = 0;
			int whereamI = 0;
			while ((maxc = maxChild(whereamI)) != -1) {
				// bubbling down
				T temp = theHeap.get(whereamI);
				theHeap.set(whereamI, theHeap.get(maxc));
				theHeap.set(maxc, temp);
				whereamI = maxc;
			}
		    } else {
			// simply remove the root and be happy!
			theHeap.remove(0);
                    }
		}
		return toReturn;
	}

	/**
	 * Computes the child with max priority - but also counting the parent,
	 * so that if both children are smaller than the parent, -1 is returned.
	 * Also returns -1 if there are no children of the given index
	 * 
	 * @param index the current working index
	 * @return index of child with largest priority, or -1
	 */
	private int maxChild(int index) {
		int maxc = -1;
		// children of index are 2 * index + 1, 2 * index + 2
		// if first child exists, is it higher than me?
		if (2 * index + 1 < theHeap.size()) {
			T item = theHeap.get(index);
			T childOne = theHeap.get(2 * index + 1);
			if (childOne.compareTo( item ) > 0)  {
				maxc = 2 * index + 1;
			}
			// how about a second child?
			if (2 * index+2 < theHeap.size()) {
			    T childTwo = theHeap.get(2 * index + 2);
			    if (childTwo.compareTo( childOne ) > 0)  {
					maxc = 2 * index + 2;
				}
			}
		}
		return maxc;
	}

	/**
	 *
	 * Add an item to the queue at the appropriate location
	 *
	 * @param toInsert the item to insert in the queue
	 */
	public void enqueue (T toInsert) {
		// put at the end and bubble up!
		theHeap.add(toInsert);
		int whereamI = theHeap.size()-1;
		int parentLoc = whereamI/2;
		// bubble if I'm not root and am higher priority than my parent.
		while ((whereamI > 0) && 
			(theHeap.get( whereamI ).compareTo( 
					theHeap.get(parentLoc)) > 0)){
			T temp = theHeap.get(whereamI);
			theHeap.set(whereamI, theHeap.get(parentLoc));
			theHeap.set(parentLoc,temp);			
			whereamI = parentLoc;
			parentLoc = whereamI/2;
		}
	}

	/**
	 *
	 *Is there anything in the queue?
	 *
	 @return true if the queue is empty; otherwise returns false
	 */
	public boolean isEmpty() {
		return theHeap.isEmpty();
	}


	/**
	 * main method - used here as a test program
	 *
	 * I can use Strings because the class implements Comparable
	 *
	 * Prints in reverse order (remember this is a Max heap)
	 *
	 * @param args[] not used
	 */

	public static void main (String args[] ) {
	    HeapQueue <String> hq = new HeapQueue <String> ();
	    hq.enqueue ("Hi");
	    hq.enqueue ("Hi There");
	    hq.enqueue ("Apple");
	    hq.enqueue ("House");
	    hq.enqueue ("Dog");

	    while (! hq.isEmpty()) {
		System.out.println (hq.dequeue());
	    }
	}

}
