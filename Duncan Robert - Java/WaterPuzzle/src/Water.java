/*
 * Water.java 
 * 
 * Version:
 * $Id: 
 *
 * Revisions:
 * $Log:
 *
 */
import java.util.ArrayList;

/** 
 * Water.java is the class responsible to provide configurations to the
 * solver, as the solver demands them, to find from a list of jugs the
 * the right jug that contains the goal.
 * 
 * @author Piter Garcia
 */
public class Water extends Puzzle<Config> {

    /*
     * contains a list of jugs
     */
	protected static Config jugs;
	
    /*
     * contains an integer value for the goal.
     */
	protected static int goal;

	
	
	/**
     * Water constructor, initializes the list of jugs and the goal.
     */
	public Water(Config jugs, int goal) {
		super(jugs);
		Water.goal = goal;
		Water.jugs = jugs;
	}



	/**
     * Water default constructor.
     */
	public Water() {
		super();
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
	public static void main( String[] args){
		
		if( args.length < 2){
		
			System.out.println("Usage: java Water amount jug1 jug2 ...");
		
		}else{
			
			int goal = Integer.parseInt(args[0]);
			ArrayList<Jugs> jug = new ArrayList<Jugs>();
			
			for (int i=1;i<args.length;i++){
				jug.add(new Jugs(Integer.parseInt(args[i])));
			}
			Config c = new Config(jug);
			Water w = new Water(c, goal);
			
			Solver<Config> s = new Solver<Config>(w);
			ArrayList<Config> solve = s.solve();
	
			if ( solve.isEmpty()){
				System.out.println("No solution.");
			}else{
				for (int i = 0 ; i < solve.size() ; i++){
					String step = "Step " + (i) + ": " + solve.get(i);
					String nextStep = step.substring(0, step.length()-1);
					System.out.println(nextStep);
				}
			}
		}

	}
	
	
	
	
	/**
     * Checks and validates whether the input time is correct before the
     *  solver starts solving the water puzzle.
     *
     * @param args, parameters string value input from the command line that 
     * contain the clock information such as maximum, start, and goal hour.
     * @return true, it returns true if is valid. 
     * @return false,it returns true if is invalid. 
     */


	
	
	/**
     * Gets the Goal time given by the user in the command line and returns it.
     *
     * @return goalTime, this is the goal hour our to be reached by 
     * the solver class.
     */
	public boolean getGoal(Config config) {	
		for( int i = 0; i < config.getJugs().size(); i++){
			if( config.getJugs().get(i).getGallons() == goal){
				return true;
			}
		}
		return false;
	}




	/**
	 * This methods obtains the neighbors of a list of jugs and it saves
	 * it in an ArrayList so the Solver class can use them to keep finding
	 * more neighbors until the goal is reached.
	 *
	 * @param  config, a list of jugs, config type, used from the Solver.class
	 *  to obtain its neighbor and compare till goal is reached.
	 * @return list, an ArrayList that contains config type values which is a
	 * list of jugs.
	 */
	public ArrayList<Config> getNeighbors(Config config ) {

		Jugs temp = null;
		ArrayList<Config>list = new ArrayList<Config>();

		for( int i = 0; i< config.getJugs().size(); i++){
			
			//filling jugs.
			if(!config.getJugs().get(i).isFull()){
				Config copy = config.configClone();
				temp = copy.getJugs().get(i);
				temp.fill();
				list.add(copy);
			}
			
			//dumping jugs.
			if(!config.getJugs().get(i).isEmpty()){
				Config copy = config.configClone();
				temp = copy.getJugs().get(i);
				temp.dump();
				list.add(copy);
			}
	
			//if pouring water onto other jugs if they are not empty or full.
			for( int j = 0; j< config.getJugs().size(); j++){
				if(!config.getJugs().get(i).isEmpty() && 
						!config.getJugs().get(j).isFull() && i != j){
					Config copy = config.configClone();
					temp = copy.getJugs().get(i);
					Jugs nextJug =  copy.getJugs().get(j);
					temp.transfer(nextJug);
					list.add(copy);
				}
			}
		}

		return list;
	}
}
