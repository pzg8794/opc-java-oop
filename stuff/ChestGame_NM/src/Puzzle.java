import java.util.ArrayList;

/**
 * 
 * Puzzle.java
 *
 * authors: Raelynn Janicke, Robert Matsch
 *
 * File:Puzzle.java
 *      $Id: Puzzle.java,v 1.2 2013/04/18 19:11:33 rjj9591 Exp $
 *
 * Revisions:
 *      $Log: Puzzle.java,v $
 *      Revision 1.2  2013/04/18 19:11:33  rjj9591
 *      Second submission with comments and txt file
 *
 *      Revision 1.1  2013/04/18 18:58:35  rjj9591
 *      First Submission
 *
 */


/**
 * Interface puzzle
 * Everything has been changed to a generic type so that any type of puzzle class can implement the methods
 */
public interface Puzzle<E> {

	/**
	 * Get the starting config for this puzzle.
	 * @return the starting config
	 */
	E getStart();
		 
	/**
	 * Get the goal config for this puzzle
	 * @return a boolean for whether or not the config is the goal config
	 */
	boolean getGoal(E config); 
		 
	/**
	 * @param config - the incoming config 
	 * @return the collection of neighbor configs
	 */
	ArrayList<E> getNeighbors(E config);
	
}

