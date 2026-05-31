import java.util.ArrayList;

/**
 * Queen.java
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
public class Queen extends Pieces{

	public Queen(ArrayList<Integer> pos, String name){
		this.name = name;
		this.pos = pos;
	}

	//another constructor for deepcopy
	public Queen clone(){
		ArrayList<Integer> clonepiece = new ArrayList<Integer>();
		clonepiece.add(pos.get(0));
		clonepiece.add(pos.get(1));
		Queen newqueen = new Queen(clonepiece,name);
		return newqueen;
	}

	public ArrayList<ArrayList<Pieces>> getNeighbors(ArrayList<Pieces> pieceboard, int row, int col){
		ArrayList<ArrayList<Pieces>> tempboard = new ArrayList<ArrayList<Pieces>>();
		ArrayList<Pieces> b1 = deepcopy(pieceboard);
		for(Pieces p: b1){
			if(p.getName().equals("Q")){
				//get row of queen and check all pieces in that row for another queen
				//get col of queen and check all pieces in that row for another queen
				int q1row = p.getPos().get(0);
				int q1col = p.getPos().get(1);
				for(Pieces rowcheck: b1){
					if((rowcheck.getPos().get(0) == q1row) && (rowcheck.getPos().get(1) > q1col)) {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(p.getPos().get(0));
						tmp.add(p.getPos().get(1) - rowcheck.getPos().get(1));
						if(rowcheck.getPos().equals(tmp)){
							b1.remove(rowcheck);
							p.setPos(tmp);
							tempboard.add(b1);
						}
					}
				}
			}
		}
		ArrayList<Pieces> b2 = deepcopy(pieceboard);
		for(Pieces p: b2){
			if(p.getName().equals("Q")){
				//get row of queen and check all pieces in that row for another queen
				//get col of queen and check all pieces in that row for another queen
				int q1row = p.getPos().get(0);
				int q1col = p.getPos().get(1);
				for(Pieces rowcheck: b2){
					if((rowcheck.getPos().get(0) == q1row) && (rowcheck.getPos().get(1) < q1col)) {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(p.getPos().get(0));
						tmp.add(p.getPos().get(1) + rowcheck.getPos().get(1));
						if(rowcheck.getPos().equals(tmp)){
							b2.remove(rowcheck);
							p.setPos(tmp);
							tempboard.add(b2);
						}
					}
				}
			}
		}
		ArrayList<Pieces> b3 = deepcopy(pieceboard);
		for(Pieces p: b3){
			if(p.getName().equals("Q")){
				//get row of queen and check all pieces in that row for another queen
				//get col of queen and check all pieces in that row for another queen
				int q1row = p.getPos().get(0);
				int q1col = p.getPos().get(1);
				for(Pieces rowcheck: b3){
					if((rowcheck.getPos().get(0) < q1row) && (rowcheck.getPos().get(1) == q1col)) {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(p.getPos().get(0) - rowcheck.getPos().get(0));
						tmp.add(p.getPos().get(1));
						if(rowcheck.getPos().equals(tmp)){
							b3.remove(rowcheck);
							p.setPos(tmp);
							tempboard.add(b3);
						}
					}
				}
			}
		}
		ArrayList<Pieces> b4 = deepcopy(pieceboard);
		for(Pieces p: b4){
			if(p.getName().equals("Q")){
				//get row of queen and check all pieces in that row for another queen
				//get col of queen and check all pieces in that row for another queen
				int q1row = p.getPos().get(0);
				int q1col = p.getPos().get(1);
				for(Pieces rowcheck: b4){
					if((rowcheck.getPos().get(0) > q1row) && (rowcheck.getPos().get(1) == q1col)) {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(p.getPos().get(0) + rowcheck.getPos().get(0));
						tmp.add(p.getPos().get(1));
						if(rowcheck.getPos().equals(tmp)){
							b4.remove(rowcheck);
							p.setPos(tmp);
							tempboard.add(b4);
						}
					}
				}
			}
		}
		return tempboard;
	}
}

