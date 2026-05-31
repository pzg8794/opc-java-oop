import java.util.ArrayList;

/**
 * Knight.java
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
public class Knight extends Pieces{

	public Knight(ArrayList<Integer> pos, String name){
		this.name = name;
		this.pos = pos;
	}
	
	//another constructor for deepcopy
	public Knight clone(){
		ArrayList<Integer> clonepiece = new ArrayList<Integer>();
		clonepiece.add(pos.get(0));
		clonepiece.add(pos.get(1));
		Knight newknight = new Knight(clonepiece,name);
		return newknight;
	}
	
	public ArrayList<ArrayList<Pieces>> getNeighbors(ArrayList<Pieces> pieceboard, int row, int col){
		ArrayList<ArrayList<Pieces>> tempboard = new ArrayList<ArrayList<Pieces>>();
		int r = row;
		int c = col;
		ArrayList<Pieces> b1 = deepcopy(pieceboard);
		for(Pieces p: b1){
			if(p.getName().equals("N")){
				if((p.getPos().get(0) + 2 <= r) && (p.getPos().get(1) + 1 <= c)){
					for(Pieces q: b1){
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(p.getPos().get(0) + 2);
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
			if(p.getName().equals("N")){
				if((p.getPos().get(0) + 1 <= r) && (p.getPos().get(1) + 2 <= c)){
					for(Pieces q: b2){
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(p.getPos().get(0) + 1);
						tmp.add(p.getPos().get(1) + 2);
						if(q.getPos().equals(tmp)){
							b2.remove(q);
							p.setPos(tmp);
							tempboard.add(b2);
						}
					}
				}
			}
		}
		ArrayList<Pieces> b3 = deepcopy(pieceboard);
		for(Pieces p: b3){
			if(p.getName().equals("N")){
				if((p.getPos().get(0) + 2 <= r) && (p.getPos().get(1) - 1 <= c)){
					for(Pieces q: b3){
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(p.getPos().get(0) + 2);
						tmp.add(p.getPos().get(1) - 1);
						if(q.getPos().equals(tmp)){
							b3.remove(q);
							p.setPos(tmp);
							tempboard.add(b3);
						}
					}
				}
			}
		}
		ArrayList<Pieces> b4 = deepcopy(pieceboard);
		for(Pieces p: b4){
			if(p.getName().equals("N")){
				if((p.getPos().get(0) + 1 <= r) && (p.getPos().get(1) - 2 <= c)){
					for(Pieces q: b4){
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(p.getPos().get(0) + 1);
						tmp.add(p.getPos().get(1) - 2);
						if(q.getPos().equals(tmp)){
							b4.remove(q);
							p.setPos(tmp);
							tempboard.add(b4);
						}
					}
				}
			}
		}
		ArrayList<Pieces> b5 = deepcopy(pieceboard);
		for(Pieces p: b5){
			if(p.getName().equals("N")){
				if((p.getPos().get(0) - 1 <= r) && (p.getPos().get(1) - 2 <= c)){
					for(Pieces q: b5){
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(p.getPos().get(0) - 1);
						tmp.add(p.getPos().get(1) - 2);
						if(q.getPos().equals(tmp)){
							b5.remove(q);
							p.setPos(tmp);
							tempboard.add(b5);
						}
					}
				}
			}
		}
		ArrayList<Pieces> b6 = deepcopy(pieceboard);
		for(Pieces p: b6){
			if(p.getName().equals("N")){
				if((p.getPos().get(0) - 2 <= r) && (p.getPos().get(1) - 1 <= c)){
					for(Pieces q: b6){
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(p.getPos().get(0) - 1);
						tmp.add(p.getPos().get(1) - 2);
						if(q.getPos().equals(tmp)){
							b6.remove(q);
							p.setPos(tmp);
							tempboard.add(b6);
						}
					}
				}
			}
		}
		ArrayList<Pieces> b7 = deepcopy(pieceboard);
		for(Pieces p: b7){
			if(p.getName().equals("N")){
				if((p.getPos().get(0) - 2 <= r) && (p.getPos().get(1) + 1 <= c)){
					for(Pieces q: b7){
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(p.getPos().get(0) - 2);
						tmp.add(p.getPos().get(1) + 1);
						if(q.getPos().equals(tmp)){
							b7.remove(q);
							p.setPos(tmp);
							tempboard.add(b7);
						}
					}
				}
			}
		}
		ArrayList<Pieces> b8 = deepcopy(pieceboard);
		for(Pieces p: b8){
			if(p.getName().equals("N")){
				if((p.getPos().get(0) - 1 <= r) && (p.getPos().get(1) + 2 <= c)){
					for(Pieces q: b8){
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(p.getPos().get(0) - 1);
						tmp.add(p.getPos().get(1) + 2);
						if(q.getPos().equals(tmp)){
							b8.remove(q);
							p.setPos(tmp);
							tempboard.add(b8);
						}
					}
				}
			}
		}
		return tempboard;
	}
}
