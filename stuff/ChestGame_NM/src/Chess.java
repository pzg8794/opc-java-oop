/**
 * Chess.java
 *
 * Version:
 * $Id$
 *
 * Revision:
 * $Log$
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Raelynn
 *
 */
public class Chess extends Observable implements Puzzle<ArrayList<Pieces>>{

	private boolean goal;
	private ArrayList<Integer> pos;
	private ArrayList<Integer> start;
	private ArrayList<String> turtles;
	protected static int row;
	protected static int col;
	private char[][] board;
	private ArrayList<Pieces> pieceboard;
	private ArrayList<Pieces> modelboard;

	
	public Chess(ArrayList<String> fi){
		this.turtles = fi;
		this.row = Integer.parseInt(String.valueOf(turtles.get(0).charAt(0)));
		this.col = Integer.parseInt(String.valueOf(turtles.get(0).charAt(1)));
		this.board = new char[row][col];
		this.pieceboard = new ArrayList<Pieces>();
		turtles.remove(0);
		int rowcount = 0;
		
		for(String line: turtles){
				for(int x = 0; x < col; x++){
					if(line.charAt(x) == ('B')){
						ArrayList<Integer> where = new ArrayList<Integer>();
						where.add(turtles.indexOf(line));
						where.add(x);
						Pieces bishop = new Bishop(where,"B");
						pieceboard.add(bishop);
						board[rowcount][x] = 'B';
					}
					else if(line.charAt(x) == ('K')){
						ArrayList<Integer> where = new ArrayList<Integer>();
						where.add(turtles.indexOf(line));
						where.add(x);
						Pieces king = new King(where,"K");
						pieceboard.add(king);
						board[rowcount][x] = 'K';
					}
					else if(line.charAt(x) == ('N')){
						ArrayList<Integer> where = new ArrayList<Integer>();
						where.add(turtles.indexOf(line));
						where.add(x);
						Pieces knight = new Knight(where,"N");
						pieceboard.add(knight);
						board[rowcount][x] = 'N';
					}
					else if(line.charAt(x) == ('P')){
						ArrayList<Integer> where = new ArrayList<Integer>();
						where.add(turtles.indexOf(line));
						where.add(x);
						Pieces pawn = new Pawn(where,"P");
						pieceboard.add(pawn);
						board[rowcount][x] = 'P';
					}
					else if(line.charAt(x) == ('R')){
						ArrayList<Integer> where = new ArrayList<Integer>();
						where.add(turtles.indexOf(line));
						where.add(x);
						Pieces rook = new Rook(where,"R");
						pieceboard.add(rook);
						board[rowcount][x] = 'R';
					}
					else if(line.charAt(x) == ('Q')){
						ArrayList<Integer> where = new ArrayList<Integer>();
						where.add(turtles.indexOf(line));
						where.add(x);
						Pieces queen = new Queen(where,"Q");
						pieceboard.add(queen);
						board[rowcount][x] = 'Q';
					}
					else if (line.charAt(x) == ('.')){
						board[rowcount][x] = '.';
					}
				}
				rowcount++;
			}
		this.modelboard = deepcopy(pieceboard);
		}
	
	//public Pieces clone();
	
	public ArrayList<Pieces> deepcopy(ArrayList<Pieces> copy){
		ArrayList<Pieces> morepieces = new ArrayList<Pieces>();
		for(Pieces p : copy){
			morepieces.add(p.clone());
		}
		return morepieces;
	}

	public static ArrayList<String> getfile(String[] args){
        
		BufferedReader inputStream = null;
		String line;
		ArrayList<String> cats = new ArrayList<String>();
        
        try {
            inputStream = new BufferedReader(new FileReader(new File(args[0])));
            try{
            	while((line = inputStream.readLine()) != null){
            		line = line.replaceAll("\\s","");
            		cats.add(line);
            	}
            }catch( IOException ioe ) {
                System.out.println( ioe.getMessage() );
            }
        }
        catch( FileNotFoundException fnfe ) {
            System.out.println( "{input-file} not found." );
        }
        finally {
            try {
                if ( inputStream != null ) inputStream.close();
            }
            catch( IOException ioe ) {
                System.out.println( ioe.getMessage() );
            }
        }
        return cats;
    }
	
	public ArrayList<Pieces> getStart() {
		return null;
	}


	public boolean getGoal(ArrayList<Pieces> pieces) {
		if(pieces.size() == 1){
			return true;
		}
		else{
			return false;
		}
	}

	public ArrayList<ArrayList<Pieces>> getNeighbors(ArrayList<Pieces> pieces) {
		/**
		 * go through all of the pieces in the arraylist of pieces, and for each one, generate its neighbors
		 * returns arraylist of arraylist of pieces (neighbors)
		 */
		ArrayList<ArrayList<Pieces>> neighbors = new ArrayList<ArrayList<Pieces>>();
		
		for (Pieces piece : pieces) {
			neighbors.addAll( piece.getNeighbors(pieces,row,col) );
		}
		//makes huge list of all neighbors for all pieces addAll to a neighbors list
		return neighbors;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> making = getfile(args);
		boolean done = false;
		Chess game = new Chess(making);
		Solver<ArrayList<Pieces>> chessgame = new Solver<ArrayList<Pieces>>();
		ArrayList<ArrayList<Pieces>> solution = chessgame.Solved(game);
		if(solution != null){
			System.out.println(":)");
			done = true;
		}
		else{
			System.out.println("No Solution");
		}
	}
	
	// **********model
	public void selectPiece(ArrayList<Integer> position){
		for(Pieces s: modelboard){
			if(s.getPos().equals(position)){
				s.setTrue();
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public boolean isValid(boolean done){
		if(done == true){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void move(ArrayList<Integer> position){
		for(Pieces s: modelboard){
			if(s.getSelected()){
				if(s.isValid()){
					s.setPos(position);
			}
		}
		setChanged();
		notifyObservers();
	}
}
