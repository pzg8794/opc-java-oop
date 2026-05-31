/*
 *  Created:      01/24/2012
 *  Last Changed: 01/24/2012
 *  
 *  RunWoolies.java 
 * 
 *  Version:
 *     $Id: RunWoolies.java,v 1.1 2013/05/07 01:21:53 pzg8794 Exp $
 *
 *  Revisions:
 *     $Log: RunWoolies.java,v $
 *     Revision 1.1  2013/05/07 01:21:53  pzg8794
 *     *** empty log message ***
 *
 */

/**
 * Test the TrollsBridge and Woolies simulation.
 * Test by creating a bunch of Woolies and let them cross the TrollsBridge.
 * @author  Piter Garcia	ID :  pzg8794 	RIT ID : 296009929
 */
public class RunWoolies {

	/** SIDE_ONE is Merctran.  */
	public final static String SIDE_ONE = "Merctran";
	/** SIDE_TWO is Sicstine.  */
	public final static String SIDE_TWO = "Sicstine";



	/** 
	 * Command interface for collecting all the functions in this test suite.
	 * Single method is Command.execute().
	 */
	private interface Command {
		public void execute();
	}



	/** 
	 * testSuite is the list of test cases.
	 */
	private static Command[] testSuite = {
		new Command() { public void execute() { RunWoolies.test0(); }},
		new Command() { public void execute() { RunWoolies.test1(); }},
		new Command() { public void execute() { RunWoolies.test2(); }},
		new Command() { public void execute() { RunWoolies.test3(); }},
	};



	/** TEST_COUNT is number of test cases.  */
	public final static int TEST_COUNT = testSuite.length;




	/**
	 * test0 is Test Scenario 0, an extremely simple, non-waiting test.
	 * test0 provides an example template/pattern for writing a test case.
	 */
	@SuppressWarnings("static-access")
	static void test0() {

		System.out.println( "Begin test0. ===============================\n" );

		Thread init = Thread.currentThread();      // init spawns the Woolies

		// Create a TrollsBridge of capacity 3.
		TrollsBridge trollBridge = new TrollsBridge( 3 );

		// Set an optional, test delay to stagger the start of each woolie.
		int delay = 4000;

		// Create the Woolies and store them in an array.
		@SuppressWarnings("rawtypes")
		Thread peds[] = {
			new Woolie( "Al",    3, SIDE_ONE, trollBridge ),
			new Woolie( "Bob",   4, SIDE_TWO, trollBridge ),
		};

		for ( int j = 0; j < peds.length; ++j ) {
			// Run them by calling their start() method.
			try {
				peds[j].start();
				init.sleep( delay );
			}
			catch ( InterruptedException e ) {
				System.err.println( "Abort. Unexpected thread interruption." );
				break;
			}
		}
		// Now, the test must give the woolies time to finish their crossings.
		for ( int j = 0; j < peds.length; ++j ) {
			try {
				peds[j].join();
			}
			catch ( InterruptedException e ) {
				System.err.println( "Abort. Unexpected thread interruption." );
				break;
			}
		}
		System.out.println( "\n=============================== End test0." );
		return;
	}




