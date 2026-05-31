/*
 * BattleShipModel.java 
 * 
 * Version:
 * $Id: 
 *
 * Revisions:
 * $Log: 
 *
 */

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Observable;

/**
 * Class definition for the model of BattleShip game
 *
 * @author: Piter Garcia
 */
public class BattleShipModel extends Observable {

	/*
	 * contains water Cell 
	 */
	private LinkedList<Cell> water;

	/*
	 * contains the current board
	 */
	private LinkedList<Cell> boardC;

	/*
	 * contains all Cell of all ships
	 */
	private LinkedList<Cell> Cell;

	/*
	 * contains all sink ships
	 */
	private LinkedList<Cell> sinkShips;

	/*
	 * contains updated board in string format
	 */
	private LinkedList<String> updatedBoard;

	/*
	 * contains all ships
	 */
	private LinkedList<Cell> ships;

	/*
	 * access to the BattleShip game
	 */
	private BattleShip battle;





	/**
	 * constructor to initialize the model of the BatleShip Game
	 * @param battle, game
	 */
	public BattleShipModel(BattleShip battle) {

		this.battle 	= battle;
		ships			= new LinkedList<Cell>(battle.getShips());
		water 			= new LinkedList<Cell>(battle.getWater());
		Cell 			= new LinkedList<Cell>(battle.getCell());
		boardC 			= new LinkedList<Cell>(battle.getBoardC());
		sinkShips 		= new LinkedList<Cell>(battle.getSinkShips());
		updatedBoard 	= new LinkedList<String>(battle.getUpdatedBoard());
	}




	/**
	 * This method returns a list of all ships.
	 * 
	 * @return tempCars, a list of ships.
	 */
	public LinkedList<Cell> getShips() {
		return ships;
	}




	/**
	 * this method resets the actual board and Cell
	 */
	public void reset() {

		for( Cell cs: getBoardC()){
			cs.unHit();
			cs.resetHit();
		}

		for( Cell cs: getCell()){
			cs.unHit();
			cs.resetHit();
		}

		for( Cell cs: getShips()){
			cs.unHit();
			cs.resetHit();
		}

		sinkShips.clear();
	}




	/**
	 * checks if a ship is valid or not.
	 *
	 * @param car, car to check for move
	 * 
	 * @return true, if the move is valid.
	 * @return false,  if the move is not valid.
	 */
	public boolean isValid(Ship ship) {

		setChanged();
		notifyObservers();
		return true;
	}




	/**
	 * this methods gives the user a move to reach the goal.
	 * 
	 * @throws FileNotFoundException
	 */
	public void hint () throws FileNotFoundException {

		//		++this.moveCount;
		//		this.setChanged();
		//		this.notifyObservers();
	}



	/**
	 * gets access to the BattleShip game
	 * @return access to the game.
	 */
	public BattleShip getBattle() {
		return battle;
	}



	/**
	 * gets the list of water Cell 
	 * @return water Cell list
	 */
	public LinkedList<Cell> getWater() {
		return water;
	}



	/**
	 * gets the current board
	 * @return current board.
	 */
	public LinkedList<Cell> getBoardC() {
		return boardC;
	}



	/**
	 * gets all Cell of all ships
	 * @return Cell of ships.
	 */
	public LinkedList<Cell> getCell() {
		return Cell;
	}



	/**
	 * gets all sink ships
	 * @return sink ships list
	 */
	public LinkedList<Cell> getSinkShips() {
		return sinkShips;
	}



	/**
	 * gets the updated board in string format
	 * @return string format of the board.
	 */
	public LinkedList<String> getUpdatedBoard() {
		return updatedBoard;
	}


	
	/**
	 * updates all water Cell on the board 
	 * @param waterHit, list of water Cell
	 * @param s, selected water cell
	 * @return a list of updated water Cell.
	 */
	public LinkedList<Water> checkWaterCell(LinkedList<Water>
	waterHit, Cell s) {

		for( Cell s1: getWater()){

			if(s1.equals(s)){
				s1.hits();

				if(s1.hasSink()){
					waterHit.add((Water)s1);
				}
				return waterHit;
			}
		}
		return waterHit;
	}


	
	/**
	 * checks if the user has won the game or not
	 * @return true if a winner.
	 */
	public boolean winner() {

		for(Cell s : getCell()){

			if(!s.wasHit()){
				return false;
			}	
		}
		return true;
	}



	
	/**
	 * checks whether the cell selected has hit ship or not
	 * @param isHint, checks if is a hint move
	 * @param s, cell selected
	 * @return true if it hits a ship what was not hit.
	 */
	public boolean hitAboat(boolean isHint, Cell s) {

		for( Cell s1: getShips()){

			if(s1.equals(s)){
				s1.hits();

				if(isHint)
					s.hit();

				if(s1.hasSink()){
					sinkShips.add((Ship)s1);

					return true;
				}else
					return false;
			}
		}
		return false;

	}



	/**
	 * checks if the cell selected by the user hits a ship cell or a water
	 * cell. If it is a hint, it will loop until a ship is hit.
	 * @param frame, access to the control model view
	 * @param s, cell selected
	 * @param textView, access to the view 
	 * @param isHint, checks if it is a hint or not
	 * @return returns true if it has hit a ship cell.
	 */
	public boolean hitAcell(BattleShipFrame frame, Cell s, BattleShipTextView textView, boolean isHint) {

		//CHECKING IF THE CELL HIT IS A BOAT
		if(getCell().contains(s)){
			textView.tryies();
			textView.hit();

			//HITTING BOAT AND CHECKING IF IT SINK 
			if(hitAboat(isHint, s)){
				//TEXT - status 1
				textView.sink();
				textView.status(getSinkShips().poll(), 1);
			}

		}else{

			if(isHint){
				frame.move(null, isHint);

			}else 
				frame.missHit(s);
		}

		return false;
	}

	
	
	
	/**
	 * updates water hit Cell after missing a hit.
	 * @param s, cell selected
	 * @param waterHit, list of water hit Cell
	 * @param textView, access to the view to update
	 * @return a list of water hit Cell.
	 */
	public LinkedList<Water> missHit(Cell s, 
			LinkedList<Water> waterHit, BattleShipTextView textView) {

		textView.tryies();// CHECKS IF A WATER CELL WAS HIT 
		waterHit = checkWaterCell(waterHit, s);
		textView.status(s, 4); //TEXT - status 4
		return waterHit;
	}


	
	
	/**
	 * checks if the selected cell was already hit or not. if is a hint it 
	 * will loop until a ship cell is found or the user wins. Otherwise, 
	 * it will either hit a ship or miss. 
	 * @param frame, fame to access control
	 * @param isHint, checks whether is a hint or not
	 * @param textView, view to access and update
	 * @return true if the cell was already hit and if it is not a hint move.
	 */
	public boolean alreadyHit(BattleShipFrame frame, boolean isHint, BattleShipTextView textView) {

		if(isHint){
			frame.move(null, isHint);

		}else{
			textView.tryies(); //TEXT - status 3
			return true;
		}
		return false;
	}
}
