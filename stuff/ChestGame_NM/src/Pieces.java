import java.util.ArrayList;

/**
 * Pieces.java
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
public abstract class Pieces {
	
	protected ArrayList<Integer> pos;
	protected String name;
	protected boolean selected = false;
	
	
	public String toString(){
		return (name + ": " + "[" + pos.get(0) + "," + pos.get(1) + "]");
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Integer> getPos(){
		return pos;
	}
	
	public void setTrue(){
		selected = true;
	}
	
	public void setFalse(){
		selected = false;
	}
	
	public boolean getSelected(){
		return selected;
	}
	
	
	//equals and hashcode
	public boolean equals(Object piece){
		Pieces check = (Pieces) piece;
		if(this.getPos() == check.getPos()){
			return true;
		}
		else{return false;}
	}
	
	public abstract Pieces clone();
	
	public ArrayList<Pieces> deepcopy(ArrayList<Pieces> copy){
		ArrayList<Pieces> morepieces = new ArrayList<Pieces>();
		for(Pieces p : copy){
			morepieces.add(p.clone());
		}
		return morepieces;
	}
	
	public void setPos(ArrayList<Integer> setter){
		pos = setter;
	}
	
	public int hashCode(){return this.toString().hashCode();}	//returns tostring of hashcode for current card
	
	//getneighbors arraylist of arraylist checking moves against the curr piece to all other pieces
	//new piece is set to that location if the move is valid then the piece is removed
	
	public abstract ArrayList<ArrayList<Pieces>> getNeighbors(ArrayList<Pieces> piece, int row, int col);
		/*Pieces currpiece = this;
		//need to have all pieces to check current against
		for(Pieces piece2: pieceboard){
			//makes sure they arent the same piece
			if(!currpiece.equals(piece2)){
				if(currpiece.genMoves())){
					currpiece.pos = piece2.pos;
					pieceboard.remove(piece2);
				}
			}
		}
	}*/
	/*
	 * get the coordinates of the pieces
	 * go through each p in the given snapshot
	 * if its me, skip it
	 * look at p's coordinates 
	 */
}
