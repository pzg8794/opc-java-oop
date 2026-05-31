import java.util.ArrayList;

/**
 * Bishop.java
 *
 * Version:
 * $Id$
 *
 * Revision:
 * $Log$
 */

/**
 * @author Raelynn
 *
 */
public class Bishop extends Pieces{

	private ArrayList<Integer> pos;
	private String name;
	
	public Bishop(ArrayList<Integer> pos, String name){
		this.name = name;
		this.pos = pos;
	}


	public ArrayList<ArrayList<Pieces>> getNeighbors(ArrayList<Pieces> piece){
		return null;
	}
	//isvalid checks the move
	//deepcopy used in get neighbors
	//checks move based on piece
}
