import java.io.*;
import java.util.*;

public class RushHour<E> extends Puzzle<E> {
	
    // classic Rush Hour parameters
    static int N;
    static int M;
    static int carsNumber;
    static final int GOAL_R = 2;
    static final int GOAL_C = 5;
    static final String[] alpha = {"A","B","C","D","E","F","G","H","I"};
    
	static String INITIAL = "";
	
	static String HORZS  = "";  // horizontal-sliding cars
	static String VERTS  = "";  // vertical-sliding cars
	static String LONGS  = "";  // length 3 cars
	static String SHORTS = "";  // length 2 cars
	static final char   GOAL_CAR = '#'      ;
	static final char   EMPTY    = '.'      ;  // empty space, movable into
	static final char   VOID     = '@'      ;  // represents everything out of bound
	private static String outputFile;
	private static String inputFile;
	private static LinkedList<Vehicle> cars = new LinkedList<Vehicle>();

	
	public RushHour(String inputFile2) {
		
      File file = new File(inputFile2);
      try {
			  
	          createInitial(new Scanner(file));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String createInitial(Scanner inputfile){
		
		LinkedList<Integer> temp = new LinkedList<Integer>();
		LinkedList<Integer> temp2 = new LinkedList<Integer>();
		
		while(inputfile.hasNext()){
			temp.add(inputfile.nextInt());
		}
		
		N = (Integer) temp.removeFirst();
		System.out.println(N);
		M = (Integer) temp.removeFirst();
		System.out.println(M);
		
		if( N < M)
			N += 1;
		else
			M += 1;
		
		carsNumber = (Integer) temp.removeFirst();
		System.out.println(carsNumber);
		
		while(!temp.isEmpty()){
			temp2.add((Integer) temp.removeFirst());
			temp2.add((Integer) temp.removeFirst());
			temp2.add((Integer) temp.removeFirst());
			temp2.add((Integer) temp.removeFirst());
			
			cars.add(new Vehicle(temp2));
			
			System.out.println(temp2.toString());
			if( temp.size() == 4){
				temp2 = new LinkedList<Integer>();
			}else{
				temp2 = new LinkedList<Integer>();
			}
		}
		
		System.out.println(cars.toString());
		
		String[][] road = new String[6][M];
	
	    int counter = 0;
		for(Vehicle i: cars){
			System.out.println();
			if(i != cars.getLast()){
				if(i.getThePosition() == 'h'){
					i.setXY();
					for( int x=0; x<i.length(); x++){
						System.out.println((i.getX()+x) + " " + i.getY());
						road[(i.getX()+x)][i.getY()] = alpha[counter];
					}
					VERTS += alpha[counter];
				}else{
					i.setXY();
					for( int x=0; x<i.length(); x++){
						System.out.println(i.getX() + " " + (i.getY()+x));
						road[i.getX()][(i.getY()+x)] = alpha[counter];
					}
					HORZS += alpha[counter];
				}
				
				if(i.length() > 2)
					LONGS += alpha[counter];
				else
					SHORTS += alpha[counter];
				
			}else{
			
				i.setXY();
				for( int x=0; x<i.length(); x++){
					System.out.println(i.getX() + " " + (i.getY()+x));
					road[i.getX()][(i.getY()+x)] = "#";
				}
				
				if(i.length() > 2)
					HORZS += LONGS += "#";
				else
					HORZS += SHORTS += "#";
			}
			counter++;
		}
	
		
			for( int x = 0; x < N; x++){
				for(int j = 0; j< M; j++){
					if( road[x][j] == null)
						road[x][j] = ".";
					
					INITIAL += road[x][j];
					System.out.print(road[x][j]);
				}
				//INITIAL += "\n";
				System.out.println();
			}
			System.out.println();
			System.out.println(INITIAL.toString());

			System.out.println(HORZS.toString());
			System.out.println(VERTS.toString());
			System.out.println(LONGS.toString());
			System.out.println(SHORTS.toString());
			
		return null;
	}
	
	   // breaks a string into lines of length N using regex
    static String prettify(String state) {
        String EVERY_NTH = "(?<=\\G.{N})".replace("N", String.valueOf(N));
        return state.replaceAll(EVERY_NTH, "\n");
    }

    // conventional row major 2D-1D index transformation
    static int rc2i(int r, int c) {
        return r * N + c;
    }

    // checks if an entity is of a given type
    static boolean isType(char entity, String type) {
        return type.indexOf(entity) != -1;
    }

    // finds the length of a car
    static int length(char car) {
        return
            isType(car, LONGS) ? 3 :
            isType(car, SHORTS) ? 2 :
            0/0; // a nasty shortcut for throwing IllegalArgumentException
    }

    // in given state, returns the entity at a given coordinate, possibly out of bound
    static char at(String state, int r, int c) {
        return (inBound(r, M) && inBound(c, N)) ? state.charAt(rc2i(r, c)) : VOID;
    }
    static boolean inBound(int v, int max) {
        return (v >= 0) && (v < max);
    }

    // checks if a given state is a goal state
    static boolean isGoal(String state) {
        return at(state, GOAL_R, GOAL_C) == GOAL_CAR;
    }

    // in a given state, starting from given coordinate, toward the given direction,
    // counts how many empty spaces there are (origin inclusive)
    static int countSpaces(String state, int r, int c, int dr, int dc) {
        int k = 0;
        while (at(state, r + k * dr, c + k * dc) == EMPTY) {
            k++;
        }
        return k;
    }

    // the predecessor map, maps currentState => previousState
    static Map<String,String> pred = new HashMap<String,String>();
    // the breadth first search queue
    static Queue<String> queue = new LinkedList<String>();
    // the breadth first search proposal method: if we haven't reached it yet,
    // (i.e. it has no predecessor), we map the given state and add to queue
    static void propose(String next, String prev) {
        if (!pred.containsKey(next)) {
            pred.put(next, prev);
            queue.add(next);
        }
    }

    // the predecessor tracing method, implemented using recursion for brevity;
    // guaranteed no infinite recursion, but may throw StackOverflowError on
    // really long shortest-path trace (which is infeasible in standard Rush Hour)
    static int trace(String current) {
        String prev = pred.get(current);
        int step = (prev == null) ? 0 : trace(prev) + 1;
        System.out.println(step);
        System.out.println(prettify(current));
        return step;
    }

    // in a given state, from a given origin coordinate, attempts to find a car of a given type
    // at a given distance in a given direction; if found, slide it in the opposite direction
    // one spot at a time, exactly n times, proposing those states to the breadth first search
    //
    // e.g.
    //    direction = -->
    //    __n__
    //   /     \
    //   ..o....c
    //      \___/
    //      distance
    //
    static void slide(String current, int r, int c, String type, int distance, int dr, int dc, int n) {
        r += distance * dr;
        c += distance * dc;
        char car = at(current, r, c);
        if (!isType(car, type)) return;
        final int L = length(car);
        StringBuilder sb = new StringBuilder(current);
        for (int i = 0; i < n; i++) {
            r -= dr;
            c -= dc;
            sb.setCharAt(rc2i(r, c), car);
            if((rc2i(r + L * dr, c + L * dc) > 0))
            sb.setCharAt(rc2i(r + L * dr, c + L * dc), EMPTY);
            propose(sb.toString(), current);
            current = sb.toString(); // comment to combo as one step
        }
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
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (at(current, r, c) != EMPTY) continue;
                int nU = countSpaces(current, r, c, -1, 0);
                int nD = countSpaces(current, r, c, +1, 0);
                int nL = countSpaces(current, r, c, 0, -1);
                int nR = countSpaces(current, r, c, 0, +1);
                slide(current, r, c, VERTS, nU, -1, 0, nU + nD - 1);
                slide(current, r, c, VERTS, nD, +1, 0, nU + nD - 1);
                slide(current, r, c, HORZS, nL, 0, -1, nL + nR - 1);
                slide(current, r, c, HORZS, nR, 0, +1, nL + nR - 1);
            }
        }
    }
	
	@Override
	public boolean getGoal(E state) {
		return Vehicle.at((String)state, GOAL_R, GOAL_C) == GOAL_CAR;
	}

	@Override
	public ArrayList<E> getNeighbors(E obj) {
		
		
        propose(INITIAL, null);
        boolean solved = false;
        while (!queue.isEmpty()) {
            String current = queue.remove();
            if (isGoal(current) && !solved) {
                solved = true;
                trace(current);
                //break; // comment to continue exploring entire space
            }
            explore(current);
        }   
		return null;
	}
	
	public static void main(String[] args) throws IOException{
		inputFile = args[0];
//	
//        File file = new File("input");
//        try {
//			  
//	          createInitial(new Scanner(file));
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
        RushHour test = new RushHour(inputFile);
        new Solver(test);
        test.getNeighbors(null);
        
        System.out.println(pred.size() + " explored");
        System.out.println(INITIAL.toString());

    }

}
