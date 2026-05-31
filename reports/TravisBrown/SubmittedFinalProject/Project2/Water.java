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


/**
 * represents cell waters on the battleship game
 * @author piter garcia
 *
 */
public class Water extends Cell {

	/*
	 * especial symbol to represent water not hit
	 */
	private char symble = '*';
	
	/*
	 * start column of the water cell
	 */
	private int startCol;
	
	/*
	 * start row of the water cell
	 */
	private int startRow;
	
	/*
	 * time the cell was hit
	 */
	private int hits;
	
	
	
	
	/**
	 * constructor to create a water cell
	 * @param i, row
	 * @param j, column
	 */
	public Water(int i, int j) {
	
		hits = 0;
		startRow = i;
		startCol = j;
	}


	
	
	/**
	 * main method 
	 * @param args, ignored
	 */
	public static void main(String[] args){
		
	}
	
	
	
	/**
	 * increments the time a cell is hit
	 */
	public void hit(){
		hits++;
	}
	
	
	
	/**
	 * gets the time that this cell was hit
	 * @return
	 */
	public int getHits(){
		return hits;
	}
	
	
	
	/**
	 * reset the time this cell was hit
	 */
	public void resetHit(){
		hits = 0;
	}
	
	
	
	/**
	 * string representation of this cell
	 * @return
	 */
	public String toString(){
		
		if(!super.wasHit())
			return "*";
		else
			return "@";
	}

	
	
	/**
	 * false by default since I do not need to
	 * keep track of the water Cell since they
	 * will always be of size one.
	 * @return
	 */
	public boolean wasHit(){
		return false;
	}
	


	/**
	 * returns the start row of this cell
	 * @return start row
	 */
	public int getStartRow() {
		return this.startRow;
	}



	/**
	 * returns the start column of this cell
	 * @return start column
	 */
	public int getStartCol() {
		return this.startCol;
	}

	
	
	/**
	 * checks if the cell is a water cell or not
	 * @param obj, cell 
	 * @return true if it is a water cell
	 */
	public boolean equals(Object obj){
		
		if( obj instanceof Water){
			Water o = (Water)obj;
			if( this.startCol == o.startCol && this.symble == o.startRow)
				return true;
			else 
				return false;
		}
		
		return false;
	}

	
	
	
	/**
	 * compares the cell with another cell 
	 * @param obj, a cell
	 * @return 0 if a cell, rest does not really matter;
	 */
	public int compareTo(Object obj) {

		if( obj instanceof Ship){
			Water o = (Water)obj;
			
			if( this.startCol == o.startCol && this.startRow == o.startRow)
				return 0;
			
			else if( this.startCol < o.startCol && this.startRow < o.startRow)
				return -1;
			
			else
				return 1;
		}
		
		return -1;
	}


	/**
	 * gets the especial character of the wate cell
	 * @return water cell unique character.
	 */
	public char getSign() {
		return symble;
	}
}
