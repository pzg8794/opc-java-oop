/*
 * Solver.java 
 * 
 * Version:
 * $Id: Solver.java,v 1.1 2013/04/19 03:12:25 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Solver.java,v $
 * Revision 1.1  2013/04/19 03:12:25  pzg8794
 * Finally Done!
 *
 * Revision 1.2  2013/04/04 06:54:08  pzg8794
 * updating project part1
 *
 * Revision 1.1  2013/04/04 06:13:40  pzg8794
 * Java Project Part1
 *
 */
import java.util.HashMap;
import java.util.ArrayList;

/** 
 * The solver class is responsible for performing BFS, it does not manipulate
 * the configurations. This class is designed to work with multiple puzzles, 
 * and every puzzle is manipulated differently.
 * 
 * @author Piter Garcia
 *
 */
public class Solver <E> {
	
	/*
     * contains a reference a to a class that extends Puzzle.java.
     */
	private Puzzle<E> p;
	
	/*
     * contains a list of neighbors.
     */
	private ArrayList<E> queue;
	
	/*
     * contains a list of the visited points.
     */
	private HashMap<E, E> visited;
	
	/*
     * start configuration of a class.
     */
	private E startConfig;





	/**
	 * Solver class constructor that initializes the class data member to a
	 * given Clock values, input from the command line.
	 *
	 */
	public Solver(Puzzle<E> p) {
		visited = new HashMap<E,E>();
		this.p =p;
		startConfig =  p.getStart();
		queue = new ArrayList<E>();
		queue.add(startConfig);
	}



	/**
	 * This methods performs the BFS algorithm to find the shortest path to get
	 * to a goal given goal from a given start and maximum input.
	 * 
	 * @return curr, is the a list of the nodes traversed to get to the goal.
	 */

	public ArrayList<E> solve(){

		E curr = null;
		boolean found = false;
		if (p.getGoal(queue.get(0))){
			found =true;
		}
		visited.put(queue.get(0), queue.get(0));

		while( !queue.isEmpty() && found == false){

			curr = queue.remove(0);
			ArrayList<E> ne = p.getNeighbors(curr);
			for( E i : ne){
				if( !visited.containsKey(i)){
					visited.put(i, curr);
					if( p.getGoal(i)){
						curr = i;
						found = true;
						break;
					}else{
						queue.add(i);
					}
				}
			}
		}

		if(found == true){
			ArrayList<E> path = new ArrayList<E>();
			path.add(curr);
			while( path.get(path.size()-1) != startConfig ){
				curr = visited.get(curr);
				path.add(curr);
			}
			
			ArrayList<E> dPath = new ArrayList<E>();
			for( int i = path.size()-1; i> -1; i--)
				dPath.add(path.get(i));
			
			return dPath;
		}
		else
			return new ArrayList<E>();
	}

}
