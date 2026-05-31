/*
 * Ship.java 
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
 * This class represents a ship
 * @author piter garcia
 *
 */
public class Ship extends Cell {

	/*
	 * number of time a ship was hit
	 */
	private int hits;

	/*
	 * especial character of a ship
	 */
	private char ship;

	/*
	 * size of the ship
	 */
	private int size;

	/*
	 * checks if the ship is vertical or not
	 */
	private boolean vertical;
	
	/*
	 * counts the number of time a ship is added
	 */
	private static int line = 0;

	/*
	 * start column of a ship
	 */
	private int startCol;
	
	/*
	 * start row of a ship
	 */
	private int startRow;
	
	/*
	 * unique id of the ship
	 */
	private static int uniqueId;
	
	/*
	 * list of all ship Cell created.
	 */
	private static LinkedList<Cell> checkList = new LinkedList<Cell>();
	
	/*
	 * list of all Cell of a ship
	 */
	private LinkedList<Cell> Cell;
	
	/*
	 * list of unique id assign to each ship accordingly
	 */
	private char[] id = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'};


	/**
	 * constructor to create a ship
	 * @param row, start row
	 * @param col, star column
	 * @param row1, end row
	 * @param col1, end column
	 * @throws MyException, thrown if ship already has another ship location
	 */
	public Ship(int row, int col, int row1, int col1) throws MyException {
		int size = 0;
		++line;
		Cell = new LinkedList<Cell>();
		int start = 0;
		if( (row != row1) && (col == col1)){
			vertical = false;

			if( row < row1){
				size = (row1 - row)+1;
				start = row;

			}else{
				size = (row - row1)+1;
				start = row1;
			}

			shipType(size);
			while( this.getCell().size() != size){

				Ship tempShip = new Ship(start++, col, vertical, size, ship);

				if( overLaps(tempShip))
					throw new MyException(line);

				else{
					checkList.add(tempShip);
					Cell.add(tempShip);
				}
			}

		}else if( (col != col1) && (row == row1)){

			vertical = true;

			if( col < col1){
				size = (col1 - col)+1;
				start = col;

			}else{
				size = (col - col1)+1;
				start = col1;
			}
			shipType(size);

			while( this.getCell().size() != size){
				Ship tempShip = new Ship(row, start++, vertical, size, ship);

				if( overLaps(tempShip))
					throw new MyException(line);

				else{
					checkList.add(tempShip);
					Cell.add(tempShip);
				}
			}

		}else{
			throw new MyException(line);
		}

	}

	
	
	
	/**
	 * checks if a ship overlaps with another ship location
	 * @param tempShip, current ship being created
	 * @return true if it has another ship's location
	 */
	private boolean overLaps(Ship tempShip) {

		LinkedList<Cell> tempList = new LinkedList<Cell>(checkList);

		for(int i=0; i< tempList.size(); i++){
			Cell cl = tempList.poll();
			if( (cl.getStartCol() == tempShip.getStartCol()) && 
					(cl.getStartRow() == tempShip.getStartRow())){
				return true;
			}	
		}
		return false;
	}

	
	
	
	/**
	 * assigns unique id and size of the ship
	 * @param abs, size of the ship
	 */
	private void shipType(int abs) {

		size = abs;
		ship = id[uniqueId++];
	}

	
	
	
	/**
	 * creates a cell of a ship
	 * @param i, start row
	 * @param col, start column
	 * @param vertical, whether is vertical or not
	 * @param size, size of the ship
	 * @param ship2, especial character
	 */
	public Ship(int i, int col, boolean vertical, int size, char ship2) {
		startRow = i;
		startCol = col;
		this.size = size;
		this.vertical = vertical;
		this.ship = ship2;
	}


	
	
	/**
	 * string representation of the ship
	 */
	public String toString(){
		if(!super.wasHit())
			return ship+"";
		else{
			return "#";
		}
	}

	
	
	
	/**
	 * gets all Cell of a ship
	 */
	public LinkedList<Cell> getCell(){
		return Cell;
	}

	
	/**
	 * gets the start row of a ship cell
	 */
	public int getStartRow() {
		return startRow;
	}


	/**
	 * gets the start column of a ship cell
	 */
	public int getStartCol() {
		return startCol;
	}

	
	
	
	/**
	 * checks whether two ships belong to the same
	 * ship or not
	 */
	public boolean equals(Object obj){

		if( obj instanceof Ship){
			Ship tp = (Ship)obj;
			if( this.ship == tp.ship)
				return true;
			else 
				return false;
		}

		return false;
	}

	
	
	
	/**
	 * reset the ship
	 */
	public void resetHit() {
		hits = 0;
	}

	
	
	
	/**
	 * hits the ship
	 */
	public void hits(){

		if(hits != size)
			hits++;
	}


	
	
	/**
	 * checks whether the ship has sink or not
	 */
	public boolean hasSink(){
		return hits == size;
	}


	
	
	/**
	 * compares two ships
	 */
	public int compareTo(Object obj) {

		if( obj instanceof Ship){
			Ship o = (Ship)obj;

			if( this.ship == o.ship )
				return 0;

			else if( this.ship < o.ship)
				return -1;

			else
				return 1;
		}

		return -1;
	}


	
	
	
	/**
	 * gets the especial character of the ship
	 */
	public char getSign() {

			return ship;
	}
	
	
	
	/**
	 * gets the size of the ship
	 */
	public int getSize() {
		return size;
	}
}
