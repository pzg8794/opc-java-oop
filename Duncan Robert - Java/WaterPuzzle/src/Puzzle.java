/*
 * Solver.java 
 * 
 * Version:
 * $Id: Puzzle.java,v 1.1 2013/04/19 03:12:25 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Puzzle.java,v $
 * Revision 1.1  2013/04/19 03:12:25  pzg8794
 * Finally Done!
 *
 * Revision 1.1  2013/04/04 06:13:38  pzg8794
 * Java Project Part1
 *
 */
import java.util.ArrayList;

/**
 * An interface to a Puzzle. It contains the routines necessary for 
 * accessing the start and goal configurations, as well as generating new 
 * neighboring configurations.
 *
 * @author Piter Garcia
 *
 */
public abstract class Puzzle<E> {
	private E config;
	
	
	
	/**
     * Constructor, creates a list of jugs.
     */
	public Puzzle(E config){
		this.config = config;
	}
	
	
	
	/**
     * Constructor, creates a list of jugs.
     */
	public Puzzle() {
	}

	/**
     * Gets the Goal time given by the user in the command line and returns it.
     *
     * @return goalTime, this is the goal hour our to be reached by the solver 
     * class.
     */
	 public abstract boolean getGoal(E config);
     //Get the goal config for this puzzle.
	 
	 
	 
	/**
	 * For an incoming configuration, generates and return all direct neighbors
	 * to this configuration.
	 *
     * @param  config, a time integer value(hour) used from the Solver.class to 
     * obtain its neighbor and compare till goal is reached.
     * @return list, an ArrayList that contains two neighbors of one integer(hr)
     */
	 public abstract ArrayList<E>	getNeighbors(E e);
     //For an incoming config, generate and return all direct neighbors to this config.
	 
	 
	 
    /**
	 * Gets the Start time given by the user in command line and returns it.
	 *
	 * @return startTime, this is start time from which the solver class will 
	 * start to count to reach the goal time.
	 */
	 public  E getStart(){
		 return config;
	 }
     //Get the starting config for this puzzle.
}
