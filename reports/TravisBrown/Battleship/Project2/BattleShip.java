/*
 * BattleShip.java
 *
 * Version:
 *     $Id: BattleShip.java,v 1.1 2013/07/23 23:02:23 pzg8794 Exp $
 *
 * Revisions:
 *     $Log: BattleShip.java,v $
 *     Revision 1.1  2013/07/23 23:02:23  pzg8794
 *     Lab7
 *
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;



/**
 * 
 * @author piter garcia
 *
 */
public class BattleShip {

	/*
	 * board containing water and ship Cell
	 */
	private Board board;

	/*
	 * size of the board
	 */
	private static int boardSize;

	/*
	 * tagles if all ships have been hit
	 */
	private static boolean allHit;


	/*
	 * scanner to read from input file
	 */
	private static Scanner in = null;


	/*
	 * contains all ships
	 */
	private static LinkedList<Cell> ships;

	/*
	 * contains all the Cell forming all ships
	 */
	private static LinkedList<Cell> Cell;

	/*
	 * contains all water Cell 
	 */
	private static LinkedList<Cell> waterC;

	/*
	 * current board
	 */
	private static LinkedList<Cell> boardC;


	/*
	 * board updated after a hit
	 */
	private static LinkedList<String> updatedBoard;

	/*
	 * scanner to scan input file 
	 */
	private static Scanner in1 = new Scanner(System.in);

	/*
	 * contains all ships sink.
	 */
	private static LinkedList<Ship> sinkShips = new LinkedList<Ship>();




	/**
	 * constructor to initialize the board with a given size
	 * @param size
	 */
	public BattleShip(int size){
		boardC = new LinkedList<Cell>();
		board = new Board(size);
		boardSize = size;
	}




	/**
	 * initialize board according to the give specs
	 * @param Cell2
	 */
	public void initBoard (LinkedList<Cell> Cell2){

		waterC = new LinkedList<Cell>();
		boolean match = false;

		for( int i=0; i< boardSize; i++){

			for( int j = 0; j< boardSize; j++){

				for( Cell s: Cell){

					if( s.getStartRow() == i && s.getStartCol() == j){
						boardC.add(s);
						match = true;
						break;
					}else{
						match = false;
					}
				}

				if(!match){
					Water water = new Water(i, j);
					waterC.add(water);
					boardC.add(water);
				}
			}
		}

		System.out.println(boardC);
	}




	/**
	 * displays the hidden board
	 */
	public void showBoard (){

		LinkedList<Cell> tempboardC = new LinkedList<Cell>(boardC);
		for( int i=-1; i < boardSize; i++){
			for( int j=0; j< boardSize; j++){

				if( i == -1)

					if( j < 10)
						System.out.print("  " + "0"+j);
					else
						System.out.print("  " + j);

				else{

					if( j == 0){

						if(i < 10)
							System.out.print("0"+ i + " " + tempboardC.poll() + "   ");
						else
							System.out.print(i + " " + tempboardC.poll() + "   ");

					}
					else
						System.out.print(tempboardC.poll()  + "   ");
				}
			}
			System.out.println();
		}
	}




	/**
	 * initiates all ships, creates them and add them to the board.
	 * If any of the ships over-bounds the size of the board, or overlaps 
	 * with another ship cell, or size is equal or less than one the 
	 * initialization stops and informs user of the any of the above spefic
	 * errors. 
	 * @param in, scanner containing ships locations
	 * @throws MyException, it is thrown if any of the above cases take place
	 */
	public void initShip (Scanner in) throws MyException {

		ships = new LinkedList<Cell>();

		int line = 0;
		while(in.hasNext()){
			++line;

			int row = in.nextInt();
			int col = in.nextInt();
			int row1 = in.nextInt();
			int col1 = in.nextInt();

			if( row >= boardSize || col >= boardSize)
				throw new MyException(line);

			if( row1 >= boardSize || col1 >= boardSize)
				throw new MyException(line);

			ships.add(new Ship(row, col, row1, col1));
		}

		LinkedList<Cell> allShips = new LinkedList<Cell>(ships);
		Cell = new LinkedList<Cell>();

		for(Cell s: allShips){

			while(s.getCell().size() != 0)
				Cell.add(s.getCell().poll());
		}
		System.out.println(Cell + "\n");
	}




	/**
	 * Not for the GUI implementation, this methods shoots a 
	 * Cell and updates board
	 * @param s, cell hit by user.
	 */
	public void shoot (Cell s) {

		//CHECKING IF THE CELL HIT IS A BOAT
		if(Cell.contains(s)){
			System.out.println("Contained");

			//HITTING BOAT AND CHECKING IF IT SINK
			for( Cell s1: ships){

				if(s1.equals(s)){
					s1.hits();

					if(s1.hasSink()){
						System.out.println("The Ship "+ s1 + " Has Sink");
						sinkShips.add((Ship)s1);
					}
					break;
				}
			}

		}else{
			System.out.println("Missed");
		}
	}




	/**
	 * skip hit if the user already hit such cell
	 * @param skip, true if the cell was already hit
	 * @return, false because is making sure a cell 
	 * is not repeated again.
	 */
	public boolean shotHit(boolean skip){

		if(!skip){

			for(Cell s : Cell){

				System.out.println(s.wasHit());
				if(!s.wasHit()){
					System.out.println(s.wasHit());
					allHit = false;
					break;
				}	
			}

		}else{
			System.out.println("Try Another Cell");
			allHit = false;
		}

		return false;
	}




