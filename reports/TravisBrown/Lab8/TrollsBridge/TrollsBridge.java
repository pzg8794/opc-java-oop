/*
 *  Created:      01/24/2012
 *  Last Changed: 01/24/2012
 *  
 *  RunWoolies.java 
 * 
 *  Version:
 *     $Id: TrollsBridge.java,v 1.2 2013/05/07 07:03:59 pzg8794 Exp $
 *
 *  Revisions:
 *     $Log: TrollsBridge.java,v $
 *     Revision 1.2  2013/05/07 07:03:59  pzg8794
 *     nice!
 *
 *     Revision 1.1  2013/05/07 01:21:52  pzg8794
 *     *** empty log message ***
 *
 */
import java.util.LinkedList;
import java.util.Queue;
/**
 * Test the TrollsBridge and Woolies simulation.
 * Test by creating a bunch of Woolies and let them cross the TrollsBridge.
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 296009929
 */
public class TrollsBridge {

	/**
	 * maximum amount of woolies allowed on the bridge.
	 */
	private int maxCapacity;
	private int size;
	@SuppressWarnings("rawtypes")

	/**
	 * a list of woolies that are crossing the bridge.
	 */
	private Queue<Woolie> woolies;
	@SuppressWarnings({ "rawtypes", "unused" })

	/**
	 * A list of woolies waiting to cross the bridge.
	 */
	private Queue<Woolie> waitingWoolies;


	/**
	 * Constructor
	 * @param i, maximum capacity of woolies allowed.
	 */
	@SuppressWarnings("rawtypes")
	public TrollsBridge(int i) {
		woolies = new LinkedList<Woolie>();
		waitingWoolies = new LinkedList<Woolie>();
		maxCapacity = i;
	}




	/**
	 * Request permission to go on the bridge. This method is
	 * called by a Woolie object. It just calls Bridge.enter().
	 *
	 * Postcondition:<ul>
	 * <li>The woolie got permission and has entered the bridge.
	 * </ul>
	 */
	@SuppressWarnings({ "rawtypes" })
	public synchronized void enterBridgePlease(Woolie woolie) {

		System.out.println("The troll scowls \"Get in line!\" when "+
				woolie.getName() + " shows up at the bridge.");


		woolies.add(woolie);

		while (isFull() || woolies.peek() != woolie){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		woolies.poll();
		size++;
	}



	/**
	 * Inform the troll that the caller (the woolie) is getting
	 * off the bridge.
	 */
	public synchronized void leave() {

			size--;
			notifyAll();
	}




	/**
	 * Inform the troll whether the bridge is full or not to allow
	 * other woolies to cross the bridge if the maximum capicity has 
	 * not been reached.
	 */
	public synchronized boolean isFull() {
		return size >= maxCapacity;
	}
}
