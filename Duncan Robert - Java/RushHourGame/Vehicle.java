import java.util.LinkedList;

/**
 * Represents a single (immutable) vehicle on the Rush Hour board.
 * 
 * Allows comparing two vehicles according to their position. Note that the
 * equals method also takes into account the type and name of the vehicle, so
 * theoretically compareTo might return 0 when equals return false.
 * 
 * @author DL, GT
 * 
 */
public class Vehicle implements Comparable<Vehicle> {

	private final VehicleType type;
	public  final LinkedList<Integer> position;
	private final char name;
	private int loc;
	private final int dx;
	private final int dy;
	private Integer x;
	private Integer y;
    
    
    
	/**
	 * Constructor.
	 * @param type        Vehicle's type.
	 * @param poisition   Vehicle's position on the RushHour board (zero-based).
	 * @param name        Vehicle's name.
	 */
	public Vehicle(VehicleType type, int position, char name) {
		this.type = type;
		this.position = new LinkedList<Integer>();
		setLoc(0);
		dx = 0;
		dy = 0;
//		this.position = position;
		this.name = name;
	}

	
	
	public Vehicle(LinkedList<Integer> temp2) {
		this.type = null;
		this.position = new LinkedList<Integer>();
		position.addAll(temp2);
		dx =  Math.abs((Integer)position.get(0) - (Integer)position.get(2));
		dy =  Math.abs((Integer)position.get(1) - (Integer)position.get(3));
		this.name = 0;
		setLoc(0);
	}



	/** @return the vehicle's type. */
	public VehicleType getType() {
		return type;
	}

	
	
	/** @return the vehicle's position on the RushHour board (zero-based). */
	public LinkedList<Integer> getPosition() {
		return position;
	}

	
	public char getThePosition(){
		char temp;
		if( dx > dy)
			temp = 'h';
		else
			temp = 'v';
		
		return temp;
	}
	
	/** @return the vehicle's name. */
	public char getName() {
		return name;
	}

	public void setXY(){
		if( (position.get(0) - position.get(1)) < (position.get(0) - position.get(1))){
			setX(position.get(0));
			setY(position.get(1));
		}else{
			setX(position.get(0));
			setY(position.get(1));
		}
	}
	/** @return the vehicle's name. */
	public int getStart() {
		
		if(this.getThePosition() == 'h'){
			
			setLoc((Integer)position.get(0));
			if( position.get(1) < position.get(3))
				return position.get(1);
			else
				return position.get(3);
			
		}else{
			
			setLoc((Integer)position.get(1));
			if( position.get(0) < position.get(2))
				return position.get(0);
			else
				return position.get(2);
		}
	}
	
	
	/** @return the vehicle's name. */
	public int getEnd() {
		
		setLoc((Integer)position.get(0));
		if(this.getThePosition() == 'h'){
			
			if( position.get(1) > position.get(3))
				return position.get(1);
			else
				return position.get(3);
			
		}else{
			setLoc((Integer)position.get(1));
			if( position.get(0) > position.get(2))
				return position.get(0);
			else
				return position.get(2);
		}
	}
	
    // checks if an entity is of a given type
    static boolean isType(char entity, String type) {
        return type.indexOf(entity) != -1;
    }

    
    // finds the length of a car
    public int length() {
	
    	if(this.getThePosition() == 'h')
    		return(Math.abs(position.get(0) - position.get(2))+1);
    	else
    		return(Math.abs(position.get(1) - position.get(3))+1);
    }

    
    
    // in a given state, starting from given coordinate, toward the given direction,
    // counts how many empty spaces there are (origin inclusive)
    static int countSpaces(String state, int r, int c, int dr, int dc) {
        int k = 0;
        while (at(state, r + k * dr, c + k * dc) == RushHour.EMPTY) {
            k++;
        }
        return k;
    }
    
    
    
    // conventional row major 2D-1D index transformation
    static int rc2i(int r, int c) {
        return r * RushHour.N + c;
    }

    
    
    // in given state, returns the entity at a given coordinate, possibly out of bound
    static char at(String state, int r, int c) {
        return (inBound(r, RushHour.M) && inBound(c, RushHour.N)) ? state.charAt(rc2i(r, c)) : RushHour.VOID;
    }
    
    
    
 // explores a given state; searches for next level states in the breadth first search
    //
    // Let (r,c) be the intersection point of this cross:
    //
    //     @       nU = 3     '@' is not a car, 'B' and 'X' are of the wrong type;
    //     .       nD = 1     only '2' can slide to the right up to 5 spaces
    //   2.....B   nL = 2
    //     X       nR = 4
    //
    // The n? counts how many spaces are there in a given direction, origin inclusive.
    // Cars matching the type will then slide on these "alleys".
    //
    static void explore(String current) {
        for (int r = 0; r < RushHour.M; r++) {
            for (int c = 0; c < RushHour.N; c++) {
                if (at(current, r, c) != RushHour.EMPTY) continue;
                int nU = countSpaces(current, r, c, -1, 0);
                int nD = countSpaces(current, r, c, +1, 0);
                int nL = countSpaces(current, r, c, 0, -1);
                int nR = countSpaces(current, r, c, 0, +1);
                
                RushHour.slide(current, r, c, RushHour.VERTS, nU, -1, 0, nU + nD - 1);
                RushHour.slide(current, r, c, RushHour.VERTS, nD, +1, 0, nU + nD - 1);
                RushHour.slide(current, r, c, RushHour.HORZS, nL, 0, -1, nL + nR - 1);
                RushHour.slide(current, r, c, RushHour.HORZS, nR, 0, +1, nL + nR - 1);
            }
        }
    }
    
    
    
    static boolean inBound(int v, int max) {
        return (v >= 0) && (v < max);
    }
    
    
    
	/** @see Comparable#compareTo */
	public int compareTo(Vehicle other) {
		return (getLoc() - other.getLoc());
	}

	
	
	/** @see Object#equals() */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		} else if (!(other instanceof Vehicle)) {
			return false;
		}
		Vehicle otherVehicle = (Vehicle)other;
		return ((type == otherVehicle.type) &&
				(position == otherVehicle.position) &&
				(name == otherVehicle.name));
	}

	
	
    // checks if a given state is a goal state
    static boolean getGoal(String state) {
        return at(state, RushHour.GOAL_R, RushHour.GOAL_C) == RushHour.GOAL_CAR;
    }
	
	
    public String toString(){
    	return this.position.toString();
    }
//	/** @see Object#hashCode() */
//	public int hashCode() {
//		return type.ordinal() * 10000 + loc * 100 + name;
//	}



	public int getLoc() {
		return loc;
	}



	public void setLoc(int loc) {
		this.loc = loc;
	}



	public Integer getY() {
		return y;
	}



	public void setY(Integer y) {
		this.y = y;
	}



	public Integer getX() {
		return x;
	}



	public void setX(Integer x) {
		this.x = x;
	}
}