	/**
	 * main test the size of the board, where the size must >=5 and
	 * >= 20. Also, it test if input file was given, if not it let
	 * the user know so action can be take. If the program is not able
	 * to find the file, then it also informs the user so action can be
	 * take. Finally, if the file is empty, user is informed as well. 
	 * All exceptions are caught here in main, all cases explained above
	 * and previously are caught here in the main.
	 * @param args
	 */
	public static void main(String[] args){

		// READING INPUT FILE
		String fileName = testInput(args);

		try {
			in = new Scanner( new File( fileName ) );
		}
		catch ( FileNotFoundException e ) {

			System.err.println( "Unable to open " + fileName + " for reading." );
			System.exit(0);
			// Terminate the program here somehow, or see below.
		}

		if(!in.hasNext()){
			System.err.println( "File Empty" );
			System.exit(0);
		}

		int size = in.nextInt();


		if( size < 5 || size > 20 ){
			System.out.println("Wrong Size!\nBoard Size must be >= 5 or <=20");
			System.exit(0);
		}

		//INITIALIZING BOARD SIZE
		BattleShip battle = new BattleShip(size);

		//INITIALIZING SHIPS
		System.out.println("ALL SHIPS:");
		try {
			battle.initShip(in);
		} catch (MyException e) {
			System.out.println(e);
			System.exit(0);
		}

		//INITIALIZING ENTIRE BOARD
		System.out.println("All Ships and Water:");
		battle.initBoard(Cell);

		//SHOWING ENTIRE GAME BOARD 
		System.out.println("\nComplete Board:");
		battle.showBoard();

		//SHOWING DISGUISE BOARD
		Board board = new Board(boardSize);
		System.out.println("\nComplete Disguised Board:");
		board.showBoard();
		updatedBoard = board.updatedBoard(boardC);

		BattleShipModel model = new BattleShipModel(battle);
		BattleShipFrame frame = new BattleShipFrame(model);
		frame.setTitle("BattleShip By Piter Zac. Garcia ");


		//PLAYING GAME
		battle.game(battle);
	}





	/**
	 * Method to play the game on the console window only, not GUI.
	 * @param battle
	 */
	private void game(BattleShip battle) {

		//LIST OF SHIPS
		sinkShips = new LinkedList<Ship>();
		allHit = true;
		int r = 0, c = 0;
		int tries = 0;

		do{

			//PLAYING GAME
			System.out.print("\n Please Enter Row: ");
			r = in1.nextInt();

			System.out.print(" Please Enter Colum: ");
			c = in1.nextInt();

			tries++;
			allHit =true;
			boolean skip = false;

			//HITTING CELL GIVEN BY PLAYER
			for( Cell s: boardC){

				if(s.getStartRow() == r && s.getStartCol() == c){
					System.out.println("\nSearching Board");


					//CHECKING IF THE CELL WAS ALREADY HIT
					if(!s.wasHit()){
						System.out.println("HIT");
						s.hit();

						battle.shoot(s);
						break;

					}else{

						System.out.println("This Cell was Already Hit");
						skip = true;
						break;
					}
				}
			}
			battle.shotHit(skip);

			System.out.println("\nComplete Board:");
			battle.showBoard();

			System.out.println("\nComplete Disguised Board:");
			board.showBoard(boardC);

		}while(!allHit);

		System.out.println("Number of Tries: " + tries);

	}




	/**
	 * Test input string args[0], to make sure that the program
	 * is invoked properly. If args[0] does not contain .txt it
	 * adds it to the file, if no file name is found then it
	 * returns an error.
	 * 
	 * @param string
	 * @return
	 */
	private static String testInput(String[] args) {

		if(args[0].length() == 0){

			System.err.println( "Usage: java Battleship config-file" );
			System.exit(0);
			// Terminate the program here somehow, or see below.
		}
		return args[0];
	}




	/**
	 * gets the board 
	 * @return
	 */
	public Board getBoard() {
		return board;
	}


	/**
	 * gets the size of the board.
	 * @return size of the board.
	 */
	public int getSize() {
		return boardSize;
	}



	/**
	 * gets the updated board
	 * @return board in string format.
	 */
	public LinkedList<String> getUpdatedBoard() {
		return updatedBoard;
	}
	



	/**
	 * resets the board to the initialized board
	 * @param resetBoard
	 */
	public void setShips(LinkedList<Cell> resetBoard) {
		ships = resetBoard;

	}

	



	/**
	 * gets the current board
	 * @return a cell representation of the board.
	 */
	public LinkedList<Cell> getBoardC() {
		return boardC;
	}



	/**
	 * gets a list of all Cell that form all ships
	 * @return cell represtion of all ships.
	 */
	public LinkedList<Cell> getCell() {
		return Cell;
	}



	/**
	 * gets all ships that sink
	 * @return list of ships that sink.
	 */
	public LinkedList<Ship> getSinkShips() {
		return sinkShips;
		//YOU MIGHT NOT NEED THIS
	}



	/**
	 * gets all Cell that are water on the board.
	 * @return list of water Cell on the board
	 */
	public LinkedList<Cell> getWater() {
		return waterC;
	}



	/**
	 * gets all ships on the board.
	 * @return list of ships Cell on the board
	 */
	public LinkedList<Cell> getShips() {
		return ships;
	}

}
