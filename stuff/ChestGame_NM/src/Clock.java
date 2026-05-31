/**
 * 
 * Clock.java
 *
 * authors: Raelynn Janicke, Robert Matsch
 *
 * File:Clock.java
 *      $Id: Clock.java,v 1.2 2013/04/18 19:11:32 rjj9591 Exp $
 *
 * Revisions:
 *      $Log: Clock.java,v $
 *      Revision 1.2  2013/04/18 19:11:32  rjj9591
 *      Second submission with comments and txt file
 *
 *      Revision 1.1  2013/04/18 18:58:32  rjj9591
 *      First Submission
 *
 */
 
import java.util.ArrayList;
import java.util.Arrays;

//Creating puzzle of type Integer; defined as generic in puzzle interface
public class Clock implements Puzzle<Integer>{

	/**
	 * declarations
	 */
	private int Start,clockmax,Goal;
	private int clockmin = 1;
	private int nb1;// Neighbor 1
	private int nb2;//Neighbor 2
	
	/**
	 * getGoal has been changed to a boolean and takes and Integer config. If the config value compared to
	 * goal value is 0, then return true, otherwise return false
	 * 
	 */
	public boolean getGoal(Integer config){
		if(config.compareTo(this.Goal) == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * creates neighbors to an configuration
	 * @param config is current hour
	 * @return Neighbors an ArrayLIst<Integer>
	 */
	public ArrayList<Integer> getNeighbors(Integer config){
		if (config == clockmax){
			nb1 = clockmin;
			nb2 = config - 1;
		}if (config == clockmin){
			nb2 = clockmax;
			nb1 = config + 1;
		}else{
			nb1= config + 1;
			nb2 = config - 1;
			if(nb2 == 0){					//if the left neighbor is 0, set the left neighbor to the hour
				nb2 = clockmax;
			}
			if(nb1 > clockmax){					//if the right side is greater than the hours, set it to 1
				nb1 = 1;
			}
		}
		ArrayList<Integer> Neighbors = new ArrayList<Integer>();
		if (!Neighbors.contains(nb1)){
			Neighbors.add(nb1);
		}if (!Neighbors.contains(nb2)){
			Neighbors.add(nb2);
		}
		return Neighbors;
	}
	
	/**
	 * gets the start hour
	 * @return Start  returns the starting hour of type Integer; getStart is generic type in puzzle interface
	 */
	public  Integer  getStart(){
		return Start;
	}
	
	/**
	 * Clock constructor
	 * @param start The starting hour of type int
	 * @param Goal The goal hour
	 * @param clockmax the clock of the max
	 */
	public Clock(int Start, int Goal, int clockmax){
		this.Start=Start;
		this.Goal=Goal;
		this.clockmax=clockmax;
	}
	/**
	 *The purpose of main is to create an clock object and find a solution and
	 * return the path if there is one
	 * @param args cmdline arguments
	 */
	public static void main(String[] args){
		int start,goal,clockmax;
		int args_toRun = 3;
		if (args.length != args_toRun){
			 System.out.println("Usage: Expected The number of hours (integer), The start time (integer > 0), and The goal time (integer > 0 )");
			 System.exit(0);
		 }else{
			 clockmax = Integer.parseInt(args[0]);
			 start = Integer.parseInt(args[1]);
			 goal = Integer.parseInt(args[2]);
			 if (start> clockmax || goal>clockmax){
				 System.out.println("Usage: Expected The number of hours (integer), The start time (integer > 0), and The goal time (integer > 0 )");
				 System.exit(0);
			 }
			 /*
			  * Creating a new clock, then creating a new solver of the integer type
			  * Then the solution is set to the solved puzzle of the new puzzle object
			  */
			 Clock c1 = new Clock(start, goal, clockmax);
			 
			 Solver<Integer> solv = new Solver<Integer>();
			 ArrayList<Integer> solution = solv.Solved(c1);
			 if( solution == null){
				 System.out.println("null:");
			 } else {
				 for (int i=0; i<= solution.size()-1;i++){
					 System.out.println("Step "+ i+ ": " + solution.get(i));
				 }
		 
			 }
		 }
	}
}

