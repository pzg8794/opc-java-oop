/**
 * 
 * Solver.java
 *
 * authors: Raelynn Janicke, Robert Matsch
 *
 * File:Solver.java
 *      $Id: Solver.java,v 1.2 2013/04/18 19:11:34 rjj9591 Exp $
 *
 * Revisions:
 *      $Log: Solver.java,v $
 *      Revision 1.2  2013/04/18 19:11:34  rjj9591
 *      Second submission with comments and txt file
 *
 *      Revision 1.1  2013/04/18 18:58:38  rjj9591
 *      First Submission
 *
 */

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Class of generic type to solve any puzzle
 */
public class Solver<E> {

	/*
	 * ArrayList of generic type is created and takes in a new generic puzzle type
	 * Setting a boolean to false
	 */
	public ArrayList<E> Solved(Puzzle<E> puzzle){
		boolean found = false;
		
		/*
		 * A hashmap  of generic type is created to keep track of the predecessors and their keys
		 * ArrayList of generic type is created to keep track of the current value
		 * A generic current is created
		 */
		HashMap<E,E> predecessors = new HashMap<E,E>();
		ArrayList<E> queue = new ArrayList<E>();
		E curr;
		
		/*
		 * Current is set to the start value of the puzzle, and that is added to the queue arraylist
		 * If that value is the goal for the puzzle, set the boolean found to true and remove that 
		 * value from the queue
		 */
		curr = puzzle.getStart();
		queue.add(curr);
		if(puzzle.getGoal(queue.get(0))){
			found = true;
			queue.remove(0);
		}
		/*
		 * Putting the current value to the predecessors hashmap
		 * Checking while the queue arraylist is not empty, and the goal has not been found
		 * Set the current to the value in the 0th position of the queue arraylist and remove it from the queue
		 * A for loop checks each neighbor to each of the neighbors of the current position
		 * Creating a new variable and setting it to the current position so original current does not change
		 * If the goal for the puzzle is a neighbor being checked, create a predecessors with that neighbor
		 * and the new variable for the current(newcurr)
		 * Set the current to the neighbor solution and set found to true
		 */
		predecessors.put(curr, curr);
		while (!queue.isEmpty() && !found){
			curr = queue.get(0);
			queue.remove(0);
			for(E nb: puzzle.getNeighbors(curr)){
				E newcurr = curr;
				if (puzzle.getGoal(nb)){
					predecessors.put(nb,newcurr);
					curr = nb;
					found = true;
					break;
				}
				/*
				 * if the predecessors hashmap does not contain that neighbor, put that neighbor and the
				 * current value into the hashmap and add that neighbor to the queue arraylist
				 */
				else{
					if(!predecessors.containsKey(nb)){
						predecessors.put(nb, curr);
						queue.add(nb);
					}
				}
				
			}
		}
		
		/*
		 * If a solution has been found
		 * Create an arraylist to return the solution of the puzzle
		 * Create another new variable for the current position
		 * Add that new value to the solution arraylist
		 * While the newestcurr is not the start of the puzzle,add 0 and the predecessors of the newestcurrent
		 * Set newestcurrent to the predecessors of that newestcurrent
		 * Return solution, or null if solution is not found
		 */
		if (found){
			ArrayList<E> solution = new ArrayList<E>();
			E newestcurr = curr;
			solution.add(newestcurr);
			while(!newestcurr.equals(puzzle.getStart())){
				solution.add(0,predecessors.get(newestcurr)); // must add 0 so the solutions are added in the correct order
				newestcurr = predecessors.get(newestcurr);
			}
			return solution;
		}
		else{
			return null;
		}
	
	}
}
	