	/**
	 * test1 is Test Scenario 1, another fairly simple simulation run.
	 * test1 provides another example for writing a test case.
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	static void test1() {

		System.out.println( "Begin test1. ===============================\n" );

		Thread init = Thread.currentThread();      // init spawns the Woolies

		// Create a TrollsBridge of capacity 3.
		TrollsBridge trollBridge = new TrollsBridge( 3 );

		int delay = 1000;

		// Create the Woolies and store them in an array.
		Thread peds[] = {
				new Woolie( "Al",    3, SIDE_ONE, trollBridge ),
				new Woolie( "Bob",   2, SIDE_ONE, trollBridge ),
				new Woolie( "Cathy", 2, SIDE_TWO, trollBridge ),
				new Woolie( "Doris", 3, SIDE_TWO, trollBridge ),
				new Woolie( "Edith", 3, SIDE_ONE, trollBridge ),
				new Woolie( "Fred",  2, SIDE_TWO, trollBridge ),
		};

		for ( int j = 0; j < peds.length; ++j ) {
			// Run them by calling their start() method.
			try {
				peds[j].start();
				init.sleep( delay );         // delay start of next woolie
			}
			catch ( InterruptedException e ) {
				System.err.println( "Abort. Unexpected thread interruption." );
			}
		}
		// Now, the test must give the woolies time to finish their crossings.
		for ( int j = 0; j < peds.length; ++j ) {
			try {
				peds[j].join();              // wait for next woolie to finish
			}
			catch ( InterruptedException e ) {
				System.err.println( "Abort. Unexpected thread interruption." );
			}
		}

		System.out.println( "\n=============================== End test1." );
	}




	/**
	 * test2 is Test Scenario 2, another fairly simple simulation run in which
	 * multiple woollies can be on the bridge at the same time and going in 
	 * different directions at the same time. 
	 */
	@SuppressWarnings("rawtypes")
	public static void test2() {
		System.out.println( "Begin test2. ===============================\n" );
		
		int delay = 500;
		// Create a TrollsBridge of capacity 3.
		TrollsBridge trollBridge = new TrollsBridge( 3 );

		// Here come the Woolies...
		Thread ped1 = new Woolie( "Jim(3,M)", 3, SIDE_ONE, trollBridge );
		Thread ped2 = new Woolie( "Paul(5,M)", 5, SIDE_ONE, trollBridge );
		Thread ped3 = new Woolie( "H-P(10,S)", 10, SIDE_TWO, trollBridge );
		Thread ped4 = new Woolie( "Karen(4,S)", 4, SIDE_TWO, trollBridge );
		Thread ped5 = new Woolie( "Edith(6,S)", 6, SIDE_TWO, trollBridge );

		// Run them
		try {
			ped1.start();
			Thread.currentThread();
			Thread.sleep( delay );

			ped2.start();
			Thread.currentThread();
			Thread.sleep( delay );

			ped3.start();
			Thread.currentThread();
			Thread.sleep( delay );

			ped4.start();
			Thread.currentThread();
			Thread.sleep( delay );

			ped5.start();
		}
		catch(InterruptedException e) {
			System.err.println( "Unexpected thread interruption" );
		}
		System.out.println( "\n=============================== End test2." );
	}



	/**
	 * test3 is Test Scenario 3, another fairly simple simulation run in which
	 * multiple woollies can be on the bridge at the same time and going in 
	 * different directions at the same time, and only three are allowed and 
	 * they all have 3 seconds to cross the bridge. 
	 */
	public static void test3() {
		// Create a TrollsBridge of capacity 3.
		System.out.println( "Begin test3. ===============================\n" );

		TrollsBridge trollBridge = new TrollsBridge( 3 );

		// Here come the Woolies...
		Thread[] peds = {
				new Woolie<Object, Object>( "A(3,M)", 3, SIDE_ONE, trollBridge ),
				new Woolie<Object, Object>( "B(5,M)", 5, SIDE_ONE, trollBridge ),
				new Woolie<Object, Object>( "C(10,S)", 10, SIDE_TWO, trollBridge ),
				new Woolie<Object, Object>( "D(4,S)", 4, SIDE_TWO, trollBridge ),
				new Woolie<Object, Object>( "E(6,S)", 6, SIDE_TWO, trollBridge ),
				new Woolie<Object, Object>( "F(3,S)", 3, SIDE_TWO, trollBridge ),
				new Woolie<Object, Object>( "G(5,S)", 5, SIDE_TWO, trollBridge ),
				new Woolie<Object, Object>( "H(10,M)", 10, SIDE_ONE, trollBridge ),
				new Woolie<Object, Object>( "I(4,M)", 4, SIDE_ONE, trollBridge ),
				new Woolie<Object, Object>( "J(6,M)", 6, SIDE_ONE, trollBridge )
		};

		// Run them
		for ( int i=0; i < peds.length; ++i ) {
			peds[ i ].start();
		}
		System.out.println( "\n=============================== End test3." );
	}

	/**
	 * Run all the tests in this test suite.
	 *
	 * @param args not usedest
	 */
	public static void main( String args[] ) {

		for ( int j = 0; j < TEST_COUNT; ++j ) {
			testSuite[j].execute();
		}
	}

}