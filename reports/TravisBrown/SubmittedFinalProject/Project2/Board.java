/*
 * Board.java
 *
 * Version:
 *     $Id: Board.java,v 1.1 2013/07/23 23:02:23 pzg8794 Exp $
 *
 * Revisions:
 *     $Log: Board.java,v $
 *     Revision 1.1  2013/07/23 23:02:23  pzg8794
 *     Lab7
 *
 */

import java.util.LinkedList;


/**
 * This class simulates a board for the battleship game.
 * @author piter garcia
 *
 */
public class Board {

	/*
	 * size of the board
	 */
	private int size;
	
	/*
	 * board on screen, updated board in integers.
	 */
	private int[][]currentBoard;
	
	/*
	 * a temporary board to check for updates
	 */
	private LinkedList<Cell> tempboardC;
	
	/*
	 * the updated board - string representation of the current board.
	 */
	private LinkedList<String> updatedBoard = new LinkedList<String>();
	
	
	
	
	/**
	 * Constructor to initialize the board with a given size <= 5 or >=20
	 * @param size
	 */
	public Board(int size){
		
		currentBoard = new int [size][size];
		
		for( int i=0; i < size; i++){
			for( int j=0; j<size; j++)
				currentBoard[i][j] = -1;
		}
		
		this.size = size;
	}

	
	
	
	/**
	 * displaying board
	 */
	public void showBoard() {


		for( int i=-1; i < size; i++){
			for( int j=0; j<size; j++){
					
					if( i == -1)
						
						if( j < 10)
							System.out.print("  " + "0"+j);
						else{
							System.out.print("  " + j);
						}
					
					else{
						
						if( j == 0){
							
							if(i < 10){
								System.out.print("0"+i + " ~" + "  ");
							}else{
								System.out.print(i + " ~" + "  ");
							}
						}
						else{
							System.out.print(" ~" + "  ");
						}
					}
			}
			System.out.println();
		}
	}
	
	
	
	
	/**
	 * main calls constructor to add size - this is only for testing
	 * real game is played in the BattleShip class.
	 * @param args, are ignored in this class
	 */
	public static void main(String[] args){
		

		Board board = new Board(10);
		board.showBoard();
		
		
	}

	
	
	
	/**
	 * Shows the current board, displaying only the ships hit.
	 * @param boardC, Cell represation of the board
	 */
	public void showBoard(LinkedList<Cell> boardC) {
		tempboardC = new LinkedList<Cell>(boardC);
		
		for( int i=-1; i < size; i++){
			for( int j=0; j< size; j++){

				if( i == -1){

					if( j < 10){
						System.out.print("  " + "0"+j);
						
					}else{
						System.out.print("  " + j);
					}

				}else{

					if( j == 0){

						if(i < 10){
							
							if( tempboardC.poll().wasHit() ){
								System.out.print("0"+ i + " " + '#' + "   ");
								updatedBoard.add("#");
							}else{
								System.out.print("0"+ i + " " + "~" + "   ");
								updatedBoard.add("~");
							}
						}
						else{
							
							if( tempboardC.poll().wasHit() ){
								System.out.print("0"+ i + " " + '#' + "   ");
								updatedBoard.add("#");
							}else{
								System.out.print("0"+ i + " " + "~" + "   ");
								updatedBoard.add("~");
							}
						}
					}
					else{
						
						if( tempboardC.poll().wasHit() ){
							System.out.print('#' + "   ");
							updatedBoard.add("#");
						}else{
							System.out.print("~" + "   ");
							updatedBoard.add("~");
						}
					}
				}
			}
			System.out.println();
		}
		
	}

	
	
	
	/**
	 * Display an updated board, but this ones returns elements as strings.
	 * @param boardC, string representation of the board.
	 * @return
	 */
	public LinkedList<String> updatedBoard(LinkedList<Cell> boardC) {
		updatedBoard.clear();
		tempboardC = new LinkedList<Cell>(boardC);
		
		for( int i=-1; i < size; i++){
			for( int j=0; j< size; j++){

				if( i == -1){

					if( j < 10){
						System.out.print("  " + "0"+j);
						
					}else{
						System.out.print("  " + j);
					}

				}else{

					if( j == 0){

						if(i < 10){
							
							if( tempboardC.poll().wasHit() ){
								System.out.print("0"+ i + " " + '#' + "   ");
								updatedBoard.add("#");
							}else{
								System.out.print("0"+ i + " " + "~" + "   ");
								updatedBoard.add("~");
							}
						}
						else{
							
							if( tempboardC.poll().wasHit() ){
								System.out.print("0"+ i + " " + '#' + "   ");
								updatedBoard.add("#");
							}else{
								System.out.print("0"+ i + " " + "~" + "   ");
								updatedBoard.add("~");
							}
						}
					}
					else{
						
						if( tempboardC.poll().wasHit() ){
							System.out.print('#' + "   ");
							updatedBoard.add("#");
						}else{
							System.out.print("~" + "   ");
							updatedBoard.add("~");
						}
					}
				}
			}
			System.out.println();
		}
		
		return updatedBoard;
	}
	
	
	
	/**
	 * gets the updated board.
	 * @return
	 */
	public LinkedList<String> getUpdatedBoard() {
		return updatedBoard;
	}
}
