/*
 * Clock.java 
 * 
 * Version:
 * $Id: Clock.java,v 1.1 2013/04/19 03:12:25 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Clock.java,v $
 * Revision 1.1  2013/04/19 03:12:25  pzg8794
 * Finally Done!
 *
 * Revision 1.4  2013/04/04 07:26:57  pzg8794
 * Finally Done
 *
 * Revision 1.3  2013/04/04 07:10:41  pzg8794
 * Done with project part1!
 *
 * Revision 1.2  2013/04/04 06:54:05  pzg8794
 * updating project part1
 *
 * Revision 1.1  2013/04/04 06:13:39  pzg8794
 * Java Project Part1
 *
 */

import java.util.ArrayList;

/** 
 * Clock.java is the class responsible to provide configurations to the
 * solver, as the solver demands them. 
 * 
 * @author Piter Garcia
 */
public class Clock extends Puzzle<Integer> {
	
    /*
     * contains the maximum number of hours to be reached.
     */
     private int numberOfHours;
    
    /*
     * contains the value used to start searching for the goal hour.
     */
     private static int startTime;
     
    /*
     * contans the value of the time that the user want to reach. 
     */
     private int goalTime;
	
	
	
	/**
     * Clock class constructor that initializes the class data member
     *  with data
     * input by the user from the command line.
     */
	public Clock(String[] args) {
		super(startTime);
		numberOfHours = Integer.parseInt(args[0]);
		startTime 	  = Integer.parseInt(args[1]);
		goalTime 	  = Integer.parseInt(args[2]);
	}

	
	
	
	/**
     * Checks and validates the data input by the user to make sure that
     * all data input is accepted by the solver class.
     * 
     * @param args, parameters contain the number, start, and goal hour.
     * @throws Exception if the input integer value is negative it 
     * throws an exception
     * and display a message about the error to the user.
     */
	public static void main ( String[] args){
		
		if(wrongInput(args)){
			try {
				throw new Exception("Usage: java Clock hours start goal");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}else{
	
			ArrayList<Integer> list = new ArrayList<Integer>();
			list = new Solver<Integer>(new Clock(args)).solve();
			
			int i = 0;
			for( Object x: list)
				if (list != null)
				System.out.println("Step "+(i++)+": " + x);
			}
	}




	/**
     * Gets the Goal time given by the user in the command line and returns it.
     *
     * @return goalTime, this is the goal hour our to be reached by 
     * the solver class.
     */
	public boolean getGoal(Integer config) {
		
		return config == goalTime;
	}
	

	
	/**
     * Gets the Start time given by the user in command line and returns it.
     *
     * @return startTime, this is start time from which the solver class will 
     * start to count to reach the goal time.
     */
	public Integer getStart() {
	
		return startTime;
	}
	
	
	
	/**
     * Gets the number of Hours time given by the user in the command line
     * and returns it.
     *
     * @return numberOfHours, this is the number of hours( maximum time) that 
     * the clock will be able to reach before getting the goal time. time 
     * start to count to reach the goal time.
     */
	public int getNumberOfHours() {
		
		return numberOfHours;
	}
	
	
	/**
     * Checks and validates whether the input time is correct before the
     *  solver starts solving the puzzle.
     *
     * @param args, parameters string value input from the command line that 
     * contain the clock information such as maximum, start, and goal hour.
     * @return true, it returns true if is valid. 
     * @return false,it returns true if is invalid. 
     */
	public static boolean wrongInput(String[] args){
		
		if( args.length != 3 ){
			return true;
		}else if(Integer.parseInt(args[2])>Integer.parseInt(args[0]))
			return true;
		else {
			for( String g: args){
				//System.out.println(" Checking input " + g);
				if(Integer.parseInt(g) <= 0)
					 return true;
			}
		}
		
		return false;
	}


	/**
	 * This methods obtains the neighbors of a integer(hour) and it saves
	 * it in an ArrayList so the Solver class can use them to keep finding
	 * more neighbors until the goal time is reached.
	 *
	 * @param  config, a time integer value(hour) used from the Solver.class
	 *  to obtain its neighbor and compare till goal is reached.
	 * @return list, an ArrayList that contains two neighbors of one
	 *  integer(hr)
	 */
	public ArrayList<Integer> getNeighbors(Integer config) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int left, right;
	
		if( config == 1){
			list.add(numberOfHours);
			//System.out.println(numberOfHours);
			list.add(2);
			//System.out.println(2);
			return list;
		}
		if(config == numberOfHours){
			list.add(numberOfHours - 1);
			//System.out.println(numberOfHours - 1);
			list.add(1);
			//System.out.println(1);

			return list;
		}
		left = config-1;
		list.add(left);
		// System.out.println(left);

		right = config + 1;
		list.add(right);
		//System.out.println(right);
		return list;
	}
}
