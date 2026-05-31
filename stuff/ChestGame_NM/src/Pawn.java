import java.util.ArrayList;

/**
 * Pawn.java
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
public class Pawn extends Pieces{

	public Pawn(ArrayList<Integer> pos, String name){
		this.name = name;
		this.pos = pos;
	}

	//another constructor for deepcopy
	public Pawn clone(){
		ArrayList<Integer> clonepiece = new ArrayList<Integer>();
		clonepiece.add(pos.get(0));
		clonepiece.add(pos.get(1));
		Pawn newpawn = new Pawn(clonepiece,name);
		return newpawn;
	}
	

	public ArrayList<ArrayList<Pieces>> getNeighbors(ArrayList<Pieces> pieceboard,int row,int col){
		//still need to check if within dimension
		//while r, c isn't off the board
			// is someting at r, c?
			// do stuff maybe
				//if so, quit this loop
				// else, r = r - 1, c = c - 1...
		ArrayList<ArrayList<Pieces>> tempboard = new ArrayList<ArrayList<Pieces>>();
		int r = row;
		int c = col;
		ArrayList<Pieces> b1 = deepcopy(pieceboard);
		for(Pieces p: b1){
			if(p.getName().equals("P")){
				if((p.getPos().get(0) + 1 <= r) && (p.getPos().get(1) + 1 <= c)){
					for(Pieces q: b1){
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(p.getPos().get(0) + 1);
						tmp.add(p.getPos().get(1) + 1);
						if(q.getPos().equals(tmp)){
							b1.remove(q);
							p.setPos(tmp);
							tempboard.add(b1);
						}
					}
				}
				
			
			}
		}
		ArrayList<Pieces> b2 = deepcopy(pieceboard);
		for(Pieces p: b2){
			if(p.getName().equals("P")){
				if((p.getPos().get(0) + 1 <= row) && (p.getPos().get(1) - 1 <= col)){
					for(Pieces q: b2){
						ArrayList<Integer> tmp2 = new ArrayList<Integer>();
						tmp2.add(p.getPos().get(0) + 1);
						tmp2.add(p.getPos().get(1) + 1);
						if(q.getPos().equals(tmp2)){
							b2.remove(q);
							p.setPos(tmp2);
							tempboard.add(b2);
						}
					}
				}
			}
		}
		return tempboard;
	}
}
