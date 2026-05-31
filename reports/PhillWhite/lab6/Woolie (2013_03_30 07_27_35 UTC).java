/*
 *  Created:      01/24/2012
 *  Last Changed: 01/24/2012
 *  
 *  Woolie.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */



/**
 * Woolie - simulates a woolie crossing a bridge. Each woolie object is 
 * constructed with a name, length of time it takes the woolie to cross 
 * the bridge, and a destination city.  The objects, which extend the 
 * Thread class, execute as individual threads.
 *
 * Before crossing the bridge, a woolie will ask a troll for permission 
 * to cross.  Once the troll grants permission, the woolie crosses the 
 * bridge.  Once on the other side, the woolie will notify the troll that 
 * it has left the bridge.
 *
 * @author  Wander Bravo	ID :  wmb1306 	RIT ID : 110006833
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 110006833
 * @param <bridgeGuard1>
 * @param <bridgeGuard1>
 */
 public class Woolie<bridgeGuard, bridgeGuard1> extends Thread {


    //The number of seconds require to cross
    private int crossingTime;
    //The name of the destination of this woolie
    private String destination;
    //The troll guarding the bridge
    private TrollsBridge bridgeGuard;
    
    
    
    
    /**
    * Construct a new Woolie object that can be started in a separate
    * thread.  The constructor will simply initializes all of the 
    * instance fields.
    *
    * @param       n    the name of this Woolie
    * @param       c    the number of seconds it takes the Woolie to cross 
    *                   the bridge
    * @param       d    the city the Woolie is heading to
    * @param       trollBridge    the bridge the Woolie is crossing
    */
    public Woolie( String n, int c, String d, TrollsBridge bridgeGuard ) {
        super( n );
        crossingTime = c;
        destination = d;
        this.bridgeGuard = bridgeGuard;
    }
    
    
    
    /**
    * This method handles the Woolie's crossing of the bridge.  There
    * are several messages that must be displayed to describe the Woolie's
    * progress crossing the bridge.  
    */
    @SuppressWarnings("static-access")
	public void run() {

        // The Woolie has started to cross the bridge
        // Get permission to cross from the troll
        bridgeGuard.enterBridgePlease(this);

        // Simulate the time on the bridge
        for ( int time = 0; time < crossingTime; time++ ) {
            
        	
        	// Take care of output
            if( time == 0 )
                System.out.println( getName() + " is starting to cross." );
            else
                System.out.println( "\t" + getName() + ' ' + time + " seconds." );

            // Let time pass
            try {
            	
				this.sleep(1000);
				
			} catch (InterruptedException e) {
	
				e.printStackTrace();
			}
           
        }

     // Finished crossing
        System.out.println( getName() + " leaves at " + destination + "." );
        
        // Tell the bridge we have crossed
        bridgeGuard.leave();
    }
    
    
    
    /**
    * The main program.
    *
    * @param    args    command line arguments taken as number of kids
    */
    @SuppressWarnings("rawtypes")
	public static int main ( String[] args){
    	
    	TrollsBridge bridgeGuard = new TrollsBridge (3);
    	
    	String names[] = {"1", "2", "3", "4", "5", "6"};
    	Woolie[] testSuite = new Woolie[6];
    	
    	 for ( int j = 0; j < 10; ++j ) {
    		 testSuite[j] = new Woolie( names[j], 3, "South", bridgeGuard);
    		 testSuite[j].start();
         }
		return 0;
    }
}