/*
 *  Created:      01/24/2012
 *  Last Changed: 01/24/2012
 *  
 *  RunWoolies.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */
import java.util.LinkedList;
import java.util.Queue;
/**
 * Test the TrollsBridge and Woolies simulation.
 * Test by creating a bunch of Woolies and let them cross the TrollsBridge.
 * @author  Wander Bravo	ID :  wmb1306 	RIT ID : 110006833
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 296009929
 */
public class TrollsBridge {

	private int maxCapacity;
	@SuppressWarnings("rawtypes")
	private Queue<Woolie> woolies;
    @SuppressWarnings("rawtypes")
	private Queue<Woolie> waitingWoolies;
    
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
        	
    	if(!isFull()){
    		woolies.add(woolie);
    	}
    	else{
    			//synchronized(woolie){
    			System.out.println("Get in line!"+woolie.getName());
    			waitingWoolies.add(woolie);
    			
    			 while (isFull()) {
    				 
        			 while ( waitingWoolies.peek() != woolie){
        				 
    					try {
    						woolie.wait();
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
        			 }
        			woolies.add(waitingWoolies.poll());
				 }
    			 
    //}
    	}

    }



	/**
    * Inform the troll that the caller (the woolie) is getting
    * off the bridge.
    */
    public synchronized void leave() {
    	
    	
    	if(!waitingWoolies.isEmpty()){
   
    		  //makeWakeupNeeded();
    		  woolies.poll();
    		  notifyAll();
    		  
        }
    	else
    		woolies.poll();
    }
	



	/**
    * Inform the troll whether the bridge is full or not to allow
    * other woolies to cross the bridge if the maximum capicity has 
    * not been reached.
    */
    public synchronized boolean isFull() {
    	return woolies.size() == maxCapacity;
	}
}
