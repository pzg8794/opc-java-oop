/**
 * 
 * Water.java
 *
 * authors: Raelynn Janicke, Robert Matsch
 *
 * File:Water.java
 *      $Id: Water.java,v 1.2 2013/04/18 19:11:32 rjj9591 Exp $
 *
 * Revisions:
 *      $Log: Water.java,v $
 *      Revision 1.2  2013/04/18 19:11:32  rjj9591
 *      Second submission with comments and txt file
 *
 *      Revision 1.1  2013/04/18 18:58:38  rjj9591
 *      First Submission
 *
 */

import java.util.ArrayList;


/*
 * Water Class that implements and ArrayList of Integers from the Puzzle interface(generic types)
 */
public class Water implements Puzzle<ArrayList<Integer>> {
	
	/*
	 * Declarations
	 */
	public int goal;
	
	public ArrayList<Integer> start;
    public ArrayList<Integer> buckets;
	
    public ArrayList<Integer> getStart(){
		return start;
	}

    /*
     * Constructor for water
     */
	public Water(ArrayList<Integer> buckets, Integer goal){
		this.buckets = buckets;
		this.goal = goal;
		this.start = new ArrayList<Integer>(buckets.size());
		for (int i = 0; i < buckets.size(); i++){
			start.add(0);									//setting first bucket value as start
		}
	}
	
	/*
	 * getGoal is set to a boolean, as it is in the Puzzle interface, and takes in an ArrayList of goals
	 * For each loop to check all integers in the arraylist goals
	 * if that value compared to the goal is 0, then return true
	 * otherwise, return false
	 */
	public boolean getGoal(ArrayList<Integer> goals){
		for (Integer check: goals){
			if (check.compareTo(goal) == 0){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * getNeighbors will return a 2d arraylist
	 * parameter: a 2d arraylist of integers that represents the config
	 * A 2d arraylist of integers is being created for the neighbors
	 */
	public ArrayList<ArrayList<Integer>> getNeighbors(ArrayList<Integer> config){
		ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>();
		
		/*
		 * This loop is used to fill up one bucket to its maximum capacity
		 * Looping through all of the elements within the size of the configuration
		 * A temporary arraylist of integers is made as using the entered config
		 * A new integer to represent the maximum capacity is set to the bucket
		 * copy.set allows for the maxcap to be set to the ith position of the arraylist
		 * the new arraylist copy is added to the neighbors 2d arraylist
		 */
		for(int i = 0; i < config.size(); i++){
			ArrayList<Integer> copy = new ArrayList<Integer>(config);
			int maxcap = buckets.get(i);
			copy.set(i, maxcap);
			neighbors.add(copy);
		}
		
		
		/*
		 * This loop is used to empty out a jug of water
		 * It goes through all elements within the size of the entered config
		 * A temporary arraylist is set with the config entered, and the value of 0 is set to the value in
		 * the ith position of the arraylist
		 * That temporary arraylist is added to the 2d arraylist of neighbors
		 */
		for(int i = 0; i < config.size(); i++){
			ArrayList<Integer> temp = new ArrayList<Integer>(config);
			temp.set(i, 0);
			neighbors.add(temp);
		}
		

		/*
		 * This loop pours water from one jug into another while respecting the capacity
		 * an integer is set to the integer value of i
		 * then another loop is used to loop through within the size of the config
		 * if those integer values are the same, break out because no pouring can be done
		 */
		for(int i = 0; i < config.size(); i++){
			int pour = config.get(i);
			for(int j = 0; j < config.size(); j++){
				if (j == i){
					/*
					 * keyword continue used to go through loop, but if j and i are the same, skip
					 * over that value, and continue through the loop
					 * Cant use break because it wont loop through other values
					 */
					continue;
				}
				/*
				 * A temporary arraylist of integers is created using the entered config
				 * a is set with the config of the j value within the loop 
				 * an int take is created with a bucket with j value minus the created amount
				 * This simulates removing the amount of water from the container
				 * An integer transferred is set to hold the amount of water moved between the jugs
				 */
				ArrayList<Integer> temp = new ArrayList<Integer>(config);
				int amount = config.get(j);
				int container = buckets.get(j) - amount;
				/*
				 * if pour is less than or equal to the current container, the moved amount is set to 
				 * pour, otherwise the moved amount is set to the container
				 */
				int moved;
				if(pour <= container){
					moved = pour;
				} else {
					moved = container;
				}
				
				/*
				 * The amount + the moved amount are set to the jth position in the temp arraylist
				 * the pour amount - moved amount is set to the ith position in the temp arraylist
				 * that arraylist is added to the 2d arraylist of neighbors
				 */
				temp.set(j, amount+(moved));
				temp.set(i, pour-(moved));
				neighbors.add(temp);
			}
		}
		return neighbors;			//returning neighbors 2d arraylist
	}

	/*
	 * main function
	 */
	public static void main(String[] args){
		/*
		 * checking if the arguments given is less than 2, if so print error message
		 */
		if (args.length < 2){
			System.out.println("Usage: java Water amount jug1 jug2 ...");
		} 
		/*
		 * If correct amount of args given
		 * An arraylist of integers is created for the buckets
		 * Loop through given arguments and adding them to the buckets arraylist
		 */
		else {
			ArrayList<Integer> buckets = new ArrayList<Integer>();
			for(int i = 1; i < args.length; i++){
				buckets.add(Integer.parseInt(args[i]));
			}
			int goal = Integer.parseInt(args[0]); //the goal is set to the first argument in the entered arguments
			
			/*
			 * A new water puzzle is created with the given buckets and the goal
			 * Solver is given an arraylist of integers to create a new solver
			 * The solution to the puzzle is a 2d arraylist of the solved water puzzle
			 */
			Water puzzle = new Water(buckets, goal);
			Solver<ArrayList<Integer>> jug = new Solver<ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> solution = jug.Solved(puzzle);
			/*
			 * if there is a solution, loop through the solution, print the steps
			 * a temp arraylist is created for the steps of the solution
			 * another loop is created to get all of the values at each step and print them
			 */
			if (solution != null){
				for(int i = 0; i < solution.size(); i++){
					System.out.print("Step " + i + ":");
					ArrayList<Integer> temp = solution.get(i);
					for (int j = 0; j < temp.size(); j++){
						System.out.print(" " + temp.get(j));
					}
					System.out.println();
				}
			} 
			else{
				System.out.println("No solution.");			//if no solution, print no solution
			}
		}
	}		
}