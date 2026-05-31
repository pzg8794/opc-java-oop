/*
 * Cells.java 
 * 
 * Version:
 * $Id: 
 *
 * Revisions:
 * $Log: 
 *
 */

import java.util.LinkedList;

/**
 * a cell representation of the ship and water cells
 * @author piter garcia
 *
 */
public abstract class Cells implements Comparable<Object>{

	/*
	 * checks whether the cell was hit or not
	 */
	private boolean hit = false;
	
	
	
	
	/**
	 * constructor
	 */
	public Cells(){
		
	}
	
	
	
	/**
	 * gets the size of the cell
	 * @return sizee of the cell
	 */
	public int getSize(){
		return 0;
	}
	
	
	
	/**
	 * increments hits
	 */
	public void hits(){
	}
	
	
	
	/**
	 * obtains all hits done to the cell
	 * @return
	 */
	public int  getHits(){
		return 0;
	}
	
	
	
	
	/**
	 * checks if the cell(ship) has sink.
	 * @return true if it has sink
	 */
	public boolean hasSink(){
		return false;
	}
	
	
	
	
	/**
	 * sets hit to true
	 */
	public void hit(){
		hit = true;
	}
	
	
	
	
	/**
	 * 
	 */
	public void unHit(){
		hit = false;
	}
	
	
	
	
	/**
	 * checks whether the cell was hit or not
	 * @return true if it was hit.
	 */
	public boolean wasHit(){
		return hit;
	}
	
	
	
	/**
	 * gets unique character of the cell
	 * @return unique character
	 */
	public abstract char getSign();
	
	
	
	/**
	 * gets a list of all cells of all ships
	 * @return list of cells of ships
	 */
	public LinkedList<Cells> getCells(){
		return null;
	}
	
	
	
	/**
	 * gets the start row
	 * @return start row
	 */
	public abstract int getStartRow();
	
	
	
	/**
	 * gets the start column
	 * @return start column
	 */
	public abstract int getStartCol();
	
	
	
	/**
	 * resets hit cell 
	 */
	public void resetHit() {
		
	}
}
